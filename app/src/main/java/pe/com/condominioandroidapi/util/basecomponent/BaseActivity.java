package pe.com.condominioandroidapi.util.basecomponent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.util.Constant;

public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar mToolbar;
    ImageView img;
    String logo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constant.initSharedPref(this);
    }
    public void initializeView() {
        //if (findViewById(R.id.toolbarMain) != null)
        initializeToolbar(logo);

        initializeObjects();
        initializeObservers();
        setupViews();
    }

    public void initializeToolbar(String logo_perfil) {
        mToolbar = findViewById(R.id.toolbarMain);
        img = findViewById(R.id.ivTituloBar);
        if(logo_perfil=="")
        { }
        else{
        byte[] decodedlogoEmpresa = Base64.decode(logo_perfil, Base64.DEFAULT);
        Bitmap decodedBytelogoEmpresa = BitmapFactory.decodeByteArray(decodedlogoEmpresa, 0, decodedlogoEmpresa.length);
        img.setImageBitmap(decodedBytelogoEmpresa);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public abstract void initializeObjects();
    public abstract void initializeObservers();
    public abstract void setupViews();

    public String getAmountWithCurrency(String currency, Double amount) {
       /* try {
            NumberFormat nf = NumberFormat.getInstance(Locale.US);
            return currency + " " + nf.format(amount);
        } catch (Exception ex) {
            return Constant.KEY_NO_APLICA;
        }*/
       return "";
    }

    public void openDialogMessage(String message){
        /*
        startActivity(new Intent(this, AlertDialogActivity.class)
                .putExtra(Constant.KEY_MESSAGE, message));*/
    }

    public boolean isNumeric(String str) {
        try {
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            Matcher matcher = pattern.matcher(str);
            return !matcher.find();
        } catch (Exception ex){
            return false;
        }
    }

    public String getAmountWithCurrency(String currency, String numberValue)
    {
        /*try {
            if (numberValue != null && !numberValue.equals("") && isNumeric(numberValue)) {
                double amount = Double.parseDouble(numberValue);
                if (amount != 0) {
                    return getAmountWithCurrency(currency, amount);
                }
            }
            return Constant.KEY_NO_APLICA;
        } catch (Exception ex) {
            return Constant.KEY_NO_APLICA;
        }*/
        return "";
    }

    public String getStringToDecimal(String numberValue) {
        if (numberValue != null && !numberValue.equals("") && isNumeric(numberValue)) {
            double number = Double.parseDouble(numberValue);
            return String.format(Locale.US,"%.1f", number);
        }
        return "";
    }
    public void hideTextView(Button show, Button hide, TextView textView) {
        show.setVisibility(View.INVISIBLE);
        hide.setVisibility(View.VISIBLE);
        textView.setMaxLines(Integer.MAX_VALUE);
    }

    public void showTextView(Button show, Button hide, TextView textView) {
        hide.setVisibility(View.INVISIBLE);
        show.setVisibility(View.VISIBLE);
        textView.setMaxLines(3);

    }
}
