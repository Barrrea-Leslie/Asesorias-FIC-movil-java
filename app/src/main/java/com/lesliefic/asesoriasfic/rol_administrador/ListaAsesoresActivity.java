package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorParAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminListaAsesoresBinding;
import com.lesliefic.asesoriasfic.login.loginActivity;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.network.ApiService;
import com.lesliefic.asesoriasfic.network.RetrofitClient;
import com.lesliefic.asesoriasfic.repositorios.AsesorParRepository;
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
    private AsesorParAdapter adapter;
    private AsesorParRepository repoAsesorPar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityListaAsesoresBinding = activityListaAsesoresBinding.inflate(getLayoutInflater());
        setContentView(activityListaAsesoresBinding.getRoot());

        repoAsesorPar = new AsesorParRepository();


        RecyclerView rv = findViewById(R.id.rvAsesores);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        adapter = new AsesorParAdapter(listaAsesores, asesorPar -> {
            Intent intent = new Intent(ListaAsesoresActivity.this, InformacionAsesoresParActivity.class);
            startActivity(intent);
        });

        rv.setAdapter(adapter);



        Button btnCrearAsesorPar;

        btnCrearAsesorPar = findViewById(R.id.btnCrearAsesor);

        btnCrearAsesorPar.setOnClickListener(v -> {

            Intent intent = new Intent(ListaAsesoresActivity.this, crearAsesoresPar.class);
            startActivity(intent);
        });

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



}