package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.AsesoriaAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminAsesoriasEnCursoBinding;
import com.lesliefic.asesoriasfic.modelo.AsesoriaRepository;
import com.lesliefic.asesoriasfic.modelo.Asesoria;

import java.util.List;

public class AsesoriasEnCursoAdminActivity extends DrawerBaseActivity {


    ActivityAdminAsesoriasEnCursoBinding activityAdminAsesoriasEnCursoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminAsesoriasEnCursoBinding = activityAdminAsesoriasEnCursoBinding.inflate(getLayoutInflater());
        setContentView(activityAdminAsesoriasEnCursoBinding.getRoot());

        RecyclerView rv = findViewById(R.id.rvAsesoriasEnCurso);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Asesoria> asesorias = AsesoriaRepository.getListaAsesorias(this);



        AsesoriaAdapter adapter = new AsesoriaAdapter(asesorias, new AsesoriaAdapter.OnItemButtonClickListener() {
            @Override
            public void onInfoClick(Asesoria asesoria) {
                Intent intent = new Intent(AsesoriasEnCursoAdminActivity.this, InformacionAsesoriaActivity.class);
                //Para mandar el id por el intent
                intent.putExtra("ASESORIA_ID", asesoria.getId());
                startActivity(intent);
            }

            @Override
            public void onCompletarAsesoria(Asesoria asesoria) {

                mostrarDialogoConfirmacion(asesoria);

            }

            private void mostrarDialogoConfirmacion(Asesoria asesoria){

                View dialogView = getLayoutInflater().inflate(R.layout.admi_ventana_confirmacion_completar_asesoria, null);

                /*new AlertDialog.Builder(AsesoriasEnCursoAdminActivity.this)
                        .setTitle("Completar asesoria")
                        .setMessage("¿Estás seguro de marcar esta asesoría como completada?")
                        .setCancelable(false)

                        .setPositiveButton("Aceptar", (dialog, which) -> {
                            Toast.makeText(getApplicationContext(), "Se completo la asesoria", Toast.LENGTH_SHORT).show();
                        })

                        .setNegativeButton("Cancelar", (dialog, which) -> {
                            dialog.dismiss();
                        })

                        .show();*/

                AlertDialog dialog = new AlertDialog.Builder(AsesoriasEnCursoAdminActivity.this)
                        .setView(dialogView)
                        .setCancelable(false)
                        .create();

                dialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                Button btnCancelar = dialogView.findViewById(R.id.btnCancelar);
                Button btnAceptar = dialogView.findViewById(R.id.btnAceptar);

                btnCancelar.setOnClickListener(v -> {
                    dialog.dismiss();
                });

                btnAceptar.setOnClickListener(v -> {
                    Toast.makeText(getApplicationContext(), "Se completo la asesoria", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

                dialog.show();
            }

            @Override
            public void onMaterialClick(Asesoria asesoria) {
                startActivity(new Intent(AsesoriasEnCursoAdminActivity.this, MaterialAdicional.class));
            }
        });

        rv.setAdapter(adapter);

        TextView opFiltros = findViewById(R.id.opFiltros);

        opFiltros.setOnClickListener(v -> {
            startActivity(new Intent(AsesoriasEnCursoAdminActivity.this, FiltrosAsesoriasEnCursoActivity.class));
        });



    }
}



