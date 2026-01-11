package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;

import java.util.List;

public class AsesorParAdapter extends RecyclerView.Adapter<AsesorParAdapter.AsesorViewHolder> {

    private List<AsesorPar> lista;
    private OnAsesorClickListener listener;
    private OnEliminarClickListener eliminarListener;

    public interface OnAsesorClickListener {
        void onClick(AsesorPar asesorPar);
    }


    public interface OnEliminarClickListener {
        void onEliminar(AsesorPar asesorPar, int position);
    }

    public AsesorParAdapter(
            List<AsesorPar> lista,
            OnAsesorClickListener listener,
            OnEliminarClickListener eliminarListener
    ) {
        this.lista = lista;
        this.listener = listener;
        this.eliminarListener = eliminarListener;
    }

    public class AsesorViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageButton btnEliminar;

        public AsesorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreAsesor);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
        }

        public void bind(final AsesorPar asesorPar) {
            txtNombre.setText(asesorPar.getNombre_completo());

            // click del item (ver info)
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onClick(asesorPar);
            });


            btnEliminar.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && eliminarListener != null) {
                    eliminarListener.onEliminar(asesorPar, pos);
                }
            });
        }
    }

    @NonNull
    @Override
    public AsesorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesor, parent, false);
        return new AsesorViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesorViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}