package pe.com.condominioandroidapi.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.com.condominioandroidapi.R;


public class AlertDialogActivity extends Activity {
    TextView tvMessage;
    Button btnClose;
    String error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ...but notify us that it happened.
        setContentView(R.layout.activity_alert_dialog);
        tvMessage = findViewById(R.id.tvMessageDialog);
        btnClose = findViewById(R.id.btnClose);
        this.setFinishOnTouchOutside(false);
        tvMessage.setText(getIntent().getStringExtra(Constant.KEY_MESSAGE));


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDialog();
            }
        });
        error = tvMessage.getText().toString();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return true;
    }


    public void closeDialog() {

       Intent resultIntent = new Intent();
       setResult(RESULT_OK, resultIntent);
       finish();

    }
}
