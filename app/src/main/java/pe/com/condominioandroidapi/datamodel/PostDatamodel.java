package pe.com.condominioandroidapi.datamodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.ProyectoResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDatamodel {

    private MutableLiveData<List<InmuebleResponse>> proListMutableLiveData;


    public PostDatamodel() {
        proListMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<InmuebleResponse>> getPoListMutableLiveData() {
        return proListMutableLiveData;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestPost(String email) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<InmuebleResponse>> call = service.cardview(builJson(email));

        call.enqueue(new Callback<PostResponse<InmuebleResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<PostResponse<InmuebleResponse>> call, Response<PostResponse<InmuebleResponse>> response) {
                if(response.body().getData().size()!=0) {
                    Constant.set(Constant.ID_PERSONA,Integer.toString(response.body().getData().get(0).getIdPersona()));
                    proListMutableLiveData.setValue(response.body().getData());
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<PostResponse<InmuebleResponse>> call, Throwable t) {

            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private JsonObject builJson(String email) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("userName", email);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
/*
    public void requestProyecto() {
        PostService dashboardService = ServiceGenerator.createService(PostService.class);
        Call<List<PostResponse>> call = dashboardService.getPosts();

        call.enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                poListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {

            }
        });

    }*/
}
