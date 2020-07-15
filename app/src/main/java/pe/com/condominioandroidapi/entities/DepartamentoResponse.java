package pe.com.condominioandroidapi.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import pe.com.condominioandroidapi.util.basecomponent.BaseResponse;

public class DepartamentoResponse extends BaseResponse implements Parcelable {
    @SerializedName("id") private Integer id;
    @SerializedName("nombreDepartamento") private String nombreDepartamento;


    public DepartamentoResponse() {
    }

    protected DepartamentoResponse(Parcel in) {
        this.id = in.readInt();
        this.nombreDepartamento = in.readString();
        this.error = in.readString();
    }

    public static final Creator<DepartamentoResponse> CREATOR = new Creator<DepartamentoResponse>() {
        @Override
        public DepartamentoResponse createFromParcel(Parcel in) {
            return new DepartamentoResponse(in);
        }

        @Override
        public DepartamentoResponse[] newArray(int size) {
            return new DepartamentoResponse[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public DepartamentoResponse setId(Integer id) {
        this.id = id;
        return this;

    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public DepartamentoResponse setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        return this;

    }

    @NonNull
    @Override
    public String toString() {
        return nombreDepartamento;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DepartamentoResponse){
            DepartamentoResponse c = (DepartamentoResponse )obj;
            return c.getNombreDepartamento().equals(nombreDepartamento) && c.getId().equals(id);
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
        dest.writeString(this.nombreDepartamento);
        dest.writeString(this.error);

    }
}
