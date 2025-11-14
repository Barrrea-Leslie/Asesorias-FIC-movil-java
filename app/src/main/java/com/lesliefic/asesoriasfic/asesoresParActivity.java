package com.lesliefic.asesoriasfic;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesoresParBinding;

public class asesoresParActivity extends DrawerBaseActivity {

    ActivityAsesoresParBinding activityAsesoresParBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAsesoresParBinding = activityAsesoresParBinding.inflate(getLayoutInflater());
        setContentView(activityAsesoresParBinding.getRoot());
    }
}