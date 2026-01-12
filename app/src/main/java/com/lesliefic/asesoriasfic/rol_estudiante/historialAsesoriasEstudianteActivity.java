package com.lesliefic.asesoriasfic.rol_estudiante;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteHistorialAsesoriasBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class historialAsesoriasEstudianteActivity extends DrawerBaseActivity {

    private ActivityEstudianteHistorialAsesoriasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudianteHistorialAsesoriasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.opFiltros.setOnClickListener(v -> {
            startActivity(new Intent(historialAsesoriasEstudianteActivity.this, EstudianteFiltroHistorialAsesoriasActivity.class));
        });

    }
}