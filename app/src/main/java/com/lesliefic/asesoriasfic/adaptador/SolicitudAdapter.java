package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.SolicitudPendiente;

import java.util.List;

public class SolicitudAdapter
        extends RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder> {

    private final List<SolicitudPendiente> lista;

    public interface OnItemClickListener {
        void onClick(SolicitudPendiente solicitud);
    }

    public interface OnAceptarClickListener {
        void onAceptar(SolicitudPendiente solicitud, int position);
    }

    public interface OnRechazarClickListener {
        void onRechazar(SolicitudPendiente solicitud, int position);
    }

    private final OnItemClickListener itemListener;
    private final OnAceptarClickListener aceptarListener;
    private final OnRechazarClickListener rechazarListener;

    public SolicitudAdapter(
            List<SolicitudPendiente> lista,
            OnItemClickListener itemListener,
            OnAceptarClickListener aceptarListener,
            OnRechazarClickListener rechazarListener
    ) {
        this.lista = lista;
        this.itemListener = itemListener;
        this.aceptarListener = aceptarListener;
        this.rechazarListener = rechazarListener;
    }

    // ===================== VIEW HOLDER =====================
    public class SolicitudViewHolder extends RecyclerView.ViewHolder {

        TextView txtAlumno;
        TextView txtMateria;
        TextView txtFecha;
        TextView txtHorario;
        TextView txtModalidad;

        Button btnRechazar;
        Button btnAceptar;

        public SolicitudViewHolder(@NonNull View itemView) {
            super(itemView);


            txtAlumno = itemView.findViewById(R.id.campoNombre);
            txtMateria = itemView.findViewById(R.id.campoMateria);
            txtFecha = itemView.findViewById(R.id.campoFecha);
            txtHorario = itemView.findViewById(R.id.campoHorario);
            txtModalidad = itemView.findViewById(R.id.campoModalidad);

            btnRechazar = itemView.findViewById(R.id.btn_rechazar);
            btnAceptar = itemView.findViewById(R.id.btn_aceptar);
        }

        public void bind(SolicitudPendiente s) {

            // 🛡️ Protección contra null (evita crashes)
            if (txtAlumno != null) {
                txtAlumno.setText(s.getEstudiante());
            }

            if (txtMateria != null) {
                txtMateria.setText(s.getMateria());
            }

            if (txtFecha != null) {
                txtFecha.setText(s.getFecha_inicio());
            }

            if (txtHorario != null) {
                txtHorario.setText(s.getHorario());
            }

            if (txtModalidad != null) {
                txtModalidad.setText(s.getModalidad());
            }

            itemView.setOnClickListener(v -> {
                if (itemListener != null) {
                    itemListener.onClick(s);
                }
            });

            btnAceptar.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && aceptarListener != null) {
                    aceptarListener.onAceptar(s, pos);
                }
            });

            btnRechazar.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && rechazarListener != null) {
                    rechazarListener.onRechazar(s, pos);
                }
            });
        }
    }

    // ===================== ADAPTER =====================
    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_solicitudes_pendientes, parent, false);
        return new SolicitudViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(
            @NonNull SolicitudViewHolder holder,
            int position
    ) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;
    }
}