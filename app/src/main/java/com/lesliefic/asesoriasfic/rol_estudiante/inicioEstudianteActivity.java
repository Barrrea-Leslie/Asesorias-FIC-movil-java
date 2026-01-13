package com.lesliefic.asesoriasfic.rol_estudiante;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.SolicitarAsesoriaAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteInicioBinding;
import com.lesliefic.asesoriasfic.modelo.AsesoresEstudiante;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Modalidad;
import com.lesliefic.asesoriasfic.modelo.Razon;
import com.lesliefic.asesoriasfic.network.request.estudiante.FiltrarSolicitarRequest;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;
import com.lesliefic.asesoriasfic.repositorios.SolicitarAsesoriaRepository;
import com.lesliefic.asesoriasfic.rol_administrador.AsesoriasEnCursoAdminActivity;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;
import com.lesliefic.asesoriasfic.rol_administrador.FiltrosAsesoriasEnCursoActivity;
import com.lesliefic.asesoriasfic.rol_administrador.InformacionAsesoresDisciplinarActivity;

import java.util.ArrayList;
import java.util.List;


public class inicioEstudianteActivity extends DrawerBaseActivity {

    private CatalogosRepository repoCatalogos;
    private SolicitarAsesoriaRepository repoSolicitar;

    private SolicitarAsesoriaAdapter adapterSolicitar;

    private int filtrarMateria;
    private int filtrarHorario;

    private List<AsesoresEstudiante> listaAsesores = new ArrayList<>();

    private final List<Materia> listaMaterias = new ArrayList<>();
    private final List<Razon> listaRazones = new ArrayList<>();
    private final List<Modalidad> listaModalidades = new ArrayList<>();
    private final List<Horario> listaHorarios = new ArrayList<>();

    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("filtros", MODE_PRIVATE);
        filtrarMateria = sp.getInt("id_materia", -1);
        filtrarHorario = sp.getInt("id_horario", -1);
        aplicarFiltros();
    }

    private ActivityEstudianteInicioBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudianteInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        repoCatalogos = new CatalogosRepository();
        repoSolicitar = new SolicitarAsesoriaRepository();

        RecyclerView rv = findViewById(R.id.rvAsesoriasEnCurso);
        rv.setLayoutManager(new LinearLayoutManager(this));


        binding.btnCrearSolicitud.setOnClickListener(v -> {
            Intent intent = new Intent(inicioEstudianteActivity.this, estudiante_solicitar_nueva_asesoria.class);

            intent.putExtra("LISTA_MATERIAS", new ArrayList<>(listaMaterias));
            intent.putExtra("LISTA_RAZONES", new ArrayList<>(listaRazones));
            intent.putExtra("LISTA_MODALIDADES", new ArrayList<>(listaModalidades));
            intent.putExtra("LISTA_HORARIOS", new ArrayList<>(listaHorarios));

            startActivity(intent);
        });

        binding.opFiltros.setOnClickListener(v -> {
            Intent intent = new Intent(inicioEstudianteActivity.this, estudiante_filtros_solicitar_asesorias.class);
            intent.putExtra("LISTA_MATERIAS", new ArrayList<>(listaMaterias));
            intent.putExtra("LISTA_RAZONES", new ArrayList<>(listaRazones));
            intent.putExtra("LISTA_MODALIDADES", new ArrayList<>(listaModalidades));
            intent.putExtra("LISTA_HORARIOS", new ArrayList<>(listaHorarios));
            startActivity(intent);
        });

        binding.opQuitarFiltros.setOnClickListener( v -> {
            obtenerAsesores();
        });

        adapterSolicitar =
                new SolicitarAsesoriaAdapter(
                        listaAsesores,

                        // 1️⃣ Click a la tarjeta
                        asesor -> {

                        },

                        // 2️⃣ Botón "Ver Info"
                        (asesor, position) -> {
                            Intent intent = new Intent(inicioEstudianteActivity.this,  EstudiantesInformacionSolicitarAsesoriasActivity.class);
                            intent.putExtra("ASESOR_INFO", asesor);
                            startActivity(intent);
                        },

                        // 3️⃣ Botón "Solicitar Asesoría"
                        (asesor, position) -> {
                            Intent intent = new Intent(inicioEstudianteActivity.this, estudiante_solicitar_nueva_asesoria.class);
                            intent.putExtra("ASESOR_INFO", asesor);
                            intent.putExtra("LISTA_MATERIAS", new ArrayList<>(listaMaterias));
                            intent.putExtra("LISTA_RAZONES", new ArrayList<>(listaRazones));
                            intent.putExtra("LISTA_MODALIDADES", new ArrayList<>(listaModalidades));
                            intent.putExtra("LISTA_HORARIOS", new ArrayList<>(listaHorarios));
                            startActivity(intent);
                        }
                );

        rv.setAdapter(adapterSolicitar);
        obtenerCatalogos();
        obtenerAsesores();
    }

    public void obtenerCatalogos() {
        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }
            @Override public void onError(String error) {
                Toast.makeText(getApplicationContext(), "error:" + error, Toast.LENGTH_SHORT).show();
            }
        });

        repoCatalogos.obtenerModalidades(new CatalogosRepository.ResultCallback<List<Modalidad>>() {
            @Override public void onSuccess(List<Modalidad> data) {
                listaModalidades.clear();
                listaModalidades.addAll(data);
            }
            @Override public void onError(String error) {
                Toast.makeText(getApplicationContext(), "error:" + error, Toast.LENGTH_SHORT).show();
            }
        });

        repoCatalogos.obtenerRazones(new CatalogosRepository.ResultCallback<List<Razon>>() {
            @Override public void onSuccess(List<Razon> data) {
                listaRazones.clear();
                listaRazones.addAll(data);
            }
            @Override public void onError(String error) {
                Toast.makeText(getApplicationContext(), "error:" + error, Toast.LENGTH_SHORT).show();
            }
        });

        repoCatalogos.obtenerHorarios(new CatalogosRepository.ResultCallback<List<Horario>>() {
            @Override public void onSuccess(List<Horario> data) {
                listaHorarios.clear();
                listaHorarios.addAll(data);
            }
            @Override public void onError(String error) {
                Toast.makeText(getApplicationContext(), "error:" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void aplicarFiltros(){


        FiltrarSolicitarRequest request = new FiltrarSolicitarRequest(filtrarMateria, filtrarHorario);
        repoSolicitar.obtenerAsesoresFiltros(request, new SolicitarAsesoriaRepository.ResultCallback<List<AsesoresEstudiante>>() {
            @Override
            public void onSuccess(List<AsesoresEstudiante> data) {
                listaAsesores.clear();
                listaAsesores.addAll(data);
                adapterSolicitar.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    public void obtenerAsesores(){
        repoSolicitar.obtenerAsesoresEstudiante(new SolicitarAsesoriaRepository.ResultCallback<List<AsesoresEstudiante>>() {
            @Override
            public void onSuccess(List<AsesoresEstudiante> data) {
                listaAsesores.clear();
                listaAsesores.addAll(data);
                adapterSolicitar.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
