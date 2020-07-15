package pe.com.condominioandroidapi.activity.splash;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

import pe.com.condominioandroidapi.activity.home.HomeActivity;
import pe.com.condominioandroidapi.activity.login.LoginActivity;
import pe.com.condominioandroidapi.util.Constant;

public class SplashActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constant.initSharedPref(this);
        if (!Objects.equals(Constant.get(Constant.KEY_ACCESS_TOKEN), ""))
            startActivity(new Intent(this, HomeActivity.class));
        else
            startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
