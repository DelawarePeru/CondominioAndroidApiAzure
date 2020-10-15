package pe.com.condominioandroidapi.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import butterknife.BindView;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.entities.GaleriaResponse;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<GaleriaResponse> galeria;
    private LayoutInflater layoutInflater;
    // private  Integer[] images = {R.drawable.slide1 , R.drawable.slide2,R.drawable.slide3};


    public ViewPagerAdapter(Context context, List<GaleriaResponse> galeria) {
        this.galeria = galeria;
        this.context = context;
    }

    @Override
    public int getCount() {
        return galeria.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.item_photo,null);
        byte[] decodedString = Base64.decode(galeria.get(position).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView ivSlide = (ImageView) view.findViewById(R.id.ivSlide);
        ImageView ivNext = (ImageView) view.findViewById(R.id.ivNext);
        ImageView tvBack = (ImageView) view.findViewById(R.id.ivBack);
        ivSlide.setImageBitmap(decodedByte);
        TextView tvOver =  view.findViewById(R.id.tvOver);

        if(position==0)
            tvBack.setVisibility(View.INVISIBLE);

        if(galeria.size() - 1 == position) {

            ivNext.setVisibility(View.INVISIBLE);
        }

        if (galeria.get(position).getTitulo() == null)
            tvOver.setVisibility(View.INVISIBLE);
        else
            tvOver.setText(galeria.get(position).getTitulo().toUpperCase());

        view.setOnClickListener(v -> {
            String img = galeria.get(position).getImage();
            byte[] decoded = Base64.decode(img, Base64.DEFAULT);
            Bitmap decodedbite = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
            int compressedDataLength = 0;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            decodedbite.compress(Bitmap.CompressFormat.PNG, 50, stream);
            byte[] byteArray = stream.toByteArray();
            try {
                Intent intent = new Intent(context, PhotoViewerActivity.class);
                intent.putExtra("image", byteArray);
                context.startActivity(intent);
            }catch (Exception e)
            {

            }
        });
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);
        return  view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
