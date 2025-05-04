package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.databinding.ActivityCodigoVerificacionBinding;

public class CodigoVerificacion extends AppCompatActivity {
    ActivityCodigoVerificacionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCodigoVerificacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCancelar.setOnClickListener(v -> {
            cerrarVentana();
        });
        binding.btnVerificar.setOnClickListener(v -> {
            validarCodigo();
        });

        EditText[] cuadros = {
                binding.editCuadro1,
                binding.editCuadro2,
                binding.editCuadro3,
                binding.editCuadro4,
                binding.editCuadro5,
                binding.editCuadro6
        };

        for (int i = 0; i < cuadros.length; i++) {
            final int index = i;
            cuadros[index].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() == 1 && index < cuadros.length - 1) {
                        cuadros[index + 1].requestFocus();
                    }
                }
            });
        }

    }

    private void cerrarVentana(){
        finish();
    }

    private void validarCodigo(){
        cerrarVentana();
        regresarInicioSesion();
    }

    private void regresarInicioSesion(){
        Intent intentInicioSesion=new Intent(this,MainActivity.class);
        startActivity(intentInicioSesion);
    }

}