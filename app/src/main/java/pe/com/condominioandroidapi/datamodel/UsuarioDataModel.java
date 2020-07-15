package pe.com.condominioandroidapi.datamodel;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.UsuarioResponse;
import pe.com.condominioandroidapi.entities.switchResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioDataModel {

    private MutableLiveData<UsuarioResponse> usuarioMutableLiveData;
    private MutableLiveData<List<switchResponse>> switchMutableLiveData;
    private Context context;


    public UsuarioDataModel() {
        usuarioMutableLiveData = new MutableLiveData<>();
        switchMutableLiveData = new MutableLiveData<>();

    }

    public UsuarioDataModel(Context context) {
        this.context = context;

    }
    public MutableLiveData<UsuarioResponse> getUsuarioMutableLiveData() {
        return usuarioMutableLiveData;
    }
    public MutableLiveData<List<switchResponse>> getSwitchMutableLiveData() {
        return switchMutableLiveData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestAvatar(Integer idPersona) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<UsuarioResponse>> call = service.avatar(builJson(2));

        call.enqueue(new Callback<PostResponse<UsuarioResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<UsuarioResponse>> call, Response<PostResponse<UsuarioResponse>> response) {
                Log.d("response" , response.body().getData().toString());
                Constant.set(Constant.NOMBRE,response.body().getData().get(0).getNombre());

                usuarioMutableLiveData.setValue(response.body().getData().get(0));
            }

            @Override
            public void onFailure(Call<PostResponse<UsuarioResponse>> call, Throwable t) {

            }
        });

    }
    private JsonObject builJson(Integer id) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idPersona", 2);

        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    public void updateAvatar(Integer idPersona, String telefono) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<UsuarioResponse>> call = service.updateAvatar(buidlJsonTwo(idPersona,telefono));

        call.enqueue(new Callback<PostResponse<UsuarioResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<UsuarioResponse>> call, Response<PostResponse<UsuarioResponse>> response) {
                Log.d("response" , response.body().getData().toString());
                usuarioMutableLiveData.setValue(response.body().getData().get(0));

            }

            @Override
            public void onFailure(Call<PostResponse<UsuarioResponse>> call, Throwable t) {

            }
        });

    }

    private JsonObject buidlJsonTwo(Integer id,String telefono) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idPersona", id);
            jo.addProperty("telefono", telefono);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestSwitch(Integer idPersona) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<switchResponse>> call = service.requestSwitch(builJson(idPersona));

        call.enqueue(new Callback<PostResponse<switchResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<switchResponse>> call, Response<PostResponse<switchResponse>> response) {
                Log.d("response" , response.body().getData().toString());
                switchMutableLiveData.setValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<PostResponse<switchResponse>> call, Throwable t) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestUpdateSwitch(Integer idPersona, Integer tipo, Integer estado) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<switchResponse>> call = service.requestSwitchUpdate(builJsonUpdateSwitch
                (idPersona, tipo, estado));

        call.enqueue(new Callback<PostResponse<switchResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<switchResponse>> call, Response<PostResponse<switchResponse>> response) {
                switchMutableLiveData.setValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<PostResponse<switchResponse>> call, Throwable t) {

            }
        });

    }

    private JsonObject builJsonUpdateSwitch(Integer id, Integer tipo, Integer estado) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("idPersona", 2);
            jo.addProperty("idTipoNotificacion", tipo);
            jo.addProperty("estado", estado);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }


}
