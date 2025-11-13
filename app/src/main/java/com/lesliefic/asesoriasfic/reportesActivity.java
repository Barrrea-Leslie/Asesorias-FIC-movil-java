package com.lesliefic.asesoriasfic;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.databinding.ActivityInicioBinding;
import com.lesliefic.asesoriasfic.databinding.ActivityReportesBinding;

public class reportesActivity extends DrawerBaseActivity {

    ActivityReportesBinding activityReportesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReportesBinding = activityReportesBinding.inflate(getLayoutInflater());
        setContentView(activityReportesBinding.getRoot());
    }
}