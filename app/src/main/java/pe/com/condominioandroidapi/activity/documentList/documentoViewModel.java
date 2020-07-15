package pe.com.condominioandroidapi.activity.documentList;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.datamodel.DetalleDocumento;
import pe.com.condominioandroidapi.datamodel.DocumentoDataModel;
import pe.com.condominioandroidapi.datamodel.InmuebleDatamodel;
import pe.com.condominioandroidapi.entities.DocumentoResponse;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class documentoViewModel extends BaseViewModel {

    private DocumentoDataModel datamodel;

    private MutableLiveData<List<DocumentoResponse<DetalleDocumento>>> documentoMutableLiveData;

    public documentoViewModel(@NonNull Application application) {
        super(application);
        datamodel = new DocumentoDataModel();
        documentoMutableLiveData = new MutableLiveData<>();
        setupObservers();
    }

    @Override
    public void setupObservers() {
        datamodel.documentoMutableLiveData().observeForever(new Observer<List<DocumentoResponse<DetalleDocumento>>>() {
            @Override
            public void onChanged(List<DocumentoResponse<DetalleDocumento>> pro) {
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                onRetrieveDataFinish();

                documentoMutableLiveData.setValue(pro);
            }
        });
    }

    MutableLiveData<List<DocumentoResponse<DetalleDocumento>>> documentoMutableLiveData() {
        return documentoMutableLiveData;
    }

    void requestDocumento(Integer idProyecto, Integer idOpcion) {
        onRetrieveData();
        new documentoViewModel.ValidateRequestPrincipalTask(this,idProyecto,idOpcion).execute();
    }

    private static class ValidateRequestPrincipalTask extends AsyncTask<Void, Void, Boolean> {
        documentoViewModel viewModel;
        Integer idProyecto;
        Integer idOpcion;

        ValidateRequestPrincipalTask(documentoViewModel viewModel, Integer idProyecto, Integer idOpcion) {
            this.viewModel = viewModel;
            this.idProyecto= idProyecto;
            this.idOpcion = idOpcion;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {

                viewModel.datamodel.requestDocuments(idProyecto, idOpcion);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }
}
