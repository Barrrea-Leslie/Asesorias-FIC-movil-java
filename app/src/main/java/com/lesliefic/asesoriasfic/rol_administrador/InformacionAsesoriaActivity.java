package com.lesliefic.asesoriasfic.rol_administrador;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

import java.util.Calendar;

public class InformacionAsesoriaActivity extends AppCompatActivity {

    private static final String TAG = "INFO_ASESORIA";

    private EditText txFechaInicio, txtFechaFinal;

    private Button btn_guardar;

    private ImageButton btn_regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_informacion_asesoria);

        btn_regresar = findViewById(R.id.btnRegresar);

        // ---------- FECHAS ----------
        txFechaInicio = findViewById(R.id.txFechaInicio);


        txtFechaFinal = findViewById(R.id.txFechaFinal);


        txFechaInicio.setOnClickListener(v -> mostrarSeleccionCalendarioInicio());
        txtFechaFinal.setOnClickListener(v -> mostrarSeleccionCalendarioFinal());

        btn_regresar.setOnClickListener(v -> finish());


    }

    private String valorSeguro(String valor, String fallback) {
        return (valor != null) ? valor : fallback;
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