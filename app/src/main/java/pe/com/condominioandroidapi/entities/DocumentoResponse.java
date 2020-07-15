package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pe.com.condominioandroidapi.datamodel.DetalleDocumento;

public class DocumentoResponse<DetalleDocumento> {

    @SerializedName("idTipoDocumento")
    private int idTipoDocumento;
    @SerializedName("tipoDocumento")
    private String tipoDocumento;
    @SerializedName("listaDocumento")
    private List<DetalleDocumento> listaDocumento;

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

    public List<DetalleDocumento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<DetalleDocumento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    /*
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
    }*/
}
