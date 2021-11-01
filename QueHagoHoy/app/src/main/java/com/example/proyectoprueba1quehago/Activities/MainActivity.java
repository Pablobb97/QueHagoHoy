package com.example.proyectoprueba1quehago.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicio();
    }

    private void inicio() {
        Intent intente= new Intent(MainActivity.this, InicioActivity.class);
        startActivity(intente);

    }
}