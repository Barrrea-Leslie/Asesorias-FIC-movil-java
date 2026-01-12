package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.HorarioAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioElegidoAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaElegidaAdapter;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.SolicitudPendiente;
import com.lesliefic.asesoriasfic.repositorios.AsesorDisciplinarRepository;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;

import java.util.ArrayList;
import java.util.List;

public class InformacionSolicitudesActivity extends AppCompatActivity {

    private SolicitudPendiente datosSolicitud;
    private ArrayList<SolicitudPendiente> listaSolicitud = new ArrayList<>();


    EditText campoAsesor, campoEstudiante, campoMateria, campoModalidad, campoHorario, campoFecha,
    campoRazon, campoNota
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_admin_informacion_solicitud);

        datosSolicitud = (SolicitudPendiente) getIntent().getSerializableExtra("SOLICITUD_INFO");


        if (datosSolicitud == null) {
            finish();
            return;
        }

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);


        campoEstudiante = findViewById(R.id.campoEstudiante);
        campoAsesor = findViewById(R.id.campoAsesor);
        campoMateria = findViewById(R.id.campoMateria);
        campoModalidad = findViewById(R.id.campoModalidad);
        campoHorario = findViewById(R.id.campoHorario);
        campoFecha = findViewById(R.id.campoFecha);
        campoRazon = findViewById(R.id.campoRazon);
        campoNota = findViewById(R.id.campoNota);

        campoEstudiante.setText(datosSolicitud.getEstudiante());
        campoAsesor.setText(datosSolicitud.getAsesor());
        campoMateria.setText(datosSolicitud.getMateria());
        campoModalidad.setText(datosSolicitud.getModalidad());
        campoHorario.setText(datosSolicitud.getHorario());
        campoFecha.setText(datosSolicitud.getFecha_inicio());
        campoRazon.setText(datosSolicitud.getRazon());
        campoNota.setText(datosSolicitud.getNota_estudiante());


        btnRegresar.setOnClickListener(v -> finish());





    }




}