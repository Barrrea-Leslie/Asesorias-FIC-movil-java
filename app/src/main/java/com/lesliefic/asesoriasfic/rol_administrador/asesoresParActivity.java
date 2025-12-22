package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;

import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoresParBinding;

public class asesoresParActivity extends DrawerBaseActivity {

    ActivityAdminAsesoresParBinding activityAsesoresParBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAsesoresParBinding = activityAsesoresParBinding.inflate(getLayoutInflater());
        setContentView(activityAsesoresParBinding.getRoot());
    }


}