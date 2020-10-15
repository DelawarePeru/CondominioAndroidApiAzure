package pe.com.condominioandroidapi.datamodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import pe.com.condominioandroidapi.entities.DepartamentoResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.ProvinciaResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;
import pe.com.condominioandroidapi.entities.layoutResponse;
import pe.com.condominioandroidapi.service.PostService;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ServiceGenerator;
import pe.com.condominioandroidapi.util.basecomponent.BaseDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProyectoDataModel  extends BaseDataModel {
    private MutableLiveData<List<VentaResponse>> vincularMutableLiveData;
    private MutableLiveData<List<VentaResponse>> proyectListMutableLiveData;
    private MutableLiveData<List<DepartamentoResponse>> departamentoListMutableLiveData;
    private MutableLiveData<List<ProvinciaResponse>> provinciaListMutableLiveData;


    public ProyectoDataModel() {
        proyectListMutableLiveData = new MutableLiveData<>();
        departamentoListMutableLiveData = new MutableLiveData<>();
        provinciaListMutableLiveData = new MutableLiveData<>();
        vincularMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<List<VentaResponse>> getProyectListMutableLiveData() {
        return proyectListMutableLiveData;
    }

    public MutableLiveData<List<DepartamentoResponse>> getDepartamentoListMutableLiveData() {
        return departamentoListMutableLiveData;
    }

    public MutableLiveData<List<ProvinciaResponse>> getProvinciaListMutableLiveData() {
        return provinciaListMutableLiveData;
    }
    public MutableLiveData<List<VentaResponse>> getVincularMutableLiveData() {
        return vincularMutableLiveData;
    }
    public void requestProyectList(String departamento, String provincia) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<VentaResponse>> call = service.ListProyect(builJson(departamento, provincia));
        call.enqueue(new Callback<PostResponse<VentaResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<VentaResponse>> call, Response<PostResponse<VentaResponse>> response) {
                if(response.body().getIdError()==0) {

                    if(response.body().getData().size()>0) {
                        proyectListMutableLiveData.setValue(response.body().getData());
                    }
                    else{
                        errorMessageLiveData.setValue(response.body().getMensaje());

                    }

                }else
                {
                    errorCodeLiveData.setValue(response.code());

                }
            }

            @Override
            public void onFailure(Call<PostResponse<VentaResponse>> call, Throwable t) {

            }
        });


    }

    public void requestVincular(String codigo, String email) {
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<VentaResponse>> call = service.Vincular(builJsonValidar(codigo, email));
        call.enqueue(new Callback<PostResponse<VentaResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<VentaResponse>> call, Response<PostResponse<VentaResponse>> response) {
                Log.d("response", response.body().getData().toString());
                vincularMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<VentaResponse>> call, Throwable t) {

            }
        });


    }

    public void requestDepartamento() {
        final List<DepartamentoResponse> divisionesData = new ArrayList<>();
        divisionesData.add(new DepartamentoResponse().setNombreDepartamento("Seleccione").setId(-1));
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<DepartamentoResponse>> call = service.DepartamentoAyuda();
        call.enqueue(new Callback<PostResponse<DepartamentoResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<DepartamentoResponse>> call, Response<PostResponse<DepartamentoResponse>> response) {
                Log.d("response", response.body().getData().toString());
                departamentoListMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<DepartamentoResponse>> call, Throwable t) {

            }
        });


    }

    public void requestProvincia(Integer departamento) {
        final List<ProvinciaResponse> divisionesData = new ArrayList<>();
        divisionesData.add(new ProvinciaResponse().setNombreProvincia("Seleccione").setId(-1));
        PostService service = ServiceGenerator
                .createService(PostService.class, Constant.get(Constant.KEY_ACCESS_TOKEN));
        Call<PostResponse<ProvinciaResponse>> call = service.ProvinciaAyuda(builJsonProvincia(departamento));
        call.enqueue(new Callback<PostResponse<ProvinciaResponse>>() {
            @Override
            public void onResponse(Call<PostResponse<ProvinciaResponse>> call, Response<PostResponse<ProvinciaResponse>> response) {
                Log.d("response", response.body().getData().toString());
                provinciaListMutableLiveData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<PostResponse<ProvinciaResponse>> call, Throwable t) {

            }
        });


    }
    private JsonObject builJsonValidar(String codigo, String email) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("codigo", codigo);
            jo.addProperty("email", email);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
    private JsonObject builJson(String departamento, String provincia) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("departamento", departamento);
            jo.addProperty("provincia", provincia);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }

    private JsonObject builJsonProvincia(Integer idDepartamento) {
        JsonObject jo = new JsonObject();
        try {
            jo.addProperty("id", idDepartamento);


        } catch (Exception ex) {

        }
        Log.d("json", jo.toString());
        return jo;
    }
}