package pe.com.condominioandroidapi.activity.Inmueble;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import pe.com.condominioandroidapi.activity.principal.PrincipalViewModel;
import pe.com.condominioandroidapi.datamodel.InmuebleDatamodel;
import pe.com.condominioandroidapi.datamodel.PostDatamodel;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class ListaInmueblesViewModel extends BaseViewModel {

    private InmuebleDatamodel datamodel;

    private MutableLiveData<List<InmuebleListResponse>> inmListMutableLiveData;
    private MutableLiveData<Integer> visibilityValueMutableLiveData;

    public ListaInmueblesViewModel(@NonNull Application application) {
        super(application);
        datamodel = new InmuebleDatamodel();
        visibilityValueMutableLiveData = new MutableLiveData<>();
        inmListMutableLiveData = new MutableLiveData<>();
        setupObservers();
    }
    @Override
    public void setupObservers() {
        datamodel.getInmuListMutableLiveData().observeForever(new Observer<List<InmuebleListResponse>>() {
        @Override
        public void onChanged(List<InmuebleListResponse> pro) {
            onRetrieveDataFinish();

            // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
            visibilityValueMutableLiveData.setValue(View.GONE);
            inmListMutableLiveData.setValue(pro);
        }
    });
    }

    MutableLiveData<Integer> getVisibilityValueMutableLiveData() {
        return visibilityValueMutableLiveData;
    }
    MutableLiveData<List<InmuebleListResponse>> getInmuListMutableLiveData() {
        return inmListMutableLiveData;
    }

    void requestListInmu(Integer idUser , Integer idProyecto) {
        onRetrieveData();
        new ValidateRequestPrincipalTask(this, idUser,  idProyecto).execute();
    }

    private static class ValidateRequestPrincipalTask extends AsyncTask<Void, Void, Boolean> {
        ListaInmueblesViewModel viewModel;
        Integer idUser;
        Integer idProyecto;

        ValidateRequestPrincipalTask(ListaInmueblesViewModel viewModel, Integer idUser,  Integer idProyecto) {
            this.viewModel = viewModel;
            this.idUser = idUser;
            this.idProyecto= idProyecto;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                viewModel.datamodel.requestListInmu(idUser, idProyecto);

            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }
}
