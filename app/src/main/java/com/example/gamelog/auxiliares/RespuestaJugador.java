package com.example.gamelog.auxiliares;

import java.util.List;

public class RespuestaJugador {
    private boolean error;
    private int estado;
    private List<Jugador> cuenta;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Jugador> getCuenta() {
        return cuenta;
    }

    public void setCuenta(List<Jugador> cuenta) {
        this.cuenta = cuenta;
    }
}
