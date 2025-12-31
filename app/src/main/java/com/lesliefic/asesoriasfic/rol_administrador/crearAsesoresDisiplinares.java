package com.lesliefic.asesoriasfic.rol_administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lesliefic.asesoriasfic.R;
import com.lesliefic.asesoriasfic.login.loginActivity;
import com.lesliefic.asesoriasfic.rol_administrador.asesoresDisciplinaresActivity;

public class crearAsesoresDisiplinares extends AppCompatActivity {

    ImageButton btn_regresar;
    EditText et_nombre_completo, et_telefono, et_correo;
    Button btn_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_crear_asesores_disiplinares);

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

         */


        btn_regresar = findViewById(R.id.btnRegresar);
        et_nombre_completo = findViewById(R.id.etNombre);
        et_correo = findViewById(R.id.etCorreo);
        et_telefono = findViewById(R.id.etTelefono);
        btn_guardar = findViewById(R.id.btnGuardar);

        btn_regresar.setOnClickListener(v -> {
            Intent intent = new Intent(crearAsesoresDisiplinares.this, asesoresDisciplinaresActivity.class);
            startActivity(intent);
        });

        btn_guardar.setOnClickListener(v -> {
            String nombre_completo = et_nombre_completo.getText().toString();
            String telefono = et_telefono.getText().toString();
            String correo = et_correo.getText().toString();

            if(nombre_completo.trim().isEmpty() || telefono.trim().isEmpty() || correo.trim().isEmpty() ){
                toastNotificacion("Todos los campos son obligatorios");
            }
            else{
                Intent intent = new Intent(crearAsesoresDisiplinares.this, asesoresDisciplinaresActivity.class);
                startActivity(intent);
            }
        });







    }

    public void toastNotificacion(String message){
        Toast.makeText(crearAsesoresDisiplinares.this, message, Toast.LENGTH_SHORT).show();
    }






}