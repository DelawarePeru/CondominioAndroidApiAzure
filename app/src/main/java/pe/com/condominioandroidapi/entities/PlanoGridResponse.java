package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class PlanoGridResponse {

    @SerializedName("codigoInmueble")
    private String codigoInmueble;

    @SerializedName("idInmueble")
    private Integer idInmueble;

    @SerializedName("idCatalogoPlano")
    private Integer idCatalogoPlano;

    @SerializedName("idTipoInmueble")
    private Integer idTipoInmueble;

    @SerializedName("estado")
    private Integer estado;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getCodigoInmueble() {
        return codigoInmueble;
    }

    public void setCodigoInmueble(String codigoInmueble) {
        this.codigoInmueble = codigoInmueble;
    }

    public Integer getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Integer getIdCatalogoPlano() {
        return idCatalogoPlano;
    }

    public void setIdCatalogoPlano(Integer idCatalogoPlano) {
        this.idCatalogoPlano = idCatalogoPlano;
    }

    public Integer getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(Integer idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }
}
