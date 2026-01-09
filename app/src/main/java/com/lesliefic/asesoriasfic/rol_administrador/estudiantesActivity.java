package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.EstudianteAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminEstudiantesBinding;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;
import com.lesliefic.asesoriasfic.repositorios.EstudiantesRepository;

import java.util.ArrayList;
import java.util.List;

public class estudiantesActivity extends DrawerBaseActivity {

    private EstudiantesRepository repoEstudiantes;
    private CatalogosRepository repoCatalogos;

    private List<Estudiante> listaEstudiantes = new ArrayList<>();
    private List<Grupo> listaGrupos = new ArrayList<>();
    private List<Licenciatura> listaLicenciaturas = new ArrayList<>();

    private EstudianteAdapter adapterLista;

    private Handler searchHandler = new Handler(Looper.getMainLooper());
    private Runnable searchRunnable;
    private static final long SEARCH_DELAY = 400;

    Button btn_crear_estudiante;

    EditText et_buscar;

    ActivityAdminEstudiantesBinding activityEstudiantesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEstudiantesBinding = activityEstudiantesBinding.inflate(getLayoutInflater());
        setContentView(activityEstudiantesBinding.getRoot());

        repoEstudiantes = new EstudiantesRepository();
        repoCatalogos = new CatalogosRepository();
        btn_crear_estudiante = findViewById(R.id.btnCrearEstudiante);

        RecyclerView rv = findViewById(R.id.rvEstudiantes);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapterLista = new EstudianteAdapter(listaEstudiantes, alumno -> {
            Intent intent = new Intent(estudiantesActivity.this, InformacionEstudiantesActivity.class);
            intent.putExtra("ESTUDIANTE_DATOS", alumno);
            intent.putExtra("LISTA_GRUPOS", new ArrayList<>(listaGrupos));
            intent.putExtra("LISTA_LICENCIATURAS", new ArrayList<>(listaLicenciaturas));
            startActivity(intent);
        });
        rv.setAdapter(adapterLista);

        btn_crear_estudiante.setOnClickListener(v -> {
            Intent intent = new Intent(estudiantesActivity.this, CrearEstudiantes.class);
            intent.putExtra("LISTA_GRUPOS", new ArrayList<>(listaGrupos));
            intent.putExtra("LISTA_LICENCIATURAS", new ArrayList<>(listaLicenciaturas));
            startActivity(intent);
        });

        et_buscar = findViewById(R.id.etBuscar);

        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(searchRunnable != null){
                    searchHandler.removeCallbacks(searchRunnable);
                }
                String texto = s.toString().trim();

                searchRunnable = () -> {
                    if(texto.isEmpty()){
                        obtenerEstudiantes();
                        return;
                    }

                    buscarEstudiantes();
                };


                searchHandler.postDelayed(searchRunnable, SEARCH_DELAY);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        obtenerCatalogos();




    }

    protected void onResume(){
        super.onResume();
        obtenerEstudiantes();
    }

    public void obtenerEstudiantes(){

        repoEstudiantes.obtenerEstudiantes(new EstudiantesRepository.ResultCallback<List<Estudiante>>() {
            @Override
            public void onSuccess(List<Estudiante> data) {
                listaEstudiantes.clear();
                listaEstudiantes.addAll(data);
                adapterLista.notifyDataSetChanged();
                Log.d("ESTUDIANTE", "ESTUDIANTE ENCONTRADOS:" + data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        estudiantesActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    public void buscarEstudiantes(){
        String busqueda = et_buscar.getText().toString().trim();


        repoEstudiantes.buscarEstudiantes(busqueda, new EstudiantesRepository.ResultCallback<List<Estudiante>>() {
            @Override
            public void onSuccess(List<Estudiante> data) {
                listaEstudiantes.clear();
                listaEstudiantes.addAll(data);
                adapterLista.notifyDataSetChanged();
                Log.d("BUSQUEDA", "onSuccess: " + data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        estudiantesActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    public void obtenerCatalogos(){

        repoCatalogos.obtenerGrupos(new CatalogosRepository.ResultCallback<List<Grupo>>() {
            @Override
            public void onSuccess(List<Grupo> data) {
                listaGrupos.clear();
                listaGrupos.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        estudiantesActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerLicenciaturas(new CatalogosRepository.ResultCallback<List<Licenciatura>>() {
            @Override
            public void onSuccess(List<Licenciatura> data) {
                listaLicenciaturas.clear();
                listaLicenciaturas.addAll(data);
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}