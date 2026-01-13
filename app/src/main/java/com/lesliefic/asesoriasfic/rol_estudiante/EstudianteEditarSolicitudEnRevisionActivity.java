package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

public class EstudianteEditarSolicitudEnRevisionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_editar_solicitud_en_revision);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> finish());

    }
}