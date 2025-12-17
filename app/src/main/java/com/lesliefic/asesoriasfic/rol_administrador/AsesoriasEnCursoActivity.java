package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoriaAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoriasEnCursoBinding;
import com.lesliefic.asesoriasfic.modelo.AsesoriaRepository;
import com.lesliefic.asesoriasfic.modelo.Asesoria;

import java.util.List;

public class AsesoriasEnCursoActivity extends DrawerBaseActivity {


    ActivityAdminAsesoriasEnCursoBinding activityAdminAsesoriasEnCursoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminAsesoriasEnCursoBinding = activityAdminAsesoriasEnCursoBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAsesoriasEnCursoBinding.getRoot());

        RecyclerView rv = findViewById(R.id.rvAsesoriasEnCurso);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Asesoria> asesorias = AsesoriaRepository.getListaAsesorias(this);

        AsesoriaAdapter adapter = new AsesoriaAdapter(asesorias, asesoria -> {
            Intent intent = new Intent(AsesoriasEnCursoActivity.this, InformacionAsesoriaActivity.class);
            startActivity(intent);
        });
        rv.setAdapter(adapter);

        TextView opFiltros = findViewById(R.id.opFiltros);

        opFiltros.setOnClickListener(v -> {
            startActivity(new Intent(AsesoriasEnCursoActivity.this, FiltrosAsesoriasEnCursoActivity.class));
        });



    }
}

