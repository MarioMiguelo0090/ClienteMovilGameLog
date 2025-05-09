package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.auxiliares.APICliente;
import com.example.gamelog.auxiliares.EncriptadorContrasena;
import com.example.gamelog.auxiliares.LoginRequest;
import com.example.gamelog.auxiliares.TokenRespuesta;
import com.example.gamelog.auxiliares.Validador;
import com.example.gamelog.databinding.ActivityMainBinding;
import com.example.gamelog.interfaces.ApiGameLogLogin;
import com.google.gson.Gson;

import retrofit2.Call;

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
        if(validarCredenciales()){
            String correo = binding.editUsuario.getText().toString().trim();
            String contrasenia = EncriptadorContrasena.encriptarSHA256(binding.editContrasenia.getText().toString().trim());
            hacerLogin(correo, contrasenia);
        }
    }

    private void abrirCreacionCuenta(){
        Intent intenteCreacionCuenta=new Intent(this,CreacionCuenta.class);
        startActivity(intenteCreacionCuenta);
    }

    private boolean validarCredenciales(){
        String correo=binding.editUsuario.getText().toString().trim();
        String contrasenia=binding.editContrasenia.getText().toString().trim();
        if(!Validador.esCorreoValido(correo)){
            binding.editUsuario.setError(getString(R.string.msj_correo_invalido));
        }
        if(!Validador.esContraseniaValida(contrasenia)){
            binding.editContrasenia.setError(getString(R.string.msj_contrasenia_invalida));
        }
        return Validador.esCorreoValido(correo)&&Validador.esContraseniaValida(contrasenia);
    }

    private void hacerLogin(String correo, String contrasenia) {
        String tipoDeUsuario = "Jugador";
        LoginRequest request = new LoginRequest(correo, contrasenia,tipoDeUsuario);
        ApiGameLogLogin api = APICliente.getRetrofit().create(ApiGameLogLogin.class);
        api.login(request).enqueue(new retrofit2.Callback<TokenRespuesta>() {
            @Override
            public void onResponse(Call<TokenRespuesta> call, retrofit2.Response<TokenRespuesta> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getSharedPreferences("loginPrefs", MODE_PRIVATE)
                            .edit()
                            .putBoolean("isLoggedIn", true)
                            .putString("token", response.body().getToken())
                            .apply();
                    abrirMenuAplicacion();
                } else if (response.code() == 400) {
                    binding.editContrasenia.setError("Credenciales inválidas");
                } else if (response.code() == 404) {
                    binding.editUsuario.setError("Cuenta no encontrada");
                } else {
                    mostrarToast("Error desconocido: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenRespuesta> call, Throwable t) {
                mostrarToast("Error de red: " + t.getMessage());
            }
        });
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void abrirMenuAplicacion(){
        Intent intentMenuActivity=new Intent(this, MenuActivity.class);
        startActivity(intentMenuActivity);
        finish();
    }


}