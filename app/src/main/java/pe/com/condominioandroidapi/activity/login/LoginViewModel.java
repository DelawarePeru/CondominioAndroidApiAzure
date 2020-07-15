package pe.com.condominioandroidapi.activity.login;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import pe.com.condominioandroidapi.datamodel.UserDataModel;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class LoginViewModel  extends BaseViewModel {

    private UserDataModel loginDataModel;
    private MutableLiveData<String> userResult;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginDataModel = new UserDataModel();
        userResult = new MutableLiveData<>();
        setupObservers();

    }
    @Override
    public void setupObservers() {
        loginDataModel.getUserResult()
                .observeForever(new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String loginResponse) {
                        onRetrieveDataFinish();
                        userResult.setValue(loginResponse);
                        Constant.setupAuthentication(Constant.get(Constant.KEY_ACCESS_TOKEN));
                    }
                });
        loginDataModel.getErrorCodeLiveData().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer errorCode) {
                messageResult.setValue(getMessageByErrorCode(errorCode));
                Constant.setupAuthentication(Constant.get(""));
            }
        });
        loginDataModel.getErrorMessageLiveData().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                messageResult.setValue(s);
                Constant.setupAuthentication(Constant.get(""));
            }
        });
    }

    LiveData<String> getLoginSuccess() {
        return userResult;
    }

    private boolean areRequiredParameters(String user, String pass, Boolean isLoggedIn) {
        onRetrieveData();
        if (!isLoggedIn) {
            if (user.isEmpty()) {
                messageResult.setValue("Ingresar usuario");
                return false;
            }

            if (pass.isEmpty()) {
                messageResult.setValue("Ingresar contraseña");
                return false;
            }
        }
        return true;
    }

    void userLogin(String user, String pass, Boolean isLoggedIn) {
        if (!areRequiredParameters(user, pass, isLoggedIn)) {
            onRetrieveDataFinish();
            return;
        }
        onRetrieveData();
        new ValidateAndLoginTask(this, user, pass, isLoggedIn).execute();
    }

    private static class ValidateAndLoginTask extends AsyncTask<Void, Void, Boolean> {
        LoginViewModel viewModel;
        String username, password;
        Boolean isLoggedIn;

        ValidateAndLoginTask(LoginViewModel viewModel, String username,
                             String password, Boolean isLoggedIn) {
            this.viewModel = viewModel;
            this.username = username;
            this.password = password;
            this.isLoggedIn = isLoggedIn;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.loginDataModel.loginUser(username, password, isLoggedIn);
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
            }
        }
    }

}
