package com.lesliefic.asesoriasfic;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {

    String prueba;

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



        btnIngresar.setOnClickListener(v -> {

            String cuenta = inputCuenta.getText().toString();
            String nip = inputNip.getText().toString();

            if (cuenta.equals("12345") && nip.equals("1234")) {
                Intent ingresarPaginaPrincipal = new Intent(loginActivity.this, InicioActivity.class);
                startActivity(ingresarPaginaPrincipal);
            }


        });

    }

}