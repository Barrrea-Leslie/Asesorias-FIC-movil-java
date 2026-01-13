package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Asesoria;

import java.util.List;

public class AsesoriaAdapter extends RecyclerView.Adapter<AsesoriaAdapter.AsesoriaViewHolder> {

    private final List<Asesoria> lista;


    public interface OnItemClickListener {
        void onClick(Asesoria asesoria);
    }

    public interface OnCompletarClickListener {
        void onCompletar(Asesoria asesoria, int position);
    }

    public interface OnMaterialClickListener {
        void onMaterial(Asesoria asesoria, int position);
    }

    public interface OnEliminarClickListener {
        void onEliminar(Asesoria asesoria, int position);
    }

    private final OnItemClickListener itemListener;
    private final OnCompletarClickListener completarListener;
    private final OnMaterialClickListener materialListener;
    private final OnEliminarClickListener eliminarListener;

    public AsesoriaAdapter(
            List<Asesoria> lista,
            OnItemClickListener itemListener,
            OnCompletarClickListener completarListener,
            OnMaterialClickListener materialListener,
            OnEliminarClickListener eliminarListener
    ) {
        this.lista = lista;
        this.itemListener = itemListener;
        this.completarListener = completarListener;
        this.materialListener = materialListener;
        this.eliminarListener = eliminarListener;
    }


    public class AsesoriaViewHolder extends RecyclerView.ViewHolder {

        TextView nombreAlumno, materia, fecha, horario, modalidad;
        Button btnCompletar, btnMaterial;
        ImageButton btnEliminar;

        public AsesoriaViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreAlumno = itemView.findViewById(R.id.nombreAlumno);
            materia = itemView.findViewById(R.id.materia);
            fecha = itemView.findViewById(R.id.fecha);
            horario = itemView.findViewById(R.id.horario);
            modalidad = itemView.findViewById(R.id.modalidad);

            btnMaterial = itemView.findViewById(R.id.btn_materialAdicional);
            btnCompletar = itemView.findViewById(R.id.btn_completarAsesoria);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
        }

        public void bind(Asesoria a) {

            if (a != null) {
                nombreAlumno.setText(a.getEstudiante() != null ? a.getEstudiante() : "Sin alumno");
                materia.setText(a.getMateria() != null ? a.getMateria() : "");
                fecha.setText(a.getFechaInicio() != null ? a.getFechaInicio() : "");
                modalidad.setText(a.getModalidad() != null ? a.getModalidad() : "");
            }

            itemView.setOnClickListener(v -> {
                if (itemListener != null && a != null) {
                    itemListener.onClick(a);
                }
            });

            btnCompletar.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && completarListener != null && a != null) {
                    completarListener.onCompletar(a, pos);
                }
            });

            btnMaterial.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && materialListener != null && a != null) {
                    materialListener.onMaterial(a, pos);
                }
            });

            btnEliminar.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && eliminarListener != null && a != null) {
                    eliminarListener.onEliminar(a, pos);
                }
            });
        }
    }


    @NonNull
    @Override
    public AsesoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesorias_en_curso, parent, false);
        return new AsesoriaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesoriaViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }
}