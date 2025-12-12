package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.AsesorDiciplinar;

import java.util.List;

public class AsesorDiciplinarAdapter extends RecyclerView.Adapter<AsesorDiciplinarAdapter.AsesorDiciplinarViewHolder> {

    private List<AsesorDiciplinar> listaAsesores;

    private OnAsesorDiciplinarClickListener listener;

    //Para un futuro evento de click
    public interface OnAsesorDiciplinarClickListener {
        void onClick(AsesorDiciplinar asesorDiciplinar);
    }

    //Constructor de la clase de adaptador
    public AsesorDiciplinarAdapter(List<AsesorDiciplinar> listaAsesores, OnAsesorDiciplinarClickListener listener) {
        this.listaAsesores = listaAsesores;
        this.listener = listener;
    }

    public class AsesorDiciplinarViewHolder extends RecyclerView.ViewHolder {

        TextView textNombre;

        public AsesorDiciplinarViewHolder (@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.txtNombreAsesor);
        }

        public void bind(final AsesorDiciplinar asesorDiciplinar){
            textNombre.setText(asesorDiciplinar.getNombre());
            itemView.setOnClickListener(v -> listener.onClick(asesorDiciplinar));
        }

    }

    @NonNull
    @Override
    public AsesorDiciplinarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor,parent,false);
        return new AsesorDiciplinarViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesorDiciplinarAdapter.AsesorDiciplinarViewHolder holder, int position) {
        holder.bind(listaAsesores.get(position));
    }

    @Override
    public int getItemCount() {
        return listaAsesores.size();
    }


}
