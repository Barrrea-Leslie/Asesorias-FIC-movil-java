package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorParAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminListaAsesoresBinding;
import com.lesliefic.asesoriasfic.modelo.AsesorPar;

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
        List<AsesorPar> asesores = Arrays.asList(
                new AsesorPar("Jose Angel Astorga Mejía"),
                new AsesorPar("Leslie Mayram Barrera"),
                new AsesorPar("Luis Fernando Velazquez Araujo"),
                new AsesorPar("Jenifer Tizoc Lopez")
        );
        AsesorParAdapter adapter = new AsesorParAdapter(asesores, asesorPar ->
                Toast.makeText(this, "click" + asesorPar.getNombre(), Toast.LENGTH_SHORT).show());
        rv.setAdapter(adapter);
    }
}