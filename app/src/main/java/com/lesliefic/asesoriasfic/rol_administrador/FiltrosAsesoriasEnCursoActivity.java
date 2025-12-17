package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;

public class FiltrosAsesoriasEnCursoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_filtros_asesorias_en_curso);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(FiltrosAsesoriasEnCursoActivity.this, AsesoriasEnCursoAdminActivity.class));
        });


        Spinner spGrupo = findViewById(R.id.spGrupo);
        Spinner spRazon = findViewById(R.id.spRazon);
        Spinner spModalidad = findViewById(R.id.spModalidad);

        String[] grupos = {"4-1", "4-3"};
        String[] razones = {"Bajo promedio", "Materia reprobada", "Dudas en la  materia"};
        String[] modalidades = {"presencial", "virtual"};

        ArrayAdapter<String> adapterGrupos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grupos);
        ArrayAdapter<String> adapterRazones = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, razones);
        ArrayAdapter<String> adapterModalidades = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modalidades);

        adapterGrupos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRazones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterModalidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGrupo.setAdapter(adapterGrupos);
        spRazon.setAdapter(adapterRazones);
        spModalidad.setAdapter(adapterModalidades);

        spGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String grupo = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spRazon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String razon = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spModalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String modalidad = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }
}