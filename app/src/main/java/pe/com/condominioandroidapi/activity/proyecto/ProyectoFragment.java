package pe.com.condominioandroidapi.activity.proyecto;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesFragment;
import pe.com.condominioandroidapi.activity.detalle.detalleActivity;
import pe.com.condominioandroidapi.activity.detalle.lugaresActivity;
import pe.com.condominioandroidapi.activity.filtro.filtroActivity;
import pe.com.condominioandroidapi.activity.principal.PrincipalFragment;
import pe.com.condominioandroidapi.activity.principal.PrincipalViewModel;
import pe.com.condominioandroidapi.activity.principal.adapter.PrincipalAdapter;
import pe.com.condominioandroidapi.activity.proyecto.listener.DetalleResultadosListener;
import pe.com.condominioandroidapi.activity.proyecto.listener.ResultadosListener;
import pe.com.condominioandroidapi.activity.proyecto.adapter.ProyectoAdapter;
import pe.com.condominioandroidapi.activity.proyecto.listener.ResultadosListener;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.ProyectoResponse;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class ProyectoFragment extends BaseFragment implements ResultadosListener, DetalleResultadosListener {
    ProyectoViewModel viewModel;
    @BindView(R.id.rvResultadosInmueble)
    RecyclerView rvResultadosInmueble;
    @BindView(R.id.pgbCardView)
    ProgressBar pgbCardView;
    @BindView(R.id.etSearch)
    EditText etSearch;
    private List<VentaResponse> data;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.activity_list_inmueble,
                container, false);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initializeObjects();
        initializeObservers();
         etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;

    }
    private void filter(String word)
    {
        ArrayList<VentaResponse> filter= new ArrayList<>();

        for( VentaResponse item: data)
        {
            if(item.getNombreDistrito().toLowerCase().contains(word.toLowerCase())
                    || item.getNombreProyecto().toLowerCase().contains(word.toLowerCase())
                    || item.getRazonSocial().toLowerCase().contains(word.toLowerCase()))
            {
                filter.add(item);
            }
        }
        rvResultadosInmueble.setAdapter(new ProyectoAdapter
                (filter,ProyectoFragment.this ));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    @Override
    public void initializeObjects() {
        viewModel = ViewModelProviders.of(this).get(ProyectoViewModel.class);
       /* loginResponse = new UserResponse();
        if (getArguments() != null) {
            action = getArguments().getString("action");
            loginResponse = getArguments().getParcelable(Constant.KEY_USER_DATA);

        }*/

    }

    @Override
    public void initializeObservers() {
        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                showMessage(message);
            }
        });
        viewModel.getProyectListMutableLiveData().observeForever(new Observer<List<VentaResponse>>() {
            @Override
            public void onChanged(List<VentaResponse> proyecto) {
                data = proyecto;

                rvResultadosInmueble.setAdapter(new ProyectoAdapter
                        (proyecto, ProyectoFragment.this ));
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
        });

        viewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                // Desde el ViewModel recibimos solo dos tipos de valores 1 o 0 (View.VISIBLE o View.GONE) que sirven para mostrar o esconder un widget
                if (value != null)
                    pgbCardView.setVisibility(value);

            }
        });
        /*viewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                // Desde el ViewModel recibimos solo dos tipos de valores 1 o 0 (View.VISIBLE o View.GONE) que sirven para mostrar o esconder un widget
                if (value != null)
                    pgbCardView.setVisibility(value);
            }
        });*/
    }

    public void setupViews(){
        ButterKnife.bind(this.getActivity());
        rvResultadosInmueble.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvResultadosInmueble.setAdapter(new ProyectoAdapter(new ArrayList<VentaResponse>(), this));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onDetalleItemClickListener(VentaResponse dato) {
        Log.d("id_catalogox1", "id_catalogox1");
        Constant.set(Constant.COORDENADA_X,dato.getCoordX());
        Constant.set(Constant.COORDENADA_Y,dato.getCoordY());
        Intent intent = new Intent(getContext(), detalleActivity.class)
                .putExtra(Constant.ID_CATALOGO, String.valueOf(dato.getIdCatalogo()))
                .putExtra(Constant.ID_LOGOEMPRESA, String.valueOf(dato.getLogoEmpresa()))
                /* .putExtra(Constant.ID_LOGOPROYECTO, String.valueOf(dato.getLogoProyecto()))
                 .putExtra(Constant.ID_PERFILEMPRESA, String.valueOf(dato.getPerfilProyecto()))*/
                .putExtra("action", action);
        Log.d("id_catalogox2", "id_catalogox2");

        startActivity(intent);

    }
    public void onDashboardItemClickListener(VentaResponse dato) {
        Intent intent = new Intent();
        startActivity(intent);
    }
    private void showMessage(String m) {
        Snackbar.make(Objects.requireNonNull(getView()), m, Snackbar.LENGTH_LONG).show();
    }
    @Override
    public void onResume() {
        super.onResume();

      viewModel.requestProyectList(Constant.departamento,Constant.provincia);


    }
    @OnClick(R.id.ivFiltro)
    public void onClickFiltro()
    {
        Intent intent = new Intent(getActivity(), filtroActivity.class);

        startActivity(intent);
    }



}
