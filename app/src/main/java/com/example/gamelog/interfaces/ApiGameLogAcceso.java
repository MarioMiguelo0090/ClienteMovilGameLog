package com.example.gamelog.interfaces;
import com.example.gamelog.auxiliares.Constantes;
import com.example.gamelog.auxiliares.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.DELETE;


public interface ApiGameLogAcceso {
    ;
    @POST("/"+Constantes.NOMBRE_API+"/acceso")
    Call<Object> registrarAcceso(@Body Usuario usuario);

    @GET("/"+Constantes.NOMBRE_API+"/acceso/{correo}")
    Call<Object> obtenerIdCuenta(@Path("correo") String correo, @Header("Authorization") String token);

    @GET("/"+Constantes.NOMBRE_API+"/acceso/login")
    Call<Object> obtenerUsuarioLogin(@Header("Authorization") String token);

    @PUT("/"+Constantes.NOMBRE_API+"/acceso/{idAcceso}")
    Call<Object> editarAcceso(@Path("idAcceso") String id, @Body Usuario usuario, @Header("Authorization") String token);

    //@PATCH("/"+Constantes.NOMBRE_API+"/acceso/{idAcceso}")
    //Call<Object> editarEstadoAcceso(@Path("idAcceso") String id, @Body EstadoAcceso estado, @Header("Authorization") String token);

    @DELETE("/"+Constantes.NOMBRE_API+"/acceso/{idAcceso}")
    Call<Object> eliminarAcceso(@Path("idAcceso") String id, @Header("Authorization") String token);

}
