package com.lesliefic.asesoriasfic.rol_asesor;

import android.os.Bundle;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesorInicioBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;

public class inicioAsesorActivity extends DrawerBaseActivity {



    ActivityAsesorInicioBinding activityInicioBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = activityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());


    }
}