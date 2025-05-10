package com.example.gamelog.interfaces;

import com.example.gamelog.auxiliares.Constantes;
import com.example.gamelog.auxiliares.Jugador;
import com.example.gamelog.auxiliares.RespuestaJugador;
import com.example.gamelog.auxiliares.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.DELETE;

public interface ApiGameLogJugador {
    @GET("/"+ Constantes.NOMBRE_API+"/jugador/{nombreDeUsuario}")
    Call<RespuestaJugador> buscarJugador(@Path("nombreDeUsuario") String nombre, @Header("access_token") String token);

    @PUT("/"+Constantes.NOMBRE_API+"/jugador/{idJugador}")
    Call<Object> actualizarJugador(@Path("idJugador") String id, @Body Jugador jugador, @Header("Authorization") String token);

    @DELETE("/"+Constantes.NOMBRE_API+"/jugador/{idJugador}")
    Call<Object> eliminarJugador(@Path("idJugador") String id, @Header("Authorization") String token);

}
