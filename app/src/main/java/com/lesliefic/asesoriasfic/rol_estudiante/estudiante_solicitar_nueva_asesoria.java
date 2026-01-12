package com.lesliefic.asesoriasfic.rol_estudiante;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

import java.util.Calendar;

public class estudiante_solicitar_nueva_asesoria extends AppCompatActivity {

    private Spinner spnMateria, spnHorarios, spnModalidad, spnRazon;
    private TextView tvFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitar_nueva_asesoria);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        spnMateria = findViewById(R.id.spMateria);
        spnHorarios = findViewById(R.id.spHorarios);
        spnModalidad = findViewById(R.id.spModalidad);
        spnRazon = findViewById(R.id.spRazon);
        tvFecha = findViewById(R.id.tvFecha);

        btnRegresar.setOnClickListener(v -> finish());
        btnConfirmar.setOnClickListener(v -> {
            finish();
            Toast.makeText(estudiante_solicitar_nueva_asesoria.this, "Se creo la solicitud correctamente", Toast.LENGTH_SHORT).show();
        });

        configurarSpinner(spnMateria, new String[]{"Materia", "Matemáticas", "Programación", "Base de Datos"});
        configurarSpinner(spnHorarios, new String[]{"Horarios", "08:00 - 09:00", "10:00 - 11:00"});
        configurarSpinner(spnModalidad, new String[]{"Modalidad", "Presencial", "Virtual"});
        configurarSpinner(spnRazon, new String[]{"Razon", "Parcial", "Bajo promedio", "Materia Reprobada"});

        tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCalendario();
            }
        });

    }

    private void configurarSpinner(Spinner spinner, String[] datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void mostrarCalendario() {
        // Obtener la fecha actual para mostrarla por defecto en el selector
        final Calendar c = Calendar.getInstance();
        int año = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        // Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // monthOfYear inicia en 0 (Enero), por eso se suma 1
                        String fechaSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        tvFecha.setText(fechaSeleccionada);
                    }
                }, año, mes, dia);

        datePickerDialog.show();
    }

}