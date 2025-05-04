package com.example.gamelog.auxiliares;

import android.util.Patterns;

public class Validador {
    private static final String PATRON_CONTRASENIA = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&/()_#^+=-]).{8,}$";

    private static final String PATRON_NOMBRE_USUARIO = "^[a-zA-Z0-9]{1,20}$";

    private static final String PATRON_SOLO_LETRAS = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

    public static boolean esCorreoValido(String correo) {
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }

    public static boolean esContraseniaValida(String contrasenia) {
        return contrasenia.matches(PATRON_CONTRASENIA);
    }

    public static boolean esNombreValido(String nombre) {
        return !nombre.isEmpty() && nombre.matches(PATRON_SOLO_LETRAS);
    }

    public static boolean esPrimerApellidoValido(String apellido) {
        return !apellido.isEmpty() && apellido.matches(PATRON_SOLO_LETRAS);
    }

    public static boolean esSegundoApellidoValido(String apellido) {
        return apellido.isEmpty() || apellido.matches(PATRON_SOLO_LETRAS);
    }

    public static boolean esNombreUsuarioValido(String nombreUsuario) {
        return nombreUsuario.matches(PATRON_NOMBRE_USUARIO);
    }

    public static boolean esDescripcionValida(String descripcion) {
        return descripcion.length() <= 200;
    }
}
