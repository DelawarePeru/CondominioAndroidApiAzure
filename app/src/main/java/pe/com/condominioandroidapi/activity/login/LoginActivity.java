package pe.com.condominioandroidapi.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import butterknife.BindView;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.activity.principal.PrincipalFragment;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class LoginActivity extends BaseFragment {
    LoginViewModel loginViewModel;
    @BindView(R.id.pgbLogin) ProgressBar pgbLogin;
    @BindView(R.id.etUsuario) EditText etUsuario;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.btnIngresar) Button btnIngresar;
    @BindView(R.id.btnRegistrar) Button btnRegistrar;
    @BindView(R.id.tvNuevoRegistro) TextView tvNuevoRegistro;
    @BindView(R.id.titleLogin) TextView titleLogin;
    @BindView(R.id.tvCancelar) TextView tvCancelar;
    @BindView(R.id.texto) TextView texto;
    @BindView(R.id.btnEnviar) Button btnEnviar;

    @BindView(R.id.tvOlvidarContraseña) TextView tvOlvidarContraseña;
    @BindView(R.id.etNombre) EditText etNombre;
    @BindView(R.id.etApellido) EditText etApellido;
    @BindView(R.id.etTelefono) EditText etTelefono;
    @BindView(R.id.etPasswordRepetir) EditText etPasswordRepetir;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_login,
                container, false);
        ButterKnife.bind(this, view);
        initializeView();
        return view;

    }
    @Override
    public void initializeObjects() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void initializeObservers() {

       loginViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                enableViews(true);
                showMessage(message);
                if (message == "Ingresar usuario") {
                    etUsuario.setBackgroundResource(R.drawable.edittext_border_error);

                } else if (message == "Ingresar contraseña")
                {
                    etPassword.setBackgroundResource(R.drawable.edittext_border_error);
                }
                else{
                    etUsuario.setBackgroundResource(R.drawable.edittext_border_error);
                    etPassword.setBackgroundResource(R.drawable.edittext_border_error);

                }
            loginViewModel.onRetrieveDataFinish();
            }
        });

        loginViewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                if (value != null)
                    pgbLogin.setVisibility(value);
            }
        });

        loginViewModel.getLoginSuccess().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String loginResponse) {
                        if (loginResponse != null) {
                            goToHomeActivity(loginResponse);
                        }
                    }
                });
        loginViewModel.getUsersSuccess().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String loginResponse) {
                        loginViewModel.userRegistroPersona(
                                Constant.get(Constant.ID_USER),
                                etNombre.getText().toString(),
                                etApellido.getText().toString(),
                                etTelefono.getText().toString(),
                                false);
                    }
                });
    }

    @Override
    public void setupViews() {
    }
    @OnClick(R.id.btnIngresar)
    void onClickBtnIngresar() {
        enableViews(false);

       loginViewModel.userLogin(
                etUsuario.getText().toString(), etPassword.getText().toString(), false);
        /*
        loginViewModel.userLogin(
                "mirandola_ale19@hotmail.com", "Claudia01@", false);
*/
    }
    @OnClick(R.id.btnRegistrar)
    void onClickRegistrar() {

        enableViewsRegistro(false);

       loginViewModel.userRegistro(
                etUsuario.getText().toString(),
                etPassword.getText().toString(),
                etNombre.getText().toString(),
                etApellido.getText().toString(),
                etTelefono.getText().toString(),
                etPasswordRepetir.getText().toString(),
                false);

/*
        loginViewModel.userRegistro(
                "walter_12m@hotmail.com",
                "Claudia01@",
                "Elena",
                "Miranda Chillitupa",
                "987654321",
                "Claudia01@",
                false);
*/
    }
    @OnClick(R.id.btnEnviar)
    void onClickbtnEnviar() {

        loginViewModel.recuperarContrasena(etUsuario.getText().toString());

    }
    @OnClick(R.id.tvOlvidarContraseña)
    void onClickOlvidarContraseña() {
        titleLogin.setText("Recuperar Contraseña");
        texto.setVisibility(View.VISIBLE);
        btnEnviar.setVisibility(View.VISIBLE);
        etPassword.setVisibility(View.GONE);
        btnIngresar.setVisibility(View.GONE);
        tvNuevoRegistro.setVisibility(View.GONE);
        tvOlvidarContraseña.setVisibility(View.GONE);
        tvCancelar.setVisibility(View.VISIBLE);


    }
    @OnClick(R.id.tvNuevoRegistro)
    void onClickNuevoRegistro() {
        MostrarRegistro();


    }
    @OnClick(R.id.tvCancelar)
    void onClicktvCancelar() {
        OcultarRegistro();
        texto.setVisibility(View.GONE);
        btnEnviar.setVisibility(View.GONE);
        btnIngresar.setVisibility(View.VISIBLE);
        etPassword.setVisibility(View.VISIBLE);
        titleLogin.setText("Bienvenido");
        tvNuevoRegistro.setVisibility(View.VISIBLE);


    }
    void MostrarRegistro()
    {
        etNombre.setVisibility(View.VISIBLE);
        etApellido.setVisibility(View.VISIBLE);
        etTelefono.setVisibility(View.VISIBLE);
        etPasswordRepetir.setVisibility(View.VISIBLE);
        tvNuevoRegistro.setVisibility(View.GONE);
        tvOlvidarContraseña.setVisibility(View.GONE);
        btnIngresar.setVisibility(View.GONE);
        btnRegistrar.setVisibility(View.VISIBLE);
        tvCancelar.setVisibility(View.VISIBLE);
    }
    void OcultarRegistro()
    {
        etNombre.setVisibility(View.GONE);
        etApellido.setVisibility(View.GONE);
        etTelefono.setVisibility(View.GONE);
        etPasswordRepetir.setVisibility(View.GONE);
        tvNuevoRegistro.setVisibility(View.VISIBLE);
        tvOlvidarContraseña.setVisibility(View.VISIBLE);
        btnIngresar.setVisibility(View.VISIBLE);
        btnRegistrar.setVisibility(View.GONE);
        tvCancelar.setVisibility(View.GONE);
    }
    private void enableViews(boolean enable) {
        etPassword.setEnabled(enable);
        etUsuario.setEnabled(enable);
        btnIngresar.setEnabled(enable);
    }
    private void enableViewsRegistro(boolean enable) {
        etPassword.setEnabled(enable);
        etUsuario.setEnabled(enable);
        etPasswordRepetir.setEnabled(enable);
        etTelefono.setEnabled(enable);
        etApellido.setEnabled(enable);
        etNombre.setEnabled(enable);
        btnRegistrar.setEnabled(enable);
    }
    private void showMessage(String m) {
        Snackbar.make(etPassword, m, Snackbar.LENGTH_LONG).show();
    }
    private void goToHomeActivity(String loginResponse) {
    //UserResponse loginResponse
        Type type = new TypeToken<UserResponse>(){}.getType();
       // String jsonData = new Gson().toJson(loginResponse, type);
      //  Constant.set(Constant.KEY_USER_DATA, jsonData);
       /* Intent intent = new Intent(LoginActivity.this,
                PrincipalFragment.class);*/

        PrincipalFragment fragment2 = new PrincipalFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment2);
        fragmentTransaction.commit();
        startActivity(getActivity().getIntent());
        getActivity().finish();
       /* startActivity(intent);
        finish();
        */
    }

}
