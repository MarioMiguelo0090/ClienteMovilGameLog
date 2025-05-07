package com.example.gamelog.auxiliares;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ApiGameLog {
    @POST("/acceso")
    Call<Object> registrarAcceso(@Body Usuario usuario);
}
