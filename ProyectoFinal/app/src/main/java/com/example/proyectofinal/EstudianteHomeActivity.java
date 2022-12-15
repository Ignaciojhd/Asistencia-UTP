package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class EstudianteHomeActivity extends AppCompatActivity {

    Button btnHistorial, btnPerfil, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_home);

        InicializarControles();

    }

    private void InicializarControles() {

        btnHistorial = (Button) findViewById(R.id.btnHistorial);
        btnPerfil = (Button) findViewById(R.id.btnPerfil);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnHistorial.setOnClickListener(v -> {

            Intent i = new Intent(this, HistorialEstudianteActivity.class);
            startActivity(i);


        });

        btnPerfil.setOnClickListener(v -> {

            Intent i = new Intent(this, PerfilActivity.class);
            startActivity(i);


        });

        btnLogout.setOnClickListener(v -> {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);


        });

    }
}