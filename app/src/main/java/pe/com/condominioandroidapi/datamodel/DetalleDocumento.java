package pe.com.condominioandroidapi.datamodel;

import com.google.gson.annotations.SerializedName;

public class DetalleDocumento {

    @SerializedName("idTipoDocumento")
    private int idTipoDocumento;
    @SerializedName("tipoDocumento")
    private String tipoDocumento;
    @SerializedName("idArchivoBinario")
    private int idArchivoBinario;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("archivo")
    private String archivo;

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getIdArchivoBinario() {
        return idArchivoBinario;
    }

    public void setIdArchivoBinario(int idArchivoBinario) {
        this.idArchivoBinario = idArchivoBinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
