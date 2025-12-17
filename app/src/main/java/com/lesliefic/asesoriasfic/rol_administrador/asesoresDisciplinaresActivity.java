package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesorDiciplinarAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoresDisciplinaresBinding;
import com.lesliefic.asesoriasfic.modelo.AsesorDiciplinar;
import com.lesliefic.asesoriasfic.modelo.AsesorDiciplinarRepository;

import java.util.Arrays;
import java.util.List;

public class asesoresDisciplinaresActivity extends DrawerBaseActivity {

    private ActivityAdminAsesoresDisciplinaresBinding activityAdminAsesoresDisciplinaresBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAdminAsesoresDisciplinaresBinding = activityAdminAsesoresDisciplinaresBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAsesoresDisciplinaresBinding.getRoot());

        RecyclerView rv = findViewById(R.id.rvAsesores);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<AsesorDiciplinar> asesoresDiciplinares = AsesorDiciplinarRepository.getListaAsesoresDiciplinar(this);
        AsesorDiciplinarAdapter adapter = new AsesorDiciplinarAdapter(asesoresDiciplinares, asesorDiciplinar ->
                Toast.makeText(this, "click" + asesorDiciplinar.getNombre(), Toast.LENGTH_SHORT).show());
        rv.setAdapter(adapter);

        /*binding = ActivityAdminAsesoresDisciplinaresBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*binding.btnGuardar.setOnClickListener(v -> {
            Intent intent = new Intent(
                    asesoresDisciplinaresActivity.this,
                    crearAsesoresDisiplinares.class
            );
            startActivity(intent);
        });*/
    }
}