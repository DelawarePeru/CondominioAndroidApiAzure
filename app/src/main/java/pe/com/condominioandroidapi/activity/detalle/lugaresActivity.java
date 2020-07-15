package pe.com.condominioandroidapi.activity.detalle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.activity.detalle.adapter.DetalleAdapter;
import pe.com.condominioandroidapi.entities.LugarResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class lugaresActivity extends BaseActivity {
    @BindView(R.id.ivclose)
    ImageView ivclose;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;
    @BindView(R.id.rvInmuebles)
    RecyclerView rvInmuebles;
    String ID_LUGAR;
    private String id_catalogo;
    detalleViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_lugares);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);

        initializeObjects();
        initializeObservers();
        setupViews();

    }
    @OnClick(R.id.ivclose)
    public void onClickClose() {

        finish();
    }



    @Override
    public void initializeObjects() {
         ID_LUGAR = getIntent().getStringExtra(Constant.ID_LUGAR);
        id_catalogo = getIntent().getStringExtra(Constant.ID_CATALOGO);


    }

    @Override
    public void initializeObservers() {

        viewModel.getLugarMutableLiveData().observeForever(detalle -> {

            for(int i =0; i<detalle.size() ;i++)
            {
                if(detalle.get(i).getIdTipoLugar() == Integer.parseInt(ID_LUGAR))
                {
                    tvTitulo.setText(detalle.get(i).getNombreTipo());
                    rvInmuebles.setAdapter(new DetalleAdapter
                            (detalle.get(i).getListaDeLugares() ));
                }
            }





        });

    }

    @Override
    public void setupViews() {
        rvInmuebles.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    public void onResume()
    {
        super.onResume();
        viewModel.requestLugar(Integer.parseInt(id_catalogo));
    }
}
