package pe.com.condominioandroidapi.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import pe.com.condominioandroidapi.util.basecomponent.BaseResponse;

public class ProvinciaResponse extends BaseResponse implements Parcelable {

    @SerializedName("id") private Integer id;
    @SerializedName("nombreProvincia") private String nombreProvincia;

    public ProvinciaResponse() {

    }
    protected ProvinciaResponse(Parcel in) {
        this.id = in.readInt();
        this.nombreProvincia = in.readString();
        this.error = in.readString();
    }

    public static final Creator<ProvinciaResponse> CREATOR = new Creator<ProvinciaResponse>() {
        @Override
        public ProvinciaResponse createFromParcel(Parcel in) {
            return new ProvinciaResponse(in);
        }

        @Override
        public ProvinciaResponse[] newArray(int size) {
            return new ProvinciaResponse[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public ProvinciaResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public ProvinciaResponse setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
        return this;
    }
    @NonNull
    @Override
    public String toString() {
        return nombreProvincia;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DepartamentoResponse){
            ProvinciaResponse c = (ProvinciaResponse )obj;
            return c.getNombreProvincia().equals(nombreProvincia) && c.getId().equals(id);
        }
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombreProvincia);
        dest.writeString(this.error);

    }
}
