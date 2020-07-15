package pe.com.condominioandroidapi.activity.detalle.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.entities.LugarResponse;

public class DetalleAdapter  extends RecyclerView.Adapter<DetalleAdapter.ViewHolder>{

    private List<LugarResponse> solicitudes;


    public DetalleAdapter(List<LugarResponse> solicitudes) {

        this.solicitudes = solicitudes;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lugar, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleAdapter.ViewHolder viewholder, int i) {
        viewholder.bindItem(solicitudes.get(i));

    }

    @Override
    public int getItemCount() {
        return solicitudes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvItem)
        TextView tvItem;



        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindItem(final LugarResponse dato) {

            tvItem.setText(dato.getNombre());




        }
    }
}





