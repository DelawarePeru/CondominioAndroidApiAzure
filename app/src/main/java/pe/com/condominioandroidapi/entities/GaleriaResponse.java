package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class GaleriaResponse {

    @SerializedName("idCatalogo")
    private int idCatalogo;

    @SerializedName("idArchivo")
    private int idArchivo;

    @SerializedName("orden")
    private int orden;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("idTipoGaleria")
    private int idTipoGaleria;

    @SerializedName("nombreOriginal")
    private String nombreOriginal;

    @SerializedName("nombreGuardado")
    private String nombreGuardado;

    @SerializedName("ruta")
    private String ruta;

    @SerializedName("idArchivoTipo")
    private int idArchivoTipo;

    @SerializedName("codigo")
    private String codigo;

    @SerializedName("tipoMime")
    private String tipoMime;

    @SerializedName("image")
    private String image;

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdTipoGaleria() {
        return idTipoGaleria;
    }

    public void setIdTipoGaleria(int idTipoGaleria) {
        this.idTipoGaleria = idTipoGaleria;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getNombreGuardado() {
        return nombreGuardado;
    }

    public void setNombreGuardado(String nombreGuardado) {
        this.nombreGuardado = nombreGuardado;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getIdArchivoTipo() {
        return idArchivoTipo;
    }

    public void setIdArchivoTipo(int idArchivoTipo) {
        this.idArchivoTipo = idArchivoTipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public void setTipoMime(String tipoMime) {
        this.tipoMime = tipoMime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
