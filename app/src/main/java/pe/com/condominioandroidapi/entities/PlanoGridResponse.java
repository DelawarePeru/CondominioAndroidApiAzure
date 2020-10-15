package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class PlanoGridResponse {

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("idInmueble")
    private Integer idInmueble;

    @SerializedName("codigoInmueble")
    private String codigoInmueble;

    @SerializedName("estadoVenta")
    private Integer estadoVenta;

    @SerializedName("idProyecto")
    private Integer idProyecto;

    @SerializedName("idTipoInmueble")
    private Integer idTipoInmueble;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getCodigoInmueble() {
        return codigoInmueble;
    }

    public void setCodigoInmueble(String codigoInmueble) {
        this.codigoInmueble = codigoInmueble;
    }

    public Integer getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(Integer estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdTipoInmueble() {
        return idTipoInmueble;
    }

    public void setIdTipoInmueble(Integer idTipoInmueble) {
        this.idTipoInmueble = idTipoInmueble;
    }
}
