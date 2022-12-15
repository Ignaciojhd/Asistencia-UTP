package com.example.proyectofinal.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Asistencia;
import com.example.proyectofinal.Modelos.AsistenciaEstudiante;
import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.R;
import com.example.proyectofinal.Services.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdpVerAsisPorGrupoList extends ArrayAdapter<AsistenciaEstudiante> {



    private List<AsistenciaEstudiante> asistencia = new ArrayList<>();
    List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

    public AdpVerAsisPorGrupoList(Context context, List<AsistenciaEstudiante> asistencia){
        super(context, R.layout.list_view_historial_template, asistencia);

        this.asistencia = asistencia;


    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_view_historial_template, null);


        try {



            ImageView imgEstudiante = (ImageView) item.findViewById(R.id.imgEstudiante);
            if(asistencia.get(position).getFoto_url() == null){
                imgEstudiante.setImageResource(R.drawable.estudiante);
            }else{
                Picasso.get().load(asistencia.get(position).getFoto_url()).into(imgEstudiante);
            }


            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(asistencia.get(position).getNombre() + " " + asistencia.get(position).getApellido());

            TextView lblCedula = (TextView)item.findViewById(R.id.lblCedula);
            lblCedula.setText(asistencia.get(position).getCedula());

            TextView lblEstado = (TextView)item.findViewById(R.id.lblEstado);

            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);
            lblFecha.setText(asistencia.get(position).getFecha());



            if(asistencia.get(position).getEstado() == 1){
                lblEstado.setBackgroundColor(Color.rgb(144,238,144));
                lblEstado.setText("P");

            }else if(asistencia.get(position).getEstado() == 3){
                lblEstado.setBackgroundColor(Color.rgb(255,0,0));
                lblEstado.setText("A");
            }else if(asistencia.get(position).getEstado() == 2){
                lblEstado.setBackgroundColor(Color.rgb(255,165,0));
                lblEstado.setText("T");
            }else if(asistencia.get(position).getEstado() == 4){
                lblEstado.setBackgroundColor(Color.rgb(211,211,211));
                lblEstado.setText("E");
            }

        }catch (Exception e){


        }


        return(item);
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }




}
