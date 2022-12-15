package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdpVerAsisPorGrupoList;
import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Asistencia;
import com.example.proyectofinal.Modelos.AsistenciaEstudiante;
import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.Modelos.SpinnerItem;
import com.example.proyectofinal.Services.ApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialEstudianteActivity extends AppCompatActivity {


    List<Grupo> listaGrupos;
    Spinner spnGrupos;
    ListView lstAsistencia;
    AdpVerAsisPorGrupoList adapter;
    List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
    List<Asistencia> listaAsistencia = new ArrayList<Asistencia>();
    List<AsistenciaEstudiante> listaAsistenciaEstudiante = new ArrayList<AsistenciaEstudiante>();
    int grupoId, estudianteId;
    Button btnBuscar, btnDataPicker;
    private DatePickerDialog datePickerDialog;
    String fecha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_estudiante);
        getEstudiantes();
        InicializarControles();
        initDatePicker();

    }

    private void InicializarControles() {

        lstAsistencia = (ListView) findViewById(R.id.lstAsistencia);
        spnGrupos = (Spinner) findViewById(R.id.spnGrupo);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnDataPicker = (Button) findViewById(R.id.btnDatePicker);
        btnDataPicker.setText("");

        estudianteId = GlobalHelper.loginHelper.perfilLogeado.getEstudiante_id(); //pendiente a mejorar

        ObtenerGrupos();

        spnGrupos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                }else{
                    SpinnerItem item = (SpinnerItem) parent.getSelectedItem();
                    Toast.makeText(getApplicationContext(),item.getName() + " " + item.getId(),Toast.LENGTH_LONG).show();
                    grupoId = item.getId();
                    fecha = "";
                    btnDataPicker.setText("");



                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        btnBuscar.setOnClickListener(v -> {

            if(grupoId != 0){
                ObtenerAsistenciaPorGrupoYEstudiante();
            }


        });

        btnDataPicker.setOnClickListener(v -> {

            datePickerDialog.show();


        });


    }





    private void LlenarSpinnerGrupos() {

        List<SpinnerItem> lstItems = new ArrayList<SpinnerItem>();

        lstItems.add(new SpinnerItem(0,"(Grupo) Asignatura" ));

        for (Grupo g: listaGrupos) {


            lstItems.add(new SpinnerItem(g.getId(),"(" + g.getGrupo() + ") " + g.getAsignatura()));

        }

        ArrayAdapter<SpinnerItem> dataAdapter = new ArrayAdapter<SpinnerItem>(this,R.layout.spinner_item_selected, lstItems);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spnGrupos.setAdapter(dataAdapter);

    }




    private void ObtenerGrupos(){

        Call<List<Grupo>> grupo = ApiService.getApiService().ObtenerGruposPorEstudiante(GlobalHelper.perfilLogeado.getEstudiante_id());

        grupo.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                try{
                    listaGrupos = response.body();

                    LlenarSpinnerGrupos();

                }catch (Exception e){

                }

            }



            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {

            }
        });



    }

    private void LlenarListView(){

        listaAsistenciaEstudiante.clear();

        for (Asistencia a: listaAsistencia) {

            Estudiante e = ObtnerEstudiantePorId(estudianteId);

            AsistenciaEstudiante as = new AsistenciaEstudiante(a.getFecha(), e.getNombre(), e.getApellido(), e.getCedula(), e.getFoto_url(), a.getEstado_asistencia_id());

            listaAsistenciaEstudiante.add(as);

        }

        adapter = new AdpVerAsisPorGrupoList(this, listaAsistenciaEstudiante);
        lstAsistencia.setAdapter(adapter);
    }





    private void ObtenerAsistenciaPorGrupoYEstudiante(){

        Call<List<Asistencia>> grupo = ApiService.getApiService().ObtenerAsistenciadeEstudiante(grupoId, estudianteId);

        grupo.enqueue(new Callback<List<Asistencia>>() {
            @Override
            public void onResponse(Call<List<Asistencia>> call, Response<List<Asistencia>> response) {

                try{
                    listaAsistencia.clear();

                    List<Asistencia> listaNegra = new ArrayList<Asistencia>();
                    if(fecha.isEmpty()){

                        listaAsistencia.addAll(response.body());
                    }else{

                        listaAsistencia.addAll(response.body());

                        for (Asistencia a: listaAsistencia) {

                            if(!(a.getFecha().equals(fecha))){

                                listaNegra.add(a);

                            }

                        }

                        for (Asistencia a: listaNegra) {

                            listaAsistencia.remove(a);


                        }

                    }



                    LlenarListView();
                }catch (Exception e){

                }


            }



            @Override
            public void onFailure(Call<List<Asistencia>> call, Throwable t) {

            }
        });



    }

    public void getEstudiantes(){

        Call<List<Estudiante>> grupo = ApiService.getApiService().ObtenerEstudiantes();

        grupo.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {

                try{

                    listaEstudiantes.addAll(response.body());



                }catch (Exception e){

                }



            }



            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {

            }
        });



    }

    public Estudiante ObtnerEstudiantePorId(int id){

        Estudiante estudianteReturn = new Estudiante();

        for (Estudiante e: listaEstudiantes) {

            if (e.getId() == id){
                estudianteReturn = e;

            }

        }


        return estudianteReturn;
    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year)
    {
        String monthString, dayString;

        if(month < 10){
            monthString = "0" + month;
        }else{
            monthString = Integer.toString(month);
        }
        if(day < 10){
            dayString = "0" + day;
        }else{
            dayString = Integer.toString(day);
        }

        return year + "-" + monthString + "-" + dayString;
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                fecha = date;
                btnDataPicker.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }


}