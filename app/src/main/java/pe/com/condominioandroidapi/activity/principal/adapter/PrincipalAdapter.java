package pe.com.condominioandroidapi.activity.principal.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.principal.listener.DetalleResultadosListener;
import pe.com.condominioandroidapi.entities.InmuebleResponse;

public class PrincipalAdapter extends RecyclerView.Adapter<PrincipalAdapter.ViewHolder>{

    private List<InmuebleResponse> solicitudes;
    private DetalleResultadosListener listener;
    public Boolean esUnico;


    public PrincipalAdapter(List<InmuebleResponse> solicitudes, DetalleResultadosListener listener) {

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
    public void onBindViewHolder(@NonNull PrincipalAdapter.ViewHolder viewholder, int i) {
        viewholder.bindItem(solicitudes.get(i));
        if(solicitudes.size()>1)
            esUnico=false;
        esUnico=true;

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

        void bindItem(final InmuebleResponse dato) {

            tvAddress.setText(dato.getDireccion());
            tvDistrict.setText(dato.getNombreDistrito());
            tvOver.setVisibility(View.INVISIBLE);
            String base64Image = dato.getArchivoLogo();
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgProyecto.setImageBitmap(decodedByte);
            imgProyecto.setScaleType(ImageView.ScaleType.CENTER_CROP);

            String logoProyecto = dato.getArchivoPerfil();
            byte[] decodedString1 = Base64.decode(logoProyecto, Base64.DEFAULT);
            Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
            img_title_small.setImageBitmap(decodedByte2);

            String logoEmpresa = dato.getLogoEmpresa();
            byte[] decodedlogoEmpresa = Base64.decode(logoEmpresa, Base64.DEFAULT);
            Bitmap decodedBytelogoEmpresa = BitmapFactory.decodeByteArray(decodedlogoEmpresa, 0, decodedlogoEmpresa.length);
            img_title.setScaleType(ImageView.ScaleType.FIT_XY);
            img_title.setImageBitmap(decodedBytelogoEmpresa);

            rlDetalle.setOnClickListener((View v) -> {
                Log.d("aqui", dato.getDireccion());
                listener.onDetalleItemClickListener(dato, esUnico);
            });
        }
    }
}





