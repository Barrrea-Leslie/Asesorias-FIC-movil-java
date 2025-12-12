package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Alumno;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder>{

    private List<Alumno> lista;

    private AlumnoAdapter.OnAsesorClickListener listener;
    public interface OnAsesorClickListener {
        void onClick(Alumno alumno);
    }
    public AlumnoAdapter(List<Alumno> lista, AlumnoAdapter.OnAsesorClickListener listener){
        this.lista=lista;
        this.listener=listener;
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        public AlumnoViewHolder (@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreAsesor);
        }
        public void bind(final Alumno alumno) {
            txtNombre.setText(alumno.getNombre());
            itemView.setOnClickListener(v -> listener.onClick(alumno));
        }
    }

    @NonNull
    @Override
    public AlumnoAdapter.AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor,parent,false);
        return new AlumnoAdapter.AlumnoViewHolder(vista);
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
