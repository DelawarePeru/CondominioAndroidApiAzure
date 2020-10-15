package pe.com.condominioandroidapi.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;

import com.google.android.material.animation.MotionTiming;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import butterknife.BindView;
import pe.com.condominioandroidapi.R;

public class PhotoViewerActivity extends Activity {
    private ScaleGestureDetector scaleGestureDetector;
    GestureDetector gestureDetector;

    float mScaleFactor = 1.0f;
    @BindView(R.id.ivZoom)
    ImageView ivZoom;
    @BindView(R.id.view)
    RelativeLayout view;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        byte[] output2 = new byte[0];
        String url = null;

        String outputString="";
        Bitmap bmp = null;
        try {
            Bundle extras = getIntent().getExtras();
            byte[] byteArray = extras.getByteArray("image");

             bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        }
        catch (Exception e)
        {

        }
        ivZoom=findViewById(R.id.ivZoom);
        view=findViewById(R.id.view);
        ivZoom.setImageBitmap(bmp);
        gestureDetector = new GestureDetector(this,new GestureListener());

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                //return gestureDetector.onTouchEvent(event);
                return gestureDetector.onTouchEvent(event);
            }

        });
        /*view.setOnTouchListener(new View.OnTouchListener() {
            Handler handler = new Handler();

            int numberOfTaps = 0;
            long lastTapTimeMs = 0;
            long touchDownMs = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchDownMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);

                        if ((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()) {
                            //it was not a tap

                            numberOfTaps = 0;
                            lastTapTimeMs = 0;
                            break;
                        }

                        if (numberOfTaps > 0
                                && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                            numberOfTaps += 1;
                        } else {
                            numberOfTaps = 1;
                        }

                        lastTapTimeMs = System.currentTimeMillis();

                        if (numberOfTaps == 3) {
                            Toast.makeText(getApplicationContext(), "triple", Toast.LENGTH_SHORT).show();
                            //handle triple tap
                        } else if (numberOfTaps == 2) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //handle double tap
                                    scaleGestureDetector.onTouchEvent(event);
                                }
                            }, ViewConfiguration.getDoubleTapTimeout());
                        }
                }

                return true;

            }

            });*/

        // you can show image from url using library like glide or picasso
    }

    protected int getLayoutResourceId() {
        return R.layout.activity_photo_viewer;
    }

    @Override
    public void onBackPressed() {
        PhotoViewerActivity.this.finish();
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_UP:
            default:
                scaleGestureDetector.onTouchEvent(motionEvent);
                break;
        }
        return true;

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float mScaleFactor = 1.0f;
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {


            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            ivZoom.setScaleX(mScaleFactor);
            ivZoom.setScaleY(mScaleFactor);
            return true;
        }    }


    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        float mScaleFactor = 3.0f;


        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if(ivZoom.getScaleX()>1)
            {
                mScaleFactor = 1.0f;
            }else
            {
                mScaleFactor = 3.0f;

            }
            mScaleFactor *= scaleGestureDetector.getScaleFactor();

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

            ivZoom.setScaleX(mScaleFactor);
            ivZoom.setScaleY(mScaleFactor);
            return true;

        }

    }
}