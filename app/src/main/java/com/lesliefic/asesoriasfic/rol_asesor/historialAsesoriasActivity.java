package com.lesliefic.asesoriasfic.rol_asesor;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesorHistorialAsesoriasBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;

public class historialAsesoriasActivity extends DrawerBaseActivity {

    private ActivityAsesorHistorialAsesoriasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAsesorHistorialAsesoriasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}