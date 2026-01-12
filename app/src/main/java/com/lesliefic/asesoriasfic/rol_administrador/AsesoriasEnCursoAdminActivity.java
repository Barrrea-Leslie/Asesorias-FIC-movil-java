package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoriaAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoriasEnCursoBinding;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.repositorios.AsesoriasCursoRepository;

import java.util.ArrayList;
import java.util.List;

public class AsesoriasEnCursoAdminActivity extends DrawerBaseActivity {


    ActivityAdminAsesoriasEnCursoBinding activityAdminAsesoriasEnCursoBinding;

    private List<Asesoria> listaAsesorias = new ArrayList<>();

    private AsesoriaAdapter adapterlista;

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

        btn_crear = findViewById(R.id.btn_crearAsesoria);

        adapterlista = new AsesoriaAdapter(
                listaAsesorias,


                (asesoria) -> {
                    Intent intent = new Intent(AsesoriasEnCursoAdminActivity.this, InformacionAsesoriaActivity.class);
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

        rv.setAdapter(adapterlista);




        TextView opFiltros = findViewById(R.id.opFiltros);

        opFiltros.setOnClickListener(v -> {
            startActivity(new Intent(AsesoriasEnCursoAdminActivity.this, FiltrosAsesoriasEnCursoActivity.class));
        });

        btn_crear.setOnClickListener(v -> {
            Intent intent = new Intent(AsesoriasEnCursoAdminActivity.this, CrearAsesoria.class);
            startActivity(intent);
        });



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
}



