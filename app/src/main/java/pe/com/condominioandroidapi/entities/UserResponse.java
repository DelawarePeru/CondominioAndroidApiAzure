package pe.com.condominioandroidapi.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import pe.com.condominioandroidapi.util.basecomponent.BaseResponse;

public class UserResponse extends BaseResponse implements Parcelable {

    @SerializedName("IdUsuario") private Integer idUsuario;
    @SerializedName("CuentaUsuario") private String cuentaUsuario;
    @SerializedName("Nombres") private String nombres;
    @SerializedName("Apellidos") private String apellidos;
    @SerializedName("NombresCompletos") private String nombresCompletos;
    @SerializedName("NombreUsuario") private String nombreUsuario;
    @SerializedName("Email") private String email;
    @SerializedName("Cargo") private String cargo;
    @SerializedName("Telefono") private String telefono;
    @SerializedName("Iniciales") private String iniciales;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getIniciales() {
        return iniciales;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "{" +
                "idUsuario=" + idUsuario +
                ", cuentaUsuario='" + cuentaUsuario + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombresCompletos='" + nombresCompletos + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", email='" + email + '\'' +
                ", cargo='" + cargo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", iniciales='" + iniciales + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idUsuario);
        dest.writeString(this.cuentaUsuario);
        dest.writeString(this.nombres);
        dest.writeString(this.apellidos);
        dest.writeString(this.nombresCompletos);
        dest.writeString(this.nombreUsuario);
        dest.writeString(this.email);
        dest.writeString(this.cargo);
        dest.writeString(this.telefono);
        dest.writeString(this.iniciales);
    }

    public UserResponse() {
    }

    protected UserResponse(Parcel in) {
        this.idUsuario = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cuentaUsuario = in.readString();
        this.nombres = in.readString();
        this.apellidos = in.readString();
        this.nombresCompletos = in.readString();
        this.nombreUsuario = in.readString();
        this.email = in.readString();
        this.cargo = in.readString();
        this.telefono = in.readString();
        this.iniciales = in.readString();
    }

    public static final Parcelable.Creator<UserResponse> CREATOR = new Parcelable.Creator<UserResponse>() {
        @Override
        public UserResponse createFromParcel(Parcel source) {
            return new UserResponse(source);
        }

        @Override
        public UserResponse[] newArray(int size) {
            return new UserResponse[size];
        }
    };
}
