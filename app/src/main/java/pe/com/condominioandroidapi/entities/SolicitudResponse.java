package pe.com.condominioandroidapi.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import pe.com.condominioandroidapi.util.basecomponent.BaseResponse;


public class SolicitudResponse extends BaseResponse implements Parcelable {

        @SerializedName("IdSolicitud") private Integer id;
        @SerializedName("NombreSolicitud") private String nombre;
        @SerializedName("NombreEmpresa") private String empresa;
        @SerializedName("NombreDivision") private String division;
        @SerializedName("MonedaMonto") private String monedaMonto;
        @SerializedName("MontoSolicitado") private String montoSolicitado;
        @SerializedName("DiasPendientes") private String diasPendientes;

        public Integer getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getEmpresa() {
            return empresa;
        }

        public String getDivision() {
            return division;
        }

        public String getMonedaMonto() {
            return monedaMonto;
        }

        public String getMontoSolicitado() {
            return montoSolicitado;
        }

        public String getDiasPendientes() {
            return diasPendientes;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(this.id);
            dest.writeString(this.nombre);
            dest.writeString(this.empresa);
            dest.writeString(this.division);
            dest.writeString(this.monedaMonto);
            dest.writeString(this.montoSolicitado);
            dest.writeString(this.diasPendientes);
        }

        public SolicitudResponse() {
        }

        protected SolicitudResponse(Parcel in) {
            this.id = (Integer) in.readValue(Integer.class.getClassLoader());
            this.nombre = in.readString();
            this.empresa = in.readString();
            this.division = in.readString();
            this.monedaMonto = in.readString();
            this.montoSolicitado = in.readString();
            this.diasPendientes = in.readString();
        }

        public static final Creator<SolicitudResponse> CREATOR = new Creator<SolicitudResponse>() {
            @Override
            public SolicitudResponse createFromParcel(Parcel source) {
                return new SolicitudResponse(source);
            }

            @Override
            public SolicitudResponse[] newArray(int size) {
                return new SolicitudResponse[size];
            }
        };
    }


