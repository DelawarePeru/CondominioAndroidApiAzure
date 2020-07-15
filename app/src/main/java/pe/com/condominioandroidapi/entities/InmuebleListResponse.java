package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class InmuebleListResponse {

    @SerializedName("idProyecto")
    private int idProyecto;
    @SerializedName("idInmueble")
    private int idInmueble;
    @SerializedName("codigoInmueble")
    private String codigoInmueble;
    @SerializedName("idTipoInmueble")
    private int idTipoInmueble;
    @SerializedName("estado")
    private int estado;

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getCodigoInmueble() {
        return codigoInmueble;
    }

    public void setCodigoInmueble(String codigoInmueble) {
        this.codigoInmueble = codigoInmueble;
    }

    public int getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(int idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
