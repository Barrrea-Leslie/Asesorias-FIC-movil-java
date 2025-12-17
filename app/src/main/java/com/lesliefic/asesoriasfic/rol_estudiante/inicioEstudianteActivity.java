package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteInicioBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class inicioEstudianteActivity extends DrawerBaseActivity {

    private ActivityEstudianteInicioBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudianteInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}