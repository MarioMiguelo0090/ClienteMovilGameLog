package com.example.gamelog.interfaces;

import com.example.gamelog.auxiliares.Constantes;
import com.example.gamelog.auxiliares.Jugador;
import com.example.gamelog.auxiliares.RelacionSeguidor;
import com.example.gamelog.auxiliares.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface ApiGameLogSeguidor {
    @POST("/"+Constantes.NOMBRE_API+"/seguidor")
    Call<Object> seguirJugador(@Body RelacionSeguidor relacion, @Header("Authorization") String token);

    @GET("/"+ Constantes.NOMBRE_API+"/seguidor/seguidores/{idJugadorSeguido}")
    Call<List<Jugador>> consultarSeguidores(@Path("idJugadorSeguido") String id, @Header("Authorization") String token);

    @GET("/"+Constantes.NOMBRE_API+"/seguidor/seguidos/{idJugadorSeguidor}")
    Call<List<Jugador>> consultarSeguidos(@Path("idJugadorSeguidor") String id, @Header("Authorization") String token);

    @DELETE("/"+Constantes.NOMBRE_API+"/seguidor/{idJugadorSeguidor}/{idJugadorSeguido}")
    Call<Object> dejarDeSeguir(@Path("idJugadorSeguidor") String seguidorId, @Path("idJugadorSeguido") String seguidoId, @Header("Authorization") String token);



}
