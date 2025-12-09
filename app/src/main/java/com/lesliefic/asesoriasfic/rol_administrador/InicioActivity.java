package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import com.lesliefic.asesoriasfic.databinding.ActivityAdminInicioBinding;

public class InicioActivity extends DrawerBaseActivity {



    ActivityAdminInicioBinding activityInicioBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = activityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());


    }
}