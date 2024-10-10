package com.app.tp2lab3loginfile.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.tp2lab3loginfile.login.MainActivity;
import com.app.tp2lab3loginfile.modelo.Usuario;
import com.app.tp2lab3loginfile.request.ApiClient;


public class RegistroViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> Musuario;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioM() {
        if (Musuario == null) {
            Musuario = new MutableLiveData<>();
        }
        return Musuario;
    }

    public void registrar(String nombre, String apellido, String dni, String email, String password) {
        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario(nombre, apellido, email, password, Long.parseLong(dni));
            Toast.makeText(context, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
            ApiClient.guardar(context, usuario);

            // Redirigir a la vista de login después de un registro exitoso
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void getDatos(Intent intent) {
        if (intent.getBooleanExtra("existe", false)) {
            Usuario usuario = ApiClient.leer(context);
            if (usuario != null) {
                Musuario.setValue(usuario);
            } else {
                Toast.makeText(context, "Oops: ¡Ocurrio un error inesperado al recuperar su usuario!", Toast.LENGTH_LONG).show();
            }
        }
    }
}


