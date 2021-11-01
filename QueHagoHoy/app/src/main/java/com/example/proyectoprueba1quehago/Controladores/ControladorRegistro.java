package com.example.proyectoprueba1quehago.Controladores;

import android.text.Editable;
import android.util.Log;

import com.example.proyectoprueba1quehago.Activities.RegistroActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ControladorRegistro {
    private RegistroActivity registroActivity;

    public ControladorRegistro(RegistroActivity registroActivity) {
        this.registroActivity = registroActivity;
    }


    public void enviar_BBDD(Editable nombre_y_apellidos, Editable nombre_usuario, Editable contrasena, Editable email, Editable fecha_nacimiento) {
        URL url;
        HttpURLConnection conexion = null;

        try {
            url = new URL("http://192.168.1.138/Android/AppPabloProyectoQueHago/public/registro/"+nombre_y_apellidos+"/"+
                    nombre_usuario+"/"+contrasena+"/"+email+"/"+fecha_nacimiento);
            System.out.println("************"+url+"************");
            conexion = (HttpURLConnection) url.openConnection();
            conexion.connect();


            InputStreamReader in = new InputStreamReader(conexion.getInputStream());
            BufferedReader br = new BufferedReader(in);

            String line = br.readLine();
            registroActivity.mensaje(line);
            System.out.println("------------------------------------------------"+line+"------------------------------------------------");
            Log.d("HTTP-GET", line);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                conexion.disconnect();
            }
        }
    }

}
