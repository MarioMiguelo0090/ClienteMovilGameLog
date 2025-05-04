package com.example.gamelog.auxiliares;

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
