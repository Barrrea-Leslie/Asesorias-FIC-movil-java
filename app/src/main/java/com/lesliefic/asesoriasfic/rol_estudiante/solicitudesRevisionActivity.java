package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteSolicitudesRevisionBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class solicitudesRevisionActivity extends DrawerBaseActivity {

    private ActivityEstudianteSolicitudesRevisionBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudianteSolicitudesRevisionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}