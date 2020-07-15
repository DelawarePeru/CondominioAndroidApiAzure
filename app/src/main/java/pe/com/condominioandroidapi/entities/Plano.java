package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class Plano {

    @SerializedName("idCatalogo")
    private Integer idCatalogo;

    @SerializedName("idInmueble")
    private Integer idInmueble;

    @SerializedName("areaTechada")
    private Float areaTechada;

    @SerializedName("areaLibreVendible")
    private Float areaLibreVendible;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("precioEstacionamiento")
    private Float precioEstacionamiento;

    @SerializedName("precioDeposito")
    private Float precioDeposito;

    @SerializedName("precioTotal")
    private Float precioTotal;

    @SerializedName("nombreGuardado")
    private String nombreGuardado;

    @SerializedName("nombreOriginal")
    private String nombreOriginal;

    @SerializedName("ruta")
    private String ruta;

    @SerializedName("image")
    private String image;

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Float getAreaTechada() {
        return areaTechada;
    }

    public void setAreaTechada(Float areaTechada) {
        this.areaTechada = areaTechada;
    }

    public Float getAreaLibreVendible() {
        return areaLibreVendible;
    }

    public void setAreaLibreVendible(Float areaLibreVendible) {
        this.areaLibreVendible = areaLibreVendible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecioEstacionamiento() {
        return precioEstacionamiento;
    }

    public void setPrecioEstacionamiento(Float precioEstacionamiento) {
        this.precioEstacionamiento = precioEstacionamiento;
    }

    public Float getPrecioDeposito() {
        return precioDeposito;
    }

    public void setPrecioDeposito(Float precioDeposito) {
        this.precioDeposito = precioDeposito;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getNombreGuardado() {
        return nombreGuardado;
    }

    public void setNombreGuardado(String nombreGuardado) {
        this.nombreGuardado = nombreGuardado;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
