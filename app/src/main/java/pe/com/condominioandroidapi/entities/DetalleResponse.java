package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class DetalleResponse {

    @SerializedName("idCatalogo")
    private int idCatalogo;

    @SerializedName("idProyecto")
    private int idProyecto;

    @SerializedName("descripcion")
    private String descripcion;

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


}
