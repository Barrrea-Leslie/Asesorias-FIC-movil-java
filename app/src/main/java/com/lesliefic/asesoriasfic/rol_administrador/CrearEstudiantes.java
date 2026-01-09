package com.lesliefic.asesoriasfic.rol_administrador;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.adaptador.GrupoSpinnerAdapter;
import com.lesliefic.asesoriasfic.adaptador.LicenciaturaSpinnerAdapter;
import com.lesliefic.asesoriasfic.modelo.Estudiante;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.network.request.admin.CrearEstudianteRequest;
import com.lesliefic.asesoriasfic.repositorios.EstudiantesRepository;
import com.lesliefic.asesoriasfic.databinding.ActivityAdminCrearEstudianteBinding;

import java.util.ArrayList;

public class CrearEstudiantes extends AppCompatActivity {

    private Estudiante datosEstudiante;

    private ArrayList<Grupo> listaGrupos = new ArrayList<>();
    private ArrayList<Licenciatura> listaLicenciaturas = new ArrayList<>();

    private Spinner spGrupo, spLicenciatura;
    private GrupoSpinnerAdapter adapterGrupo;
    private LicenciaturaSpinnerAdapter adapterLicenciatura;

    private EstudiantesRepository repoEstudiantes;

    private EditText campoNombre, campoApePat, campoApeMat, campoNumeroCuenta,
            campoCorreo, campoNumCel, campoContrasena, campoPromedio;

    private Button btn_guardar;

    ActivityAdminCrearEstudianteBinding activityAdminCrearEstudianteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminCrearEstudianteBinding = activityAdminCrearEstudianteBinding.inflate(getLayoutInflater());
        setContentView(activityAdminCrearEstudianteBinding.getRoot());



        listaGrupos = (ArrayList<Grupo>) getIntent().getSerializableExtra("LISTA_GRUPOS");
        listaLicenciaturas = (ArrayList<Licenciatura>) getIntent().getSerializableExtra("LISTA_LICENCIATURAS");



        repoEstudiantes = new EstudiantesRepository();

        ImageButton btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(v -> finish());

        // Spinners
        spGrupo = findViewById(R.id.spGrupo);
        adapterGrupo = new GrupoSpinnerAdapter(this, listaGrupos);
        spGrupo.setAdapter(adapterGrupo);

        spLicenciatura = findViewById(R.id.spLicenciatura);
        adapterLicenciatura = new LicenciaturaSpinnerAdapter(this, listaLicenciaturas);
        spLicenciatura.setAdapter(adapterLicenciatura);

       // Seleccionar por ID

//       seleccionarPorIdGrupo(spGrupo, datosEstudiante.getIdGrupo());
//       seleccionarPorIdLicenciatura(spLicenciatura, datosEstudiante.getIdLicenciatura());

       // Campos
       campoNombre = findViewById(R.id.campoNombre);
       campoApePat = findViewById(R.id.campoApePat);
       campoApeMat = findViewById(R.id.campoApeMat);
       campoNumeroCuenta = findViewById(R.id.campoNumeroCuenta);
       campoCorreo = findViewById(R.id.campoCorreo);
       campoNumCel = findViewById(R.id.campoNumCel);
       campoContrasena = findViewById(R.id.campoContrasena);
       campoPromedio = findViewById(R.id.campoPromedio);



       btn_guardar = findViewById(R.id.btnGuardar);
        btn_guardar.setOnClickListener(v -> CrearEstudiante());
   }

    public void editarEstudiante() {
        String textoNumeroCuenta = campoNumeroCuenta.getText().toString().trim();

        Grupo grupo = (Grupo) spGrupo.getSelectedItem();
        Licenciatura licenciatura = (Licenciatura) spLicenciatura.getSelectedItem();

        if (grupo == null || licenciatura == null) {
            Toast.makeText(this, "Selecciona grupo y licenciatura", Toast.LENGTH_SHORT).show();
            return;
        }

        if (campoNombre.getText().toString().trim().isEmpty()
                || campoApePat.getText().toString().trim().isEmpty()
                || campoApeMat.getText().toString().trim().isEmpty()
                || textoNumeroCuenta.isEmpty()
                || campoCorreo.getText().toString().trim().isEmpty()
                || campoNumCel.getText().toString().trim().isEmpty()
                || campoContrasena.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int numeroCuenta;
        try {
            numeroCuenta = Integer.parseInt(textoNumeroCuenta);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de cuenta inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        int idGrupo = grupo.getIdGrupo();
        int idLicenciatura = licenciatura.getId_licenciatura();





    }

    public void CrearEstudiante(){
        String textoNumeroCuenta = campoNumeroCuenta.getText().toString().trim();
        Grupo grupo = (Grupo) spGrupo.getSelectedItem();
        Licenciatura licenciatura = (Licenciatura) spLicenciatura.getSelectedItem();
        String textoPromedio = campoPromedio.getText().toString().trim();

        int numeroCuenta = Integer.parseInt(textoNumeroCuenta);
        int idGrupo = grupo.getIdGrupo();
        int idLicenciatura = licenciatura.getId_licenciatura();
        double promedio = Double.parseDouble(textoPromedio);





        CrearEstudianteRequest request = new CrearEstudianteRequest
                (
                    campoNombre.getText().toString().trim(),
                    campoApePat.getText().toString().trim(),
                    campoApeMat.getText().toString().trim(),
                    numeroCuenta,
                    campoContrasena.getText().toString().trim(),
                    idLicenciatura,
                    idGrupo,
                    campoCorreo.getText().toString().trim(),
                    campoNumCel.getText().toString().trim(),
                    promedio,
                    3
                );

        repoEstudiantes.CrearEstudiante(request, new EstudiantesRepository.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if(data == 1){
                    finish();
                    Toast.makeText(
                            CrearEstudiantes.this,
                            "ESTUDIANTE CREADO EXITOSAMENTE",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void seleccionarPorIdGrupo(Spinner spinner, int idBuscado) {
        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Grupo g = (Grupo) spinner.getAdapter().getItem(i);
            if (g.getIdGrupo() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }

    private void seleccionarPorIdLicenciatura(Spinner spinner, int idBuscado) {
        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            Licenciatura li = (Licenciatura) spinner.getAdapter().getItem(i);
            if (li.getId_licenciatura() == idBuscado) {
                spinner.setSelection(i);
                return;
            }
        }
    }
}