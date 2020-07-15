package pe.com.condominioandroidapi.activity.principal.listener;

import pe.com.condominioandroidapi.entities.InmuebleResponse;
import pe.com.condominioandroidapi.entities.ProyectoResponse;
import pe.com.condominioandroidapi.entities.VentaResponse;

public interface DetalleResultadosListener {

    void onDetalleItemClickListener(InmuebleResponse dato , Boolean esUnico);

}
