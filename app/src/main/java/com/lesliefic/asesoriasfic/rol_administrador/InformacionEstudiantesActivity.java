package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Estudiante;

public class InformacionEstudiantesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_estudiantes);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(InformacionEstudiantesActivity.this, estudiantesActivity.class));
        });

        Spinner spGrupo = findViewById(R.id.spGrupo);

        String[] grupos = {"4-1", "4-3"};
        ArrayAdapter<String> adapterGrupos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grupos);
        adapterGrupos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrupo.setAdapter(adapterGrupos);

        spGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}