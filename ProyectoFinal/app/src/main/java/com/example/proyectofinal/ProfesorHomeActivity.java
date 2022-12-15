package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfesorHomeActivity extends AppCompatActivity {

    Button btnPasarAsistencia, btnHistorial, btnPerfil, btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_home);

        InicializarComponenetes();

    }

    private void InicializarComponenetes() {

        btnPasarAsistencia = (Button) findViewById(R.id.btnPasarAsistencia);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
        btnPerfil = (Button) findViewById(R.id.btnPerfil);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnHistorial.setOnClickListener(v -> {

            try {

                Intent i = new Intent(this, HistorialProfesorActivity.class);

                startActivity(i);

            }catch (Exception e){

            }

        });

        btnPasarAsistencia.setOnClickListener(v -> {

            try {

                Intent i = new Intent(this, PasarAsistencia.class);

                startActivity(i);

            }catch (Exception e){

            }

        });

        btnPerfil.setOnClickListener(v -> {

            try {

                Intent i = new Intent(this, PerfilActivity.class);

                startActivity(i);

            }catch (Exception e){

            }

        });

        btnLogout.setOnClickListener(v -> {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);


        });




    }

    public void PasarAsistenciaClicked(View v){

        try {

            Intent i = new Intent(this, PasarAsistencia.class);

            startActivity(i);

        }catch (Exception e){

        }


    }

    public void VerHistorialClicked(View view){

        try {

            Intent i = new Intent(this, HistorialProfesorActivity.class);

            startActivity(i);

        }catch (Exception e){

        }


    }




}