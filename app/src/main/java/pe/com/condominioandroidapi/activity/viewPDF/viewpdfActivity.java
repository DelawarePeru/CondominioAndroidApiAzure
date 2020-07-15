package pe.com.condominioandroidapi.activity.viewPDF;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.util.Constant;
import pe.com.condominioandroidapi.util.basecomponent.BaseActivity;

public class viewpdfActivity extends AppCompatActivity {

    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.txtPdf)
    TextView txtPdf;
    private String uri;
    private String title;
    private String cod_color;

    public Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        ButterKnife.bind(this);
        initializeObjects();

        initializeToolbarPdf();

        initializeObservers();
        setupViews();
    }


    public void initializeObjects() {
        uri = getIntent().getStringExtra(Constant.URI);
        title = getIntent().getStringExtra(Constant.PDF_TITLE);
        cod_color = getIntent().getStringExtra(Constant.CODIGO_COLOR);

        Log.d("RUTA",uri);

    }

    public void initializeToolbarPdf() {
        mToolbar = findViewById(R.id.toolbarMainPdf);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        txtPdf.setText(title);
        txtPdf.setTextColor(Color.parseColor(cod_color));
        setSupportActionBar(mToolbar); // Setting/replace toolbar as the ActionBar
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(v -> finish());

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_compartir);*/
    }
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.xml.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            File file = new File(uri);
            Uri uri = FileProvider.getUriForFile( this,
                    this.getApplicationContext()
                            .getPackageName() + ".provider", file);
            Intent intent = ShareCompat.IntentBuilder.from(this)
                    .setType("application/pdf")
                    .setStream(uri)
                    .setChooserTitle("Choose bar")
                    .createChooserIntent()
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            this.startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    public void initializeObservers() {
        File file =  new File(uri);

        pdfView.fromFile(file).load();


    }

    public void setupViews() {



    }
}
