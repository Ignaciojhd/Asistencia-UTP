package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.Modelos.Asistencia;
import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Modelos.LoginResponse;
import com.example.proyectofinal.Services.ApiService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText txtCorreo, txtPass;
    Button btnResgistrar, btnLogin;
    LoginResponse inicio;
    Login perfilLogeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InicializarComponentes();


    }

    private void InicializarComponentes() {

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnResgistrar = (Button) findViewById(R.id.btnRegistrar);

        btnLogin.setOnClickListener(v -> {



            if(txtCorreo.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty()){

                ShowAlert("Llene todos los campos!", "Error!");


            }else{

                Call<LoginResponse> asistencia = ApiService.getApiService().LoginUser(txtCorreo.getText().toString(), txtPass.getText().toString());

                asistencia.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if(response.code() == 200){

                            inicio = response.body();


                            perfilLogeado = (Login) inicio.getUsuario();

                            CargarHome();

                        }else{

                            ShowAlert("Credenciales invalidas! Verifique sus datos", "Error!");

                        }






                    }



                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                        ShowAlert("Error. No se pudo procesar la verificación.", "Error!");

                    }
                });

            }



        });

        btnResgistrar.setOnClickListener(v -> {

        Intent i = new Intent(this, EstudianteHomeActivity.class);

        startActivity(i);

        });


    }

    private void CargarHome() {

        if(perfilLogeado.getRole() == 1){
            Intent i = new Intent(this, AdminHomeActivity.class);
            startActivity(i);
        }else if(perfilLogeado.getRole() == 2){
            Intent i = new Intent(this, ProfesorHomeActivity.class);
            startActivity(i);
        }else if(perfilLogeado.getRole() == 3){
            Intent i = new Intent(this, EstudianteHomeActivity.class);
            startActivity(i);
        }

    }

    public void ShowAlert(String message, String tipo){

        AlertDialog alertDialog = new AlertDialog.Builder(super.getApplicationContext()).create();
        alertDialog.setTitle(tipo);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
        alertDialog.show();

    }




}