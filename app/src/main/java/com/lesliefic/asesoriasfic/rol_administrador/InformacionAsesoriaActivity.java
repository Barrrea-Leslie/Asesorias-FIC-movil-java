package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Asesoria;
import com.lesliefic.asesoriasfic.modelo.AsesoriaRepository;
import com.lesliefic.asesoriasfic.modelo.Estudiante;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InformacionAsesoriaActivity extends AppCompatActivity {

    private EditText txFechaInicio, txtFechaFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesoria);

        int asesoriaId = getIntent().getIntExtra("ASESORIA_ID", -1);
        Asesoria asesoria = null;
        if (asesoriaId != -1){
            asesoria = AsesoriaRepository.getById(this, asesoriaId);
        }
        Estudiante estudiante = asesoria.getEstudiante();



        ImageButton btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(InformacionAsesoriaActivity.this, AsesoriasEnCursoAdminActivity.class));
        });

        String[] todosLosGrupos = {"3-1", "3-2", "3-3", "4-1", "4-3"};
        String[] todasLasMaterias = {"Programacion", "Base de datos", "Matematicas Discretas"};
        String[] todosLosHorarios = {"11:00 - 12:00", "01:00 - 02:00", "18:00 - 19:00", "19:00 - 20:00"};
        String[] todasLasRazones = {"Bajo promedio", "Materia reprobada", "Dudas en la  materia"};
        String[] todasLasModalidades = {"Presencial", "Virtual"};



        Spinner spGrupo = findViewById(R.id.spGrupo);
        spGrupo.setAdapter(crearSpinner(estudiante.getGrupo(), todosLosGrupos));

        Spinner spMateria = findViewById(R.id.spMateria);
        spMateria.setAdapter(crearSpinner(asesoria.getMateria(), todasLasMaterias));

        Spinner spHorario = findViewById(R.id.spHorario);
        spHorario.setAdapter(crearSpinner(asesoria.getHorario(), todosLosHorarios));

        Spinner spRazon = findViewById(R.id.spRazon);
        spRazon.setAdapter(crearSpinner(asesoria.getRazon(), todasLasRazones));

        Spinner spModalidad = findViewById(R.id.spModalidad);
        spModalidad.setAdapter(crearSpinner(asesoria.getModalidad(), todasLasModalidades));

        EditText campoEstudiante = findViewById(R.id.campoEstudiante);
        campoEstudiante.setText(estudiante.getNombre());

        EditText campoLicenciatura = findViewById(R.id.campoLicenciatura);
        campoLicenciatura.setText(estudiante.getLicenciatura());

        //calendarios
        txFechaInicio = findViewById(R.id.txFechaInicio);
        txFechaInicio.setText(asesoria.getFechaInicio());

        txtFechaFinal = findViewById(R.id.txFechaFinal);
        txtFechaFinal.setText(asesoria.getFechaFin());


        txFechaInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarSeleccionCalendarioInicio();
            }
        });

        txtFechaFinal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarSeleccionCalendarioFinal();
            }
        });

        //Funcionaliad boton para aplicar cambios
        Button btnCambios = findViewById(R.id.btnCambios);

        btnCambios.setOnClickListener(v -> {

            String nombre = campoEstudiante.getText().toString().trim();
            String licenciatura = campoLicenciatura.getText().toString().trim();

            if (nombre.isEmpty() || licenciatura.isEmpty()){
                Toast.makeText(this, "Llene los campos obligatorios", Toast.LENGTH_SHORT).show();
            }
            else {
                startActivity(new Intent(InformacionAsesoriaActivity.this, AsesoriasEnCursoAdminActivity.class));
            }

        });

    }

    private ArrayAdapter<String> crearSpinner(String valorActual, String[] valoresDisponibles){

        List<String> lista = new ArrayList<>();
        lista.add(valorActual);

        for (String valor : valoresDisponibles){
            if (!valor.equals(valorActual)){
                lista.add(valor);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;

    }

    private void mostrarSeleccionCalendarioInicio() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        txFechaInicio.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void mostrarSeleccionCalendarioFinal() {
        // Obtener la fecha actual
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        txtFechaFinal.setText(selectedDate);
                    }
                }, year, month, day);


        datePickerDialog.show();
    }

}
