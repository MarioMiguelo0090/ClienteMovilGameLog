package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.auxiliares.Validador;
import com.example.gamelog.databinding.ActivityRecuperarContraseniaBinding;

public class RecuperarContrasenia extends AppCompatActivity {

    ActivityRecuperarContraseniaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRecuperarContraseniaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCancelar.setOnClickListener(v->{
            regresarInicioSesion();
        });
        binding.btnAceptar.setOnClickListener(v -> {
            validarCuenta();
        });

    }

    private void regresarInicioSesion(){
        finish();
    }

    private void validarCuenta(){
        if(validarCampos()){
            abrirCodigoVerificacion();
        }
    }

    private void abrirCodigoVerificacion(){
        Intent intentCodigoVerificacion=new Intent(this,CodigoVerificacion.class);
        startActivity(intentCodigoVerificacion);
    }

    private boolean validarCampos(){
        String correo=binding.editCorreo.getText().toString().trim();
        String nombreUsuario=binding.editUsuario.getText().toString().trim();
        if(!Validador.esCorreoValido(correo)){
            binding.editCorreo.setError(getString(R.string.msj_correo_invalido));
        }
        if(!Validador.esNombreUsuarioValido(nombreUsuario)){
            binding.editUsuario.setError(getString(R.string.msj_nombre_usuario_invalido));
        }
        return Validador.esCorreoValido(correo)&&Validador.esNombreUsuarioValido(nombreUsuario);
    }
}