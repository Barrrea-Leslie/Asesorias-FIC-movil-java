package com.lesliefic.asesoriasfic;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesoresDiciplinaresBinding;


public class asesoresDiciplinaresActivity extends DrawerBaseActivity {

    ActivityAsesoresDiciplinaresBinding activityAsesoresDiciplinaresBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAsesoresDiciplinaresBinding = activityAsesoresDiciplinaresBinding.inflate(getLayoutInflater());
        setContentView(activityAsesoresDiciplinaresBinding.getRoot());
    }
}