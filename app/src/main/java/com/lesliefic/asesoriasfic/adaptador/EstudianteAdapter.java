package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Estudiante;

import java.util.List;

public class EstudianteAdapter
        extends RecyclerView.Adapter<EstudianteAdapter.AlumnoViewHolder> {

    private final List<Estudiante> lista;


    public interface OnItemClickListener {
        void onClick(Estudiante estudiante);
    }


    public interface OnDeleteClickListener {
        void onDelete(Estudiante estudiante, int position);
    }

    private final OnItemClickListener itemListener;
    private final OnDeleteClickListener deleteListener;

    public EstudianteAdapter(
            List<Estudiante> lista,
            OnItemClickListener itemListener,
            OnDeleteClickListener deleteListener
    ) {
        this.lista = lista;
        this.itemListener = itemListener;
        this.deleteListener = deleteListener;
    }

    // 🔹 ViewHolder
    public class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre;
        ImageButton btnEliminar;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreAsesor);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
        }

        public void bind(Estudiante estudiante) {
            txtNombre.setText(estudiante.getNombreCompleto());


            itemView.setOnClickListener(v -> {
                if (itemListener != null) itemListener.onClick(estudiante);
            });


            btnEliminar.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && deleteListener != null) {
                    deleteListener.onDelete(estudiante, pos);
                }
            });
        }
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor, parent, false);
        return new AlumnoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return (lista != null) ? lista.size() : 0;
    }


}