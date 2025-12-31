package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Horario;

import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder> {

    public interface OnHorarioClickListener {
        void onClick(Horario horario);
    }

    private List<Horario> lista;
    private OnHorarioClickListener listener;

    public HorarioAdapter(List<Horario> lista, OnHorarioClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtHorario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHorario = itemView.findViewById(R.id.txtHorario);
        }

        public void bind(Horario horario, OnHorarioClickListener listener) {
            txtHorario.setText(horario.getHorario());
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onClick(horario);
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elegir_horario, parent, false);
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

    public void setItems(List<Horario> nuevos) {
        this.lista = nuevos;
        notifyDataSetChanged();
    }
}