package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class layoutResponse {

    @SerializedName("logoArchivo")
    private String logoArchivo;

    @SerializedName("colorHexadecimal")
    private String colorHexadecimal;

    @SerializedName("archivo")
    private String archivo;

    @SerializedName("idProyecto")
    private int idProyecto;

    @SerializedName("idInmueble")
    private int idInmueble;


    public String getLogoArchivo() {
        return logoArchivo;
    }

    public void setLogoArchivo(String logoArchivo) {
        this.logoArchivo = logoArchivo;
    }

    public String getColorHexadecimal() {
        return colorHexadecimal;
    }

    public void setColorHexadecimal(String colorHexadecimal) {
        this.colorHexadecimal = colorHexadecimal;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

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
}
