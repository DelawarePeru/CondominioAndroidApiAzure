package pe.com.condominioandroidapi.activity.proyecto;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.security.ProtectionDomain;
import java.util.List;

import pe.com.condominioandroidapi.datamodel.ProyectoDataModel;
import pe.com.condominioandroidapi.entities.DepartamentoResponse;
import pe.com.condominioandroidapi.entities.ProvinciaResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class ProyectoViewModel extends BaseViewModel {
    private ProyectoDataModel datamodel;
    private MutableLiveData<List<VentaResponse>> ventaMutableLiveData;
    private MutableLiveData<List<DepartamentoResponse>> departamentoMutableLiveData;
    private MutableLiveData<List<ProvinciaResponse>> provinciaMutableLiveData;
    private MutableLiveData<List<VentaResponse>> vincularMutableLiveData;


    public ProyectoViewModel(@NonNull Application application) {
        super(application);
        datamodel = new ProyectoDataModel();
        ventaMutableLiveData = new MutableLiveData<>();
        departamentoMutableLiveData = new MutableLiveData<>();
        provinciaMutableLiveData = new MutableLiveData<>();
        vincularMutableLiveData =  new MutableLiveData<>();
        setupObservers();

    }

    @Override
    public void setupObservers() {

        datamodel.getProyectListMutableLiveData().observeForever(new Observer <List<VentaResponse>>() {
            @Override
            public void onChanged(List<VentaResponse> pro) {
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                onRetrieveDataFinish();
                ventaMutableLiveData.setValue(pro);
            }
        });
        datamodel.getDepartamentoListMutableLiveData().observeForever(new Observer <List<DepartamentoResponse>>() {
            @Override
            public void onChanged(List<DepartamentoResponse> pro) {
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                onRetrieveDataFinish();
                departamentoMutableLiveData.setValue(pro);
            }
        });
        datamodel.getProvinciaListMutableLiveData().observeForever(new Observer <List<ProvinciaResponse>>() {
            @Override
            public void onChanged(List<ProvinciaResponse> pro) {
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                onRetrieveDataFinish();
                provinciaMutableLiveData.setValue(pro);
            }
        });
        datamodel.getVincularMutableLiveData().observeForever(new Observer <List<VentaResponse>>() {
            @Override
            public void onChanged(List<VentaResponse> pro) {
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                onRetrieveDataFinish();
                vincularMutableLiveData.setValue(pro);
            }
        });
        datamodel.getErrorCodeLiveData().observeForever(new Observer<Integer>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(@Nullable Integer errorCode) {
                messageResult.setValue(getMessageByErrorCode(errorCode));
            }
        });
        datamodel.getErrorMessageLiveData().observeForever(new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(@Nullable String s) {
                messageResult.setValue(s);
            }
        });
    }

    MutableLiveData<List<VentaResponse>> getProyectListMutableLiveData() {
        return ventaMutableLiveData;
    }
    public MutableLiveData<List<DepartamentoResponse>> getDepartamentoListMutableLiveData() {
        return departamentoMutableLiveData;
    }
    public MutableLiveData<List<ProvinciaResponse>> getProvinciaListMutableLiveData() {
        return provinciaMutableLiveData;
    }
    public MutableLiveData<List<VentaResponse>> getVincularMutableLiveData() {
        return vincularMutableLiveData;
    }

    void requestProyectList(String departamento,String provincia)
    {
        onRetrieveData();
        new ProyectoViewModel.ValidateRequestPrincipalTask(this,departamento,provincia).execute();
    }

    public void requestVincular(String email, String codigo)
    {
        onRetrieveData();
        new ProyectoViewModel.ValidateRequesCodigo(this,email,codigo).execute();
    }

    public void requestDepartamentoList()
    {
        onRetrieveData();
        new ProyectoViewModel.ValidateRequestDepartamentoTask(this).execute();
    }
    public void requestProvinciaList(Integer idDepartamento)
    {
        onRetrieveData();
        new ProyectoViewModel.ValidateRequestProvinciaTask(this, idDepartamento).execute();
    }

    private static class ValidateRequestPrincipalTask extends AsyncTask<Void,Void,Boolean>
    {
        ProyectoViewModel viewModel;
        String departamento;
        String provincia;

        ValidateRequestPrincipalTask(ProyectoViewModel viewModel, String departamento, String provincia)
        {
            this.viewModel = viewModel;
            this.departamento = departamento;
            this.provincia= provincia;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestProyectList(departamento,provincia);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
        }
    private static class ValidateRequesCodigo extends AsyncTask<Void,Void,Boolean>
    {
        ProyectoViewModel viewModel;
        String codigo;
        String email;

        ValidateRequesCodigo(ProyectoViewModel viewModel, String email, String codigo)
        {
            this.viewModel = viewModel;
            this.codigo = codigo;
            this.email= email;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestVincular(codigo,email);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }

    private static class ValidateRequestDepartamentoTask extends AsyncTask<Void,Void,Boolean>
    {
        ProyectoViewModel viewModel;

        ValidateRequestDepartamentoTask(ProyectoViewModel viewModel)
        {
            this.viewModel = viewModel;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestDepartamento();
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }

    private static class ValidateRequestProvinciaTask extends AsyncTask<Void,Void,Boolean>
    {
        ProyectoViewModel viewModel;
        Integer idDepartamento;

        ValidateRequestProvinciaTask(ProyectoViewModel viewModel, Integer idDepartamento)
        {
            this.viewModel = viewModel;
            this.idDepartamento = idDepartamento;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestProvincia(idDepartamento);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }
}
