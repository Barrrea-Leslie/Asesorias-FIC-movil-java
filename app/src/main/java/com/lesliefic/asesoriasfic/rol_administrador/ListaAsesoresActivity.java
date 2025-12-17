package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorParAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminListaAsesoresBinding;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;
import com.lesliefic.asesoriasfic.modelo.AsesoresParRepository;

import java.util.Arrays;
import java.util.List;

public class ListaAsesoresActivity extends DrawerBaseActivity {

    ActivityAdminListaAsesoresBinding activityListaAsesoresBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityListaAsesoresBinding = activityListaAsesoresBinding.inflate(getLayoutInflater());
        setContentView(activityListaAsesoresBinding.getRoot());


        RecyclerView rv = findViewById(R.id.rvAsesores);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<AsesorPar> asesores = AsesoresParRepository.getListaAsesoresPar(this);
        AsesorParAdapter adapter = new AsesorParAdapter(asesores, asesorPar ->
                Toast.makeText(this, "click" + asesorPar.getNombre(), Toast.LENGTH_SHORT).show());
        rv.setAdapter(adapter);

        Button btnCrearAsesorPar;

        btnCrearAsesorPar = findViewById(R.id.btnCrearAsesor);


    }
}