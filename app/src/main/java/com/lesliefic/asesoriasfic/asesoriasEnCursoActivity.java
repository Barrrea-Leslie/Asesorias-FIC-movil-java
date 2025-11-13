package com.lesliefic.asesoriasfic;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.databinding.ActivityAsesoriasEnCursoBinding;
import com.lesliefic.asesoriasfic.databinding.ActivityInicioBinding;

public class asesoriasEnCursoActivity extends DrawerBaseActivity {

    ActivityAsesoriasEnCursoBinding activityAsesoriasEnCursoBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAsesoriasEnCursoBinding = activityAsesoriasEnCursoBinding.inflate(getLayoutInflater());
        setContentView(activityAsesoriasEnCursoBinding.getRoot());
    }
}