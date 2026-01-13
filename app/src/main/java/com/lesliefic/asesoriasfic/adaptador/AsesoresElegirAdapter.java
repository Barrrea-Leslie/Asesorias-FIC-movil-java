package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Asesores;

import java.util.List;

public class AsesoresElegirAdapter extends RecyclerView.Adapter<AsesoresElegirAdapter.ViewHolder> {


    public interface OnAsesorClickListener {
        void onClick(Asesores asesor);
    }

    // Datos
    private List<Asesores> lista;
    private OnAsesorClickListener listener;

    // Constructor
    public AsesoresElegirAdapter(List<Asesores> lista, OnAsesorClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    // ViewHolder (una fila)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtUsuario); // mismo ID
        }

        public void bind(Asesores asesor, OnAsesorClickListener listener) {
            txtNombre.setText(asesor.getAsesor());
            itemView.setOnClickListener(v -> listener.onClick(asesor));
        }
    }

    // Crear la fila
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elegir_usuario, parent, false); // mismo layout
        return new ViewHolder(v);
    }

    // Llenar la fila
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);
    }

    // Cantidad de elementos
    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    // Método para actualizar la lista
    public void setItems(List<Asesores> nuevos) {
        this.lista = nuevos;
        notifyDataSetChanged();
    }
}