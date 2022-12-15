package com.example.proyectofinal.Helpers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Services.ApiService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalHelper {

    public static Login perfilLogeado;
    private static byte[] key;
    private static final String EncriptionKey = "ONv4TI0nXn57FSf2dejLD7kL7AmuSNAE";
    private static SecretKeySpec secretKey;


    public static GlobalHelper loginHelper = new GlobalHelper();


    Estudiante estudiantePorId;
    public Estudiante ObtnerEstudiantePorId(int id){

        Call<List<Estudiante>> grupo = ApiService.getApiService().ObtenerEstudiantes();

        grupo.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {

                try{

                    List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

                    listaEstudiantes.addAll(response.body());

                    for (Estudiante e: listaEstudiantes) {

                        if (e.getId() == id){
                            estudiantePorId = e;

                        }

                    }

                }catch (Exception e){

                }



            }



            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {

            }
        });


        return estudiantePorId;
    }

    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encrypt(String strToEncrypt) {
        try {
            prepareSecreteKey(EncriptionKey);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String decrypt(String strToDecrypt) {
        try {
            prepareSecreteKey(EncriptionKey);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }



}
