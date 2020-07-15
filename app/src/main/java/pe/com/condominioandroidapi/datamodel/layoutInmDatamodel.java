package pe.com.condominioandroidapi.datamodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.layoutResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class layoutInmDatamodel {

    private MutableLiveData<List<layoutResponse>> layoutMutableLiveData;

    public layoutInmDatamodel() {
        layoutMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<layoutResponse>> getlayoutListMutableLiveData() {
        return layoutMutableLiveData;
    }

    public void requestLayout(Integer idInmueble) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<layoutResponse>> call = service.layout(builJson(idInmueble));
        Log.d("idInmuebleRequest", idInmueble.toString());
        call.enqueue(new Callback<PostResponse<layoutResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<layoutResponse>> call, Response<PostResponse<layoutResponse>> response) {
                Log.d("response" , response.body().getData().toString());
                layoutMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<layoutResponse>> call, Throwable t) {

            }
        });

    }
    private JsonObject builJson(Integer idInmueble) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idInmueble", idInmueble);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
}
