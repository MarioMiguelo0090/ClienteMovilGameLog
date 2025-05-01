package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRecuperar.setOnClickListener(v->{
            abrirRecuperacionContrasenia();
        });
        binding.btnCrearCuenta.setOnClickListener(v -> {
            abrirCreacionCuenta();
        });
        binding.btnIniciarSesion.setOnClickListener(v -> {
            abrirMenuPrincipal();
        });
    }

    private void abrirRecuperacionContrasenia(){
        Intent intentRecuperarContrasenia=new Intent(this, RecuperarContrasenia.class);
        startActivity(intentRecuperarContrasenia);
    }

    private void abrirMenuPrincipal(){
        Intent intentMenuActivity=new Intent(this, MenuActivity.class);
        startActivity(intentMenuActivity);
    }

    private void abrirCreacionCuenta(){
        Intent intenteCreacionCuenta=new Intent(this,CreacionCuenta.class);
        startActivity(intenteCreacionCuenta);
    }
}