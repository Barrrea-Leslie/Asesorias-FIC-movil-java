package com.lesliefic.asesoriasfic.rol_estudiante;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.HorarioSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.ModalidadSpinnerAdapter;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Modalidad;
import com.lesliefic.asesoriasfic.modelo.Razon;
import com.lesliefic.asesoriasfic.network.request.estudiante.FiltrarSolicitarRequest;
import com.lesliefic.asesoriasfic.repositorios.SolicitarAsesoriaRepository;

import java.util.ArrayList;
import java.util.List;

public class estudiante_filtros_solicitar_asesorias extends AppCompatActivity {

    private Spinner spDiaSemana, spMateria, spHorario, spModalidad;
    private Button btnAplicar;
    private ImageButton btnRegresar;

    private SolicitarAsesoriaRepository repoSolicitar;

    private List<Materia> listaMaterias = new ArrayList<>();
    private List<Materia> listaMateriasAsesor = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private List<Modalidad> listaModalidades = new ArrayList<>();

    private MateriaSpinnerAdapter adapSpMateria;
    private HorarioSpinnerAdapter adapSpHorario;
    private ModalidadSpinnerAdapter adapSpModalidad;

    private int filtrarMateria;
    private int filtrarHorario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes_filtros_solicitar_asesorias);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnAplicar = findViewById(R.id.btnFiltro);

        repoSolicitar = new SolicitarAsesoriaRepository();


        spMateria = findViewById(R.id.spMateria);
        spHorario = findViewById(R.id.spHorario);




        // Listas desde Intent (igual que en CrearAsesoria)
        if (getIntent() != null) {
            Object extraMaterias = getIntent().getSerializableExtra("LISTA_MATERIAS");
            Object extraHorarios = getIntent().getSerializableExtra("LISTA_HORARIOS");
            Object extraModalidades = getIntent().getSerializableExtra("LISTA_MODALIDADES");

            if (extraMaterias instanceof ArrayList) listaMaterias = (ArrayList<Materia>) extraMaterias;
            if (extraHorarios instanceof ArrayList) listaHorarios = (ArrayList<Horario>) extraHorarios;
            if (extraModalidades instanceof ArrayList) listaModalidades = (ArrayList<Modalidad>) extraModalidades;
        }

        if (listaMaterias == null) listaMaterias = new ArrayList<>();
        if (listaHorarios == null) listaHorarios = new ArrayList<>();
        if (listaModalidades == null) listaModalidades = new ArrayList<>();




        adapSpMateria = new MateriaSpinnerAdapter(this, listaMaterias);
        spMateria.setAdapter(adapSpMateria);

        adapSpHorario = new HorarioSpinnerAdapter(this, listaHorarios);
        spHorario.setAdapter(adapSpHorario);



        adapSpMateria.notifyDataSetChanged();
        adapSpHorario.notifyDataSetChanged();


        btnRegresar.setOnClickListener(v -> finish());
        btnAplicar.setOnClickListener(v -> {
            aplicarFiltros();
            finish();
        });
    }

    public void aplicarFiltros(){
        Materia materia = (Materia) spMateria.getSelectedItem();
        Horario horario = (Horario) spHorario.getSelectedItem();


        filtrarMateria= materia.getId_materia();
        filtrarHorario = horario.getId_horario();


        FiltrarSolicitarRequest request = new FiltrarSolicitarRequest(filtrarMateria, filtrarHorario);
        repoSolicitar.obtenerAsesoresFiltros(request, new SolicitarAsesoriaRepository.ResultCallback<List<AsesoresEstudiante>>() {
            @Override
            public void onSuccess(List<AsesoresEstudiante> data) {
                guardarFiltros();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void guardarFiltros(){
        SharedPreferences sp = getSharedPreferences("filtros", MODE_PRIVATE);
        sp.edit().putInt("id_materia", filtrarMateria).putInt("id_horario", filtrarHorario).apply();
        finish();
    }

    private void seleccionarPorIdMateria(Spinner spinner, int idBuscado) {
        if (spinner.getAdapter() == null) return;

        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Materia m = (Materia) spinner.getAdapter().getItem(i);
            if (m != null && m.getId_materia() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    private void seleccionarPorIdHorario(Spinner spinner, int idBuscado) {
        if (spinner.getAdapter() == null) return;

        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Horario h = (Horario) spinner.getAdapter().getItem(i);
            if (h != null && h.getId_horario() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }
}