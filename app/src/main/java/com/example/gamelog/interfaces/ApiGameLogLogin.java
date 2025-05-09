package com.example.gamelog.interfaces;

import com.example.gamelog.auxiliares.Constantes;
import com.example.gamelog.auxiliares.LoginRequest;
import com.example.gamelog.auxiliares.TokenRespuesta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiGameLogLogin {
    @POST("/"+ Constantes.NOMBRE_API+"/login")
    Call<TokenRespuesta> login(@Body LoginRequest loginRequest);

}
