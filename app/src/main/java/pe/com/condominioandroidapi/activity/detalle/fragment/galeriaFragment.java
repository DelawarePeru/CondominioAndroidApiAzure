package pe.com.condominioandroidapi.activity.detalle.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.detalle.detalleActivity;
import pe.com.condominioandroidapi.activity.detalle.detalleViewModel;
import pe.com.condominioandroidapi.entities.GaleriaResponse;
import pe.com.condominioandroidapi.util.ViewPagerAdapter;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class galeriaFragment extends BaseFragment {

    ViewPager vpPhoto;
    detalleViewModel viewModel;
    List<GaleriaResponse> galeria;
    private String id_catalogo;    @BindView(R.id.tvTitle)
    TextView tvTitle;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
       id_catalogo = ((detalleActivity)this.getActivity()).id_catalogo;
        View view =  inflater.inflate(R.layout.fragment_slider,
                container, false);
        vpPhoto = view.findViewById(R.id.vpPhoto);


        ButterKnife.bind(this, view);
        initializeObjects();
        initializeObservers();


        return view;

    }
    @Override
    public void initializeObjects() {
        tvTitle.setText("Galeria del proyecto");

    }

    @Override
    public void initializeObservers() {
        viewModel.getGaleriaMutableLiveData().observeForever(detalle -> {
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getActivity(), detalle);
            vpPhoto.setAdapter(viewPagerAdapter);
            //vpPhoto.setCurrentItem(viewPagerAdapter.getCount()-1);


        });


    }

    @Override
    public void setupViews() {

    }
    @Override
    public void onResume()
    {
        super.onResume();
        viewModel.requestGaleria(Integer.parseInt(id_catalogo),2);
    }
}
