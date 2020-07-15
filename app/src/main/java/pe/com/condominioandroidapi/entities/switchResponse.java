package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class switchResponse {

    @SerializedName("idUsuarioNotificaciones")
    private Integer idUsuarioNotificaciones;
    @SerializedName("idTipoNotificacion")
    private Integer idTipoNotificacion;
    @SerializedName("idUsuario")
    private Integer idUsuario;
    @SerializedName("estado")
    private Integer estado;

    public Integer getIdUsuarioNotificaciones() {
        return idUsuarioNotificaciones;
    }

    public void setIdUsuarioNotificaciones(Integer idUsuarioNotificaciones) {
        this.idUsuarioNotificaciones = idUsuarioNotificaciones;
    }

    public Integer getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(Integer idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
