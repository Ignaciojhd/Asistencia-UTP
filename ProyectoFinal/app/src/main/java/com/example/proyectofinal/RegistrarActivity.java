package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Modelos.LoginResponse;
import com.example.proyectofinal.Modelos.RegistrarResponse;
import com.example.proyectofinal.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtCorreo, txtPass, txtCedula, txtNombre, txtApellido;
    Button btnResgistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        InicializarControles();

    }

    private void InicializarControles() {

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        btnResgistrar = (Button) findViewById(R.id.btnRegistrar);


        btnResgistrar.setOnClickListener(v -> {

            if(!(txtCorreo.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty() || txtCedula.getText().toString().isEmpty() || txtNombre.getText().toString().isEmpty() || txtApellido.getText().toString().isEmpty())){


                Call<RegistrarResponse> asistencia = ApiService.getApiService().Registrar(txtNombre.getText().toString(), txtApellido.getText().toString(), txtCedula.getText().toString(),txtCorreo.getText().toString(), txtPass.getText().toString());

                asistencia.enqueue(new Callback<RegistrarResponse>() {
                    @Override
                    public void onResponse(Call<RegistrarResponse> call, Response<RegistrarResponse> response) {

                        if(response.code() == 200){

                            Log.i("Register", response.body().toString());
                            ShowAlert("Cuenta Registrada");

                        }else{

                            ShowAlert("Ya existen estos datos");

                        }


                    }

                    @Override
                    public void onFailure(Call<RegistrarResponse> call, Throwable t) {

                        Log.i("Register", t.toString());
                        ShowAlert("Error al registar cuenta");


                    }
                });

            }else{

                ShowAlert("Llene todos los campos!");

            }


        });


    }

    public void ShowAlert(String message){

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Aviso!");
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