package com.example.gamelog.auxiliares;

public class LoginRequest {
    private String correo;
    private String contrasenia;

    private String tipoDeUsuario;


    public LoginRequest(String correo, String contrasenia, String tipoDeUsuario) {
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasena) {
        this.contrasenia = contrasena;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }
}
