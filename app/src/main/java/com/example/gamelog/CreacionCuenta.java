package com.example.gamelog;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gamelog.auxiliares.Validador;
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
        binding.btnRegistrar.setOnClickListener(v->{
            registrarUsuario();
        });
    }

    private void regresarInicioSesion(){
        finish();
    }

    private void registrarUsuario(){
        if(verificarCampos()){
            Toast.makeText(this, getString(R.string.msj_registro_usuario_exitoso), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private boolean verificarCampos(){
        boolean validacion=true;
        String nombre=binding.editNombre.getText().toString().trim();
        String primerApellido=binding.editPrimerApellido.getText().toString().trim();;
        String segundoApellido=binding.editSegundoApellido.getText().toString().trim();;
        String nombreUsuario=binding.editNombreUsuario.getText().toString().trim();;
        String descripcion=binding.editDescripcion.getText().toString().trim();;
        String correo=binding.editCorreo.getText().toString().trim();;
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