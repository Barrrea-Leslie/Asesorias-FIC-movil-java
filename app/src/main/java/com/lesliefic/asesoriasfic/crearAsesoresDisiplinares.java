package com.lesliefic.asesoriasfic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class crearAsesoresDisiplinares extends AppCompatActivity {

    Button btn_crearAsesorDisiplinar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_asesores_disiplinares);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        //iteraccion del boton
        btn_crearAsesorDisiplinar = findViewById(R.id.btn_crearAsesorDisiplinar);

        btn_crearAsesorDisiplinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(crearAsesoresDisiplinares.this, asesoresDiciplinaresActivity.class);
                startActivity(intent);
            }
        });




    }
}