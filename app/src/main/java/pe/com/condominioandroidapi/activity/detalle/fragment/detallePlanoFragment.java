package pe.com.condominioandroidapi.activity.detalle.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.ListaInmueblesFragment;
import pe.com.condominioandroidapi.activity.detalle.detalleActivity;
import pe.com.condominioandroidapi.activity.detalle.detalleViewModel;
import pe.com.condominioandroidapi.activity.detalle.formularioActivity;
import pe.com.condominioandroidapi.activity.detalle.lugaresActivity;
import pe.com.condominioandroidapi.entities.GaleriaResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.ViewPagerAdapter;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class detallePlanoFragment extends BaseFragment {
    detalleViewModel viewModel;
    @BindView(R.id.ivPlano)
    ViewPager ivPlano;
    @BindView(R.id.txtTechada)
    TextView txtTechada;
    @BindView(R.id.txtLibre)
    TextView txtLibre;
    @BindView(R.id.txtTotal)
    TextView txtTotal;
    @BindView(R.id.txtTipoEst)
    TextView txtTipoEst;
    @BindView(R.id.txtDoble)
    TextView txtDoble;
    @BindView(R.id.txtDeposito)
    TextView txtDeposito;
    @BindView(R.id.txtPrecioTotal)
    TextView txtPrecioTotal;
    @BindView(R.id.lydetallePlano)
    LinearLayout lydetallePlano;
    private String id_catalogo;
    @BindView(R.id.tvConstructora)
    TextView tvConstructora;
    @BindView(R.id.tvCorreoVenta)
    TextView tvCorreoVenta;
    @BindView(R.id.tvTelefonoFijo)
    TextView tvTelefonoFijo;
    @BindView(R.id.tvTelefonoMovil)
    TextView tvTelefonoMovil;
    String correo;
    String telefonoMov;
    String telefonFi;
    private String id_inmueble;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
        View view =  inflater.inflate(R.layout.detalle_plano,
                container, false);
        id_inmueble = getArguments().getString("id_inmueble");

        ButterKnife.bind(this, view);
        initializeObjects();
        initializeObservers();

        return view;
    }
    @Override
    public void initializeObjects() {

        id_catalogo = ((detalleActivity) this.getActivity()).id_catalogo;


    }

    @Override
    public void initializeObservers() {
        viewModel.getPlanoMutableLiveData().observeForever(detalle -> {
           /* byte[] decodedString = Base64.decode(detalle.get(0).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivPlano.setImageBitmap(decodedByte);*/
            List<GaleriaResponse>  lista = new ArrayList<>();;
            GaleriaResponse galeriaResponse;

            for(int i =0; i<detalle.size() ;i++)
            {   galeriaResponse = new  GaleriaResponse();
                galeriaResponse.setImage(detalle.get(i).getImage());
                lista.add(galeriaResponse);
            }
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getActivity(), lista);
            ivPlano.setAdapter(viewPagerAdapter);
            txtTechada.setText(detalle.get(0).getAreaTechada().toString());
            txtLibre.setText(detalle.get(0).getAreaLibreVendible().toString());
            float Suma = detalle.get(0).getAreaLibreVendible()+
                    detalle.get(0).getAreaTechada();
            txtTotal.setText(Float.toString(Suma));
            txtTipoEst.setText("Estac. " + detalle.get(0).getNombre());

            txtDoble.setText(detalle.get(0).getPrecioEstacionamiento().toString());
            txtDeposito.setText(detalle.get(0).getPrecioDeposito().toString());
            txtPrecioTotal.setText(detalle.get(0).getPrecioTotal().toString());
            lydetallePlano.setVisibility(View.VISIBLE);
           // ivPlano.setScaleType(ImageView.ScaleType.CENTER_CROP);

        });

        viewModel.getContactoMutableLiveData().observeForever(detalle -> {

            tvConstructora.setText(detalle.get(0).getRazonSocial());
            tvCorreoVenta.setText(detalle.get(0).getCorreo());
            correo = detalle.get(0).getCorreo();
            tvTelefonoFijo.setText(detalle.get(0).getTelefonoFijo());
            telefonFi = detalle.get(0).getTelefonoFijo();
            tvTelefonoMovil.setText(detalle.get(0).getTelefonoMovil());
            telefonoMov = detalle.get(0).getTelefonoMovil();

        });

    }

    @Override
    public void setupViews() {

    }
    @OnClick(R.id.ibSend)
    public void onClickBtnEmail()
    {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { correo });
        emailIntent.putExtra(Intent.EXTRA_CC, "");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Información");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Buenas ... ");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
        };
    }
    @OnClick(R.id.imWhatsapp)
    public  void onClickBtnWhatsapp()
    {
        try {
            String headerReceiver = "Deseo información";
            String bodyMessageFormal = "";
            String whatsappContain = headerReceiver + bodyMessageFormal;
            String trimToNumner = "+51"+telefonoMov;
            Intent intent = new Intent ( Intent.ACTION_VIEW );
            intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + "/?text=" + whatsappContain ) );
            startActivity ( intent );
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
    @OnClick(R.id.ibTelephone)
    public void onClickBtnOk() {
        try{
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:01"+telefonFi));
            startActivity(callIntent);
        }
        catch (Exception e)
        {

        }


    }
    @OnClick(R.id.btnSolicitar)
    public void onClickBtnSolicitar() {
        Intent intent = new Intent(getContext(), formularioActivity.class)
                .putExtra("action", action);
        startActivity(intent);


    }
    @Override
    public void onResume()
    {
        super.onResume();
        viewModel.requestPlano(Integer.parseInt(id_inmueble));
        viewModel.requestContacto(Integer.parseInt(id_catalogo));

    }
}
