package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Materia;

import java.util.List;

public class MateriaElegidaAdapter extends RecyclerView.Adapter<MateriaElegidaAdapter.ViewHolder> {

    // 🔹 Interfaz SOLO para eliminar
    public interface OnEliminarClickListener {
        void onEliminar(Materia materia);
    }

    private List<Materia> lista;
    private OnEliminarClickListener eliminarListener;

    public MateriaElegidaAdapter(List<Materia> lista, OnEliminarClickListener eliminarListener) {
        this.lista = lista;
        this.eliminarListener = eliminarListener;
    }

    // 🔹 ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtEliminar, txtNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreMateria);
            txtEliminar = itemView.findViewById(R.id.txtEliminar);
        }

        public void bind(Materia materia, OnEliminarClickListener eliminarListener) {
            txtNombre.setText(materia.getMateria());
            txtEliminar.setOnClickListener(v -> {
                if (eliminarListener != null) {
                    eliminarListener.onEliminar(materia);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_materia, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lista.get(position), eliminarListener);
    }

    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    public void setItems(List<Materia> nuevos) {
        this.lista = nuevos;
        notifyDataSetChanged();
    }
}