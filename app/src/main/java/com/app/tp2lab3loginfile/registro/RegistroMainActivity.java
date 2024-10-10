package com.app.tp2lab3loginfile.registro;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.app.tp2lab3loginfile.databinding.ActivityRegistroMainBinding;


public class RegistroMainActivity extends AppCompatActivity {
    private RegistroViewModel mv;
    private ActivityRegistroMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroMainBinding.inflate(getLayoutInflater());
        mv = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(RegistroViewModel.class);
        setContentView(binding.getRoot());

        mv.getUsuarioM().observe(this, usuario -> {
            if (usuario != null) {
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etEmail.setText(usuario.getEmail());
                binding.etPass.setText(usuario.getPassword());
                binding.etDni.setText(String.valueOf(usuario.getDni()));
            }
        });

        mv.getDatos(getIntent());

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String dni = binding.etDni.getText().toString();
                String email = binding.etEmail.getText().toString();
                String password = binding.etPass.getText().toString();
                mv.registrar(nombre, apellido, dni, email, password);
            }
        });
    }
}

