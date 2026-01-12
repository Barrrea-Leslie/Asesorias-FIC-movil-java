package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.SolicitudAdapter;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminSolicitudesPendientesBinding;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Solicitud;
import com.lesliefic.asesoriasfic.modelo.SolicitudPendiente;
import com.lesliefic.asesoriasfic.network.request.admin.EliminarSolicitudRequest;
import com.lesliefic.asesoriasfic.repositorios.SolicitudesRepository;
import com.lesliefic.asesoriasfic.rol_administrador.InformacionSolicitudesActivity;

import java.util.ArrayList;
import java.util.List;

public class solicitudesPendientesActivity extends DrawerBaseActivity {

    ActivityAdminSolicitudesPendientesBinding activitySolicitudesPendientesBinding;

    private List<SolicitudPendiente> listaSolicitudes = new ArrayList<>();

    private int id_solicitud_elegida;

    private RecyclerView rv;
    private LinearLayout emptyState;



    private SolicitudesRepository repoSolicitudes;
    private SolicitudAdapter soliAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySolicitudesPendientesBinding = activitySolicitudesPendientesBinding.inflate(getLayoutInflater());
        setContentView(activitySolicitudesPendientesBinding.getRoot());

        repoSolicitudes = new SolicitudesRepository();

        rv = findViewById(R.id.rvSolicitudes);
        rv.setLayoutManager(new LinearLayoutManager(this));

        emptyState = findViewById(R.id.emptyState);




        soliAdapter = new SolicitudAdapter(
                listaSolicitudes,


                solicitud -> {
                    Intent intent = new Intent(solicitudesPendientesActivity.this, InformacionSolicitudesActivity.class);
                    intent.putExtra("SOLICITUD_INFO", solicitud);
                    startActivity(intent);
                },


                (solicitud, position) -> {
                    aceptarSolicitud(solicitud.getId_solicitud());
                },


                (solicitud, position) -> {
                    id_solicitud_elegida = solicitud.getId_solicitud();
                    mostrarDialogoEliminar();
                }
        );
        rv.setAdapter(soliAdapter);
        obtenerSolicitudes();

    }

    protected void onResume(){
        super.onResume();
        obtenerSolicitudes();
    }

    private void obtenerSolicitudes() {
        repoSolicitudes.obtenerSolicitudes(new SolicitudesRepository.ResultCallback<List<SolicitudPendiente>>() {
            @Override
            public void onSuccess(List<SolicitudPendiente> data) {
                listaSolicitudes.clear();
                listaSolicitudes.addAll(data);
                soliAdapter.notifyDataSetChanged();

                if(data == null || data.isEmpty()){
                    rv.setVisibility(View.GONE);
                    emptyState.setVisibility(View.VISIBLE);
                } else {
                    rv.setVisibility(View.VISIBLE);
                    emptyState.setVisibility(View.GONE);
                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


    }

    private void mostrarDialogoEliminar(){

        View dialogView = getLayoutInflater().inflate(R.layout.admi_ventana_confirmacion_eliminar_solicitud, null);

        AlertDialog dialog = new AlertDialog.Builder(solicitudesPendientesActivity.this)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        Button btnCancelar = dialogView.findViewById(R.id.btnCancelar);
        Button btnAceptar = dialogView.findViewById(R.id.btnAceptar);
        EditText et_explicacion = dialogView.findViewById(R.id.campoMotivo);


        btnCancelar.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnAceptar.setOnClickListener(v -> {
            String explicacionAsesor = et_explicacion.getText().toString().trim();
            if(explicacionAsesor.isEmpty()){
                Toast.makeText(
                        getApplicationContext(),
                        "Ingrese el motivo del rechazo",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                dialog.dismiss();
                eliminarSolicitud(id_solicitud_elegida, explicacionAsesor, dialog);
                onResume();
            }

        });

        dialog.show();
    }

    public void eliminarSolicitud(int id_solicitud, String explicacion_asesor, AlertDialog dialog){
        EliminarSolicitudRequest request = new EliminarSolicitudRequest(id_solicitud, explicacion_asesor);

        repoSolicitudes.eliminarSolicitud(request, new SolicitudesRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    dialog.dismiss();
                    obtenerSolicitudes();
                    Toast.makeText(
                            getApplicationContext(),
                            "Solicitud eliminada exitosamente",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                Toast.makeText(
                        getApplicationContext(),
                        "error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public void aceptarSolicitud(int id_solicitud){

        repoSolicitudes.aceptarSolicitud(id_solicitud, new SolicitudesRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    obtenerSolicitudes();
                    Toast.makeText(
                            getApplicationContext(),
                            "Solicitud aceptada exitosamente",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error: " + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }


}