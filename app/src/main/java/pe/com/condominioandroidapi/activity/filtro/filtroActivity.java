package pe.com.condominioandroidapi.activity.filtro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.detalle.detalleViewModel;
import pe.com.condominioandroidapi.activity.proyecto.ProyectoFragment;
import pe.com.condominioandroidapi.activity.proyecto.ProyectoViewModel;
import pe.com.condominioandroidapi.activity.proyecto.adapter.ProyectoAdapter;
import pe.com.condominioandroidapi.entities.DepartamentoResponse;
import pe.com.condominioandroidapi.entities.ProvinciaResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class filtroActivity extends BaseActivity {
    ProyectoViewModel viewModel;
    @BindView(R.id.spnProvincia) AppCompatSpinner spnProvincia;
    @BindView(R.id.spnDepartamento) AppCompatSpinner spnDepartamento;
    Integer nombreDepartamento;
    String nombreProvincia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_avanzado);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ProyectoViewModel.class);

        initializeObjects();
        initializeObservers();
        setupViews();

    }
    @Override
    public void initializeObjects() {
        viewModel.getDepartamentoListMutableLiveData().observeForever(new Observer<List<DepartamentoResponse>>() {
            @Override
            public void onChanged(List<DepartamentoResponse> proyecto) {
                setDataToDivisionSpinner(proyecto);

            }
        });
        viewModel.getProvinciaListMutableLiveData().observeForever(new Observer<List<ProvinciaResponse>>() {
            @Override
            public void onChanged(List<ProvinciaResponse> proyecto) {
                setDataToProvinciaSpinner(proyecto);

            }
        });

    }

    @Override
    public void initializeObservers() {

    }

    @Override
    public void setupViews() {

    }

    public void setDataToDivisionSpinner(List<DepartamentoResponse> departamento){
        if (this != null && departamento != null) {
            final ArrayAdapter<DepartamentoResponse> adapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departamento);
            spnDepartamento.setAdapter(adapter);
            spnDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    DepartamentoResponse response = (DepartamentoResponse) parent.getSelectedItem();
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.marcaTitle));

                    nombreDepartamento = response.getId();
                    if (!nombreDepartamento.equals("")) {
                        if (response.getNombreDepartamento().equals("")) {
                            spnProvincia.setEnabled(false);
                            nombreProvincia = "";
                           /* viewModel.requestSolicitudes(
                                    estadoSolicitud, loginResponse.getIdUsuario(),
                                    codigoEmpresa, division.getId());*/
                        }
                        else {
                            spnProvincia.setEnabled(true);
                            Constant.departamento = response.getNombreDepartamento();
                            /*  rvResultados.setAdapter(new ResultadosAdapter(new ArrayList<DashboardResponse>(), DashboardFragment.this));*/
                            viewModel.requestProvinciaList(response.getId());
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    public void setDataToProvinciaSpinner(List<ProvinciaResponse> provincia) {
        if (this != null && provincia != null) {
            ArrayAdapter<ProvinciaResponse> adapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provincia);
            spnProvincia.setAdapter(adapter);
            spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ProvinciaResponse provinciaResponse = (ProvinciaResponse) parent.getSelectedItem();
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.marcaTitle));
                    nombreProvincia = provinciaResponse.getNombreProvincia();
                    Constant.provincia = provinciaResponse.getNombreProvincia();
                    if (!nombreProvincia.equals("") && !nombreProvincia.equals(""))
                        Log.d("hola","hola");
                     /*   dashboardViewModel.requestSolicitudes(estadoSolicitud, loginResponse.getIdUsuario(),
                                codigoEmpresa, codigoDivision);*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.requestDepartamentoList();


    }
    @OnClick(R.id.btnFiltrar)
    public void onClickFiltrar()
    {
        finish();
    }
    @OnClick(R.id.btnCancelar)
    public void onClickCancelar()
    {
        Constant.departamento="";
        Constant.provincia="";
        finish();
    }
}
