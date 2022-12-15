package com.example.proyectofinal.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorMateriasList extends ArrayAdapter<Grupo> {

    private List<Grupo> grupo = new ArrayList<>();

    public AdaptadorMateriasList(Context context, List<Grupo> estudiantes){
        super(context, R.layout.list_view_asistencia_template, estudiantes);

        grupo = estudiantes;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_view_materias_template, null);



        TextView lblAsignatura = (TextView)item.findViewById(R.id.lblAsignatura);
        lblAsignatura.setText(grupo.get(position).getAsignatura());

        TextView lblGrupo = (TextView)item.findViewById(R.id.lblGrupo);
        lblGrupo.setText(grupo.get(position).getGrupo());


        return(item);
    }




}
