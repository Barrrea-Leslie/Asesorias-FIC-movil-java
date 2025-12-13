package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Solicitud;

import java.util.List;

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder> {


    private List<Solicitud> lista;

    public SolicitudAdapter(List<Solicitud> lista){
        this.lista = lista;
    }

    public class SolicitudViewHolder extends RecyclerView.ViewHolder{

        TextView nombreAlumno;
        TextView materia;
        TextView fecha;
        TextView horario;
        TextView modalidad;

        public SolicitudViewHolder (@NonNull View itemView) {

            super(itemView);

            nombreAlumno = itemView.findViewById(R.id.nombreAlumno);
            materia = itemView.findViewById(R.id.materia);
            fecha = itemView.findViewById(R.id.fecha);
            horario = itemView.findViewById(R.id.horario);
            modalidad = itemView.findViewById(R.id.modalidad);

        }

        public void bind(final Solicitud solicitud) {

            nombreAlumno.setText(solicitud.getAlumno().getNombre());
            materia.setText(solicitud.getMateria());
            fecha.setText(solicitud.getFecha());
            horario.setText(solicitud.getHorario());
            modalidad.setText(solicitud.getModalidad());

        }

    }

    @NonNull
    @Override
    public SolicitudAdapter.SolicitudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_solicitudes_pendientes, parent, false);
        return new SolicitudAdapter.SolicitudViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudAdapter.SolicitudViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
