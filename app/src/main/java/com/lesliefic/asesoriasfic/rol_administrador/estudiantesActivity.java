package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.EstudianteAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminEstudiantesBinding;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.EstudianteRepository;

import java.util.Arrays;
import java.util.List;

public class estudiantesActivity extends DrawerBaseActivity {

    ActivityAdminEstudiantesBinding activityEstudiantesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEstudiantesBinding = activityEstudiantesBinding.inflate(getLayoutInflater());
        setContentView(activityEstudiantesBinding.getRoot());



        RecyclerView rv = findViewById(R.id.rvEstudiantes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Estudiante> estudiantes = EstudianteRepository.getEstudiantes(this);
        EstudianteAdapter adapter = new EstudianteAdapter(estudiantes, alumno -> {
            Intent intent = new Intent(estudiantesActivity.this, InformacionEstudiantesActivity.class);
            startActivity(intent);
        });
        rv.setAdapter(adapter);


    }
}