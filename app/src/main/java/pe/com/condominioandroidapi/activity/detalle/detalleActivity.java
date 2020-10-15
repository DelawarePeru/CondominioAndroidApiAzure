package pe.com.condominioandroidapi.activity.detalle;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.detalle.fragment.presentacionFragment;
import pe.com.condominioandroidapi.activity.setting.SettingFragment;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class detalleActivity extends BaseActivity {

    private int RESOURCE_ID = 1;
    public String id_catalogo;
    public String id_inmueble;
    public String logo_empresa;
    public String logo_proyecto;
    public String perfil_proyecto;
    detalleViewModel viewModel;
  /*  @BindView(R.id.rvdetalleProyecto)
    RecyclerView rvdetalleProyecto;*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeObjects();
        setContentView(R.layout.detalle_proyecto);
        ButterKnife.bind(this);

        initializeToolbar(logo_empresa);
        initializeObservers();
        setupViews();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initializeObjects() {
        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
        Log.d("initializeObjects", "initializeObjects");
        logo_empresa = getIntent().getStringExtra(Constant.ID_LOGOEMPRESA);

        id_catalogo = getIntent().getStringExtra(Constant.ID_CATALOGO);
    }

   /*

        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
        Log.d("initializeObjects", "initializeObjects");

        id_catalogo = getIntent().getStringExtra(Constant.ID_CATALOGO);
        logo_proyecto = getIntent().getStringExtra(Constant.ID_LOGOPROYECTO);
        perfil_proyecto = getIntent().getStringExtra(Constant.ID_PERFILEMPRESA);
        Log.d("id_catalogox2", id_catalogo);



    }
*/
    @Override
    public void initializeObservers() {



    }

    @Override
    public void setupViews() {


    }



}
