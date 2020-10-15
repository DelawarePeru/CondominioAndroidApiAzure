package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class TipoInmuebleResponse {

    @SerializedName("idCatalogo")
    private Integer idCatalogo;
    @SerializedName("idTipoInmueble")
    private Integer idTipoInmueble;
    @SerializedName("tipoInmueble")
    private String tipoInmueble;

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(Integer idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
}
