package pe.com.condominioandroidapi.activity.detalle;

import android.app.Application;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import pe.com.condominioandroidapi.datamodel.DetalleDataModel;
import pe.com.condominioandroidapi.entities.ContactoProyecto;
import pe.com.condominioandroidapi.entities.DetalleResponse;
import pe.com.condominioandroidapi.entities.GaleriaResponse;
import pe.com.condominioandroidapi.entities.LugarResponse;
import pe.com.condominioandroidapi.entities.Plano;
import pe.com.condominioandroidapi.entities.PlanoGridResponse;
import pe.com.condominioandroidapi.entities.TipoInmuebleResponse;
import pe.com.condominioandroidapi.util.Helper;
import pe.com.condominioandroidapi.util.basecomponent.BaseViewModel;

public class detalleViewModel extends BaseViewModel {
        DetalleDataModel datamodel;
    private MutableLiveData<List<DetalleResponse>> detalleMutableLiveData;
    private MutableLiveData<List<TipoInmuebleResponse>> tipoMutableLiveData;

    private MutableLiveData<List<LugarResponse>> lugarMutableLiveData;
    private MutableLiveData<List<GaleriaResponse>> galeriaMutableLiveData;
    private MutableLiveData<List<PlanoGridResponse>> gridMutableLiveData;
    private MutableLiveData<List<Plano>> planoMutableLiveData;
    private MutableLiveData<List<ContactoProyecto>> contactoMutableLiveData;



    public detalleViewModel(@NonNull Application application) {
        super(application);
        datamodel = new DetalleDataModel();
        detalleMutableLiveData = new MutableLiveData<>();
        lugarMutableLiveData = new MutableLiveData<>();
        gridMutableLiveData = new MutableLiveData<>();
        galeriaMutableLiveData = new MutableLiveData<>();
        planoMutableLiveData = new MutableLiveData<>();
        contactoMutableLiveData = new MutableLiveData<>();
        tipoMutableLiveData= new MutableLiveData<>();

        setupObservers();
    }
    @Override
    public void setupObservers() {
        datamodel.getContactoMutableLiveData().observeForever(new Observer<List<ContactoProyecto>>() {
            @Override
            public void onChanged(List<ContactoProyecto> pro) {
                onRetrieveDataFinish();

                contactoMutableLiveData.setValue(pro);
            }
        });
        datamodel.getDetalleMutableLiveData().observeForever(new Observer<List<DetalleResponse>>() {
            @Override
            public void onChanged(List<DetalleResponse> pro) {
                onRetrieveDataFinish();

                detalleMutableLiveData.setValue(pro);
            }
        });
        datamodel.getTipoMutableLiveData().observeForever(new Observer<List<TipoInmuebleResponse>>() {
            @Override
            public void onChanged(List<TipoInmuebleResponse> pro) {
                onRetrieveDataFinish();

                tipoMutableLiveData.setValue(pro);
            }
        });
        datamodel.getLugarMutableLiveData().observeForever(new Observer<List<LugarResponse>>() {
            @Override
            public void onChanged(List<LugarResponse> pro) {
                onRetrieveDataFinish();

                lugarMutableLiveData.setValue(pro);
            }
        });

        datamodel.getGaleriaMutableLiveData().observeForever(new Observer<List<GaleriaResponse>>() {
            @Override
            public void onChanged(List<GaleriaResponse> pro) {
                onRetrieveDataFinish();

                galeriaMutableLiveData.setValue(pro);
            }
        });


        datamodel.getGridMutableLiveData().observeForever(new Observer<List<PlanoGridResponse>>() {
            @Override
            public void onChanged(List<PlanoGridResponse> pro) {
                onRetrieveDataFinish();

                gridMutableLiveData.setValue(pro);
            }
        });

        datamodel.getPlanoMutableLiveData().observeForever(new Observer<List<Plano>>() {
            @Override
            public void onChanged(List<Plano> pro) {
                onRetrieveDataFinish();

                planoMutableLiveData.setValue(pro);
            }
        });


    }
    public MutableLiveData<List<DetalleResponse>> getDetalleMutableLiveData() {
        return detalleMutableLiveData;
    }
    public MutableLiveData<List<LugarResponse>> getLugarMutableLiveData() {
        return lugarMutableLiveData;
    }

    public MutableLiveData<List<TipoInmuebleResponse>> getTipoInmuebleMutableLiveData() {
        return tipoMutableLiveData;
    }
    public MutableLiveData<List<GaleriaResponse>> getGaleriaMutableLiveData() {
        return galeriaMutableLiveData;
    }

    public MutableLiveData<List<PlanoGridResponse>> getGridMutableLiveData() {
        return gridMutableLiveData;
    }
    public MutableLiveData<List<Plano>> getPlanoMutableLiveData() {
        return planoMutableLiveData;
    }

    public MutableLiveData<List<ContactoProyecto>> getContactoMutableLiveData() {
        return contactoMutableLiveData;
    }

    public void requestDetalle(Integer idCatalogo)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestPrincipalTask(idCatalogo,this).execute();
    }
    public void requestLugar(Integer idCatalogo)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestLugarTask(idCatalogo,this).execute();
    }
    public void requestGaleria(Integer idCatalogo,Integer idTipo)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestGaleriaTask(idCatalogo, idTipo,this).execute();
    }
    public void requestGrid(Integer idCatalogo,Integer idTipo)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestGridTask(idCatalogo, idTipo,this).execute();
    }
    public void requestTipoInmueble(Integer idCatalogo)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestTipoInmueble(idCatalogo, this).execute();
    }
    public void requestPlano(Integer idInmueble)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestPlanoTask(idInmueble,this).execute();
    }
    public void requestContacto(Integer idCatalogo)
    {
        onRetrieveData();
        new detalleViewModel.ValidateRequestContactoTask(idCatalogo,this).execute();
    }
    private static class ValidateRequestPrincipalTask extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idCatalogo;

        ValidateRequestPrincipalTask(Integer idCatalogo,detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idCatalogo=idCatalogo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestDetalle(idCatalogo);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }

    private static class ValidateRequestLugarTask extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idCatalogo;

        ValidateRequestLugarTask(Integer idCatalogo,detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idCatalogo=idCatalogo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestLugar(idCatalogo);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }

    private static class ValidateRequestGaleriaTask extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idCatalogo;
        Integer idTipo;

        ValidateRequestGaleriaTask(Integer idCatalogo,Integer idTipo,detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idCatalogo=idCatalogo;
            this.idTipo = idTipo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestGaleria(idCatalogo, idTipo);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }
    private static class ValidateRequestGridTask extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idCatalogo;
        Integer idTipo;

        ValidateRequestGridTask(Integer idCatalogo,Integer idTipo,detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idCatalogo=idCatalogo;
            this.idTipo = idTipo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestGrid(idCatalogo, idTipo);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }
    private static class ValidateRequestTipoInmueble extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idCatalogo;

        ValidateRequestTipoInmueble(Integer idCatalogo, detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idCatalogo=idCatalogo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestTipoInmueble(idCatalogo);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }

    private static class ValidateRequestPlanoTask extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idInmuble;

        ValidateRequestPlanoTask(Integer idInmuble, detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idInmuble = idInmuble;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestPlano(idInmuble);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }

    private static class ValidateRequestContactoTask extends AsyncTask<Void,Void,Boolean>
    {
        detalleViewModel viewModel;
        Integer idCatalogo;

        ValidateRequestContactoTask(Integer idCatalogo, detalleViewModel viewModel)
        {
            this.viewModel = viewModel;
            this.idCatalogo = idCatalogo;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return Helper.isOnline();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                viewModel.datamodel.requestContacto(idCatalogo);
            }
            else {
                viewModel.messageResult.setValue("No tienes acceso a internet");
                viewModel.onRetrieveDataFinish();
            }

        }
    }
}
