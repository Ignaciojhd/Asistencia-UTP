package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {


    Login perfil;
    TextView lblNombre, lblCorreo, lblCedula, lblMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        IncializarControles();


    }

    private void IncializarControles() {

        perfil = GlobalHelper.loginHelper.perfilLogeado;

        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lblCorreo = (TextView) findViewById(R.id.lblCorreo);
        lblCedula = (TextView) findViewById(R.id.lblCedula);
        lblMaterias = (TextView) findViewById(R.id.lblMaterias);

        try{

            lblNombre.setText(perfil.getNombres() + " " + perfil.getApellidos());
            lblCorreo.setText(perfil.getEmail());
            lblCedula.setText(perfil.getCedula());

        }catch (Exception e){



        }

        lblMaterias.setOnClickListener(v -> {

            Intent i = new Intent(this, PerfilMateriasActivity.class);
            startActivity(i);


        });

        try{
            SetMateriasText();
        }catch (Exception e){

        }








    }



        private void SetMateriasText(){



            if(perfil.getDocente_id() > 0){

                Call<List<Grupo>> grupo = ApiService.getApiService().ObtenerGruposPorProfesor(GlobalHelper.perfilLogeado.getDocente_id());

                grupo.enqueue(new Callback<List<Grupo>>() {
                    @Override
                    public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {

                        List<Grupo> listaGrupos = response.body();

                        int cantidad = listaGrupos.size();

                        lblMaterias.setText(Integer.toString(cantidad));


                    }



                    @Override
                    public void onFailure(Call<List<Grupo>> call, Throwable t) {

                    }
                });

            }else{

                Call<List<Grupo>> grupo = ApiService.getApiService().ObtenerGruposPorEstudiante(GlobalHelper.perfilLogeado.getEstudiante_id());

                grupo.enqueue(new Callback<List<Grupo>>() {
                    @Override
                    public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {

                        List<Grupo> listaGrupos = response.body();

                        int cantidad = listaGrupos.size();

                        lblMaterias.setText(Integer.toString(cantidad));


                    }



                    @Override
                    public void onFailure(Call<List<Grupo>> call, Throwable t) {

                    }
                });

            }





        }



}