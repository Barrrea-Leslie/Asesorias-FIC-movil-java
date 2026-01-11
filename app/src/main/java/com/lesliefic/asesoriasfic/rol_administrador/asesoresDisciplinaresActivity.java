package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorDisciplinarAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoresDisciplinaresBinding;
import com.lesliefic.asesoriasfic.modelo.AsesorDisciplinar;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.repositorios.AsesorDisciplinarRepository;
import com.lesliefic.asesoriasfic.repositorios.AsesorParRepository;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;
import com.lesliefic.asesoriasfic.repositorios.EstudiantesRepository;
import com.lesliefic.asesoriasfic.repositorios.UsuariosRepository;

import java.util.ArrayList;
import java.util.List;

public class asesoresDisciplinaresActivity extends DrawerBaseActivity {

    ActivityAdminAsesoresDisciplinaresBinding activityAdminAsesoresDisciplinaresBinding;

    private final List<AsesorDisciplinar> listaAsesoresDisciplinares = new ArrayList<>();

    private final List<Materia> listaMaterias = new ArrayList<>();
    private final List<Horario> listaHorarios = new ArrayList<>();
    private final List<Grupo> listaGrupos = new ArrayList<>();
    private final List<Licenciatura> listaLicenciaturas = new ArrayList<>();

    EditText et_buscar;

    private AsesorDisciplinarAdapter adapter;
    private AsesorDisciplinarRepository repoAsesoresDisciplinares;
    private CatalogosRepository repoCatalogos;
    private UsuariosRepository repoUsuarios;

    private Handler searchHandler = new Handler(Looper.getMainLooper());
    private Runnable searchRunnable;
    private static final long SEARCH_DELAY = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminAsesoresDisciplinaresBinding = activityAdminAsesoresDisciplinaresBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAsesoresDisciplinaresBinding.getRoot());


        repoCatalogos = new CatalogosRepository();
        repoAsesoresDisciplinares = new AsesorDisciplinarRepository();
        repoUsuarios = new UsuariosRepository();


        et_buscar = findViewById(R.id.etBuscar);

        RecyclerView rv = findViewById(R.id.rvAsesores);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AsesorDisciplinarAdapter(listaAsesoresDisciplinares,
                asesorDisciplinar -> {
            Intent intent = new Intent(
                    asesoresDisciplinaresActivity.this,
                    InformacionAsesoresDisciplinarActivity.class
            );

            intent.putExtra("ASESOR_INFO", asesorDisciplinar);

            startActivity(intent);
        },
            (asesorDisciplinar, position) -> {
                mostrarDialogoEliminar(asesorDisciplinar.getId_persona());
            }
            );

        rv.setAdapter(adapter);


        Button btnCrearAsesorDisciplinar = findViewById(R.id.btnCrearAsesor);
        btnCrearAsesorDisciplinar.setOnClickListener(v -> {
            Intent intent = new Intent(
                    asesoresDisciplinaresActivity.this,
                    crearAsesoresDisciplinares.class //
            );
            startActivity(intent);
        });


        obtenerCatalogos();

        et_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(searchRunnable != null){
                    searchHandler.removeCallbacks(searchRunnable);
                }
                String texto = s.toString().trim();

                searchRunnable = () -> {
                    if(texto.isEmpty()){
                        buscarAsesores();
                        return;
                    }

                    buscarAsesores();
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarAsesores();

    }

    public void toastNotificacion(String message) {
        Toast.makeText(asesoresDisciplinaresActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void buscarAsesores(){
        String busqueda = et_buscar.getText().toString().trim();

        repoAsesoresDisciplinares.buscarAsesorDisci(busqueda, new EstudiantesRepository.ResultCallback<List<AsesorDisciplinar>>() {
            @Override
            public void onSuccess(List<AsesorDisciplinar> data) {
                listaAsesoresDisciplinares.clear();
                listaAsesoresDisciplinares.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        asesoresDisciplinaresActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }



    public void obtenerCatalogos() {
        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        asesoresDisciplinaresActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerHorarios(new CatalogosRepository.ResultCallback<List<Horario>>() {
            @Override
            public void onSuccess(List<Horario> data) {
                listaHorarios.clear();
                listaHorarios.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        asesoresDisciplinaresActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerGrupos(new CatalogosRepository.ResultCallback<List<Grupo>>() {
            @Override
            public void onSuccess(List<Grupo> data) {
                listaGrupos.clear();
                listaGrupos.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        asesoresDisciplinaresActivity.this,
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
                Toast.makeText(
                        asesoresDisciplinaresActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


    }
    private void mostrarDialogoEliminar(int id_persona){

        View dialogView = getLayoutInflater().inflate(R.layout.admin_ventana_confirmacion_eliminar_usuario, null);

        AlertDialog dialog = new AlertDialog.Builder(asesoresDisciplinaresActivity.this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        Button btnCancelar = dialogView.findViewById(R.id.btnCancelar);
        Button btnAceptar = dialogView.findViewById(R.id.btnAceptar);

        btnCancelar.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnAceptar.setOnClickListener(v -> {
            eliminarUsuario(id_persona);
            dialog.dismiss();
            onResume();
        });

        dialog.show();
    }

    public void eliminarUsuario(int id_persona){
        repoUsuarios.eliminarUsuario(id_persona, new UsuariosRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    Toast.makeText(getApplicationContext(), "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(), "error:" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}