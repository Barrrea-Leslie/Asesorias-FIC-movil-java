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

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.ViewHolder> {

    // 1️⃣ Interfaz para manejar el click
    public interface OnMateriaClickListener {
        void onClick(Materia materia);
    }

    // 2️⃣ Datos
    private List<Materia> lista;
    private OnMateriaClickListener listener;

    // 3️⃣ Constructor
    public MateriaAdapter(List<Materia> lista, OnMateriaClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    // 4️⃣ ViewHolder (una fila)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreMateria); // <- ID del item
        }

        public void bind(Materia materia, OnMateriaClickListener listener) {
            txtNombre.setText(materia.getMateria());
            itemView.setOnClickListener(v -> listener.onClick(materia));

        }
    }

    // 5️⃣ Crear la fila
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elegir_materia, parent, false); // <- layout del item
        return new ViewHolder(v);
    }

    // 6️⃣ Llenar la fila
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);
    }

    // 7️⃣ Cantidad de elementos
    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }

    // 8️⃣ Método para actualizar la lista
    public void setItems(List<Materia> nuevos) {
        this.lista = nuevos;
        notifyDataSetChanged();
    }
}