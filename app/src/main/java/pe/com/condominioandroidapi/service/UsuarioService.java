package pe.com.condominioandroidapi.service;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import pe.com.condominioandroidapi.entities.Login;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsuarioService {
    @POST("Account/LoginMovil")
    Call<String> basicLogin(@Body JsonObject jsonObject);

    @POST("Account/RegisterMovil")
    Call<ResponseBody> registro(@Body JsonObject jsonObject);

    @POST("Account/ForgotPasswordMovil")
    Call<ResponseBody> recuperar(@Body JsonObject jsonObject);

    @POST("Persona/AgregarPersona")
    Call<PostResponse> AgregarPersona(@Body JsonObject jsonObject);
}
