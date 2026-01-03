package com.lesliefic.asesoriasfic.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Solicitud;

import java.util.List;

public class AsesoriaAdapter extends RecyclerView.Adapter<AsesoriaAdapter.AsesoriaViewHolder> {

    private List<Asesoria> lista;
    private OnItemButtonClickListener listener;

    public interface OnItemButtonClickListener {
        void onInfoClick(Asesoria asesoria);
        void onCompletarAsesoria(Asesoria asesoria);

        void onMaterialClick(Asesoria asesoria);

        void onEliminaAsesoria(Asesoria asesoria);
    }

    public AsesoriaAdapter(List<Asesoria> lista, OnItemButtonClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    public class AsesoriaViewHolder extends RecyclerView.ViewHolder{

        TextView nombreAlumno;
        TextView materia;
        TextView fecha;
        TextView horario;
        TextView modalidad;

        Button btn_infoAsesorias;
        Button btn_completarAsesoria;
        Button btn_materialAdicional;
        ImageButton btn_eliminar;
        public AsesoriaViewHolder (@NonNull View itemView) {

            super(itemView);

            nombreAlumno = itemView.findViewById(R.id.nombreAlumno);
            materia = itemView.findViewById(R.id.materia);
            fecha = itemView.findViewById(R.id.fecha);
            horario = itemView.findViewById(R.id.horario);
            modalidad = itemView.findViewById(R.id.modalidad);

            btn_infoAsesorias = itemView.findViewById(R.id.btn_infoAsesorias);
            btn_completarAsesoria = itemView.findViewById(R.id.btn_completarAsesoria);
            btn_materialAdicional = itemView.findViewById(R.id.btn_materialAdicional);
            btn_eliminar = itemView.findViewById(R.id.btn_eliminar);
        }

        public void bind(final Asesoria asesoria) {

            if (asesoria != null && asesoria.getEstudiante() != null) {
                String nombre = asesoria.getEstudiante().getNombre();
                nombreAlumno.setText(nombre != null ? nombre : "Sin nombre");
            } else {
                nombreAlumno.setText("Sin alumno");
            }

            materia.setText(asesoria.getMateria() != null ? asesoria.getMateria() : "");
            fecha.setText(asesoria.getFechaInicio() != null ? asesoria.getFechaInicio() : "");
            horario.setText(asesoria.getHorario() != null ? asesoria.getHorario() : "");
            modalidad.setText(asesoria.getModalidad() != null ? asesoria.getModalidad() : "");

            btn_infoAsesorias.setOnClickListener(v -> {
                if (listener != null) listener.onInfoClick(asesoria);
            });

            btn_completarAsesoria.setOnClickListener(v -> {
                if (listener != null) listener.onCompletarAsesoria(asesoria);
            });

            btn_materialAdicional.setOnClickListener(v -> {
                if (listener != null) listener.onMaterialClick(asesoria);
            });

            btn_eliminar.setOnClickListener(v -> {
                if (listener != null) listener.onEliminaAsesoria(asesoria);
            });
        }

    }

    @NonNull
    @Override
    public AsesoriaAdapter.AsesoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_asesorias_en_curso, parent, false);
        return new AsesoriaAdapter.AsesoriaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AsesoriaAdapter.AsesoriaViewHolder holder, int position) {
        holder.bind(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
