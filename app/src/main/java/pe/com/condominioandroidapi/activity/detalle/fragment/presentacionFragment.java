package pe.com.condominioandroidapi.activity.detalle.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.detalle.adapter.DetalleAdapter;
import pe.com.condominioandroidapi.activity.detalle.detalleActivity;
import pe.com.condominioandroidapi.activity.detalle.detalleViewModel;
import pe.com.condominioandroidapi.activity.principal.PrincipalViewModel;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class presentacionFragment extends BaseFragment {

    private String id_catalogo;
    private String logo_empresa;
    private String logo_proyecto;
    private String perfil_proyecto;
    detalleViewModel viewModel;
    @BindView(R.id.tvOver)
    TextView tvOver;

    @BindView(R.id.ivPerfilProyecto)
    ImageView ivPerfilProyecto;

    @BindView(R.id.ivlogoProyecto)
    ImageView ivlogoProyecto;

    @BindView(R.id.pgbDialog)
    ProgressBar pgbDialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
        id_catalogo = ((detalleActivity)this.getActivity()).id_catalogo;
       /* logo_empresa = ((detalleActivity)this.getActivity()).logo_empresa;
        logo_proyecto = ((detalleActivity)this.getActivity()).logo_proyecto;
        perfil_proyecto = ((detalleActivity)this.getActivity()).perfil_proyecto;*/

        View view =  inflater.inflate(R.layout.fragment_presentacion,
                container, false);
        ButterKnife.bind(this, view);

       initializeObservers();

        return view;

    }
    @Override
    public void initializeObjects() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initializeObservers() {
        viewModel.getDetalleMutableLiveData().observeForever(detalle -> {
            Log.d("coordenadaX", detalle.get(0).getCoordX());
            Log.d("coordenadaX", detalle.get(0).getCoordY());
            Constant.set(Constant.COORDENADA_X,detalle.get(0).getCoordX());
            Constant.set(Constant.COORDENADA_Y,detalle.get(0).getCoordY());

            tvOver.setText(detalle.get(0).getDescripcion());

                byte[] decodedString = Base64.decode(detalle.get(0).getPerfilProyecto(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ivPerfilProyecto.setImageBitmap(decodedByte);
                ivPerfilProyecto.setScaleType(ImageView.ScaleType.CENTER_CROP);



                byte[] decodedlogoEmpresa = Base64.decode(detalle.get(0).getLogoProyecto(), Base64.DEFAULT);
                Bitmap decodedBytelogoEmpresa = BitmapFactory.decodeByteArray(decodedlogoEmpresa, 0, decodedlogoEmpresa.length);
                ivlogoProyecto.setImageBitmap(decodedBytelogoEmpresa);


        });

        viewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                if (value != null)
                    pgbDialog.setVisibility(value);
            }
        });

    }

    @Override
    public void setupViews() {

    }
    @Override
    public void onResume()
    {
        super.onResume();
       viewModel.requestDetalle(Integer.parseInt(id_catalogo));
    }
}
