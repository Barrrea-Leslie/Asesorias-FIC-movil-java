package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Estudiante;

import java.util.List;

public class CrearAsesorParAdapter
        extends RecyclerView.Adapter<CrearAsesorParAdapter.ViewHolder> {

    // 1️⃣ Interfaz para manejar el click
    public interface OnEstudianteClickListener {
        void onClick(Estudiante estudiante);
    }

    // 2️⃣ Datos
    private List<Estudiante> lista;
    private OnEstudianteClickListener listener;

    // 3️⃣ Constructor
    public CrearAsesorParAdapter(List<Estudiante> lista,
                                 OnEstudianteClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    // 4️⃣ ViewHolder (una fila)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreAsesor);
        }

        public void bind(Estudiante estudiante,
                         OnEstudianteClickListener listener) {
            txtNombre.setText(estudiante.getNombre() + " " + estudiante.getApellidoPaterno() + " " + estudiante.getApellidoMaterno());
            itemView.setOnClickListener(v -> listener.onClick(estudiante));
        }
    }

    // 5️⃣ Crear la fila
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_estudiante_destacado, parent, false);
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
        return lista.size();
    }

    // 8️⃣ Método para actualizar la lista
    public void setItems(List<Estudiante> nuevos) {
        this.lista = nuevos;
        notifyDataSetChanged();
    }
}