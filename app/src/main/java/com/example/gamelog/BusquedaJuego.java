package com.example.gamelog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gamelog.auxiliares.RAWGService;
import com.example.gamelog.auxiliares.Juego;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;




import com.example.gamelog.databinding.ActivityBusquedaJuegoBinding;

import java.util.ArrayList;

public class BusquedaJuego extends AppCompatActivity {
    ActivityBusquedaJuegoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBusquedaJuegoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.searchBusquedaJuego.setIconified(false);
        binding.searchBusquedaJuego.requestFocus();
        binding.searchBusquedaJuego.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String consulta) {
                buscarJuego(consulta);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void abrirInformacionJuego(Juego juego){
        Intent intentInformacionJuego = new Intent(BusquedaJuego.this, ScrollingInformacionJuego.class);
        intentInformacionJuego.putExtra("nombre", juego.getName());
        intentInformacionJuego.putExtra("descripcion", juego.getDescription());
        intentInformacionJuego.putExtra("fecha_lanzamiento", juego.getReleased());
        intentInformacionJuego.putExtra("imagen_fondo", juego.getBackgroundImage());
        intentInformacionJuego.putExtra("calificacion", juego.getRating());
        ArrayList<String> plataformas = new ArrayList<>();
        for (Juego.PlatformWrapper p : juego.getPlatforms()) {
            plataformas.add(p.getPlatform().getName());
        }
        intentInformacionJuego.putStringArrayListExtra("plataformas", plataformas);
        startActivity(intentInformacionJuego);
    }

    private void buscarJuego(String consulta){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RAWGService service = retrofit.create(RAWGService.class);
        String apiKey = BuildConfig.RAWG_API_KEY;

        Call<Juego> llamada = service.obtenerJuego(consulta.toLowerCase().replace(" ", "-"), apiKey);

        llamada.enqueue(new Callback<Juego>() {
            @Override
            public void onResponse(Call<Juego> call, Response<Juego> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Juego juego = response.body();
                    binding.txvTituloJuego.setText(juego.getName());
                    binding.txvTituloJuego.setVisibility(View.VISIBLE);
                    binding.imgJuego.setVisibility(View.VISIBLE);

                    Glide.with(BusquedaJuego.this)
                            .load(juego.getBackgroundImage())
                            .into(binding.imgJuego);

                    binding.imgJuego.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            abrirInformacionJuego(juego);
                        }
                    });
                } else {
                    Toast.makeText(BusquedaJuego.this, getString(R.string.msj_juego_no_encontrado), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Juego> call, Throwable t) {
                Toast.makeText(BusquedaJuego.this, getString(R.string.msj_error_conexion), Toast.LENGTH_SHORT).show();
            }
        });
    }
}