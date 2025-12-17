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

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.AlumnoViewHolder>{

    private List<Estudiante> lista;

    private EstudianteAdapter.OnAsesorClickListener listener;
    public interface OnAsesorClickListener {
        void onClick(Estudiante estudiante);
    }
    public EstudianteAdapter(List<Estudiante> lista, EstudianteAdapter.OnAsesorClickListener listener){
        this.lista=lista;
        this.listener=listener;
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        public AlumnoViewHolder (@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreAsesor);
        }
        public void bind(final Estudiante estudiante) {
            txtNombre.setText(estudiante.getNombre());
            itemView.setOnClickListener(v -> listener.onClick(estudiante));
        }
    }

    @NonNull
    @Override
    public EstudianteAdapter.AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor,parent,false);
        return new EstudianteAdapter.AlumnoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        holder.bind(lista.get(position));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
