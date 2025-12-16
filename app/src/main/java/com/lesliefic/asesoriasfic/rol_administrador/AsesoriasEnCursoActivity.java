package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoriaAdapter;
import com.lesliefic.asesoriasfic.adaptador.SolicitudAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoriasEnCursoBinding;
import com.lesliefic.asesoriasfic.modelo.Alumno;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.Solicitud;

import java.util.ArrayList;
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
        List<Asesoria> asesorias = generarAsesoria();

        AsesoriaAdapter adapter = new AsesoriaAdapter(asesorias);
        rv.setAdapter(adapter);

    }

    private List<Asesoria> generarAsesoria() {

        Alumno luis = new Alumno("Luis Fernando Velazquez Araujo");
        Alumno jenifer = new Alumno("Jenifer Guadalupe Tizoc Lopez");

        List<Asesoria> lista = new ArrayList<>();

        lista.add(new Asesoria(
                luis,
                "Programacion",
                "15/12/2025",
                "18:00 - 19:00",
                "Virtual"
        ));

        lista.add(new Asesoria(
                jenifer,
                "Algebra Lineal",
                "16/12/2025",
                "10:00 - 11:30",
                "Presencial"
        ));

        return lista;

    }

}