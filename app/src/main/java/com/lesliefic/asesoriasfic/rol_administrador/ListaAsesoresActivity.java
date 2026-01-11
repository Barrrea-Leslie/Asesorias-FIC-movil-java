package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorParAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminListaAsesoresBinding;
import com.lesliefic.asesoriasfic.login.loginActivity;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.repositorios.AsesorParRepository;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;
import com.lesliefic.asesoriasfic.repositorios.UsuariosRepository;
import com.lesliefic.asesoriasfic.rol_administrador.crearAsesoresPar;

import com.lesliefic.asesoriasfic.adaptador.AsesorParAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAsesoresActivity extends DrawerBaseActivity {

    ActivityAdminListaAsesoresBinding activityListaAsesoresBinding;

    private List<AsesorPar> listaAsesores = new ArrayList<>();
    private List<Estudiante> listaEstudiantes = new ArrayList<>();

    private List<Materia> listaMaterias = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private List<Grupo> listaGrupos = new ArrayList<>();
    private List<Licenciatura> listaLicenciaturas = new ArrayList<>();
    private AsesorParAdapter adapter;
    private AsesorParRepository repoAsesorPar;
    private UsuariosRepository repoUsuarios;

    private CatalogosRepository repoCatalogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityListaAsesoresBinding = activityListaAsesoresBinding.inflate(getLayoutInflater());
        setContentView(activityListaAsesoresBinding.getRoot());

        repoAsesorPar = new AsesorParRepository();
        repoCatalogos = new CatalogosRepository();
        repoUsuarios = new UsuariosRepository();


        RecyclerView rv = findViewById(R.id.rvAsesores);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        adapter = new AsesorParAdapter(listaAsesores, asesorPar -> {
            Intent intent = new Intent(ListaAsesoresActivity.this, InformacionAsesoresParActivity.class);
            intent.putExtra("ASESOR_INFO", asesorPar);
            intent.putExtra("LISTA_GRUPOS", new ArrayList<>(listaGrupos));
            intent.putExtra("LISTA_LICENCIATURAS", new ArrayList<>(listaLicenciaturas));
            startActivity(intent);
        },
                (asesorPar, position) -> {
                    mostrarDialogoEliminar(asesorPar.getIdAsesor());
                }
                );



        rv.setAdapter(adapter);



        Button btnCrearAsesorPar;

        btnCrearAsesorPar = findViewById(R.id.btnCrearAsesor);

        btnCrearAsesorPar.setOnClickListener(v -> {

            Intent intent = new Intent(ListaAsesoresActivity.this, crearAsesoresPar.class);
            startActivity(intent);
        });

        obtenerAsesoresPar();
        obtenerCatalogos();


    }

    protected void onResume(){
        super.onResume();
        obtenerAsesoresPar();
    }

    public void toastNotificacion(String message){
        Toast.makeText(ListaAsesoresActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public void obtenerAsesoresPar(){
        repoAsesorPar.obtenerAsesoresPar(new AsesorParRepository.ResultCallback<List<AsesorPar>>() {
            @Override
            public void onSuccess(List<AsesorPar> data) {
                listaAsesores.clear();
                listaAsesores.addAll(data);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        ListaAsesoresActivity.this,
                        "Error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }

    public void obtenerCatalogos(){
        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        ListaAsesoresActivity.this,
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
                        ListaAsesoresActivity.this,
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
                        ListaAsesoresActivity.this,
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

    private void mostrarDialogoEliminar(int id_persona){

        View dialogView = getLayoutInflater().inflate(R.layout.admin_ventana_confirmacion_eliminar_usuario, null);

        AlertDialog dialog = new AlertDialog.Builder(ListaAsesoresActivity.this)
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