package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

import java.util.Calendar;

public class InformacionAsesoriaActivity extends AppCompatActivity {

    private EditText txFechaInicio, txtFechaFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesoria);


        ImageButton btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(InformacionAsesoriaActivity.this, AsesoriasEnCursoActivity.class));
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

        Spinner spMateria = findViewById(R.id.spMateria);

        String[] materias = {"Programacion", "Base de datos"};
        ArrayAdapter<String> adapterMaterias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materias);
        adapterMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMateria.setAdapter(adapterMaterias);

        spMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spHorario = findViewById(R.id.spHorario);

        String[] horarios = {"18:00 - 19:00", "12:00 - 01:00"};
        ArrayAdapter<String> adapterHorarios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horarios);
        adapterMaterias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHorario.setAdapter(adapterHorarios);

        spMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //calendarios
        txFechaInicio = findViewById(R.id.txFechaInicio);
        txFechaInicio.setText("25/09/2025");

        txtFechaFinal = findViewById(R.id.txFechaFinal);
        txtFechaFinal.setText("10/10/2025");

        ImageButton btnMCFechaInicio = findViewById(R.id.btnMCFechaInicio);
        ImageButton btnMCFechaFinal = findViewById(R.id.btnMCFechaFinal);

        btnMCFechaInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarSeleccionCalendarioInicio();
            }
        });

        btnMCFechaFinal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarSeleccionCalendarioFinal();
            }
        });

    }

    private void mostrarSeleccionCalendarioInicio() {
        // Obtener la fecha actual
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // month es base 0 (enero es 0), así que sumamos 1
                        // Formatear la fecha como dd/MM/yyyy
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        txFechaInicio.setText(selectedDate);
                    }
                }, year, month, day);

        // Mostrar el diálogo
        datePickerDialog.show();
    }

    private void mostrarSeleccionCalendarioFinal() {
        // Obtener la fecha actual
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Crear el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // month es base 0 (enero es 0), así que sumamos 1
                        // Formatear la fecha como dd/MM/yyyy
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        txtFechaFinal.setText(selectedDate);
                    }
                }, year, month, day);

        // Mostrar el diálogo
        datePickerDialog.show();
    }

}
