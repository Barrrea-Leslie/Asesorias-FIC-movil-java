package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import com.lesliefic.asesoriasfic.databinding.ActivityAdminEstudiantesBinding;

public class estudiantesActivity extends DrawerBaseActivity {

    ActivityAdminEstudiantesBinding activityEstudiantesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEstudiantesBinding = activityEstudiantesBinding.inflate(getLayoutInflater());
        setContentView(activityEstudiantesBinding.getRoot());


    }
}