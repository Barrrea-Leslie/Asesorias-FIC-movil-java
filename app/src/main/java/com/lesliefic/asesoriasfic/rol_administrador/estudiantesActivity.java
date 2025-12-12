package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AlumnoAdapter;
import com.lesliefic.asesoriasfic.adaptador.AsesorParAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminEstudiantesBinding;
import com.lesliefic.asesoriasfic.modelo.Alumno;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;

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
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Jose Angel Astorga Mejía"),
                new Alumno("Leslie Mayram Barrera"),
                new Alumno("Jenifer Tizoc Lopez"),
                new Alumno("Jenifer Tizoc Lopez")
        );
        AlumnoAdapter adapter = new AlumnoAdapter(alumnos, alumno ->
                Toast.makeText(this, "click" + alumno.getNombre(), Toast.LENGTH_SHORT).show());
        rv.setAdapter(adapter);


    }
}