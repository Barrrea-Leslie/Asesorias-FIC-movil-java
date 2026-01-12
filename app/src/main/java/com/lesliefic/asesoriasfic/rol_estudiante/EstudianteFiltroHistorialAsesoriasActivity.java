package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;

public class EstudianteFiltroHistorialAsesoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_filtro_historial_asesorias);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        Button btnAplicar = findViewById(R.id.btnFiltro);

        Spinner spMateria = findViewById(R.id.spMateria);
        Spinner spGradoEscolar = findViewById(R.id.spGradoEscolar);
        Spinner spModalidad = findViewById(R.id.spModalidad);

        configurarSpinner(spMateria, new String[]{"Matemáticas", "Programación", "Base de Datos"});
        configurarSpinner(spGradoEscolar, new String[]{"4-1", "4-3"});
        configurarSpinner(spModalidad, new String[]{"Presencial", "Virtual"});

        btnRegresar.setOnClickListener(v -> finish());
        btnAplicar.setOnClickListener(v -> finish());

    }

    private void configurarSpinner(Spinner spinner, String[] datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}