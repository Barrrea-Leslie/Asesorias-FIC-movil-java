package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Asesor;

import java.util.List;

public class AsesorAdapter extends RecyclerView.Adapter<AsesorAdapter.AsesorViewHolder>{
    private List<Asesor> lista;
    private OnAsesorClickListener listener;
    public interface OnAsesorClickListener {
        void onClick(Asesor asesor);
    }
    public AsesorAdapter (List<Asesor> lista,OnAsesorClickListener listener){
        this.lista=lista;
        this.listener=listener;
    }
    public class AsesorViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        public AsesorViewHolder (@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreAsesor);
        }
        public void bind(final Asesor asesor) {
            txtNombre.setText(asesor.getNombre());
            itemView.setOnClickListener(v -> listener.onClick(asesor));
        }
    }

    @NonNull
    @Override
    public AsesorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View vista= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor,parent,false);
        return new AsesorViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesorViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }
}
