package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LugarResponse {

    @SerializedName("idCatalogo")
    private int idCatalogo;

    @SerializedName("coordX")
    private String coordX;

    @SerializedName("coordY")
    private String coordY;

    @SerializedName("introduccion")
    private String introduccion;

    @SerializedName("idTipoLugar")
    private int idTipoLugar;

    @SerializedName("nombreTipo")
    private String nombreTipo;

    @SerializedName("listaDeLugares")
    private List<LugarResponse> listaDeLugares;

    @SerializedName("nombre")
    private String nombre;

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

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

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public int getIdTipoLugar() {
        return idTipoLugar;
    }

    public void setIdTipoLugar(int idTipoLugar) {
        this.idTipoLugar = idTipoLugar;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public List<LugarResponse> getListaDeLugares() {
        return listaDeLugares;
    }

    public void setListaDeLugares(List<LugarResponse> listaDeLugares) {
        this.listaDeLugares = listaDeLugares;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
