package com.app.tp2lab3loginfile.login;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.app.tp2lab3loginfile.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private LoginActivityViewModel mv;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuración del ViewModel y del binding
        mv = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(LoginActivityViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Acción para el botón "Ingresar"
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.verificarUsuario(binding.etUsuario.getText().toString(),
                        binding.etPass.getText().toString());
            }
        });

        // Acción para el botón "Registrar"
        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.registrarUsuario();
            }
        });
    }
}