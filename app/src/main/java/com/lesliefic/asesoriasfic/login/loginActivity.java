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
import com.lesliefic.asesoriasfic.modelo.LoginResponse;
import com.lesliefic.asesoriasfic.network.request.admin.IniciarSesionRequest;
import com.lesliefic.asesoriasfic.repositorios.UsuariosRepository;
import com.lesliefic.asesoriasfic.rol_administrador.AsesoriasEnCursoAdminActivity;
import com.lesliefic.asesoriasfic.rol_asesor.inicioAsesorActivity;
import com.lesliefic.asesoriasfic.rol_estudiante.inicioEstudianteActivity;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity {

    UsuariosRepository repoUsuarios;

    EditText inputCuenta, inputNip;

    LoginResponse datosLogin;

    int connected = 0;
    int rol = 0;

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
        repoUsuarios = new UsuariosRepository();

        Button btnIngresar = findViewById(R.id.btn_ingresar);

        inputCuenta = findViewById(R.id.input_cuenta);
        inputNip = findViewById(R.id.input_nip);

        ArrayList<Persona> Personas = new ArrayList<>();

        Personas.add(new Persona(12345, 1234, 1));
        Personas.add(new Persona(12346, 1235, 2));
        Personas.add(new Persona(12347, 1236, 3));

        btnIngresar.setOnClickListener(v -> {
            if(inputCuenta.getText().toString().isEmpty() || inputNip.getText().toString().isEmpty()){
                toastNotificacion("ingrese todas las credenciales");
            } else{
                iniciarSesion();
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

    private void guardarIdUsuario(int id){
        SharedPreferences prefs = getSharedPreferences("id_usuario", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("ID_USUARIO", id);
        editor.apply();
    }

    private int obtenerIdUsuario() {
        SharedPreferences prefs = getSharedPreferences("id_usuario", MODE_PRIVATE);
        return prefs.getInt("ID_USUARIO", 1);
    }

    public void iniciarSesion(){
        String usuarioText = inputCuenta.getText().toString();
        int usuario = Integer.parseInt(usuarioText);
        String contrasena = inputNip.getText().toString();
        IniciarSesionRequest request = new IniciarSesionRequest(usuario, contrasena);

        repoUsuarios.iniciarSesion(request, new UsuariosRepository.ResultCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse data) {
                if(data.getOk() != null && data.getOk() == 1){
                    int rol = data.getIdRol();
                    guardarRol(rol);
                    guardarIdUsuario(data.getIdPersona());

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

                            case 4:
                            guardarRol(rol);
                            Intent ingresarInicioAsesorPar = new Intent(loginActivity.this, inicioAsesorActivity.class);
                            startActivity(ingresarInicioAsesorPar);
                            break;

                    }
                }
                else{
                    toastNotificacion("Credenciales incorrectas, intente de nuevo");
                }
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
    }

}