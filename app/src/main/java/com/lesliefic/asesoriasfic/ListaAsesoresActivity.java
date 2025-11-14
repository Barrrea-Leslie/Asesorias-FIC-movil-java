package com.lesliefic.asesoriasfic;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.adaptador.AsesorAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityListaAsesoresBinding;
import com.lesliefic.asesoriasfic.modelo.Asesor;

import java.util.Arrays;
import java.util.List;

public class ListaAsesoresActivity extends DrawerBaseActivity {

    ActivityListaAsesoresBinding activityListaAsesoresBinding;

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
                new Asesor("Jenifer Tizoc Lopez")
        );
        AsesorAdapter adapter = new AsesorAdapter(asesores, asesor ->
                Toast.makeText(this, "click" + asesor.getNombre(), Toast.LENGTH_SHORT).show());
        rv.setAdapter(adapter);
    }
}