package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectofinal.Adaptadores.AdaptadorMateriasList;
import com.example.proyectofinal.Adaptadores.AdpVerAsisPorGrupoList;
import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Asistencia;
import com.example.proyectofinal.Modelos.AsistenciaEstudiante;
import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilMateriasActivity extends AppCompatActivity {

    Login perfil;
    TextView lblNombre, lblCorreo, lblCedula, lblMaterias;
    List<Grupo> listaGrupos = new ArrayList<Grupo>();
    ListView lstMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_materias);
        IncializarControles();

    }


    private void LlenarListView(){


        AdaptadorMateriasList adapter = new AdaptadorMateriasList(this, listaGrupos);
        lstMaterias.setAdapter(adapter);
    }

    private void IncializarControles() {

        perfil = GlobalHelper.loginHelper.perfilLogeado;

        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lblCorreo = (TextView) findViewById(R.id.lblCorreo);
        lblCedula = (TextView) findViewById(R.id.lblCedula);
        lblMaterias = (TextView) findViewById(R.id.lblMaterias);
        lstMaterias = (ListView) findViewById(R.id.lstMaterias);

        lblNombre.setText(perfil.getNombres() + " " + perfil.getApellidos());


        SetGrupos();





    }



    private void SetGrupos(){



        if(perfil.getDocente_id() > 0){

            Call<List<Grupo>> grupo = ApiService.getApiService().ObtenerGruposPorProfesor(GlobalHelper.perfilLogeado.getDocente_id());

            grupo.enqueue(new Callback<List<Grupo>>() {
                @Override
                public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {

                    listaGrupos.clear();

                    listaGrupos = response.body();

                    int cantidad = listaGrupos.size();

                    lblMaterias.setText(Integer.toString(cantidad));

                    LlenarListView();


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

                    listaGrupos.clear();

                    listaGrupos = response.body();

                    int cantidad = listaGrupos.size();

                    lblMaterias.setText(Integer.toString(cantidad));

                    LlenarListView();

                }



                @Override
                public void onFailure(Call<List<Grupo>> call, Throwable t) {

                }
            });

        }





    }

}