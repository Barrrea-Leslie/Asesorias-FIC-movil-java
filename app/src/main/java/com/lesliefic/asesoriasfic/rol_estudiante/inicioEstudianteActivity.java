package com.lesliefic.asesoriasfic.rol_estudiante;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteInicioBinding;
import com.lesliefic.asesoriasfic.rol_administrador.AsesoriasEnCursoAdminActivity;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;
import com.lesliefic.asesoriasfic.rol_administrador.FiltrosAsesoriasEnCursoActivity;


public class inicioEstudianteActivity extends DrawerBaseActivity {

    private ActivityEstudianteInicioBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitar_asesorias);

        binding = ActivityEstudianteInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Button btn_crearSolicitud = findViewById(R.id.btn_crearSolicitud);

        btn_crearSolicitud.setOnClickListener(v -> {
            startActivity(new Intent(inicioEstudianteActivity.this, estudiante_solicitar_nueva_asesoria.class));
        });

    }


}