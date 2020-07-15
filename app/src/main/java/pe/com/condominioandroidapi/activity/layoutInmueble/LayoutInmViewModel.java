package pe.com.condominioandroidapi.activity.layoutInmueble;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.datamodel.InmuebleDatamodel;
import pe.com.condominioandroidapi.datamodel.layoutInmDatamodel;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.layoutResponse;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class LayoutInmViewModel extends BaseViewModel {
    private layoutInmDatamodel datamodel;

    private MutableLiveData<List<layoutResponse>> layoutMutableLiveData;
    private MutableLiveData<Integer> visibilityValueMutableLiveData;

    public LayoutInmViewModel(@NonNull Application application) {
        super(application);
        datamodel = new layoutInmDatamodel();
        visibilityValueMutableLiveData = new MutableLiveData<>();
        layoutMutableLiveData = new MutableLiveData<>();
        setupObservers();
    }

    @Override
    public void setupObservers() {

        datamodel.getlayoutListMutableLiveData().observeForever(new Observer <List<layoutResponse>>() {
            @Override
            public void onChanged(List<layoutResponse> pro) {
                // 2. Al recibir la lista desde el servicio escondemos el ProgressBar
                onRetrieveDataFinish();
                layoutMutableLiveData.setValue(pro);
            }
        });
    }

    MutableLiveData<List<layoutResponse>> getlayoutListMutableLiveData() {
        return layoutMutableLiveData;
    }

    void requestLayout( Integer idInmueble) {
        onRetrieveData();
        new LayoutInmViewModel.ValidateRequestPrincipalTask(this,idInmueble).execute();
    }

    private static class ValidateRequestPrincipalTask extends AsyncTask<Void, Void, Boolean> {
        LayoutInmViewModel viewModel;
        Integer idInmueble;

        ValidateRequestPrincipalTask(LayoutInmViewModel viewModel,  Integer idInmueble) {
            this.viewModel = viewModel;
            this.idInmueble = idInmueble;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                viewModel.datamodel.requestLayout(idInmueble);
                Log.d("obtener", "obtener view model");
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }
        }
    }


}
