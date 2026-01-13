package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante;

import java.util.List;

public class SolicitarAsesoriaAdapter
        extends RecyclerView.Adapter<SolicitarAsesoriaAdapter.SolicitarAsesoriaViewHolder> {

    private final List<AsesoresEstudiante> lista;

    public interface OnItemClickListener {
        void onClick(AsesoresEstudiante asesor);
    }

    public interface OnInfoClickListener {
        void onInfo(AsesoresEstudiante asesor, int position);
    }

    public interface OnSolicitarClickListener {
        void onSolicitar(AsesoresEstudiante asesor, int position);
    }

    private final OnItemClickListener itemListener;
    private final OnInfoClickListener infoListener;
    private final OnSolicitarClickListener solicitarListener;

    public SolicitarAsesoriaAdapter(
            List<AsesoresEstudiante> lista,
            OnItemClickListener itemListener,
            OnInfoClickListener infoListener,
            OnSolicitarClickListener solicitarListener
    ) {
        this.lista = lista;
        this.itemListener = itemListener;
        this.infoListener = infoListener;
        this.solicitarListener = solicitarListener;
    }

    public class SolicitarAsesoriaViewHolder extends RecyclerView.ViewHolder {

        TextView campoNombreAsesor, campoMateria, campoModalidad;
        Button btnInfo, btnSolicitar;

        public SolicitarAsesoriaViewHolder(@NonNull View itemView) {
            super(itemView);

            campoNombreAsesor = itemView.findViewById(R.id.CampoNombreAsesor);
            campoMateria = itemView.findViewById(R.id.CampoMateria);
            campoModalidad = itemView.findViewById(R.id.CampoModalidad);

            btnInfo = itemView.findViewById(R.id.btn_info);
            btnSolicitar = itemView.findViewById(R.id.btn_solicitar);
        }

        public void bind(AsesoresEstudiante asesor) {

            if (asesor != null) {

                // Ajusta los getters según tu modelo real
                if (asesor.getNombre_completo() != null) {
                    campoNombreAsesor.setText(asesor.getNombre_completo());
                }


            }

            itemView.setOnClickListener(v -> {
                if (itemListener != null && asesor != null) {
                    itemListener.onClick(asesor);
                }
            });

            btnInfo.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && infoListener != null && asesor != null) {
                    infoListener.onInfo(asesor, pos);
                }
            });

            btnSolicitar.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && solicitarListener != null && asesor != null) {
                    solicitarListener.onSolicitar(asesor, pos);
                }
            });
        }
    }

    @NonNull
    @Override
    public SolicitarAsesoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_solicitar_asesorias, parent, false);
        return new SolicitarAsesoriaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitarAsesoriaViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }
}