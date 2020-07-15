package pe.com.condominioandroidapi.activity.detalle.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.detalle.detalleActivity;
import pe.com.condominioandroidapi.activity.detalle.detalleViewModel;
import pe.com.condominioandroidapi.activity.detalle.lugaresActivity;
import pe.com.condominioandroidapi.activity.layoutInmueble.layoutInmueble;
import pe.com.condominioandroidapi.entities.LugarResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class lugarFragment extends BaseFragment implements  OnMapReadyCallback  {

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    //



    List<LugarResponse> listadelugares;
    detalleViewModel viewModel;
    private String id_catalogo;
    String coordX="";
    String coordY="";
    @BindView(R.id.tvText)
    TextView tvText;
    @BindView(R.id.btn1)
    ImageButton btn1;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
        id_catalogo = ((detalleActivity)this.getActivity()).id_catalogo;
        View view =  inflater.inflate(R.layout.fragment_ubicacion,
                container, false);
        ButterKnife.bind(this, view);

       initializeObservers();
        onResume();
        initializeObservers();

        return view;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapView mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);

    }
    @Override
    public void initializeObjects() {

    }

    @Override
    public void initializeObservers() {
       viewModel.getLugarMutableLiveData().observeForever(detalle -> {

        tvText.setText(detalle.get(0).getIntroduccion());
        coordX = detalle.get(0).getCoordX();
        coordY = detalle.get(0).getCoordY();
        listadelugares = detalle;

        });

    }

    @Override
    public void setupViews() {

    }
    @Override
    public void onResume()
    {
        super.onResume();
       viewModel.requestLugar(Integer.parseInt(id_catalogo));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.btn1)
    public void onClickBtnOk() {

        Intent intent = new Intent(getActivity(), lugaresActivity.class);
        Integer idLugar = listadelugares.get(0).getIdTipoLugar();
        intent.putExtra(Constant.ID_LUGAR, idLugar.toString());
        intent.putExtra(Constant.ID_CATALOGO, id_catalogo);

        startActivity(intent);
    }
    @OnClick(R.id.btn2)
    public void onClickBtnOk2() {

        Intent intent = new Intent(getActivity(), lugaresActivity.class);
        Integer idLugar = listadelugares.get(1).getIdTipoLugar();
        intent.putExtra(Constant.ID_LUGAR, idLugar.toString());
        intent.putExtra(Constant.ID_CATALOGO, id_catalogo);

        startActivity(intent);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("coordenadas",coordX.toString());
        Log.d("coordenadas", coordY.toString());

        LatLng marker = new LatLng(-12.129709, -77.0093276);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
        googleMap.addMarker(new MarkerOptions().title("Testing").position(new LatLng(-12.129709, -77.0093276)));
        googleMap.addMarker(new MarkerOptions().title("Hello Google Maps!").position(marker));

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true); // This is how I had implemented the setMyLocationEnabled method
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

    }
}
