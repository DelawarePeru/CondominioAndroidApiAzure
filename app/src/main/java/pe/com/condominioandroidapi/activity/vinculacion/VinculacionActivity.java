package pe.com.condominioandroidapi.activity.vinculacion;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.activity.proyecto.ProyectoViewModel;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class VinculacionActivity  extends BaseActivity{

    ProyectoViewModel viewModel;
    @BindView(R.id.etNombre)
    EditText etNombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_pop_vinculacion);
        ButterKnife.bind(this);
      //  viewModel = ViewModelProviders.of(this).get(ListaInmueblesViewModel.class);

        initializeObjects();
        initializeObservers();
        setupViews();

    }


    @Override
    public void initializeObjects() {
        viewModel = ViewModelProviders.of(this).get(ProyectoViewModel.class);


    }
    @Override
    public void initializeObservers() {

    }

    @Override
    public void setupViews() {

    }

    @OnClick(R.id.btnCancelarEnvio)
    public void onClickFiltrar()
    {
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.btnAceptarEnvio)
    public void onClickCancelar()
    {
        String codigo = etNombre.getText().toString();

        if(codigo.length()>0) {
            viewModel.requestVincular(Constant.get(Constant.EMAIL), codigo);
        }else
        {
            etNombre.setBackgroundResource(R.drawable.edittext_border_error);
        }

        finish();
    }
}
