package pe.com.condominioandroidapi.datamodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import pe.com.condominioandroidapi.entities.ContactoProyecto;
import pe.com.condominioandroidapi.entities.DetalleResponse;
import pe.com.condominioandroidapi.entities.GaleriaResponse;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.LugarResponse;
import pe.com.condominioandroidapi.entities.Plano;
import pe.com.condominioandroidapi.entities.PlanoGridResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleDataModel {
    private MutableLiveData<List<DetalleResponse>> detalleMutableLiveData;
    private MutableLiveData<List<LugarResponse>> lugarMutableLiveData;
    private MutableLiveData<List<GaleriaResponse>> galeriaMutableLiveData;
    private MutableLiveData<List<PlanoGridResponse>> gridMutableLiveData;
    private MutableLiveData<List<Plano>> planoMutableLiveData;
    private MutableLiveData<List<ContactoProyecto>> contactoMutableLiveData;



    public  DetalleDataModel()
    {
        detalleMutableLiveData = new MutableLiveData<>();
        lugarMutableLiveData = new MutableLiveData<>();
        galeriaMutableLiveData = new MutableLiveData<>();
        gridMutableLiveData = new MutableLiveData<>();
        planoMutableLiveData = new MutableLiveData<>();
        contactoMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<DetalleResponse>> getDetalleMutableLiveData() {
        return detalleMutableLiveData;
    }
    public MutableLiveData<List<LugarResponse>> getLugarMutableLiveData() {
        return lugarMutableLiveData;
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

    public void requestDetalle(Integer idCatalogo) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<DetalleResponse>> call = service.PresentacionProyecto(builJson(idCatalogo));

        call.enqueue(new Callback<PostResponse<DetalleResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<DetalleResponse>> call, Response<PostResponse<DetalleResponse>> response) {
                Log.d("response" , response.body().getData().toString());
                detalleMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<DetalleResponse>> call, Throwable t) {

            }
        });

    }


    public void requestLugar(Integer idCatalogo)
    {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<LugarResponse>> call = service.LugarProyecto(builJson(idCatalogo));

        call.enqueue(new Callback<PostResponse<LugarResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<LugarResponse>> call, Response<PostResponse<LugarResponse>> response) {
                lugarMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<LugarResponse>> call, Throwable t) {

            }
        });

    }

    public void requestGaleria(Integer idCatalogo, Integer idTipo)
    {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<GaleriaResponse>> call = service.GaleriaProyecto(builJsonGaleria(idCatalogo, idTipo));

        call.enqueue(new Callback<PostResponse<GaleriaResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<GaleriaResponse>> call, Response<PostResponse<GaleriaResponse>> response) {
                galeriaMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<GaleriaResponse>> call, Throwable t) {

            }
        });

    }

    public void requestGrid(Integer idCatalogo, Integer idTipo)
    {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<PlanoGridResponse>> call = service.PlanoGrid(builJsonGrid(idCatalogo, idTipo));

        call.enqueue(new Callback<PostResponse<PlanoGridResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<PlanoGridResponse>> call, Response<PostResponse<PlanoGridResponse>> response) {
                gridMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<PlanoGridResponse>> call, Throwable t) {

            }
        });

    }

    public void requestPlano(Integer idInmueble)
    {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<Plano>> call = service.PlanoPorInmueble(builPlano(idInmueble));

        call.enqueue(new Callback<PostResponse<Plano>>() {
            @Override
            public void onResponse(Call<PostResponse<Plano>> call, Response<PostResponse<Plano>> response) {
                planoMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<Plano>> call, Throwable t) {

            }
        });

    }

    public void requestContacto(Integer idCatalogo)
    {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<ContactoProyecto>> call = service.ContactoCatalogo(builJson(idCatalogo));

        call.enqueue(new Callback<PostResponse<ContactoProyecto>>() {
            @Override
            public void onResponse(Call<PostResponse<ContactoProyecto>> call, Response<PostResponse<ContactoProyecto>> response) {
                contactoMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<ContactoProyecto>> call, Throwable t) {

            }
        });

    }
    private JsonObject builJson(Integer idCatalogo) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idCatalogo", idCatalogo);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    private JsonObject builPlano(Integer idInmueble) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idInmueble", idInmueble);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    private JsonObject builJsonGaleria(Integer idCatalogo, Integer idTipo) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idCatalogo", idCatalogo);
            jo.addProperty("idTipoGaleria", idTipo);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    private JsonObject builJsonGrid(Integer idCatalogo, Integer idTipo) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idCatalogo", idCatalogo);
            jo.addProperty("idTipoInmueble", idTipo);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
}
