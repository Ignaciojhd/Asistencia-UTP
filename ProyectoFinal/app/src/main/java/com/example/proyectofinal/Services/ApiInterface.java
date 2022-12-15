package com.example.proyectofinal.Services;

import com.example.proyectofinal.Modelos.Asistencia;
import com.example.proyectofinal.Modelos.Estudiante;
import com.example.proyectofinal.Modelos.Grupo;
import com.example.proyectofinal.Modelos.Login;
import com.example.proyectofinal.Modelos.LoginResponse;
import com.example.proyectofinal.Modelos.RegistrarResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("estudiantes/all")
    Call<List<Estudiante>> ObtenerEstudiantes();

    @GET("grupos/all")
    Call<List<Grupo>> ObtenerGrupos();

    @GET("grupos/estudiante/{eid}")
    Call<List<Grupo>> ObtenerGruposPorEstudiante(@Path("eid") int id);

    @GET("grupos/profesor/{gid}")
    Call<List<Grupo>> ObtenerGruposPorProfesor(@Path("gid") int id);

    @GET("estudiantes/grupos/{id}")
    Call<List<Estudiante>> ObtenerEstudiantesPorGrupoId(@Path("id") int id);

    @GET("estudiantes/asistencia/{id}")
    Call<List<Asistencia>> ObtenerEstudiantesQueAsistieronPorGrupoId(@Path("id") int id);

    @GET("estudiante/asistencia/{gaid}/{eid}")
    Call<List<Asistencia>> ObtenerAsistenciadeEstudiante(@Path("gaid") int gaid, @Path("eid") int eid);


    @POST("estudiante/asistencia")
    Call<Asistencia> MarcarAsistencia(@Query("fecha") String fecha, @Query("hora") String hora, @Query("estudiante_id") int estudiante_id, @Query("grupo_asignatura_id") int grupo_asignatura_id, @Query("estado_asistencia_id") int estado_asistencia_id);


    @POST("login")
    Call<LoginResponse> LoginUser(@Query("usuario") String usuario, @Query("contrasena") String contrasena);

    @Headers("Accept: application/json")
    @POST("registrar")
    Call<RegistrarResponse> Registrar(@Query("nombre") String nombre, @Query("apellido") String apellido, @Query("cedula") String cedula, @Query("correo") String correo, @Query("contrasena") String pass);

}
