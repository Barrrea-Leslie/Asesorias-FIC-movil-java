package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private static final String TAG = "INFO_ASESORIA";

    private EditText txFechaInicio, txtFechaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesoria);

        // ---------- 1) OBTENER ID ----------
        int asesoriaId = getIntent().getIntExtra("ASESORIA_ID", -1);
        if (asesoriaId == -1) {
            Toast.makeText(this, "No se recibió ASESORIA_ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // ---------- 2) TRAER ASESORÍA ----------
        Asesoria asesoria = AsesoriaRepository.getById(this, asesoriaId);
        if (asesoria == null) {
            Toast.makeText(this, "No se encontró la asesoría con id: " + asesoriaId, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // ---------- 3) TRAER ESTUDIANTE (PUEDE SER NULL) ----------
        Estudiante estudiante = asesoria.getEstudiante(); // puede venir null dependiendo del repo/parseo

        Log.d(TAG, "asesoriaId=" + asesoriaId);
        Log.d(TAG, "asesoria materia=" + asesoria.getMateria());
        Log.d(TAG, "estudiante=" + (estudiante == null ? "null" : "ok"));

        // ---------- BOTÓN REGRESAR ----------
        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(InformacionAsesoriaActivity.this, AsesoriasEnCursoAdminActivity.class));
            finish();
        });

        // ---------- SPINNERS (VALORES DEMO) ----------
        String[] todosLosGrupos = {"3-1", "3-2", "3-3", "4-1", "4-3"};
        String[] todasLasMaterias = {"Programacion", "Base de datos", "Matematicas Discretas"};
        String[] todosLosHorarios = {"11:00 - 12:00", "01:00 - 02:00", "18:00 - 19:00", "19:00 - 20:00"};
        String[] todasLasRazones = {"Bajo promedio", "Materia reprobada", "Dudas en la  materia"};
        String[] todasLasModalidades = {"Presencial", "Virtual"};

        // Si luego reactivas el grupo, primero asegúrate que estudiante NO sea null
        /*
        Spinner spGrupo = findViewById(R.id.spGrupo);
        String grupoActual = (estudiante != null && estudiante.getGrupo() != null) ? estudiante.getGrupo() : todosLosGrupos[0];
        spGrupo.setAdapter(crearSpinner(grupoActual, todosLosGrupos));
        */

        Spinner spMateria = findViewById(R.id.spMateria);
        spMateria.setAdapter(crearSpinner(valorSeguro(asesoria.getMateria(), todasLasMaterias[0]), todasLasMaterias));

        Spinner spHorario = findViewById(R.id.spHorario);
        spHorario.setAdapter(crearSpinner(valorSeguro(asesoria.getHorario(), todosLosHorarios[0]), todosLosHorarios));

        Spinner spRazon = findViewById(R.id.spRazon);
        spRazon.setAdapter(crearSpinner(valorSeguro(asesoria.getRazon(), todasLasRazones[0]), todasLasRazones));

        Spinner spModalidad = findViewById(R.id.spModalidad);
        spModalidad.setAdapter(crearSpinner(valorSeguro(asesoria.getModalidad(), todasLasModalidades[0]), todasLasModalidades));

        // ---------- CAMPOS ----------
        EditText campoEstudiante = findViewById(R.id.campoEstudiante);
        campoEstudiante.setText(estudiante != null && estudiante.getNombre() != null ? estudiante.getNombre() : "");

        EditText campoLicenciatura = findViewById(R.id.campoLicenciatura);
        // OJO: setText(int) se interpreta como recurso. Convertimos a String.
        if (estudiante != null) {
            campoLicenciatura.setText(String.valueOf(estudiante.getIdLicenciatura()));
        } else {
            campoLicenciatura.setText("");
        }

        // ---------- FECHAS ----------
        txFechaInicio = findViewById(R.id.txFechaInicio);
        txFechaInicio.setText(valorSeguro(asesoria.getFechaInicio(), ""));

        txtFechaFinal = findViewById(R.id.txFechaFinal);
        txtFechaFinal.setText(valorSeguro(asesoria.getFechaFin(), ""));

        txFechaInicio.setOnClickListener(v -> mostrarSeleccionCalendarioInicio());
        txtFechaFinal.setOnClickListener(v -> mostrarSeleccionCalendarioFinal());

        // ---------- BOTÓN APLICAR CAMBIOS ----------
        Button btnCambios = findViewById(R.id.btnCambios);
        btnCambios.setOnClickListener(v -> {

            String nombre = campoEstudiante.getText().toString().trim();
            String licenciatura = campoLicenciatura.getText().toString().trim();

            if (nombre.isEmpty() || licenciatura.isEmpty()) {
                Toast.makeText(this, "Llene los campos obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí podrías guardar cambios si tienes endpoint/Repo para update
            // Por ahora solo regresa
            startActivity(new Intent(InformacionAsesoriaActivity.this, AsesoriasEnCursoAdminActivity.class));
            finish();
        });

        // ---------- INFO EXTRA (OPCIONAL): si estudiante vino null, avisamos ----------
        if (estudiante == null) {
            Toast.makeText(this,
                    "Aviso: esta asesoría no trae datos del estudiante (viene null).",
                    Toast.LENGTH_LONG).show();
        }
    }

    private String valorSeguro(String valor, String fallback) {
        return (valor != null) ? valor : fallback;
    }

    private ArrayAdapter<String> crearSpinner(String valorActual, String[] valoresDisponibles) {
        List<String> lista = new ArrayList<>();

        if (valorActual == null) valorActual = "";

        // Evitar que valorActual sea null o vacío raro
        lista.add(valorActual);

        for (String valor : valoresDisponibles) {
            if (valor != null && !valor.equals(valorActual)) {
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
                (view, y, m, d) -> {
                    String selectedDate = String.format("%02d/%02d/%d", d, m + 1, y);
                    txFechaInicio.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void mostrarSeleccionCalendarioFinal() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, y, m, d) -> {
                    String selectedDate = String.format("%02d/%02d/%d", d, m + 1, y);
                    txtFechaFinal.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
}