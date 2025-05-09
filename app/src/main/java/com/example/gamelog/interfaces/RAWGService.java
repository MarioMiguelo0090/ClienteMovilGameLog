package com.example.gamelog.interfaces;

import com.example.gamelog.auxiliares.Juego;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RAWGService {
    @GET("games/{slug}")
    Call<Juego> obtenerJuego(
            @Path("slug") String slug,
            @Query("key") String apiKey
    );
}
