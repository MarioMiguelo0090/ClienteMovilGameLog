package com.example.gamelog;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.databinding.ActivityBusquedaPerfilBinding;

public class BusquedaPerfil extends AppCompatActivity {
    ActivityBusquedaPerfilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBusquedaPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.searchBusquedaPerfil.setIconified(false);
        binding.searchBusquedaPerfil.requestFocus();
        binding.searchBusquedaPerfil.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String consulta) {
                buscarPerfil(consulta);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void buscarPerfil(String consulta){
        Toast.makeText(this, "Buscando: " + consulta, Toast.LENGTH_SHORT).show();
    }


}

