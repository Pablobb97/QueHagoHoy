package com.example.proyectoprueba1quehago.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.Controladores.ControladorInicio;
import com.example.proyectoprueba1quehago.R;

public class InicioActivity extends AppCompatActivity {
    private Button botonInicio;
    private Button botonRegistro;
    private Button botonInicioSinConexion;
    private EditText nombre_usuario;
    private EditText contrasena;

    private ControladorInicio controladorInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_registro);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        controladorInicio=new ControladorInicio(this);
        recogerValores();
        comporbarBotones();
    }

    private void recogerValores() {
        nombre_usuario=findViewById(R.id.editTextLogin);
        contrasena=findViewById(R.id.editTextContrasena);
    }

    private void comporbarBotones() {
        botonInicio=findViewById(R.id.idButtonInicioSesion);
        botonRegistro=findViewById(R.id.idButtonRegistro);
        botonInicioSinConexion=findViewById(R.id.idButtonInicioSesionSinConexion);

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarApp();
            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InicioActivity.this, "Has Pulsado Registro", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
        botonInicioSinConexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarAppSinConexion();
            }
        });

    }


    private void iniciarAppSinConexion() {
        Intent intent= new Intent(InicioActivity.this, AppActivity.class);
        startActivity(intent);
    }


    private void iniciarApp() {
        if (controladorInicio.comprobacionInicio(nombre_usuario.getText(),contrasena.getText())){
            Intent intent= new Intent(InicioActivity.this, AppActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(InicioActivity.this, "Acceso Incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

}
