package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoriaAdapter;
import com.lesliefic.asesoriasfic.adaptador.GrupoSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.HorarioSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.LicenciaturaSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.MateriaSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.ModalidadSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.RazonSpinnerAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoriasEnCursoBinding;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Modalidad;
import com.lesliefic.asesoriasfic.modelo.Razon;
import com.lesliefic.asesoriasfic.repositorios.AsesoriasCursoRepository;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsesoriasEnCursoAdminActivity extends DrawerBaseActivity {


    ActivityAdminAsesoriasEnCursoBinding activityAdminAsesoriasEnCursoBinding;

    private List<Asesoria> listaAsesorias = new ArrayList<>();
    private List<Grupo> listaGrupos = new ArrayList<>();
    private List<Licenciatura> listaLicenciaturas = new ArrayList<>();
    private List<Modalidad> listaModalidades = new ArrayList<>();
    private List<Razon> listaRazones = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private List<Materia> listaMaterias = new ArrayList<>();
    CatalogosRepository repoCatalagos;

    private AsesoriaAdapter adapterlista;



    private GrupoSpinnerAdapter AdapSpGrupo;
    private LicenciaturaSpinnerAdapter AdapSpLicenciatura;

    private AsesoriasCursoRepository repoAsesorias;

    private Button btn_crear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminAsesoriasEnCursoBinding = activityAdminAsesoriasEnCursoBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAsesoriasEnCursoBinding.getRoot());


        RecyclerView rv = findViewById(R.id.rvAsesoriasEnCurso);
        rv.setLayoutManager(new LinearLayoutManager(this));

        repoAsesorias = new AsesoriasCursoRepository();
        repoCatalagos = new CatalogosRepository();

        btn_crear = findViewById(R.id.btn_crearAsesoria);



        adapterlista = new AsesoriaAdapter(
                listaAsesorias,


                (asesoria) -> {
                    Intent intent = new Intent(AsesoriasEnCursoAdminActivity.this, InformacionAsesoriaActivity.class);
                    intent.putExtra("ASESORIA_INFO", asesoria);
                    intent.putExtra("LISTA_MATERIAS", new ArrayList<>(listaMaterias));
                    intent.putExtra("LISTA_RAZONES", new ArrayList<>(listaRazones));
                    intent.putExtra("LISTA_MODALIDADES", new ArrayList<>(listaModalidades));
                    intent.putExtra("LISTA_HORARIOS", new ArrayList<>(listaHorarios));
                    startActivity(intent);
                },


                (asesoria, position) -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "completar",
                            Toast.LENGTH_SHORT
                    ).show();
                },


                (asesoria, position) -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "material",
                            Toast.LENGTH_SHORT
                    ).show();
                },
                (asesoria, position) -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "eliminar",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );


        obtenerAsesorias();
        obtenerCatalogos();

        rv.setAdapter(adapterlista);




        TextView opFiltros = findViewById(R.id.opFiltros);

        opFiltros.setOnClickListener(v -> {
            startActivity(new Intent(AsesoriasEnCursoAdminActivity.this, FiltrosAsesoriasEnCursoActivity.class));
        });

        btn_crear.setOnClickListener(v -> {
            Intent intent = new Intent(AsesoriasEnCursoAdminActivity.this, CrearAsesoria.class);
            intent.putExtra("LISTA_MATERIAS", new ArrayList<>(listaMaterias));
            intent.putExtra("LISTA_RAZONES", new ArrayList<>(listaRazones));
            intent.putExtra("LISTA_MODALIDADES", new ArrayList<>(listaModalidades));
            intent.putExtra("LISTA_HORARIOS", new ArrayList<>(listaHorarios));
            startActivity(intent);
        });



    }

    protected void onResume(){
        super.onResume();
        obtenerAsesorias();
    }

    public void obtenerAsesorias(){
        repoAsesorias.obtenerAsesorias(new AsesoriasCursoRepository.ResultCallback<List<Asesoria>>() {
            @Override
            public void onSuccess(List<Asesoria> data) {
                listaAsesorias.clear();
                listaAsesorias.addAll(data);
                adapterlista.notifyDataSetChanged();

            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void obtenerCatalogos() {


        repoCatalagos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        repoCatalagos.obtenerModalidades(new CatalogosRepository.ResultCallback<List<Modalidad>>() {
            @Override
            public void onSuccess(List<Modalidad> data) {
                listaModalidades.clear();
                listaModalidades.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        repoCatalagos.obtenerRazones(new CatalogosRepository.ResultCallback<List<Razon>>() {
            @Override
            public void onSuccess(List<Razon> data) {
                listaRazones.clear();
                listaRazones.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        repoCatalagos.obtenerHorarios(new CatalogosRepository.ResultCallback<List<Horario>>() {
            @Override
            public void onSuccess(List<Horario> data) {
                listaHorarios.clear();
                listaHorarios.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


    }

}



