package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import com.lesliefic.asesoriasfic.databinding.ActivityAdminSolicitudesPendientesBinding;

public class solicitudesPendientesActivity extends DrawerBaseActivity {

    ActivityAdminSolicitudesPendientesBinding activitySolicitudesPendientesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySolicitudesPendientesBinding = activitySolicitudesPendientesBinding.inflate(getLayoutInflater());
        setContentView(activitySolicitudesPendientesBinding.getRoot());
    }
}