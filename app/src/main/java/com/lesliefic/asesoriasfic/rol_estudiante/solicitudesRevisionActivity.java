package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.databinding.ActivityEstudianteSolicitudesRevisionBinding;
import com.lesliefic.asesoriasfic.rol_administrador.DrawerBaseActivity;


public class solicitudesRevisionActivity extends DrawerBaseActivity {

    private ActivityEstudianteSolicitudesRevisionBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEstudianteSolicitudesRevisionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner spFiltroEstado = findViewById(R.id.spFiltroEstado);

        configurarSpinner(spFiltroEstado, new String[]{"Todo", "Rechazadas", "En revision"});

    }

    private void configurarSpinner(Spinner spinner, String[] datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}