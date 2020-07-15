package pe.com.condominioandroidapi.activity.Inmueble.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.listener.InmuebleListener;
import pe.com.condominioandroidapi.activity.principal.adapter.PrincipalAdapter;
import pe.com.condominioandroidapi.activity.principal.listener.DetalleResultadosListener;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;

public class InmuebleListAdapter extends RecyclerView.Adapter<InmuebleListAdapter.ViewHolder>{

    private List<InmuebleListResponse> solicitudes;
    private InmuebleListener listener;
    private int lastSelectedPosition = -1;


    public InmuebleListAdapter(List<InmuebleListResponse> solicitudes, InmuebleListener listener) {

        this.solicitudes = solicitudes;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pop_inmueble, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleListAdapter.ViewHolder viewholder, int i) {

        viewholder.rbInmueble.setChecked(lastSelectedPosition == i);

        viewholder.bindItem(solicitudes.get(i));
        Log.d("solicitudes", solicitudes.toString());
    }

    @Override
    public int getItemCount() {
        if (solicitudes != null)
            return solicitudes.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlTituloItem)
        RelativeLayout rlTituloItem;

        @BindView(R.id.rbInmueble)
        public RadioButton rbInmueble;

        /*@BindView(R.id.rbGroup)
        RadioGroup rbGroup;*/

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindItem(final InmuebleListResponse dato) {
            rbInmueble.setText(dato.getCodigoInmueble());


            rbInmueble.setOnClickListener((View v) -> {

               /* rbGroup.getCheckedRadioButtonId();
                rbGroup.clearCheck();
                rbGroup.check(rbGroup.getCheckedRadioButtonId());*/
               lastSelectedPosition = getAdapterPosition();
                notifyDataSetChanged();
                Log.d("click in code", dato.getCodigoInmueble());
                listener.onDocumentoClickListener(dato);

        });

        }
    }
}





