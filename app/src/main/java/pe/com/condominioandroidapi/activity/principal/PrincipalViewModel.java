package pe.com.condominioandroidapi.activity.principal;

import android.app.Application;

import android.os.AsyncTask;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;


import pe.com.condominioandroidapi.datamodel.PostDatamodel;
import pe.com.condominioandroidapi.datamodel.PrincipalDataModel;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.ProyectoResponse;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class PrincipalViewModel extends BaseViewModel {
    private PostDatamodel datamodel;

    private MutableLiveData<List<InmuebleResponse>> proListMutableLiveData;
    private MutableLiveData<Integer> visibilityValueMutableLiveData;


    public PrincipalViewModel(@NonNull Application application) {
        super(application);
        datamodel = new PostDatamodel();
        visibilityValueMutableLiveData = new MutableLiveData<>();
        proListMutableLiveData = new MutableLiveData<>();

    /*    divisiones = new MutableLiveData<>();
        empresas = new MutableLiveData<>();
        dashboardResult = new MutableLiveData<>();*/
        setupObservers();
    }

    @Override
    public void setupObservers() {

        datamodel.getPoListMutableLiveData().observeForever(new Observer<List<InmuebleResponse>>() {
            @Override
            public void onChanged(List<InmuebleResponse> pro) {
                onRetrieveDataFinish();

                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                visibilityValueMutableLiveData.setValue(View.GONE);
                proListMutableLiveData.setValue(pro);
            }
        });
    }

    MutableLiveData<Integer> getVisibilityValueMutableLiveData() {
        return visibilityValueMutableLiveData;
    }
    MutableLiveData<List<InmuebleResponse>> getProListMutableLiveData() {
        return proListMutableLiveData;
    }

    void requestDahsboardList(String email) {
        onRetrieveData();
        new ValidateRequestPrincipalTask(this, email).execute();
    }

    private static class ValidateRequestPrincipalTask extends AsyncTask<Void, Void, Boolean> {
        PrincipalViewModel viewModel;
        String email;
        ValidateRequestPrincipalTask(PrincipalViewModel viewModel, String email) {
            this.viewModel = viewModel;
            this.email = email;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.datamodel.requestPost(email);

            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }

   /* private static class ValidateRequestSolicitudesTask extends AsyncTask<Void, Void, Boolean> {
        DashboardViewModel viewModel;
        Integer idUser;
        Integer estadoSolicitud;
        String codigoEmpresa;
        String codigoDivision;

        ValidateRequestSolicitudesTask(DashboardViewModel viewModel, Integer estadoSolicitud,
                                       Integer idUser, String codigoEmpresa, String codigoDivision) {
            this.viewModel = viewModel;
            this.idUser = idUser;
            this.estadoSolicitud = estadoSolicitud;
            this.codigoEmpresa = codigoEmpresa;
            this.codigoDivision = codigoDivision;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.dashboardDataModel
                        .requestSolicitudesPendientesAprobadas(estadoSolicitud, idUser, codigoEmpresa, codigoDivision);
            else
                viewModel.messageResult.setValue("Por favor, intenténtelo nuevamente...");
        }
    }

    private static class ValidateRequestDivisionesTask extends AsyncTask<Void, Void, Boolean> {
        DashboardViewModel viewModel;
        Integer idUser;
        ValidateRequestDivisionesTask(DashboardViewModel viewModel, Integer idUser) {
            this.viewModel = viewModel;
            this.idUser = idUser;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.dashboardDataModel.requestDivisiones(idUser);
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }

    private static class ValidateRequestEmpresasTask extends AsyncTask<Void, Void, Boolean> {
        DashboardViewModel viewModel;
        Integer idUser;
        String codigoDivision;
        ValidateRequestEmpresasTask(DashboardViewModel viewModel, Integer idUser, String codigoDivision) {
            this.viewModel = viewModel;
            this.idUser = idUser;
            this.codigoDivision = codigoDivision;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean)
                viewModel.dashboardDataModel.requestEmpresas(idUser, codigoDivision);
            else {

                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }
*/
}
