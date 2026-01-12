package com.lesliefic.asesoriasfic.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.modelo.Grupo;
import com.lesliefic.asesoriasfic.modelo.Horario;
import com.lesliefic.asesoriasfic.modelo.Licenciatura;
import com.lesliefic.asesoriasfic.modelo.Materia;
import com.lesliefic.asesoriasfic.repositorios.CatalogosRepository;
import com.lesliefic.asesoriasfic.rol_administrador.AsesoriasEnCursoAdminActivity;
import com.lesliefic.asesoriasfic.rol_asesor.inicioAsesorActivity;
import com.lesliefic.asesoriasfic.rol_estudiante.inicioEstudianteActivity;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {

    private CatalogosRepository repoCatalogos;

    private List<Licenciatura> listaLicenciaturas = new ArrayList<>();
    private List<Grupo> listaGrupos = new ArrayList<>();
    private List<Horario> listaHorarios = new ArrayList<>();
    private List<Materia> listaMaterias = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnIngresar = findViewById(R.id.btn_ingresar);

        EditText inputCuenta = findViewById(R.id.input_cuenta);
        EditText inputNip = findViewById(R.id.input_nip);

        ArrayList<Persona> Personas = new ArrayList<>();

        Personas.add(new Persona(12345, 1234, 1));
        Personas.add(new Persona(12346, 1235, 2));
        Personas.add(new Persona(12347, 1236, 3));





        btnIngresar.setOnClickListener(v -> {

            String cuenta = inputCuenta.getText().toString();
            String nip = inputNip.getText().toString();
            int connected = 0;
            int rol = 0;

            for(Persona p : Personas){
                String usuarioList = String.valueOf(p.usuario);
                String nipList = String.valueOf(p.nip);
                if(cuenta.equals(usuarioList) && nip.equals(nipList)){
                    connected = 1;
                    rol = p.rol;
                }
            }

            if (connected == 1) {
                switch (rol) {

                    case 1:
                        guardarRol(rol);
                        Intent ingresarInicioAdmin = new Intent(loginActivity.this, AsesoriasEnCursoAdminActivity.class);

                        startActivity(ingresarInicioAdmin);
                        break;

                    case 2:
                        guardarRol(rol);
                        Intent ingresarInicioAsesor = new Intent(loginActivity.this, inicioAsesorActivity.class);
                        startActivity(ingresarInicioAsesor);
                        break;

                    case 3:
                        guardarRol(rol);
                        Intent ingresarInicioEstudiante = new Intent(loginActivity.this, inicioEstudianteActivity.class);
                        startActivity(ingresarInicioEstudiante);
                        break;

                }

            }
            else{
                toastNotificacion("Credenciales incorrectas, intente de nuevo");
            }


        });

    }

    public void toastNotificacion(String message){
        Toast.makeText(loginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public class Persona{
        int usuario;
        int nip;
        int rol;

        public Persona(int usuario, int nip, int rol){
            this.usuario = usuario;
            this.nip = nip;
            this.rol = rol;
        }
    }

    private void guardarRol(int rol){
        SharedPreferences prefs = getSharedPreferences("sesion", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("ROL", rol);
        editor.apply();
    }

    public void obtenerCatalogos(){
        repoCatalogos.obtenerLicenciaturas(new CatalogosRepository.ResultCallback<List<Licenciatura>>() {
            @Override
            public void onSuccess(List<Licenciatura> data) {
                listaLicenciaturas.clear();
                listaLicenciaturas.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerGrupos(new CatalogosRepository.ResultCallback<List<Grupo>>() {
            @Override
            public void onSuccess(List<Grupo> data) {
                listaGrupos.clear();
                listaGrupos.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerHorarios(new CatalogosRepository.ResultCallback<List<Horario>>() {
            @Override
            public void onSuccess(List<Horario> data) {
                listaHorarios.clear();
                listaHorarios.addAll(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(
                        getApplicationContext(),
                        "error:" + error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        repoCatalogos.obtenerMaterias(new CatalogosRepository.ResultCallback<List<Materia>>() {
            @Override
            public void onSuccess(List<Materia> data) {
                listaMaterias.clear();
                listaMaterias.addAll(data);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

}