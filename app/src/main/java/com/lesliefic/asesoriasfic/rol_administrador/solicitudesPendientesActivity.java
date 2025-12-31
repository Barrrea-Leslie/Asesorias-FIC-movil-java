package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.SolicitudAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminSolicitudesPendientesBinding;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class solicitudesPendientesActivity extends DrawerBaseActivity {

    ActivityAdminSolicitudesPendientesBinding activitySolicitudesPendientesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySolicitudesPendientesBinding = activitySolicitudesPendientesBinding.inflate(getLayoutInflater());
        setContentView(activitySolicitudesPendientesBinding.getRoot());

        RecyclerView rv = findViewById(R.id.rvSolicitudes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Solicitud> solicitudes = generarSolicitudes();

        SolicitudAdapter adapter = new SolicitudAdapter(solicitudes);
        rv.setAdapter(adapter);

    }

    private List<Solicitud> generarSolicitudes() {

        Estudiante luis = new Estudiante(1, "luis");
        Estudiante jenifer = new Estudiante(2, "jenifer");

        List<Solicitud> lista = new ArrayList<>();

        lista.add(new Solicitud(
           luis,
           "Programacion",
           "15/12/2025",
           "18:00 - 19:00",
           "Virtual"
        ));

        lista.add(new Solicitud(
                jenifer,
                "Algebra Lineal",
                "16/12/2025",
                "10:00 - 11:30",
                "Presencial"
        ));

        return lista;

    }
}