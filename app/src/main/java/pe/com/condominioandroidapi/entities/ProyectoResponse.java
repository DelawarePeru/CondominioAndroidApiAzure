package pe.com.condominioandroidapi.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProyectoResponse {
    @SerializedName("listaProyecto") private ArrayList<InmuebleResponse> listaProyecto;
    @SerializedName("logoEmpresa") private String logoEmpresa;
    @SerializedName("nombre") private String nombre;

    public ArrayList<InmuebleResponse> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(ArrayList<InmuebleResponse> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    public String getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(String logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}



