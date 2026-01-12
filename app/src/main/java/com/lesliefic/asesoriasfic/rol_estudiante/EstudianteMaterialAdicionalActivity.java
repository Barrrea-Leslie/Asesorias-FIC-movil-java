package com.lesliefic.asesoriasfic.rol_estudiante;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.MaterialAdapter;
import com.lesliefic.asesoriasfic.modelo.MaterialEstudio;

import java.util.ArrayList;
import java.util.List;

public class EstudianteMaterialAdicionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_material_adicional);

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(v -> finish());

        String nombreAsesor = getIntent().getStringExtra("nombreAsesor");
        setTitle("Material de " + nombreAsesor);

        RecyclerView rv = findViewById(R.id.rvMateriales);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<MaterialEstudio> materiales = new ArrayList<>();
        materiales.add(new MaterialEstudio("Guía de Programación I", "PDF", "https://drive.google.com/file/d/1H63xEeSZ1WR7OQQISeg3CIDoXdPEwpaV/view?usp=sharing"));
        materiales.add(new MaterialEstudio("Ejercicios de Bases de Datos", "DOCX", "https://docs.google.com/document/d/1MRhuJVp170pQcQ5fbuRssIsZizNvy9O7/edit?usp=sharing&ouid=114891694404924852620&rtpof=true&sd=true"));
        materiales.add(new MaterialEstudio("Guía de Programación I", "PDF",  "https://drive.google.com/file/d/1H63xEeSZ1WR7OQQISeg3CIDoXdPEwpaV/view?usp=sharing"));

        MaterialAdapter adapter = new MaterialAdapter(materiales);
        rv.setAdapter(adapter);
    }
}