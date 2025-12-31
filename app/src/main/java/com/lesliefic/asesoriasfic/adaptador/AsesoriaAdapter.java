package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Estudiante;

import java.util.List;

public class AsesoriaAdapter extends RecyclerView.Adapter<AsesoriaAdapter.AsesoriaViewHolder> {

    private List<Asesoria> lista;
    private OnItemButtonClickListener listener;

    public interface OnItemButtonClickListener {
        void onButtonClick(Asesoria asesoria);
    }

    public AsesoriaAdapter(List<Asesoria> lista, OnItemButtonClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public static class AsesoriaViewHolder extends RecyclerView.ViewHolder {

        TextView nombreAlumno;
        TextView materia;
        TextView fecha;
        TextView horario;
        TextView modalidad;
        Button btn_infoAsesorias;

        public AsesoriaViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreAlumno = itemView.findViewById(R.id.nombreAlumno);
            materia = itemView.findViewById(R.id.materia);
            fecha = itemView.findViewById(R.id.fecha);
            horario = itemView.findViewById(R.id.horario);
            modalidad = itemView.findViewById(R.id.modalidad);
            btn_infoAsesorias = itemView.findViewById(R.id.btn_infoAsesorias);
        }

        public void bind(final Asesoria asesoria, final OnItemButtonClickListener listener) {


            String nombre = "Sin estudiante";
            Estudiante e = null;
            if (asesoria != null) e = asesoria.getEstudiante();
            if (e != null && e.getNombre() != null && !e.getNombre().trim().isEmpty()) {
                nombre = e.getNombre();
            }
            nombreAlumno.setText(nombre);


            materia.setText(getSafeText(asesoria != null ? asesoria.getMateria() : null));
            fecha.setText(getSafeText(asesoria != null ? asesoria.getFecha() : null));
            horario.setText(getSafeText(asesoria != null ? asesoria.getHorario() : null));
            modalidad.setText(getSafeText(asesoria != null ? asesoria.getModalidad() : null));


            btn_infoAsesorias.setOnClickListener(v -> {
                if (listener != null && asesoria != null) {
                    listener.onButtonClick(asesoria);
                }
            });
        }

        private String getSafeText(String value) {
            if (value == null) return "—";
            String v = value.trim();
            return v.isEmpty() ? "—" : v;
        }
    }

    @NonNull
    @Override
    public AsesoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesorias_en_curso, parent, false);
        return new AsesoriaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesoriaViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return (lista == null) ? 0 : lista.size();
    }


    public void setLista(List<Asesoria> nuevaLista) {
        this.lista = nuevaLista;
        notifyDataSetChanged();
    }
}