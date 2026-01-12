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

public class EstudianteElegirAdapter extends RecyclerView.Adapter<EstudianteElegirAdapter.ViewHolder> {


    public interface OnEstudianteClickListener {
        void onClick(Estudiante estudiante);
    }


    private List<Estudiante> lista;
    private OnEstudianteClickListener listener;


    public EstudianteElegirAdapter(List<Estudiante> lista, OnEstudianteClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtUsuario); // <- ID del item
        }

        public void bind(Estudiante estudiante, OnEstudianteClickListener listener) {
            txtNombre.setText(estudiante.getNombreCompleto());
            itemView.setOnClickListener(v -> listener.onClick(estudiante));
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elegir_usuario, parent, false); // <- layout del item
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);
    }


    @Override
    public int getItemCount() {
        return lista == null ? 0 : lista.size();
    }


    public void setItems(List<Estudiante> nuevos) {
        this.lista = nuevos;
        notifyDataSetChanged();
    }
}