package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoresDisciplinaresBinding;

public class asesoresDisciplinaresActivity extends DrawerBaseActivity {

    private ActivityAdminAsesoresDisciplinaresBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminAsesoresDisciplinaresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnGuardar.setOnClickListener(v -> {
            Intent intent = new Intent(
                    asesoresDisciplinaresActivity.this,
                    crearAsesoresDisiplinares.class
            );
            startActivity(intent);
        });
    }
}