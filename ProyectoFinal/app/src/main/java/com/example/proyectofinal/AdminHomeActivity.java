package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomeActivity extends AppCompatActivity {


    Button btnConfigurar, btnPerfil, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        InicializarComponentes();

    }

    private void InicializarComponentes() {

        btnConfigurar = (Button) findViewById(R.id.btnConfigurar);
        btnPerfil = (Button) findViewById(R.id.btnPerfil);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnConfigurar.setOnClickListener(v -> {

            try {

                Intent i =  new Intent(this, EscribirTag.class);

                startActivity(i);

            }catch (Exception e){


            }


        });

        btnPerfil.setOnClickListener(v -> {

            try {

                Intent i =  new Intent(this, PerfilAdminActivity.class);

                startActivity(i);

            }catch (Exception e){


            }


        });

        btnLogout.setOnClickListener(v -> {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);


        });

    }


    public void ConfigurarClicked(View v){

        Intent i =  new Intent(this, EscribirTag.class);

        startActivity(i);



    }

}