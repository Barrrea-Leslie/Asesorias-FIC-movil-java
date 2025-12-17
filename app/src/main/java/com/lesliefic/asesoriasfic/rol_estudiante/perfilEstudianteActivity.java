package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityEstudiantePerfilBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class perfilEstudianteActivity extends DrawerBaseActivity {

    private ActivityEstudiantePerfilBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudiantePerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}