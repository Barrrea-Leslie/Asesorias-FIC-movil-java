package com.lesliefic.asesoriasfic.rol_estudiante;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

        binding = ActivityEstudianteInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearSolicitud.setOnClickListener(v -> {
            startActivity(new Intent(this, estudiante_solicitar_nueva_asesoria.class));
        });

        binding.opFiltros.setOnClickListener(v -> {
            startActivity(new Intent(this, estudiante_filtros_solicitar_asesorias.class));
        });

        binding.btnMaterial.setOnClickListener(v -> {
            startActivity(new Intent(this, EstudianteMaterialAdicionalActivity.class));
        });
    }
}
