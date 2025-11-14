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
import com.lesliefic.asesoriasfic.modelo.Asesor;

import java.util.Arrays;
import java.util.List;

public class ListaAsesoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_asesores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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