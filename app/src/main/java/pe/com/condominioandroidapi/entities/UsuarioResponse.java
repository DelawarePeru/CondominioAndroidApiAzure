package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class UsuarioResponse {

    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellidoPaterno")
    private String apellidoPaterno;
    @SerializedName("apellidoMaterno")
    private String apellidoMaterno;
    @SerializedName("correo")
    private String correo;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("redSocial")
    private String redSocial;
    @SerializedName("archivo")
    private String archivo;
    @SerializedName("extensionArchivo")
    private String extensionArchivo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(String redSocial) {
        this.redSocial = redSocial;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getExtensionArchivo() {
        return extensionArchivo;
    }

    public void setExtensionArchivo(String extensionArchivo) {
        this.extensionArchivo = extensionArchivo;
    }
}
