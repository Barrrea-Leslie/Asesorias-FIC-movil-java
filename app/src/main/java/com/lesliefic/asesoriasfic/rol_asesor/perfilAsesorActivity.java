package com.lesliefic.asesoriasfic.rol_asesor;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesorPerfilBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;

public class perfilAsesorActivity extends DrawerBaseActivity {

    private ActivityAsesorPerfilBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAsesorPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}