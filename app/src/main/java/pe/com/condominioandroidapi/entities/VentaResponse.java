package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class VentaResponse {


    @SerializedName("idCatalogo")
    private int idCatalogo;

    @SerializedName("estadoObra")
    public String estadoObra;

    @SerializedName("idEmpresa")
    public int idEmpresa;

    @SerializedName("razonSocial")
    public String razonSocial ;

    @SerializedName("colorHexadecimal")
    public String colorHexadecimal;

    @SerializedName("logoEmpresa")
    public String logoEmpresa;

    @SerializedName("idProyecto")
    public int idProyecto;

    @SerializedName("nombreProyecto")
    public String nombreProyecto;

    @SerializedName("direccion")
    public String direccion;

    @SerializedName("nombreDistrito")
    public String nombreDistrito;

    @SerializedName("nombrePerfil")
    public String nombrePerfil;

    @SerializedName("perfilProyecto")
    public String perfilProyecto;

    @SerializedName("nombreLogo")
    public String nombreLogo;

    @SerializedName("logoProyecto")
    public String logoProyecto;

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getEstadoObra() {
        return estadoObra;
    }

    public void setEstadoObra(String estadoObra) {
        this.estadoObra = estadoObra;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getColorHexadecimal() {
        return colorHexadecimal;
    }

    public void setColorHexadecimal(String colorHexadecimal) {
        this.colorHexadecimal = colorHexadecimal;
    }

    public String getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(String logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getPerfilProyecto() {
        return perfilProyecto;
    }

    public void setPerfilProyecto(String perfilProyecto) {
        this.perfilProyecto = perfilProyecto;
    }

    public String getNombreLogo() {
        return nombreLogo;
    }

    public void setNombreLogo(String nombreLogo) {
        this.nombreLogo = nombreLogo;
    }

    public String getLogoProyecto() {
        return logoProyecto;
    }

    public void setLogoProyecto(String logoProyecto) {
        this.logoProyecto = logoProyecto;
    }
}
