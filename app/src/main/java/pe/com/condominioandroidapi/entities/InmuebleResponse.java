package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InmuebleResponse {
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @SerializedName("idPersona")
    private int idPersona;

    @SerializedName("idProyecto")
    private int idProyecto;

    @SerializedName("nombreProyecto")
    private String nombreProyecto;

    @SerializedName("razonSocial")
    private String razonSocial;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("idDistrito")
    private int idDistrito;

    @SerializedName("estado")
    private int estado;

    @SerializedName("idEmpresa")
    private int idEmpresa;

    @SerializedName("nombreDistrito")
    private String nombreDistrito;

    @SerializedName("archivoLogo")
    private String archivoLogo;

    @SerializedName("archivoPerfil")
    private String archivoPerfil;

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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getArchivoLogo() {
        return archivoLogo;
    }

    public void setArchivoLogo(String archivoLogo) {
        this.archivoLogo = archivoLogo;
    }

    public String getArchivoPerfil() {
        return archivoPerfil;
    }

    public void setArchivoPerfil(String archivoPerfil) {
        this.archivoPerfil = archivoPerfil;
    }
}
