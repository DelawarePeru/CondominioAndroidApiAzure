package pe.com.condominioandroidapi.activity.documentList;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.itextpdf.text.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.documentList.adapter.documentoAdapter;
import pe.com.condominioandroidapi.activity.documentList.listener.documentoListener;
import pe.com.condominioandroidapi.activity.viewPDF.viewpdfActivity;
import pe.com.condominioandroidapi.datamodel.DetalleDocumento;
import pe.com.condominioandroidapi.entities.DocumentoResponse;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class documentList extends BaseActivity implements documentoListener {

    documentoViewModel viewModel;
    private String  idProyecto;
    private String  idCodigo;
    private String cod_color;
    private static final int STORAGE_CODE = 1000;
    private String mFileName;
    private String body;
    private String id_opcion;
    private String id_inmueble;
    private String ID_LOGOPROYECTO;
    @BindView(R.id.rvAmpliaciones)
    RecyclerView rvAmpliaciones;

    @BindView(R.id.tvCodigoDocu)
    TextView tvCodigoDocu;

    @BindView(R.id.pgbDialog)
    ProgressBar pgbDialog;

    @BindView(R.id.logo_proyecto)
    ImageView logo_proyecto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_condominio);
        ButterKnife.bind(this);
        initializeToolbar(Constant.ID_LOGOEMPRESA);
        initializeObjects();
        initializeObservers();
        setupViews();
    }

    @Override
    public void initializeObjects() {
        viewModel = ViewModelProviders.of(this).get(documentoViewModel.class);
        idProyecto = getIntent().getStringExtra(Constant.ID_PROYECTO);
        idCodigo = getIntent().getStringExtra(Constant.CODIGO_INMUEBLE);
        cod_color = getIntent().getStringExtra(Constant.CODIGO_COLOR);
        id_opcion = getIntent().getStringExtra(Constant.ID_OPCION);
        id_inmueble = getIntent().getStringExtra(Constant.id_Inmueble);
        ID_LOGOPROYECTO = getIntent().getStringExtra(Constant.ID_LOGOPROYECTO);

        String base64Image = ID_LOGOPROYECTO;

        byte[] decodedString = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        logo_proyecto.setImageBitmap(decodedByte);

        tvCodigoDocu.setBackgroundResource(R.drawable.circ_codi_inmu);
        tvCodigoDocu.setPadding(40 , 5,40,5);
        GradientDrawable drawable = (GradientDrawable) tvCodigoDocu.getBackground();
        drawable.setCornerRadius(25);
        drawable.setColor(Color.parseColor(cod_color));
        tvCodigoDocu.setText(idCodigo);


    }

    @Override
    public void initializeObservers() {

        viewModel.documentoMutableLiveData().observeForever(new Observer<List<DocumentoResponse<DetalleDocumento>>>() {
            @Override
            public void onChanged(@Nullable List<DocumentoResponse<DetalleDocumento>> documento) {
                rvAmpliaciones.setAdapter(new documentoAdapter(documento, documentList.this));
            }
        });

        viewModel.getPgbVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                // Desde el ViewModel recibimos solo dos tipos de valores 1 o 0 (View.VISIBLE o View.GONE) que sirven para mostrar o esconder un widget
                if (value != null)
                    pgbDialog.setVisibility(value);
            }
        });

    }

    @Override
    public void setupViews() {

        rvAmpliaciones.setLayoutManager(new LinearLayoutManager(this));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDocumentoDwClickListener(DocumentoResponse dato, Integer id) {
        Log.d("trycatch",((DetalleDocumento) dato.getListaDocumento().get(id)).getNombre());
        mFileName =  ((DetalleDocumento) dato.getListaDocumento().get(id)).getNombre();
        body = ((DetalleDocumento) dato.getListaDocumento().get(id)).getArchivo();
        if(mFileName == null || body == null)
        {
            Toast.makeText(this,"Archivo no disponible", Toast.LENGTH_LONG).show();

        }else
        {
            writeFileOnInternalStorage(mFileName, body);
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void writeFileOnInternalStorage(String mFileName, String body){

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                    PackageManager.PERMISSION_DENIED)
            {
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions,STORAGE_CODE);
            }else
            {
                savePDF(mFileName, body);

            }

        }else {
            savePDF(mFileName, body);
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void savePDF(String mFileName, String body)
    {
        Document mdoc = new Document();
        String titulo = mFileName;
        String mFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+
                mFileName +".pdf";
        File f = new File(mFilePath);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            byte[] base = Base64.getDecoder().decode(body);
            fos.write(base);
            /*Toast.makeText(this, mFileName+".pdf" + " is save to " +
                    mFilePath, Toast.LENGTH_LONG).show();*/
            Intent intent = new Intent(this, viewpdfActivity.class);
            intent.putExtra(Constant.URI, mFilePath);
            intent.putExtra(Constant.PDF_TITLE, titulo);
            intent.putExtra(Constant.CODIGO_COLOR, cod_color);

            startActivity(intent);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


 /*       File file =  new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ mFileName+".pdf");

        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(Uri.fromFile(file), "application/pdf");

        Uri apkURI = FileProvider.getUriForFile(
                this,
                this.getApplicationContext()
                        .getPackageName() + ".provider", file);
        install.setDataAndType(apkURI, "application/pdf");
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        this.startActivity(install);*/

       /* File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ "prueba"+".pdf");
        Log.d("mFilePath" ,file.toString());

         Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }

*/
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case STORAGE_CODE : {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    savePDF(mFileName, body);
                }else
                {
                    Toast.makeText(this,"Permiso denegado . . .", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(id_opcion.equals("1")) {
            viewModel.requestDocumento(Integer.parseInt(idProyecto),Integer.parseInt(id_opcion));
        }else if(id_opcion.equals("2"))
        {
            viewModel.requestDocumento(Integer.parseInt(id_inmueble),Integer.parseInt(id_opcion));

        }


    }
}
