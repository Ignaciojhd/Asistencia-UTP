package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Login;

public class PerfilAdminActivity extends AppCompatActivity {

    Login perfil;
    TextView lblNombre, lblCorreo, lblCedula, lblMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_admin);
        IncializarControles();
    }


    private void IncializarControles() {

        perfil = GlobalHelper.loginHelper.perfilLogeado;

        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lblCorreo = (TextView) findViewById(R.id.lblCorreo);
        lblCedula = (TextView) findViewById(R.id.lblCedula);
        lblMaterias = (TextView) findViewById(R.id.lblMaterias);

        lblNombre.setText(perfil.getNombres() + " " + perfil.getApellidos());
        lblCorreo.setText(perfil.getEmail());
        lblCedula.setText(perfil.getCedula());







    }
}