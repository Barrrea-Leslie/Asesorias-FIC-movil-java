package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;

public class estudiante_filtros_solicitar_asesorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes_filtros_solicitar_asesorias);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        Button btnAplicar = findViewById(R.id.btnFiltro);

        Spinner spDiaSemana = findViewById(R.id.spDiaSemana);
        Spinner spMateria = findViewById(R.id.spMateria);
        Spinner spHorario = findViewById(R.id.spHorario);
        Spinner spModalidad = findViewById(R.id.spModalidad);

        btnRegresar.setOnClickListener(v -> finish());
        btnAplicar.setOnClickListener(v -> finish());

        configurarSpinner(spDiaSemana, new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"});
        configurarSpinner(spMateria, new String[]{"Matemáticas", "Programación", "Base de Datos"});
        configurarSpinner(spHorario, new String[]{"08:00 - 09:00", "10:00 - 11:00"});
        configurarSpinner(spModalidad, new String[]{"Presencial", "Virtual"});


    }

    private void configurarSpinner(Spinner spinner, String[] datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}