package pe.com.condominioandroidapi.entities;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("usuario")
    private int usuario;

    @SerializedName("password")
    private int password;

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
