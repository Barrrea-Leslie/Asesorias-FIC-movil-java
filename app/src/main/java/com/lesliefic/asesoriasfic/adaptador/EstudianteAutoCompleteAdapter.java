package com.lesliefic.asesoriasfic.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Estudiante;

import java.util.List;

public class EstudianteAutoCompleteAdapter extends ArrayAdapter<Estudiante> {

    private final List<Estudiante> lista;

    public interface OnItemClickListener {
        void onClick(Estudiante estudiante);
    }

    private final OnItemClickListener listener;

    public EstudianteAutoCompleteAdapter(
            @NonNull Context context,
            @NonNull List<Estudiante> lista,
            @Nullable OnItemClickListener listener
    ) {
        super(context, 0, lista);
        this.lista = lista;
        this.listener = listener;
    }

    public class EstudianteViewHolder {
        TextView txtUsuario;
        View itemView;

        public EstudianteViewHolder(@NonNull View itemView) {
            this.itemView = itemView;
            txtUsuario = itemView.findViewById(R.id.txtUsuario);
        }

        public void bind(Estudiante e) {
            if (e != null) {
                txtUsuario.setText(e.getNombreCompleto());
            }

            itemView.setOnClickListener(v -> {
                if (listener != null && e != null) {
                    listener.onClick(e);
                }
            });
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_elegir_usuario, parent, false);
        }

        EstudianteViewHolder holder = new EstudianteViewHolder(convertView);
        holder.bind(lista.get(position));

        return convertView;
    }
}