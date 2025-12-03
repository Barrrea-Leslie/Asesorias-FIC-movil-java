package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminListaAsesoresBinding;
import com.lesliefic.asesoriasfic.modelo.Asesor;

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
        List<Asesor> asesores = Arrays.asList(
                new Asesor("Jose Angel Astorga Mejía"),
                new Asesor("Leslie Mayram Barrera"),
                new Asesor("Jenifer Tizoc Lopez"),
                new Asesor("Jenifer Tizoc Lopez")
        );
        AsesorAdapter adapter = new AsesorAdapter(asesores, asesor ->
                Toast.makeText(this, "click" + asesor.getNombre(), Toast.LENGTH_SHORT).show());
        rv.setAdapter(adapter);
    }
}