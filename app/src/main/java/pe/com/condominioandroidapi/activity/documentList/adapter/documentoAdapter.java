package pe.com.condominioandroidapi.activity.documentList.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.condominioandroidapi.R;
import pe.com.condominioandroidapi.activity.Inmueble.adapter.InmuebleListAdapter;
import pe.com.condominioandroidapi.activity.Inmueble.listener.InmuebleListener;
import pe.com.condominioandroidapi.activity.detalle.fragment.detallePlanoFragment;
import pe.com.condominioandroidapi.activity.documentList.listener.documentoListener;
import pe.com.condominioandroidapi.datamodel.DetalleDocumento;
import pe.com.condominioandroidapi.entities.DocumentoResponse;
import pe.com.condominioandroidapi.entities.InmuebleListResponse;

public class documentoAdapter  extends RecyclerView.Adapter<documentoAdapter.ViewHolder> {

    private List<DocumentoResponse<DetalleDocumento>> documento;
    private documentoListener listener;
    Context context;

    public documentoAdapter(List<DocumentoResponse<DetalleDocumento>> documento, documentoListener listener) {

        this.documento = documento;
        this.listener = listener;

    }

    @NonNull
    @Override
    public documentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_documento, viewGroup, false);

        return new documentoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull documentoAdapter.ViewHolder viewholder, int i) {
         context = viewholder.itemView.getContext();

        viewholder.bindItem(documento.get(i));
        Log.d("documento", documento.toString());
    }

    @Override
    public int getItemCount() {
        if (documento != null)
            return documento.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tituloDocumento)
        TextView tituloDocumento;
        @BindView(R.id.rvPadre)
        LinearLayout rvPadre;
        @BindView(R.id.ivIcon)
        ImageView ivIcon;

        TextView tvDocumento;
        @BindView(R.id.rlDow)
        LinearLayout rlDow;
       /* @BindView(R.id.ivDownload)*/
        ImageView ivDownload;
        @BindView(R.id.rlItem)
        LinearLayout rlItem;



        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindItem(final DocumentoResponse dato) {



            Log.d("documentoProyecto", dato.getListaDocumento().toString());
            if (dato.getListaDocumento().size() != 0) {

                tituloDocumento.setText(dato.getTipoDocumento());

                switch (dato.getIdTipoDocumento()) {
                    case 1:
                        ivIcon.setImageResource(R.drawable.ic_planos);
                        break;
                    case 2:
                        ivIcon.setImageResource(R.drawable.ic_manuales);
                        break;
                    case 3:
                        ivIcon.setImageResource(R.drawable.ic_guias);
                        break;
                    case 4:
                        ivIcon.setImageResource(R.drawable.ic_permisos);
                        break;
                    default:
                        ivIcon.setImageResource(R.drawable.ic_otros);
                        break;
                }
                rlDow.setOrientation(LinearLayout.VERTICAL);



                for(int i =0; i<dato.getListaDocumento().size();i++)
                    {
                        tvDocumento = new TextView(context);
                        tvDocumento.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.9f));

                        ivDownload = new ImageView(context);
                        rlItem = new LinearLayout(context);
                        rlItem.setOrientation(LinearLayout.HORIZONTAL);
                        
                        rlItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));

                        // TextView tv = new TextView(context);

                         tvDocumento.setText(((DetalleDocumento) dato.getListaDocumento().get(i)).getNombre());

                        ivDownload.setBackgroundResource(R.drawable.icon_lupa_gris);
                        ivDownload.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                        ivDownload.getLayoutParams().height=25;
                        ivDownload.getLayoutParams().width=25;
                        ivDownload.setId(i);
                        rlItem.addView(tvDocumento);
                        rlItem.setId(i);
                         rlItem.addView(ivDownload);
                        rlItem.setPadding(35,15,35,15);


                        rlDow.addView(rlItem);
                        rlItem.setClickable(true);
                        rlItem.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Integer codigo = ((LinearLayout) v).getId();
                                Log.d("puntero2", v.toString());

                                Log.d("puntero2", Integer.toString(codigo));
                                listener.onDocumentoDwClickListener(dato,codigo);


                            }
                        });

                    }

            }
            else {
                rvPadre.setVisibility(View.GONE);
            }

           /* rlItem.setOnClickListener((View v) -> {
                LinearLayout clickedButton = (LinearLayout) v;

                // do what I need to do when a button is clicked here...
                int id = clickedButton.getId();
                Log.d("puntero", Integer.toString(id));
               // listener.onDocumentoDwClickListener(dato);
            });*/
        }
    }
}
