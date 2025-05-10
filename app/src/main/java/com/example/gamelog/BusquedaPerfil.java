package com.example.gamelog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.auxiliares.APICliente;
import com.example.gamelog.auxiliares.Jugador;
import com.example.gamelog.auxiliares.RespuestaJugador;
import com.example.gamelog.databinding.ActivityBusquedaPerfilBinding;
import com.example.gamelog.interfaces.ApiGameLogJugador;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void buscarPerfil(String consulta) {
        if(!validarToken()){
            return;
        }
        ApiGameLogJugador api = APICliente.getRetrofit().create(ApiGameLogJugador.class);
        Call<RespuestaJugador> call = api.buscarJugador(consulta,"Bearer " +  obtenerToken());
        call.enqueue(new Callback<RespuestaJugador>() {
            @Override
            public void onResponse(Call<RespuestaJugador> call, Response<RespuestaJugador> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RespuestaJugador respuesta = response.body();
                    if (!respuesta.isError() && !respuesta.getCuenta().isEmpty()) {
                        Jugador jugador = respuesta.getCuenta().get(0);
                        binding.textNombreUsuario.setVisibility(View.VISIBLE);
                        binding.textNombreUsuario.setText(jugador.getNombreDeUsuario());
                        binding.imageFotoPerfil.setVisibility(View.VISIBLE);
                    }
                } else {
                    int codigo = response.code();
                    switch (codigo) {
                        case 400:
                            Toast.makeText(BusquedaPerfil.this, getString(R.string.msj_nombre_usuario_invalido), Toast.LENGTH_LONG).show();
                            break;
                        case 404:
                            Toast.makeText(BusquedaPerfil.this, getString(R.string.msj_jugador_no_encontrado), Toast.LENGTH_SHORT).show();
                            break;
                        case 401:
                            Toast.makeText(BusquedaPerfil.this, getString(R.string.msj_volver_inicio_sesion), Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(BusquedaPerfil.this, getString(R.string.msj_error_conexion_servidor), Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RespuestaJugador> call, Throwable t) {
                Toast.makeText(BusquedaPerfil.this, getString(R.string.msj_error_conexion_servidor), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validarToken(){
        boolean validacion=true;
        if (obtenerToken() == null) {
            validacion=false;
            Toast.makeText(this, getString(R.string.msj_volver_inicio_sesion), Toast.LENGTH_SHORT).show();
        }
        return validacion;
    }

    private String obtenerToken(){
        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        return prefs.getString("token", null);
    }


}


