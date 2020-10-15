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

import pe.com.condominioandroidapi.activity.setting.SettingViewModel;
import pe.com.condominioandroidapi.datamodel.UserDataModel;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class LoginViewModel  extends BaseViewModel {

  private UserDataModel loginDataModel;
  private MutableLiveData<String> userResult;
  private MutableLiveData<String> userResultRegister;

  public LoginViewModel(@NonNull Application application) {
    super(application);
    loginDataModel = new UserDataModel();
    userResult = new MutableLiveData<>();
    userResultRegister= new MutableLiveData<>();
    setupObservers();

  }
  @Override
  public void setupObservers() {
    loginDataModel.getUserResult()
            .observeForever(new Observer<String>() {
              @RequiresApi(api = Build.VERSION_CODES.O)
              @Override
              public void onChanged(@Nullable String loginResponse) {
                onRetrieveDataFinish();
                userResult.setValue(loginResponse);
                Constant.setupAuthentication(Constant.get(Constant.KEY_ACCESS_TOKEN));
              }
            });
    loginDataModel.getUserListResult()
            .observeForever(new Observer<String>() {
              @RequiresApi(api = Build.VERSION_CODES.O)
              @Override
              public void onChanged(@Nullable String loginResponse) {
                onRetrieveDataFinish();
                userResultRegister.setValue(loginResponse);
              }
            });
    loginDataModel.getErrorCodeLiveData().observeForever(new Observer<Integer>() {
      @RequiresApi(api = Build.VERSION_CODES.O)
      @Override
      public void onChanged(@Nullable Integer errorCode) {
        messageResult.setValue(getMessageByErrorCode(errorCode));
        Constant.setupAuthentication(Constant.get(""));
      }
    });
    loginDataModel.getErrorMessageLiveData().observeForever(new Observer<String>() {
      @RequiresApi(api = Build.VERSION_CODES.O)
      @Override
      public void onChanged(@Nullable String s) {
        messageResult.setValue(s);
        Constant.setupAuthentication(Constant.get(""));
      }
    });
  }

  LiveData<String> getUsersSuccess() {
    return userResultRegister;
  }

  LiveData<String> getLoginSuccess() {
    return userResult;
  }

  private boolean areRequired(String email) {
    onRetrieveData();
      if (email.isEmpty()) {
        messageResult.setValue("Ingresar correo");
        return false;
      }


    return true;
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
  private boolean areRequiredParametersRegistro(String user, String pass,
                                                String nombre,String apellido,
                                                String telefono,String passRepetir,
                                                Boolean isLoggedIn) {
    onRetrieveData();
    if (!isLoggedIn) {
      if (nombre.isEmpty()) {
        messageResult.setValue("Ingresar nombres");
        return false;
      }
      if (apellido.isEmpty()) {
        messageResult.setValue("Ingresar apellido");
        return false;
      }
      if (user.isEmpty()) {
        messageResult.setValue("Ingresar usuario");
        return false;
      }

      if (pass.isEmpty()) {
        messageResult.setValue("Ingresar contraseña");
        return false;
      }

      if (telefono.isEmpty()) {
        messageResult.setValue("Ingresar teléfono");
        return false;
      }
      if (passRepetir.isEmpty()) {
        messageResult.setValue("Confirma tu contraseña");
        return false;
      }
    }
    return true;
  }
  void recuperarContrasena(String email) {
    if (!areRequired(email)) {
      onRetrieveDataFinish();
      return;
    }
    onRetrieveData();
    new ValidateContrasena(this, email).execute();
  }
  void userLogin(String user, String pass, Boolean isLoggedIn) {
    if (!areRequiredParameters(user, pass, isLoggedIn)) {
      onRetrieveDataFinish();
      return;
    }
    onRetrieveData();
    new ValidateAndLoginTask(this, user, pass, isLoggedIn).execute();
  }
  void userRegistro(String user, String pass,String nombre,
                    String apellido,String telefono,String passRepetir,
                    Boolean isLoggedIn) {
    if (!areRequiredParametersRegistro(user, pass,nombre,apellido,
            telefono,passRepetir,isLoggedIn)) {
      onRetrieveDataFinish();
      return;
    }

    onRetrieveData();
    new ValidarAndRegistrarTask(this, user, pass, nombre,
            apellido, telefono,passRepetir , isLoggedIn).execute();
  }
  void userRegistroPersona(String id ,String nombre,
                    String apellido,String telefono,
                    Boolean isLoggedIn) {
    onRetrieveData();
    new ValidarAndRegistrarPersonaTask(this,id, nombre,
            apellido, telefono, isLoggedIn).execute();
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

  private static class ValidarAndRegistrarTask extends AsyncTask<Void, Void, Boolean> {
    LoginViewModel viewModel;
    String username, password, nombre, apellido,telefono, passRepetir;
    Boolean isLoggedIn;

    ValidarAndRegistrarTask(LoginViewModel viewModel, String username,
                         String password,String nombre,String apellido,
                            String telefono,String passRepetir ,Boolean isLoggedIn) {
      this.viewModel = viewModel;
      this.username = username;
      this.password = password;
      this.nombre = nombre;
      this.apellido = apellido;
      this.telefono = telefono;
      this.passRepetir = passRepetir;
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
      if (aBoolean) {
        viewModel.loginDataModel.registrarUsuario(username, password, passRepetir,nombre,apellido,telefono, isLoggedIn);

      }
       else {
        viewModel.messageResult.setValue("No tienes acceso a internet");
      }
    }
  }
  private static class ValidarAndRegistrarPersonaTask extends AsyncTask<Void, Void, Boolean> {
    LoginViewModel viewModel;
    String username,  nombre, apellido,telefono, passRepetir;
    String id;
    Boolean isLoggedIn;

    ValidarAndRegistrarPersonaTask(LoginViewModel viewModel,String id, String nombre,
                                   String apellido,
                            String telefono,Boolean isLoggedIn) {
      this.viewModel = viewModel;
      this.id = id;
      this.nombre = nombre;
      this.apellido = apellido;
      this.telefono = telefono;

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
      if (aBoolean) {

          viewModel.loginDataModel.registrarPersona(id,nombre,apellido,telefono);


      }
      else {
        viewModel.messageResult.setValue("No tienes acceso a internet");
      }
    }
  }

  private static class ValidateContrasena extends AsyncTask<Void, Void, Boolean> {
    LoginViewModel viewModel;
    String username;

    ValidateContrasena(LoginViewModel viewModel, String username) {
      this.viewModel = viewModel;
      this.username = username;

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
      return Helper.isOnline();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPostExecute(Boolean aBoolean) {
      super.onPostExecute(aBoolean);
      if (aBoolean) {
        viewModel.loginDataModel.recuperarContrasena(username);

      }
      else {
        viewModel.messageResult.setValue("No tienes acceso a internet");
      }
    }
  }

}
