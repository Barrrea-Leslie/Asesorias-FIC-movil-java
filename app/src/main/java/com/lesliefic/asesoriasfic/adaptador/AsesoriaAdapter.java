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
import com.lesliefic.asesoriasfic.modelo.Solicitud;

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

    public class AsesoriaViewHolder extends RecyclerView.ViewHolder{

        TextView nombreAlumno;
        TextView materia;
        TextView fecha;
        TextView horario;
        TextView modalidad;

        Button btn_infoAsesorias;
        public AsesoriaViewHolder (@NonNull View itemView) {

            super(itemView);

            nombreAlumno = itemView.findViewById(R.id.nombreAlumno);
            materia = itemView.findViewById(R.id.materia);
            fecha = itemView.findViewById(R.id.fecha);
            horario = itemView.findViewById(R.id.horario);
            modalidad = itemView.findViewById(R.id.modalidad);

            btn_infoAsesorias = itemView.findViewById(R.id.btn_infoAsesorias);

        }

        public void bind(final Asesoria asesoria) {

            nombreAlumno.setText(asesoria.getEstudiante().getNombre());
            materia.setText(asesoria.getMateria());
            fecha.setText(asesoria.getFecha());
            horario.setText(asesoria.getHorario());
            modalidad.setText(asesoria.getModalidad());

            btn_infoAsesorias.setOnClickListener(v -> {
                if (listener != null){
                    listener.onButtonClick(asesoria);
                }
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
