package pe.com.condominioandroidapi.activity.Inmueble;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.adapter.InmuebleListAdapter;
import pe.com.condominioandroidapi.activity.Inmueble.listener.InmuebleListener;
import pe.com.condominioandroidapi.activity.layoutInmueble.layoutInmueble;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class ListaInmueblesFragment extends  BaseActivity implements InmuebleListener {

    @BindView(R.id.btnCancelarEnvio)
    Button btnCancelarEnvio;
    @BindView(R.id.btnAceptarEnvio)
    Button btnAceptarEnvio;
    @BindView(R.id.rvInmuebles)
    RecyclerView rvInmuebles;
    @BindView(R.id.pgbDialog)
    ProgressBar pgbDialog;

    ListaInmueblesViewModel viewModel;
    public String idInmueble ;
    public String codigo ;
    public String idProyecto;
    public Boolean esUnico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_inmueble);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ListaInmueblesViewModel.class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        initializeObjects();
        initializeObservers();
        setupViews();

    }

    @Override
    public void initializeObjects() {

        idProyecto = getIntent().getStringExtra(Constant.ID_PROYECTO);


    }

    @Override
    public void setupViews() {
        rvInmuebles.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    public void initializeObservers() {


        viewModel.getInmuListMutableLiveData().observeForever(new Observer <List<InmuebleListResponse>>() {
            @Override
            public void onChanged(@Nullable List<InmuebleListResponse> inmueble) {
                rvInmuebles.setAdapter(new InmuebleListAdapter
                        (inmueble, ListaInmueblesFragment.this ));
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                if(inmueble.size()>1)
                    esUnico = false;
                esUnico=true;

            }
        });

        viewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                if (value != null)
                pgbDialog.setVisibility(value);

            }
        });

    }
    @OnClick(R.id.btnCancelarEnvio)
    public void onClickBtnCerrar() {
        finish();
    }

    @OnClick(R.id.btnAceptarEnvio)
    public void onClickBtnOk() {

        if(idInmueble == null)
        {
            Toast.makeText(this,"Se debe seleccionar un inmueble", Toast.LENGTH_LONG).show();


        }else {
            try {
                Intent intent = new Intent(this, layoutInmueble.class);
                intent.putExtra(Constant.id_Inmueble, idInmueble);
                intent.putExtra(Constant.CODIGO_INMUEBLE, codigo);
                intent.putExtra(Constant.ID_PROYECTO, idProyecto);
                startActivity(intent);

                finish();
            }
            catch (Exception e)
            {

            }
        }
    }

    @Override
    public void onDocumentoClickListener (InmuebleListResponse dato) {

        idInmueble = String.valueOf(dato.getIdInmueble());
        codigo = String.valueOf(dato.getCodigoInmueble());
        idProyecto = String.valueOf(dato.getIdProyecto());
        Log.d("datoidInmueble", String.valueOf(dato.getIdInmueble()));
        Log.d("datocodigo", String.valueOf(dato.getCodigoInmueble()));
        Log.d("datoidProyecto", String.valueOf(dato.getIdProyecto()));
    }

    /*
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            initializeView();
        }

        @Override
        public void initializeObjects() {

        }
        public void setupViews(){
            ButterKnife.bind(this.getActivity());
            rvInmuebles.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            rvInmuebles.setAdapter(new InmuebleListAdapter(new ArrayList<InmuebleListResponse>(), this));

        }
        @Override
        public void initializeObservers() {

            viewModel.getInmuListMutableLiveData().observeForever(new Observer <List<InmuebleListResponse>>() {
                @Override
                public void onChanged(@Nullable List<InmuebleListResponse> inmueble) {
                    rvInmuebles.setAdapter(new InmuebleListAdapter
                            (inmueble, ListaInmueblesFragment.this ));
                }
            });

        }

        @Override
        public void onDetalleItemClickListener(InmuebleResponse dato) {
            Log.d("seleccione" , "seleccione" );



        }*/
    @Override
    public void onResume() {
        super.onResume();

        viewModel.requestListInmu(Integer.parseInt(Objects.requireNonNull(Constant.get(Constant.ID_PERSONA)))
                ,Integer.parseInt(idProyecto));


    }


}
