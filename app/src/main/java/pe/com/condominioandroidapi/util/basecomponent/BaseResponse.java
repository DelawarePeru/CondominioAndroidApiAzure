package pe.com.condominioandroidapi.util.basecomponent;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("Error")
    public String error = "Error al intentar o";

    public BaseResponse setError(String error) {
        this.error = error;
        return this;
    }

    public String getError() {
        return error;
    }
}
