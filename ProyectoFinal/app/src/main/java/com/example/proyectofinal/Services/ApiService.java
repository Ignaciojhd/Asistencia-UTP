package com.example.proyectofinal.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static ApiInterface API_SERVICE;


    public static ApiInterface getApiService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl = "https://asistencia-upn43.ondigitalocean.app/api/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            API_SERVICE = retrofit.create(ApiInterface.class);
        }

        return API_SERVICE;
    }



}
