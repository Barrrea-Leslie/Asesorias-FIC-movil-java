package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import com.lesliefic.asesoriasfic.databinding.ActivityAdminReportesBinding;

public class reportesActivity extends DrawerBaseActivity {

    ActivityAdminReportesBinding activityReportesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReportesBinding = activityReportesBinding.inflate(getLayoutInflater());
        setContentView(activityReportesBinding.getRoot());
    }
}