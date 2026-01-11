package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;

import java.util.List;

public class AsesorDisciplinarAdapter
        extends RecyclerView.Adapter<AsesorDisciplinarAdapter.AsesorDisciplinarViewHolder> {

    private final List<AsesorDisciplinar> listaAsesores;


    public interface OnItemClickListener {
        void onClick(AsesorDisciplinar asesor);
    }


    public interface OnDeleteClickListener {
        void onDelete(AsesorDisciplinar asesor, int position);
    }

    private final OnItemClickListener itemListener;
    private final OnDeleteClickListener deleteListener;

    public AsesorDisciplinarAdapter(
            List<AsesorDisciplinar> listaAsesores,
            OnItemClickListener itemListener,
            OnDeleteClickListener deleteListener
    ) {
        this.listaAsesores = listaAsesores;
        this.itemListener = itemListener;
        this.deleteListener = deleteListener;
    }

    // 🔹 ViewHolder
    public class AsesorDisciplinarViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreAsesor;
        ImageButton btnEliminar;

        public AsesorDisciplinarViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreAsesor = itemView.findViewById(R.id.txtNombreAsesor);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar); // 👈 tu id
        }

        public void bind(AsesorDisciplinar asesor) {
            txtNombreAsesor.setText(asesor.getNombre_completo());


            itemView.setOnClickListener(v -> {
                if (itemListener != null) itemListener.onClick(asesor);
            });


            btnEliminar.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && deleteListener != null) {
                    deleteListener.onDelete(asesor, pos);
                }
            });
        }
    }

    @NonNull
    @Override
    public AsesorDisciplinarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor, parent, false);
        return new AsesorDisciplinarViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesorDisciplinarViewHolder holder, int position) {
        holder.bind(listaAsesores.get(position));
    }

    @Override
    public int getItemCount() {
        return (listaAsesores != null) ? listaAsesores.size() : 0;
    }


}