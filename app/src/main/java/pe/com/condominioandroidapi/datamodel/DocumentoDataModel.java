package pe.com.condominioandroidapi.datamodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import pe.com.condominioandroidapi.entities.DocumentoResponse;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentoDataModel {

    private MutableLiveData<List<DocumentoResponse<DetalleDocumento>>> documentoMutableLiveData;


    public DocumentoDataModel() {
        documentoMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<DocumentoResponse<DetalleDocumento>>> documentoMutableLiveData() {
        return documentoMutableLiveData;
    }

    public void requestDocuments(Integer idProyecto, Integer idOpcion) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<DocumentoResponse<DetalleDocumento>>> call = null;       
        if(idOpcion == 1)
            call = service.listarDocumentoProyecto(builJson(idProyecto));
        if(idOpcion == 2)
            call = service.listarDocumentoInmueble(builJsonInmueble(idProyecto));

        call.enqueue(new Callback<PostResponse<DocumentoResponse<DetalleDocumento>>>() {
            @Override
            public void onResponse(Call<PostResponse<DocumentoResponse<DetalleDocumento>>> call, Response<PostResponse<DocumentoResponse<DetalleDocumento>>> response) {
                Log.d("response" , response.body().getData().toString());
                documentoMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<DocumentoResponse<DetalleDocumento>>> call, Throwable t) {

            }
        });

    }

    private JsonObject builJson(Integer idProyecto) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idProyecto", idProyecto);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
    private JsonObject builJsonInmueble(Integer idInmueble) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idInmueble", idInmueble);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
}
