package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;

import java.util.List;

public class AsesorDisciplinarAdapter
        extends RecyclerView.Adapter<AsesorDisciplinarAdapter.AsesorDisciplinarViewHolder> {

    private List<AsesorDisciplinar> listaAsesores;
    private OnAsesorDisciplinarClickListener listener;

    // 🔹 Interface de click
    public interface OnAsesorDisciplinarClickListener {
        void onClick(AsesorDisciplinar asesor);
    }

    public AsesorDisciplinarAdapter(
            List<AsesorDisciplinar> listaAsesores,
            OnAsesorDisciplinarClickListener listener
    ) {
        this.listaAsesores = listaAsesores;
        this.listener = listener;
    }

    // 🔹 ViewHolder
    public class AsesorDisciplinarViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreAsesor;

        public AsesorDisciplinarViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreAsesor = itemView.findViewById(R.id.txtNombreAsesor);
        }

        public void bind(AsesorDisciplinar asesor) {
            txtNombreAsesor.setText(asesor.getNombre_completo());
            itemView.setOnClickListener(v -> listener.onClick(asesor));
        }
    }

    @NonNull
    @Override
    public AsesorDisciplinarViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor, parent, false);
        return new AsesorDisciplinarViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(
            @NonNull AsesorDisciplinarViewHolder holder,
            int position
    ) {
        holder.bind(listaAsesores.get(position));
    }

    @Override
    public int getItemCount() {
        return listaAsesores != null ? listaAsesores.size() : 0;
    }
}