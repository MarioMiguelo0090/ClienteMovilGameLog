package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;

import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;

import com.example.gamelog.databinding.ActivityScrollingInformacionJuegoBinding;
import com.example.gamelog.databinding.ContentScrollingInformacionBinding;

import java.util.ArrayList;


public class ScrollingInformacionJuego extends AppCompatActivity {

    private ActivityScrollingInformacionJuegoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingInformacionJuegoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        String fechaLanzamiento = intent.getStringExtra("fecha_lanzamiento");
        String imagenFondo = intent.getStringExtra("imagen_fondo");
        double calificacion = intent.getDoubleExtra("calificacion", 0);
        ArrayList<String> plataformas = getIntent().getStringArrayListExtra("plataformas");
        if (plataformas != null) {
            String disponibles = TextUtils.join(", ", plataformas);
            binding.contentScrollingInformacion.txvDisponible.setText(disponibles);
        }
        if(descripcion!=null){
            CharSequence descripcionFormateada = HtmlCompat.fromHtml(descripcion, HtmlCompat.FROM_HTML_MODE_LEGACY);
            binding.contentScrollingInformacion.txvDescripcion.setText(descripcionFormateada);
        }
        binding.contentScrollingInformacion.txvTituloJuego.setText(nombre);
        binding.contentScrollingInformacion.txvAnioLanzamiento.setText(fechaLanzamiento);
        binding.contentScrollingInformacion.ratingBar.setRating((float) calificacion);
        Glide.with(this).load(imagenFondo).into(binding.contentScrollingInformacion.imageJuego);
        binding.contentScrollingInformacion.btnReseniar.setOnClickListener(v -> {
            abrirVentanaResenia();
        });
        binding.contentScrollingInformacion.btnVerResenias.setOnClickListener(v->{
            abrirResenias();
        });
    }

    private void abrirVentanaResenia(){
        Intent intentReseniaJuego=new Intent(this, ReseniaJuego.class);
        startActivity(intentReseniaJuego);
    }

    private void abrirResenias(){
        Intent intentReseniaJugadores=new Intent(this,ReseniaJugadores.class);
        startActivity(intentReseniaJugadores);
    }
}