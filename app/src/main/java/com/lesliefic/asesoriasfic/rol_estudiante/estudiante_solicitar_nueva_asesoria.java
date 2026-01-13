package com.lesliefic.asesoriasfic.rol_estudiante;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

import java.util.Calendar;

public class estudiante_solicitar_nueva_asesoria extends AppCompatActivity {

    private Spinner spMateria, spHorarios, spModalidad, spRazon;
    private TextView tvFecha;

    private EditText campoNombreAsesor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_solicitar_nueva_asesoria);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        spMateria = findViewById(R.id.spMateria);
        spHorarios = findViewById(R.id.spHorarios);
        spModalidad = findViewById(R.id.spModalidad);
        spRazon = findViewById(R.id.spRazon);
        tvFecha = findViewById(R.id.tvFecha);

        campoNombreAsesor = findViewById(R.id.campoNombreAsesor);

        btnRegresar.setOnClickListener(v -> finish());
        btnConfirmar.setOnClickListener(v -> {
            if (validarCampos()){
                finish();
                Toast.makeText(estudiante_solicitar_nueva_asesoria.this, "Se creo la solicitud correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        configurarSpinner(spMateria, new String[]{"Materia", "Matemáticas", "Programación", "Base de Datos"});
        configurarSpinner(spHorarios, new String[]{"Horarios", "08:00 - 09:00", "10:00 - 11:00"});
        configurarSpinner(spModalidad, new String[]{"Modalidad", "Presencial", "Virtual"});
        configurarSpinner(spRazon, new String[]{"Razon", "Parcial", "Bajo promedio", "Materia Reprobada"});

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

    private boolean validarCampos() {

        if (campoNombreAsesor.getText().toString().trim().isEmpty()){
            campoNombreAsesor.setError("El nombre del asesor es obligatorio");
            campoNombreAsesor.requestFocus();
            return false;
        }

        if (spMateria.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Por favor, seleccione una materia", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (spHorarios.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Por favor, seleccione un horario", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (spModalidad.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Por favor, seleccione la modalidad", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (spRazon.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Por favor, seleccione una razón", Toast.LENGTH_SHORT).show();
            return false;
        }

        // 3. Validar Fecha (TextView)
        // Suponiendo que en tu XML el texto inicial es "Seleccionar fecha" o está vacío
        String fechaActual = tvFecha.getText().toString().trim();
        if (fechaActual.isEmpty() || fechaActual.equals("Elige una fecha") || fechaActual.equals("Fecha")) {
            Toast.makeText(this, "Por favor, seleccione una fecha", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
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