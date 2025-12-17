package com.lesliefic.asesoriasfic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.rol_administrador.AsesoriasEnCursoActivity;
import com.lesliefic.asesoriasfic.rol_administrador.ListaAsesoresActivity;

public class InformacionAsesoresParActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesores_par);


        ImageButton btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(InformacionAsesoresParActivity.this, ListaAsesoresActivity.class));
        });

    }
}