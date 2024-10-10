package com.app.tp2lab3loginfile.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.app.tp2lab3loginfile.modelo.Usuario;
import com.app.tp2lab3loginfile.registro.RegistroMainActivity;
import com.app.tp2lab3loginfile.request.ApiClient;


public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void verificarUsuario(String email, String password) {
        Usuario usuario = ApiClient.login(context, email, password);

        if (usuario != null) {
            Intent intent = new Intent(context, RegistroMainActivity.class);
            intent.putExtra("existe", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrarUsuario() {
        Intent intent = new Intent(context, RegistroMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
