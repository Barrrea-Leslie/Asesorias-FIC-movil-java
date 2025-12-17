package com.lesliefic.asesoriasfic.rol_asesor;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesorAsesoriasCursoBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class asesoriasCursoActivity extends DrawerBaseActivity {

    private ActivityAsesorAsesoriasCursoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAsesorAsesoriasCursoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}