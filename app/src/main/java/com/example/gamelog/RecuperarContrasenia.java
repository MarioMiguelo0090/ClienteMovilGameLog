package com.example.gamelog;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    }

    private void regresarInicioSesion(){
        finish();
    }
}