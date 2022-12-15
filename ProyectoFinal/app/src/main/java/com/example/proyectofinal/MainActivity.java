package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Modelos.LoginResponse;
import com.example.proyectofinal.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo, txtPass;
    Button btnResgistrar, btnLogin;
    LoginResponse inicio;
    Login perfilLogeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarComponentes();

    }

    private void InicializarComponentes() {

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnResgistrar = (Button) findViewById(R.id.btnRegistrar);

        btnLogin.setOnClickListener(v -> {



            if(txtCorreo.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty()){

                Toast.makeText(this, "Escriba sus Datos, por favor.", Toast.LENGTH_LONG);


            }else{

                Call<LoginResponse> asistencia = ApiService.getApiService().LoginUser(txtCorreo.getText().toString(), txtPass.getText().toString());

                asistencia.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if(response.code() == 200){

                            inicio = response.body();


                            perfilLogeado = (Login) inicio.getUsuario();

                            CargarHome();

                        }else if(response.code() == 422){



                        }






                    }



                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });

            }



        });

        btnResgistrar.setOnClickListener(v -> {

            Intent i = new Intent(this, RegistrarActivity.class);

            startActivity(i);

        });


    }

    private void CargarHome() {

        GlobalHelper.loginHelper.perfilLogeado = perfilLogeado;

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




}