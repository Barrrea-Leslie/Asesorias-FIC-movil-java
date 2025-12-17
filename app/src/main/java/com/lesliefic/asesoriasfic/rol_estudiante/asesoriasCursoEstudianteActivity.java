package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteAsesoriasCursoBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class asesoriasCursoEstudianteActivity extends DrawerBaseActivity {

    private ActivityEstudianteAsesoriasCursoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudianteAsesoriasCursoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}