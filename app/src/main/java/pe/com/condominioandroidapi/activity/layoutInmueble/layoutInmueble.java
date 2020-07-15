package pe.com.condominioandroidapi.activity.layoutInmueble;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesFragment;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesViewModel;
import pe.com.condominioandroidapi.activity.Inmueble.adapter.InmuebleListAdapter;
import pe.com.condominioandroidapi.activity.documentList.documentList;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;
import pe.com.condominioandroidapi.entities.layoutResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class layoutInmueble extends BaseActivity {

    @BindView(R.id.rlCondominio)
    LinearLayout rlCondominio;
    @BindView(R.id.rlDepartamento)
    LinearLayout rlDepartamento;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.tvCondominio)
    TextView tvCondominio;
    @BindView(R.id.tvDocumentacion)
    TextView tvDocumentacion;
    @BindView(R.id.tvDepa)
    TextView tvDepa;
    @BindView(R.id.tvCodigo)
    TextView tvCodigo;
    @BindView(R.id.logo_proyecto)
    ImageView logo_proyecto;



    LayoutInmViewModel viewModel;
    private String  idInmueble;
    private String ID_LOGOPROYECTO;
    private String  codigo;
    private String idProyecto;
    private String cod_color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inm_fragment);
        ButterKnife.bind(this);
        initializeToolbar("");
        initializeObjects();
        initializeObservers();
        setupViews();
    }

    @Override
    public void initializeObjects() {
        viewModel = ViewModelProviders.of(this).get(LayoutInmViewModel.class);
        idInmueble = getIntent().getStringExtra(Constant.id_Inmueble);
        codigo = getIntent().getStringExtra(Constant.CODIGO_INMUEBLE);
        idProyecto = getIntent().getStringExtra(Constant.ID_PROYECTO);
        Log.d("obtener", idInmueble);
        Log.d("obtener", codigo);
        Log.d("obtener", idProyecto);


    }

    @Override
    public void initializeObservers() {

        viewModel.getlayoutListMutableLiveData().observeForever(inmueble -> {
            Log.d("obtener", inmueble.toString());
            tvInfo.setTextColor(Color.parseColor(inmueble.get(0).getColorHexadecimal()));
            tvCondominio.setTextColor(Color.parseColor(inmueble.get(0).getColorHexadecimal()));
            tvDocumentacion.setTextColor(Color.parseColor(inmueble.get(0).getColorHexadecimal()));
            tvDepa.setTextColor(Color.parseColor(inmueble.get(0).getColorHexadecimal()));
            tvCodigo.setBackgroundResource(R.drawable.circ_codi_inmu);
            tvCodigo.setPadding(40 , 5,40,5);
            GradientDrawable drawable = (GradientDrawable) tvCodigo.getBackground();
            drawable.setColor(Color.parseColor(inmueble.get(0).getColorHexadecimal()));
            drawable.setCornerRadius(25);
            cod_color = inmueble.get(0).getColorHexadecimal();
            tvCodigo.setText(codigo);

            String base64Image =inmueble.get(0).getArchivo();

            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            logo_proyecto.setImageBitmap(decodedByte);
            Constant.ID_LOGOEMPRESA = inmueble.get(0).getLogoArchivo();
            ID_LOGOPROYECTO = inmueble.get(0).getArchivo();

            initializeToolbar(Constant.ID_LOGOEMPRESA);


        });

    }

    @Override
    public void setupViews() {
    }

    @OnClick(R.id.rlCondominio)
    public void onClickBtnCondominio() {

        Intent intent = new Intent(this, documentList.class);
        intent.putExtra(Constant.ID_PROYECTO ,idProyecto);
        intent.putExtra(Constant.CODIGO_COLOR ,cod_color);
        intent.putExtra(Constant.CODIGO_INMUEBLE ,codigo);
        intent.putExtra(Constant.ID_OPCION ,"1");
        intent.putExtra(Constant.id_Inmueble ,idInmueble);
        intent.putExtra(Constant.ID_LOGOPROYECTO ,ID_LOGOPROYECTO);



        Log.d("idproye", idProyecto);


        startActivity(intent);
    }

    @OnClick(R.id.rlDepartamento)
    public void onClickBtnDepartamento() {

        Intent intent = new Intent(this, documentList.class);
        intent.putExtra(Constant.ID_PROYECTO ,idProyecto);
        intent.putExtra(Constant.CODIGO_COLOR ,cod_color);
        intent.putExtra(Constant.CODIGO_INMUEBLE ,codigo);
        intent.putExtra(Constant.ID_OPCION ,"2");
        intent.putExtra(Constant.id_Inmueble ,idInmueble);
        intent.putExtra(Constant.ID_LOGOPROYECTO ,ID_LOGOPROYECTO);


        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.requestLayout(Integer.parseInt(idInmueble));
        Log.d("obtener","obtener");

    }

}