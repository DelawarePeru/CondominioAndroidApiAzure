package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class DetalleResponse {

    @SerializedName("idCatalogo")
    private int idCatalogo;

    @SerializedName("idProyecto")
    private int idProyecto;

    @SerializedName("descripcion")
    private String descripcion;


    @SerializedName("logoProyecto")
    public String logoProyecto;


    @SerializedName("perfilProyecto")
    public String perfilProyecto;

    @SerializedName("coordX")
    private String coordX;

    @SerializedName("coordY")
    private String coordY;

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogoProyecto() {
        return logoProyecto;
    }

    public void setLogoProyecto(String logoProyecto) {
        this.logoProyecto = logoProyecto;
    }

    public String getPerfilProyecto() {
        return perfilProyecto;
    }

    public void setPerfilProyecto(String perfilProyecto) {
        this.perfilProyecto = perfilProyecto;
    }
}
