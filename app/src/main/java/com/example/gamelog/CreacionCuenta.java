package com.example.gamelog;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.databinding.ActivityCreacionCuentaBinding;
import com.example.gamelog.databinding.ActivityMainBinding;
import com.example.gamelog.databinding.ActivityRecuperarContraseniaBinding;

public class CreacionCuenta extends AppCompatActivity {
    ActivityCreacionCuentaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCreacionCuentaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCancelar.setOnClickListener(v -> {
            regresarInicioSesion();
        });
    }

    private void regresarInicioSesion(){
        finish();
    }
}