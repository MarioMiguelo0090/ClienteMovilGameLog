package com.example.gamelog.interfaces;

import com.example.gamelog.auxiliares.Constantes;
import com.example.gamelog.auxiliares.Juego;

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
import retrofit2.http.Query;

public interface ApiGameLogJuego {
    @POST("/"+ Constantes.NOMBRE_API+"/juego")
    Call<Object> registrarJuego(@Body Juego juego, @Header("Authorization") String token);

    @GET("/"+Constantes.NOMBRE_API+"/juego/{idJuego}")
    Call<Juego> buscarJuegoPorId(@Path("idJuego") String id, @Header("Authorization") String token);

    @GET("/"+Constantes.NOMBRE_API+"/juego")
    Call<List<Juego>> buscarJuegoPorNombre(@Query("nombre") String nombre, @Header("Authorization") String token);

    @DELETE("/"+Constantes.NOMBRE_API+"/juego/{idJuego}")
    Call<Object> eliminarJuego(@Path("idJuego") String id, @Header("Authorization") String token);

}
