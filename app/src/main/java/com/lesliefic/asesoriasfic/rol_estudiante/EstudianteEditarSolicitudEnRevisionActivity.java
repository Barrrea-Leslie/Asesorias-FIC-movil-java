package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

public class EstudianteEditarSolicitudEnRevisionActivity extends AppCompatActivity {

    private EditText campoMateria, campoNombreAsesor, campoHorario;
    private TextView tvFecha;
    private Button btnGuardarCambios;
    private ImageButton btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_editar_solicitud_en_revision);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios);

        campoMateria = findViewById(R.id.campoMateria);
        campoNombreAsesor = findViewById(R.id.campoNombreAsesor);
        campoHorario = findViewById(R.id.campoHorario);
        tvFecha = findViewById(R.id.tvFecha);

        btnRegresar.setOnClickListener(v -> finish());

        btnGuardarCambios.setOnClickListener(v -> {
            if (validarCampos()) {
                finish();
                Toast.makeText(this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validarCampos() {

        if (campoMateria.getText().toString().trim().isEmpty()) {
            campoMateria.setError("La materia es obligatoria");
            campoMateria.requestFocus();
            return false;
        }

        if (campoNombreAsesor.getText().toString().trim().isEmpty()) {
            campoNombreAsesor.setError("El nombre del asesor es obligatorio");
            campoNombreAsesor.requestFocus();
            return false;
        }

        String fechaActual = tvFecha.getText().toString().trim();
        if (fechaActual.equals("Elige una fecha") || fechaActual.isEmpty()) {
            Toast.makeText(this, "Por favor, seleccione una fecha", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (campoHorario.getText().toString().trim().isEmpty()) {
            campoHorario.setError("El horario es obligatorio");
            campoHorario.requestFocus();
            return false;
        }

        return true;
    }

}