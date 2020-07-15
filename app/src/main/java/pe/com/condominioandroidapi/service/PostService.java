package pe.com.condominioandroidapi.service;



import com.google.gson.JsonObject;

import java.util.List;

import pe.com.condominioandroidapi.datamodel.DetalleDocumento;
import pe.com.condominioandroidapi.entities.ContactoProyecto;
import pe.com.condominioandroidapi.entities.DepartamentoResponse;
import pe.com.condominioandroidapi.entities.DetalleResponse;
import pe.com.condominioandroidapi.entities.DocumentoResponse;
import pe.com.condominioandroidapi.entities.GaleriaResponse;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.LugarResponse;
import pe.com.condominioandroidapi.entities.Plano;
import pe.com.condominioandroidapi.entities.PlanoGridResponse;
import pe.com.condominioandroidapi.entities.PostResponse;
import pe.com.condominioandroidapi.entities.ProvinciaResponse;
import pe.com.condominioandroidapi.entities.ProyectoResponse;
import pe.com.condominioandroidapi.entities.UsuarioResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;
import pe.com.condominioandroidapi.entities.layoutResponse;
import pe.com.condominioandroidapi.entities.switchResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostService {


    @POST("Persona/InformacionPersona")
    Call<PostResponse<UsuarioResponse>> avatar(
            @Body JsonObject jsonObject
    );
    @POST("Persona/ActualizarInformacion")
    Call<PostResponse<UsuarioResponse>> updateAvatar(
            @Body JsonObject jsonObject
    );

    @POST("Persona/InformacionSwitch")
    Call<PostResponse<switchResponse>> requestSwitch(
            @Body JsonObject jsonObject
    );

    @POST("Persona/InformacionSwitchUpdate")
    Call<PostResponse<switchResponse>> requestSwitchUpdate(
            @Body JsonObject jsonObject
    );

    @POST("Proyecto/ListarProyectoPorEmpresa")
    Call<PostResponse<InmuebleResponse>> cardview(
            @Body JsonObject jsonObject
    );

    @POST("Inmueble/ListarPorProyecto")
    Call<PostResponse<InmuebleListResponse>> listInmueble(
            @Body JsonObject jsonObject
    );

    @POST("Inmueble/InmuebleLayout")
    Call<PostResponse<layoutResponse>> layout(
                    @Body JsonObject jsonObject
            );

    @POST("Venta/ProyectList")
    Call<PostResponse<VentaResponse>> ListProyect(
            @Body JsonObject jsonObject

    );

    @POST("Venta/PresentacionProyecto")
    Call<PostResponse<DetalleResponse>> PresentacionProyecto(
            @Body JsonObject jsonObject

    );

    @POST("Venta/LugaresProyecto")
    Call<PostResponse<LugarResponse>> LugarProyecto(
            @Body JsonObject jsonObject

    );

    @POST("Venta/GaleriaProyecto")
    Call<PostResponse<GaleriaResponse>> GaleriaProyecto(
            @Body JsonObject jsonObject

    );

    @POST("Venta/PlanoPorTipo")
    Call<PostResponse<PlanoGridResponse>> PlanoGrid(
            @Body JsonObject jsonObject

    );

    @POST("Venta/PlanoPorInmueble")
    Call<PostResponse<Plano>> PlanoPorInmueble(
            @Body JsonObject jsonObject

    );
    @POST("Venta/ContactoProyecto")
    Call<PostResponse<ContactoProyecto>> ContactoCatalogo(
            @Body JsonObject jsonObject

    );

    @POST("Proyecto/ListarDocumentosProyecto")
    Call<PostResponse<DocumentoResponse<DetalleDocumento>>> listarDocumentoProyecto(
            @Body JsonObject jsonObject
    );
    @POST("Inmueble/ListarDocumentosInmuebles")
    Call<PostResponse<DocumentoResponse<DetalleDocumento>>> listarDocumentoInmueble(
            @Body JsonObject jsonObject
    );
    @POST("Venta/ProvinciaList")
    Call<PostResponse<ProvinciaResponse>> ProvinciaAyuda(
            @Body JsonObject jsonObject
    );@POST("Venta/DepartamentoList")
    Call<PostResponse<DepartamentoResponse>> DepartamentoAyuda(
            );
}
