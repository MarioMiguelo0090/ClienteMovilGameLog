package com.example.gamelog;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamelog.auxiliares.APICliente;
import com.example.gamelog.interfaces.ApiGameLogAcceso;
import com.example.gamelog.auxiliares.EncriptadorContrasena;
import com.example.gamelog.auxiliares.Usuario;
import com.example.gamelog.auxiliares.Validador;
import com.example.gamelog.databinding.ActivityCreacionCuentaBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        binding.btnRegistrar.setOnClickListener(v->{
            registrarUsuario();
        });
    }

    private void regresarInicioSesion(){
        finish();
    }

    private void registrarUsuario(){
        if(verificarCampos()){
            registrarNuevoUsuarioEnServidor();
        }
    }

    private void registrarNuevoUsuarioEnServidor(){
        Usuario usuario=new Usuario();
        usuario.setNombre(binding.editNombre.getText().toString().trim());
        usuario.setPrimerApellido(binding.editPrimerApellido.getText().toString().trim());
        usuario.setSegundoApellido(binding.editSegundoApellido.getText().toString().trim());
        usuario.setNombreDeUsuario(binding.editNombreUsuario.getText().toString().trim());
        usuario.setDescripcion(binding.editDescripcion.getText().toString().trim());
        usuario.setCorreo(binding.editCorreo.getText().toString().trim());
        usuario.setContrasenia(EncriptadorContrasena.encriptarSHA256(binding.editContrasenia.getText().toString().trim()));
        ApiGameLogAcceso api = APICliente.getRetrofit().create(ApiGameLogAcceso.class);
        Call<Object> call = api.registrarAcceso(usuario);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreacionCuenta.this, getString(R.string.msj_registro_usuario_exitoso), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    int statusCode = response.code();
                    switch (statusCode) {
                        case 400:
                            Toast.makeText(CreacionCuenta.this, getString(R.string.msj_correo_nombre_usuario_registrado_anteriormente), Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            Toast.makeText(CreacionCuenta.this, getString(R.string.msj_error_conexion_bd), Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(CreacionCuenta.this, getString(R.string.msj_error_conexion_servidor), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean verificarCampos(){
        boolean validacion=true;
        String nombre=binding.editNombre.getText().toString().trim();
        String primerApellido=binding.editPrimerApellido.getText().toString().trim();
        String segundoApellido=binding.editSegundoApellido.getText().toString().trim();
        String nombreUsuario=binding.editNombreUsuario.getText().toString().trim();
        String descripcion=binding.editDescripcion.getText().toString().trim();
        String correo=binding.editCorreo.getText().toString().trim();
        String contrasenia=binding.editContrasenia.getText().toString().trim();
        if(!Validador.esNombreValido(nombre)){
            binding.editNombre.setError(getString(R.string.msj_nombre_invalido));
            validacion=false;
        }
        if(!Validador.esPrimerApellidoValido(primerApellido)){
            binding.editPrimerApellido.setError(getString(R.string.msj_apellido_invalido));
            validacion=false;
        }
        if(!Validador.esSegundoApellidoValido(segundoApellido)){
            binding.editSegundoApellido.setError(getString(R.string.msj_apellido_invalido));
            validacion=false;
        }
        if(!Validador.esNombreUsuarioValido(nombreUsuario)){
            binding.editNombreUsuario.setError(getString(R.string.msj_nombre_usuario_invalido));
            validacion=false;
        }
        if(!Validador.esDescripcionValida(descripcion)){
            binding.editDescripcion.setError(getString(R.string.msj_descripcion_invalido));
            validacion=false;
        }
        if(!Validador.esCorreoValido(correo)){
            binding.editCorreo.setError(getString(R.string.msj_correo_invalido));
            validacion=false;
        }
        if(!Validador.esContraseniaValida(contrasenia)){
            binding.editContrasenia.setError(getString(R.string.msj_contrasenia_invalida));
            validacion=false;
        }
        return validacion;
    }
}