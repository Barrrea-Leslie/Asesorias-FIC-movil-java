package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;
import android.widget.ImageButton;

import com.lesliefic.asesoriasfic.rol_administrador.ListaAsesoresActivity;

public class crearAsesoresPar extends AppCompatActivity {

    ImageButton btn_regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_crear_asesor_par);


        btn_regresar = findViewById(R.id.btnRegresar);

        btn_regresar.setOnClickListener(v -> {
            Intent intent = new Intent(crearAsesoresPar.this, ListaAsesoresActivity.class);
            startActivity(intent);
        });






    }
}