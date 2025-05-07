package com.example.gamelog.auxiliares;

import java.security.MessageDigest;

public class EncriptadorContrasena {
    public static String encriptarSHA256(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes("UTF-8"));
            StringBuilder resultado = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) resultado.append('0');
                resultado.append(hex);
            }
            return resultado.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }
}
