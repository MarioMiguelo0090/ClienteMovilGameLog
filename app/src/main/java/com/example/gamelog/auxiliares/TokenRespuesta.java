package com.example.gamelog.auxiliares;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TokenRespuesta {
    private boolean error;
    private int estado;
    private List<Usuario> cuenta;

    public boolean isError() {
        return error;
    }

    public int getEstado() {
        return estado;
    }

    public List<Usuario> getCuenta() {
        return cuenta;
    }
}
