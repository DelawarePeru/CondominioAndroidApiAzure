package pe.com.condominioandroidapi.activity.principal;

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
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletionService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesFragment;
import pe.com.condominioandroidapi.activity.filtro.filtroActivity;
import pe.com.condominioandroidapi.activity.layoutInmueble.layoutInmueble;
import pe.com.condominioandroidapi.activity.principal.adapter.PrincipalAdapter;
import pe.com.condominioandroidapi.activity.principal.listener.DetalleResultadosListener;
import pe.com.condominioandroidapi.activity.principal.listener.ResultadosListener;
import pe.com.condominioandroidapi.activity.vinculacion.VinculacionActivity;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.ProyectoResponse;
import pe.com.condominioandroidapi.entities.UserResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class PrincipalFragment extends BaseFragment implements ResultadosListener , DetalleResultadosListener {

    @BindView(R.id.rvResultadosInmueble)
    RecyclerView rvResultadosInmueble;
    @BindView(R.id.pgbCardView)
    ProgressBar pgbCardView;
    @BindView(R.id.etSearch)
    EditText etSearch;
    private List<InmuebleResponse> data;
    @BindView(R.id.ivFiltro)
    ImageView ivFiltro;
    UserResponse loginResponse;
    PrincipalViewModel   viewModel;




    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.activity_list_inmueble,
                container, false);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        viewModel= ViewModelProviders.of(this).get(PrincipalViewModel.class);
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
        ArrayList<InmuebleResponse> filter= new ArrayList<>();

        for( InmuebleResponse item: data)
        {
            if(item.getNombreDistrito().toLowerCase().contains(word.toLowerCase())
                    || item.getNombreProyecto().toLowerCase().contains(word.toLowerCase())
                    || item.getRazonSocial().toLowerCase().contains(word.toLowerCase()))
            {
                filter.add(item);
            }
        }
        rvResultadosInmueble.setAdapter(new PrincipalAdapter
                (filter,PrincipalFragment.this ));

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initializeObjects() {
        loginResponse = new UserResponse();
        if (getArguments() != null) {
            action = getArguments().getString("action");
            loginResponse = getArguments().getParcelable(Constant.KEY_USER_DATA);

        }

    }

    @Override
    public void initializeObservers() {
        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                showMessage(message);
            }
        });/*
        principalViewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                if (value != null)
                    pgbDetalleSolicitud.setVisibility(value);
            }
        });

        principalViewModel.getSolicitudes().observeForever(new Observer<List<PrincipalResponse>>() {
            @Override
            public void onChanged(@Nullable List<PrincipalResponse> principalResponse) {

                    rvResultadosInmueble.setAdapter(new PrincipalAdapter(principalResponse, PrincipalFragment.this));
            }
        });*/


        viewModel.getProListMutableLiveData().observeForever(new Observer<List<InmuebleResponse>>() {
            @Override
            public void onChanged(List<InmuebleResponse> proyecto) {
                data = proyecto;
                rvResultadosInmueble.setAdapter(new PrincipalAdapter
                        (proyecto,PrincipalFragment.this ));
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
    }
    /* principalViewModel.getDivisionesResult().observeForever(new Observer<List<DivisionResponse>>() {
         @Override
         public void onChanged(@Nullable List<DivisionResponse> divisiones) {
             setDataToDivisionSpinner(divisiones);
         }
     });
     principalViewModel.getEmpresasResult().observeForever(new Observer<List<EmpresaResponse>>() {
         @Override
         public void onChanged(@Nullable List<EmpresaResponse> empresas) {
             setDataToEmpresasSpinner(empresas);
         }
     });

 }*/
    public void setupViews(){
        ButterKnife.bind(this.getActivity());
        rvResultadosInmueble.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvResultadosInmueble.setAdapter(new PrincipalAdapter(new ArrayList<InmuebleResponse>(), this));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDetalleItemClickListener(InmuebleResponse dato, Boolean esUnico) {
        Log.d("seleccione" , "seleccione" );


        Intent intent = new Intent(getContext(), ListaInmueblesFragment.class)
                .putExtra(Constant.KEY_USER_DATA, loginResponse)
                .putExtra(Constant.ID_PROYECTO, String.valueOf(dato.getIdProyecto()))
                .putExtra("action", action);
        startActivity(intent);

    }


    public void onDashboardItemClickListener(ProyectoResponse dato) {
        Intent intent = new Intent();
        startActivity(intent);
    }

    private void showMessage(String m) {
        Snackbar.make(Objects.requireNonNull(getView()), m, Snackbar.LENGTH_LONG).show();
    }
    @OnClick(R.id.ivFiltro)
    public void onClickFiltro()
    {
        Intent intent = new Intent(getActivity(), VinculacionActivity.class);

        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
        if(Constant.get(Constant.EMAIL) != null) {
            viewModel.requestDahsboardList(Constant.get(Constant.EMAIL));
        }


    }

}
