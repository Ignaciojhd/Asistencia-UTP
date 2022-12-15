package com.example.proyectofinal.Adaptadores;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class AdpPasarAsistenciaList extends ArrayAdapter<Estudiante> {

    private List<Estudiante> estudiante = new ArrayList<>();

    public AdpPasarAsistenciaList(Context context, List<Estudiante> estudiantes){
        super(context, R.layout.list_view_asistencia_template, estudiantes);

        estudiante = estudiantes;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_view_asistencia_template, null);

        ImageView imgEstudiante = (ImageView) item.findViewById(R.id.imgEstudiante);
        if(estudiante.get(position).getFoto_url() == null){
            imgEstudiante.setImageResource(R.drawable.estudiante);
        }else{
            Picasso.get().load(estudiante.get(position).getFoto_url()).into(imgEstudiante);
        }


        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(estudiante.get(position).getNombre() + " " + estudiante.get(position).getApellido());

        TextView lblCedula = (TextView)item.findViewById(R.id.lblCedula);
        lblCedula.setText(estudiante.get(position).getCedula());

        TextView lblEstado = (TextView)item.findViewById(R.id.lblEstado);
        lblEstado.setText(estudiante.get(position).getEstado());

        if(estudiante.get(position).getEstado() == "P"){
            lblEstado.setBackgroundColor(Color.rgb(144,238,144));

        }else if(estudiante.get(position).getEstado() == "A"){
            lblEstado.setBackgroundColor(Color.rgb(255,0,0));

        }else if(estudiante.get(position).getEstado() == "T"){
            lblEstado.setBackgroundColor(Color.rgb(255,165,0));

        }else if(estudiante.get(position).getEstado() == "E"){
            lblEstado.setBackgroundColor(Color.rgb(211,211,211));
        }

        return(item);
    }

    public void CambiarEstado(int pos, String estado) {

        estudiante.get(pos).setEstado(estado);
        notifyDataSetChanged();

    }

    public String ObtenerEstado(int pos){

        return estudiante.get(pos).getEstado();
    }


}
