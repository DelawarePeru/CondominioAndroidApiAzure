package pe.com.condominioandroidapi.activity.proyecto.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.proyecto.listener.DetalleResultadosListener;
import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;

public class ProyectoAdapter extends RecyclerView.Adapter<ProyectoAdapter.ViewHolder>{

    private List<VentaResponse> solicitudes;
    private DetalleResultadosListener listener;
    private Bitmap mBitmap;
    private Resources mResources;


        public ProyectoAdapter(List<VentaResponse> solicitudes, DetalleResultadosListener listener) {

        this.solicitudes = solicitudes;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inmueble, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoAdapter.ViewHolder viewholder, int i) {
        viewholder.bindItem(solicitudes.get(i));

    }

    @Override
    public int getItemCount() {
        return solicitudes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlDetalle)
        LinearLayout rlDetalle;
        @BindView(R.id.imgProyecto)
        ImageView imgProyecto;
        @BindView(R.id.img_title)
        ImageView img_title;
        @BindView(R.id.img_title_small)
        ImageView img_title_small;
        @BindView(R.id.tvAddressTitle)
        TextView tvAddressTitle;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.tvDistrict)
        TextView tvDistrict;
        @BindView(R.id.tvOver)
        TextView tvOver;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindItem(final VentaResponse dato) {

            tvAddress.setText(dato.getDireccion());
            tvDistrict.setText(dato.getNombreDistrito());
            tvOver.setText(dato.getEstadoObra());

            rlDetalle.setOnClickListener((View v) -> {
                Log.d("aqui", dato.toString());
                listener.onDetalleItemClickListener(dato);
            });

            String base64Image = dato.getPerfilProyecto();
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgProyecto.setImageBitmap(decodedByte);
            imgProyecto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgProyecto.setTag(dato.getEstadoObra());


            String logoProyecto = dato.getLogoProyecto();
            byte[] decodedlogoProyecto = Base64.decode(logoProyecto, Base64.DEFAULT);
            Bitmap decodedBytelogo = BitmapFactory.decodeByteArray(decodedlogoProyecto, 0, decodedlogoProyecto.length);
            img_title_small.setImageBitmap(decodedBytelogo);

           String logoEmpresa = dato.getLogoEmpresa();
            byte[] decodedlogoEmpresa = Base64.decode(logoEmpresa, Base64.DEFAULT);
            Bitmap decodedBytelogoEmpresa = BitmapFactory.decodeByteArray(decodedlogoEmpresa, 0, decodedlogoEmpresa.length);
            img_title.setScaleType(ImageView.ScaleType.FIT_XY);
            img_title.setImageBitmap(decodedBytelogoEmpresa);

        }
    }
}





