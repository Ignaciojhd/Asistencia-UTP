package com.example.proyectofinal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.proyectofinal.Adaptadores.AdpPasarAsistenciaList;
import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Asistencia;
import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.Modelos.SpinnerItem;
import com.example.proyectofinal.Services.ApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasarAsistencia extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent mPendingIntent;
    List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
    List<Grupo> listaGrupos;
    Spinner spnGrupos;
    ListView lstEstudiantes;
    AdpPasarAsistenciaList adapter;
    Switch swtCorregir;
    int grupoId;
    Boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasar_asistencia);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null){
            Toast.makeText(this,"NO tiene NFC io!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);

        InicializarComponentes();

    }

    private void InicializarComponentes() {

        spnGrupos = (Spinner) findViewById(R.id.spnGrupos);
        lstEstudiantes = (ListView) findViewById(R.id.lstEstudiantes);
        swtCorregir = (Switch) findViewById(R.id.swtCorregir);

        ObtenerGrupos(); //Obtiene los grupos y llama a la funcion que llena el Spinner

        spnGrupos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                }else{
                    SpinnerItem item = (SpinnerItem) parent.getSelectedItem();
                    Toast.makeText(getApplicationContext(),item.getName() + " " + item.getId(),Toast.LENGTH_LONG).show();
                    grupoId = item.getId();
                    ObtenerEstudiantesPorGrupo(grupoId); //Obtiene la Lista de estudiantes por el grupo seleccionado para la variable global lstEstudiantes



                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lstEstudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(swtCorregir.isChecked()){

                    switch (adapter.ObtenerEstado(position)){

                        case "A":
                            adapter.CambiarEstado(position, "P");
                            break;

                        case "P":
                            adapter.CambiarEstado(position, "T");
                            break;

                        case "T":
                            adapter.CambiarEstado(position, "E");
                            break;

                        case "E":
                            adapter.CambiarEstado(position, "A");
                            break;

                        default:

                            break;

                    };



                }



            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }


    private void LlenarListView(){

        for (Estudiante e: listaEstudiantes) {
            e.setEstado("A");
        }

        adapter = new AdpPasarAsistenciaList(this, listaEstudiantes);
        lstEstudiantes.setAdapter(adapter);
    }

    private void ObtenerEstudiantesPorGrupo(int gaid){

        Call<List<Estudiante>> grupo = ApiService.getApiService().ObtenerEstudiantesPorGrupoId(gaid);
        listaEstudiantes.clear();
        grupo.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {

                listaEstudiantes.addAll(response.body());
                LlenarListView();


            }



            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {

            }
        });



    }

    private void ObtenerGrupos(){

        Call<List<Grupo>> grupo = ApiService.getApiService().ObtenerGruposPorProfesor(GlobalHelper.perfilLogeado.getDocente_id());

        grupo.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {

                listaGrupos = response.body();

                LlenarSpinner();
            }



            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {

            }
        });



    }

    private void LlenarSpinner() {

        List<SpinnerItem> lstItems = new ArrayList<SpinnerItem>();

        lstItems.add(new SpinnerItem(0,"(Grupo) Asignatura" ));

        for (Grupo g: listaGrupos) {


            lstItems.add(new SpinnerItem(g.getId(),"(" + g.getGrupo() + ") " + g.getAsignatura()));

        }

        ArrayAdapter<SpinnerItem> dataAdapter = new ArrayAdapter<SpinnerItem>(this, R.layout.spinner_item_selected, lstItems);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spnGrupos.setAdapter(dataAdapter);

    }






    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onNewIntent(Intent intent) {
        getTagInfo(intent);
        super.onNewIntent(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getTagInfo(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        String techList = tag.getTechList()[2];
        if (techList.equals(Ndef.class.getName())){
            Ndef mNdef = Ndef.get(tag);
            if (mNdef!= null) {
                NdefMessage mNdefMessage = mNdef.getCachedNdefMessage();
                NdefRecord[] records = mNdefMessage.getRecords();
                if(records != null){
                    String cedulaDecrypt = "";
                    String cedula = new String(mNdefMessage.getRecords()[0].getPayload());
                    cedula = cedula.substring(1,cedula.length() - 2); //Esto elimina simbolos adicionales que agrega el tag para que la comparacion de strings sea valida
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        cedulaDecrypt = GlobalHelper.loginHelper.decrypt(cedula);
                    }
                    for (Estudiante e: listaEstudiantes) {

                        Log.i("compare", cedulaDecrypt + "=" + e.getCedula());
                        if(cedulaDecrypt.equals(e.getCedula()) == true){
                           adapter.CambiarEstado(listaEstudiantes.indexOf(e), "P");
                           lstEstudiantes.setSelection(listaEstudiantes.indexOf(e));
                        }

                    }

                }





            }
        }
    }


    public void PostAsistencia(View view){



        if(listaEstudiantes.size() > 0){


            result = false;

            for (Estudiante e: listaEstudiantes) {


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(new Date());

                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm a");
                String time = simpleTimeFormat.format(new Date());

                Estudiante estudianteConAsistencia = (Estudiante) lstEstudiantes.getAdapter().getItem(listaEstudiantes.indexOf(e));

                Asistencia asis = new Asistencia(e.getId(),grupoId, ConvertirLetraAEstado(estudianteConAsistencia.getEstado()), date, time);

                Call<Asistencia> asistencia = ApiService.getApiService().MarcarAsistencia(date, time, e.getId(), grupoId, ConvertirLetraAEstado(estudianteConAsistencia.getEstado()));

                asistencia.enqueue(new Callback<Asistencia>() {
                    @Override
                    public void onResponse(Call<Asistencia> call, Response<Asistencia> response) {



                        result = true;
                        if(e == listaEstudiantes.get(listaEstudiantes.size() - 1)){

                            ShowAlert("Asistencia guardada exitosamente.", "Exito");

                        }


                    }



                    @Override
                    public void onFailure(Call<Asistencia> call, Throwable t) {

                        result = false;
                        if(e == listaEstudiantes.get(listaEstudiantes.size() - 1)){

                            ShowAlert("Error al guardar la asistencia. Intente nuevamente.", "Error");

                        }

                    }
                });





            }




        }






    }

    public void ShowAlert(String message, String tipo){

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
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

    public int ConvertirLetraAEstado(String estadoString){

        int estado = 3;

        switch (estadoString){

            case "P":

                estado = 1;
                break;

            case "T":

                estado = 2;
                break;

            case "A":

                estado = 3;
                break;

            case "E":

                estado = 4;
                break;

            default:
                break;
        }

        return estado;
    }


}