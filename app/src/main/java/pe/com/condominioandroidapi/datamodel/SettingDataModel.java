package pe.com.condominioandroidapi.datamodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingDataModel {

    private MutableLiveData<List<InmuebleListResponse>> inmListMutableLiveData;


    public SettingDataModel() {
        inmListMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<InmuebleListResponse>> getAvatarMutableLiveData() {
        return inmListMutableLiveData;
    }
    public void requestListInmu(Integer idPersona, Integer idProyecto) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<InmuebleListResponse>> call = service.listInmueble(builJson(2,idProyecto));

        call.enqueue(new Callback<PostResponse<InmuebleListResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<InmuebleListResponse>> call, Response<PostResponse<InmuebleListResponse>> response) {
                Log.d("response" , response.body().getData().toString());
                inmListMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<InmuebleListResponse>> call, Throwable t) {

            }
        });

    }
    private JsonObject builJson(Integer id, Integer idProyecto) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idPersona", 2);
            jo.addProperty("idProyecto", idProyecto);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

}
