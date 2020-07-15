package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class ContactoProyecto {

    @SerializedName("idCatalogo")
    private Integer idCatalogo;
    @SerializedName("idProyecto")
    private Integer idProyecto;
    @SerializedName("razonSocial")
    private String razonSocial;
    @SerializedName("correo")
    private String correo;
    @SerializedName("telefonoFijo")
    private String telefonoFijo;
    @SerializedName("telefonoMovil")
    private String telefonoMovil;

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }
}
