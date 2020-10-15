package pe.com.condominioandroidapi.activity.setting;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.datamodel.InmuebleDatamodel;
import pe.com.condominioandroidapi.datamodel.SettingDataModel;
import pe.com.condominioandroidapi.datamodel.UsuarioDataModel;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.UsuarioResponse;
import pe.com.condominioandroidapi.entities.switchResponse;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class SettingViewModel extends BaseViewModel {

    private UsuarioDataModel datamodel;

    private MutableLiveData<UsuarioResponse> usuarioMutableLiveData;
    private MutableLiveData<List<switchResponse>> switchMutableLiveData;

    private MutableLiveData<Integer> visibilityValueMutableLiveData;

    public SettingViewModel(@NonNull Application application) {
        super(application);
        datamodel = new UsuarioDataModel();
        visibilityValueMutableLiveData = new MutableLiveData<>();
        usuarioMutableLiveData = new MutableLiveData<>();
        switchMutableLiveData = new MutableLiveData<>();

        setupObservers();
    }
    @Override
    public void setupObservers() {
        datamodel.getUsuarioMutableLiveData().observeForever(new Observer<UsuarioResponse>() {
            @Override
            public void onChanged(UsuarioResponse pro) {
                onRetrieveDataFinish();
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                visibilityValueMutableLiveData.setValue(View.GONE);
                usuarioMutableLiveData.setValue(pro);
            }
        });

        datamodel.getSwitchMutableLiveData().observeForever(new Observer<List<switchResponse>>() {
            @Override
            public void onChanged(List<switchResponse> pro) {
                onRetrieveDataFinish();
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                switchMutableLiveData.setValue(pro);
            }
        });
    }

    MutableLiveData<Integer> getVisibilityValueMutableLiveData() {
        return visibilityValueMutableLiveData;
    }
    MutableLiveData<UsuarioResponse> getUsuarioMutableLiveData() {
        return usuarioMutableLiveData;
    }
    MutableLiveData<List<switchResponse>> getSwitchMutableLiveData() {
        return switchMutableLiveData;
    }


    void requestAvatar(String idUser) {
        onRetrieveData();
        new SettingViewModel.ValidateRequestPrincipalTask(this, idUser).execute();
    }

    private static class ValidateRequestPrincipalTask extends AsyncTask<Void, Void, Boolean> {
        SettingViewModel viewModel;
        String idUser;

        ValidateRequestPrincipalTask(SettingViewModel viewModel, String idUser) {
            this.viewModel = viewModel;
            this.idUser = idUser;
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
                viewModel.datamodel.requestAvatar(idUser);

            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }

    void updateAvatar(Integer idUser, String telefono) {
        onRetrieveData();
        new SettingViewModel.ValidateUpdateTask(this, idUser,  telefono).execute();
    }



    private static class ValidateUpdateTask extends AsyncTask<Void, Void, Boolean> {
        SettingViewModel viewModel;
        Integer idUser;
        String telefono;

        ValidateUpdateTask(SettingViewModel viewModel, Integer idUser, String telefono) {
            this.viewModel = viewModel;
            this.idUser = idUser;
            this.telefono = telefono;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.datamodel.updateAvatar(idUser,telefono);

            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }

    void requestSwitch(Integer idUser) {
        onRetrieveData();
        new SettingViewModel.ValidateSwitchPrincipalTask(this, idUser).execute();
    }

    private static class ValidateSwitchPrincipalTask extends AsyncTask<Void, Void, Boolean> {
        SettingViewModel viewModel;
        Integer idUser;

        ValidateSwitchPrincipalTask(SettingViewModel viewModel, Integer idUser) {
            this.viewModel = viewModel;
            this.idUser = idUser;
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
                viewModel.datamodel.requestSwitch(idUser);

            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }

    void updateSwitchUpdate(Integer idUser, int tipo , int estado) {
        onRetrieveData();
        new SettingViewModel.updateSwitchUpdateTask(this, idUser,  tipo , estado).execute();
    }
    private static class updateSwitchUpdateTask extends AsyncTask<Void, Void, Boolean> {
        SettingViewModel viewModel;
        Integer idUser;
        Integer tipo;
        Integer estado;

        updateSwitchUpdateTask(SettingViewModel viewModel, Integer idUser
                , Integer tipo, Integer estado) {
            this.viewModel = viewModel;
            this.idUser = idUser;
            this.tipo= tipo;
            this.estado=estado;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.datamodel.requestUpdateSwitch(idUser, tipo,estado);

            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }

}
