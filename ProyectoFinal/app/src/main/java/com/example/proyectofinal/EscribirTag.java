package com.example.proyectofinal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.proyectofinal.Helpers.GlobalHelper;
import com.example.proyectofinal.Modelos.Estudiante;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class EscribirTag extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent mPendingIntent;
    Boolean pressed = false;
    Button btnActivar;
    EditText txtCedula;
    TextView lblNFC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_tag);

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

        btnActivar = (Button) findViewById(R.id.btnActivar);
        txtCedula = (EditText) findViewById(R.id.txtCedula);
        lblNFC = (TextView) findViewById(R.id.lblNFC);


    }

    public void ActivarEscritura(View v){

        pressed = true;
        lblNFC.setVisibility(View.VISIBLE);
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


    @Override
    protected void onNewIntent(Intent intent) {
        try {

            if(pressed == true){
                String ced = txtCedula.getText().toString();
                String cedEncriptada = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    cedEncriptada = GlobalHelper.loginHelper.encrypt(ced);
                }
                setTagInfo(intent, cedEncriptada);
                pressed = false;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        super.onNewIntent(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTagInfo(Intent intent, String text) throws UnsupportedEncodingException {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        String techList = tag.getTechList()[2];
        if (techList.equals(Ndef.class.getName())){

            NdefRecord[] records = {createRecord(text)};
            NdefMessage message = new NdefMessage(records);

            Ndef mNdef = Ndef.get(tag);
            if (mNdef!= null) {

                try {

                    mNdef.connect();
                    mNdef.writeNdefMessage(message);
                    mNdef.close();

                    Toast.makeText(getApplicationContext(),"Escritura Exitosa",Toast.LENGTH_LONG).show();
                    lblNFC.setVisibility(View.INVISIBLE);

                }catch (Exception e){
                    lblNFC.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Error de Escritura",Toast.LENGTH_LONG).show();
                }






            }
        }
    }

    private NdefRecord createRecord(String text) throws UnsupportedEncodingException{

        String lang = "en";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength = langBytes.length;
        int textLength = textBytes.length;
        byte[] payload = new byte[1 + langLength + textLength];

        //payload[0] = (byte) langLength;

        System.arraycopy(langBytes, 0, payload, 1, langLength);
        System.arraycopy(textBytes, 0, payload, 1, textLength);

        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload);

        return recordNFC;


    }

}