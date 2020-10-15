package pe.com.condominioandroidapi.activity.detalle;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.concurrent.Executor;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class formularioActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks {
    @BindView(R.id.cbCaptcha)
    CheckBox cbCaptcha;
    @BindView(R.id.etNombre)
    EditText etNombre;
    @BindView(R.id.etCorreo)
    EditText etCorreo;
    @BindView(R.id.etTelefono)
    EditText etTelefono;
    @BindView(R.id.etMessage)
    EditText etMessage;
    GoogleApiClient googleApiClient;
    String SiteKey = "6LdSe6kZAAAAACo4ikn2fcfUolJezXFK8-KOsp-1";
    Boolean isSend= false;
    private boolean isNombre = true;
    private boolean isEmail = true;
    private boolean isTelefono = true;
    private boolean isMensaje = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity);
        ButterKnife.bind(this);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(formularioActivity.this)
                .build();
        googleApiClient.connect();
        cbCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCaptcha.isChecked())
                {

                    SafetyNet.getClient(formularioActivity.this).verifyWithRecaptcha(SiteKey)
                            .addOnSuccessListener(formularioActivity.this, response -> {
                                if (!response.getTokenResult().isEmpty()) {
                                    isSend = true;
                                }
                            })
                            .addOnFailureListener(formularioActivity.this, e -> {
                                if (e instanceof ApiException) {
                                    ApiException apiException = (ApiException) e;
                                    Toast.makeText(formularioActivity.this,
                                            apiException.getMessage() + apiException.getStatusCode(),Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(formularioActivity.this,
                                            "MAL",Toast.LENGTH_SHORT).show();
                                }
                            });
                }else
                {
                    cbCaptcha.setTextColor(Color.BLUE);
                }
            }
        });
        initializeObjects();
        initializeObservers();
        setupViews();

    }
    @Override
    public void initializeObjects() {
        if(Constant.get(Constant.NOMBRE)!="")
        {
            etNombre.setText(Constant.get(Constant.NOMBRE));
        }
        if(Constant.get(Constant.TELEFONO)!="")
        {
            etTelefono.setText(Constant.get(Constant.TELEFONO));

        }
        if(Constant.get(Constant.EMAIL)!="")
        {
            etCorreo.setText(Constant.get(Constant.EMAIL));

        }

    }

    @Override
    public void initializeObservers() {

    }

    @Override
    public void setupViews() {

    }
    @OnClick(R.id.btnEnviarFormulario)
    public void onClickBtnOk() {
        ValidarDatos();
        if(!isSend )
        {
            Toast.makeText(this,"Validar captcha.",Toast.LENGTH_SHORT).show();
        }
        else
        if(isEmail && isMensaje&& isNombre && isTelefono)
        {
            finish();

        }
        else {
            Toast.makeText(this,"Completar los datos.",Toast.LENGTH_SHORT).show();

        }


    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private void ValidarDatos() {
        String nombre = etNombre.getText().toString();
        String correo = etCorreo.getText().toString();
        String telefono = etTelefono.getText().toString();
        String mensaje = etMessage.getText().toString();

        if(nombre.length() <1) {

            isNombre = false;
            etNombre.setBackgroundResource(R.drawable.edittext_border_error);

        }
        else{
            etNombre.setBackgroundResource(R.drawable.edittext_border_setting);
            isNombre = true;
        }


        if(correo.length() <1 ) {
            isEmail = false;
            etCorreo.setBackgroundResource(R.drawable.edittext_border_error);

        }else if(!validarEmail(correo))
        {    //etCorreo.setError("Email no válido");

            isEmail = false;
            etCorreo.setBackgroundResource(R.drawable.edittext_border_error);

        }else{
            etCorreo.setBackgroundResource(R.drawable.edittext_border_setting);
            isEmail = true;
        }

        if(telefono.length() <9) {
            isTelefono = false;
            etTelefono.setBackgroundResource(R.drawable.edittext_border_error);
        }else {
            etTelefono.setBackgroundResource(R.drawable.edittext_border_setting);
            isTelefono = true;
        }

        if(mensaje.length() <1) {
            isMensaje = false;
            etMessage.setBackgroundResource(R.drawable.edittext_border_error);
        }else{
            etMessage.setBackgroundResource(R.drawable.edittext_border_setting);
            isMensaje = true;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
