package pe.com.condominioandroidapi.activity.detalle.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.detalle.detalleActivity;
import pe.com.condominioandroidapi.activity.detalle.detalleViewModel;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseFragment;

public class planoFragment  extends BaseFragment {
    Button btnFlat;
    Button btnDuplex;
    Button btnComun;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.menuTipo)
    LinearLayout menuTipo;
    @BindView(R.id.oculto)
    LinearLayout oculto;
    @BindView(R.id.textView)
    TextView textView;
    Integer ROWS = 1;
    Integer COLUMNS = 3;
    TableLayout tableLayout;
    TableLayout tableTipo;

    detalleViewModel viewModel;
    String id_catalogo;
    List<String> lista;
    List<Integer> listaId;
    List<Integer> listaEstados;
    List<String> nombreTipo;
    List<Integer> idTipo;
    TableRow row;
    TableRow tipoMenu;
    Button btnTipoMenu;

    Button bt;
    Integer ids;
    //Integer idTipo;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
       viewModel = ViewModelProviders.of(this).get(detalleViewModel.class);
        View view =  inflater.inflate(R.layout.plano_fragmento,
                container, false);
       // vpPhoto = view.findViewById(R.id.vpPhoto);
        linearLayout = view.findViewById(R.id.linearLayout);
        menuTipo = view.findViewById(R.id.menuTipo);
        oculto = view.findViewById(R.id.oculto);

        linearLayout.setWeightSum(3);
        row = new TableRow(getContext());
         bt = new Button(getActivity());
        id_catalogo = ((detalleActivity) this.getActivity()).id_catalogo;
        ButterKnife.bind(this, view);
        initializeObjects();

        return view;

    }
    @Override
    public void initializeObjects() {

        viewModel.getGridMutableLiveData().observeForever(detalle -> {
            linearLayout.removeAllViews();
           // oculto.setVisibility(View.GONE);

            lista = new ArrayList<>();
            listaId = new ArrayList<>();
            listaEstados = new ArrayList<>();
            for(int i =0; i<detalle.size() ;i++)
            {

                String inmueble =  detalle.get(i).getNombre();
                Integer id =  detalle.get(i).getIdInmueble();
                Integer estado =  detalle.get(i).getEstadoVenta();

                lista.add(inmueble);
                listaId.add(id);
                listaEstados.add(estado);
                Log.d("listasdeinm", lista.toString());
            }

            createTable(ROWS,COLUMNS,lista,listaId);
        });

        viewModel.getTipoInmuebleMutableLiveData().observeForever(detalle -> {
            idTipo = new ArrayList<>();
            nombreTipo = new ArrayList<>();
            for(int i =0; i<detalle.size() ;i++)
            {

                String tipoInmueble =  detalle.get(i).getTipoInmueble();
                Integer idCatalogo =  detalle.get(i).getIdCatalogo();
                Integer idTipoInmueble =  detalle.get(i).getIdTipoInmueble();

                idTipo.add(idTipoInmueble);
                nombreTipo.add(tipoInmueble);
            }

            createMenu();

        });


    }
    public void createMenu()
    {
        tableTipo = new TableLayout(getContext());
        tableTipo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        int a = 0;
        for (int i=0; i<1 ;i++) {

            tipoMenu = new TableRow(getContext());
            tipoMenu.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int  j = 0 ; j<idTipo.size() ; j++) {
                if(idTipo.size()>a) {
                    btnTipoMenu = new Button(getActivity());

                    btnTipoMenu.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                    btnTipoMenu.setText(nombreTipo.get(a));
                    btnTipoMenu.setId(idTipo.get(a));
                    btnTipoMenu.setBackgroundColor(getResources().getColor(R.color.colorRipple));
                    btnTipoMenu.setTextColor(getResources().getColor(R.color.marcaTitle));
                    btnTipoMenu.setClickable(true);

                    tipoMenu.addView(btnTipoMenu);


                    btnTipoMenu.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        public void onClick(View v) {

                            if( ((Button) v).isClickable() ) {
                                Integer idTipo = ((Button) v).getId();

                                Log.d("planogrid", idTipo.toString());
                                viewModel.requestGrid(Integer.parseInt(id_catalogo),idTipo);



                            }

                        }


                    });

                    a++;
                }
                else{break;}
            }
            tableTipo.addView(tipoMenu);
        }
        menuTipo.addView(tableTipo);

    }
    public void createTable(Integer rows, Integer cols, List<String> letter, List<Integer> id) {
        tableLayout = new TableLayout(getContext());
        tableLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        int a = 0;
        for (int i=0; i<rows ;i++) {

            row = new TableRow(getContext());
            row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int  j = 0 ; j<cols ; j++) {
                if(letter.size()>a) {
                     bt = new Button(getActivity());

                    bt.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                    bt.setText(letter.get(a));
                    bt.setId(id.get(a));
                    bt.setBackgroundColor(getResources().getColor(R.color.marcaPrimary));
                    bt.setTextColor(getResources().getColor(R.color.marcaTitle));
                    if(listaEstados.get(a)==0)
                        bt.setClickable(true);
                    else {
                        bt.setOnTouchListener((v, event) -> false);
                        bt.setClickable(false);
                        bt.setFocusable(false);
                        bt.setEnabled(false);
                        bt.setBackgroundColor(getResources().getColor(R.color.marcaHeader));
                        bt.setTextColor(getResources().getColor(R.color.marcaPrimary));

                    }
                    row.addView(bt);


                    bt.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        public void onClick(View v) {
                            // bt.setBackground(getResources().getDrawable(R.drawable.btn_pressed));


                           /* ((Button) v).setBackground(getResources().getDrawable(R.drawable.btn_pressed_inmueble));
                            ((Button) v).setTextColor(getResources().getColor(R.color.colorAccent));
                            */

                            if( ((Button) v).isClickable() ) {
                                oculto.setVisibility(View.VISIBLE);
                                Fragment fragment = null;
                                Bundle bundle = new Bundle();
                                String codigo = ((Button) v).getText().toString();
                                Integer id_inmueble = ((Button) v).getId();
                                bundle.putString("id_inmueble", Integer.toString(id_inmueble));
                                fragment = new detallePlanoFragment();
                                fragment.setArguments(bundle);

                                replaceFragment(fragment);
                            }

                        }


                    });

                    a++;
                }
                else{break;}
            }
            tableLayout.addView(row);
        }

        linearLayout.addView(tableLayout);

    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.oculto, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void initializeObservers() {



    }

    @Override
    public void setupViews() {

    }
/*
    @OnClick(R.id.btnFlat)
    public void onClickBtnOk() {
       // idTipo = 3;
        btnFlat.setBackground(getResources().getDrawable(R.drawable.btn_pressed));
        btnFlat.setBackgroundResource(R.color.btnLogin);
        btnDuplex.setBackgroundResource(R.color.colorRipple);
        btnComun.setBackgroundResource(R.color.colorRipple);
        linearLayout.removeAllViews();
        //if( !listaId.isEmpty() || !listaId.isEmpty()) {
            createTable(ROWS, COLUMNS, lista, listaId);




    }

    @OnClick(R.id.btnDuplex)
    public void onClickBtnDuplex() {
        btnDuplex.setBackground(getResources().getDrawable(R.drawable.btn_pressed));
        btnFlat.setBackgroundResource(R.color.colorRipple);
        btnDuplex.setBackgroundResource(R.color.btnLogin);
        btnComun.setBackgroundResource(R.color.colorRipple);


        linearLayout.removeAllViews();
        oculto.setVisibility(View.GONE);
        /*if( !listaId.isEmpty() || !listaId.isEmpty()) {
            createTable(ROWS, COLUMNS, lista, listaId);
        }*/
    //}
    /*@OnClick(R.id.btnComun)
    public void onClickBtnComun() {
        btnComun.setBackground(getResources().getDrawable(R.drawable.btn_pressed));
        btnDuplex.setBackgroundResource(R.color.colorRipple);
        btnFlat.setBackgroundResource(R.color.colorRipple);
        btnComun.setBackgroundResource(R.color.btnLogin);


        linearLayout.removeAllViews();
        oculto.setVisibility(View.GONE);
    }
   */
    @Override
    public void onResume()
    {
        super.onResume();
        viewModel.requestTipoInmueble(Integer.parseInt(id_catalogo));

       //viewModel.requestGrid(Integer.parseInt(id_catalogo),3);
    }

}
