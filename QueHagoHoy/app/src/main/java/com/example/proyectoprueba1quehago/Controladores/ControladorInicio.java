package com.example.proyectoprueba1quehago.Controladores;

import android.text.Editable;
import android.util.Log;

import com.example.proyectoprueba1quehago.Activities.InicioActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ControladorInicio {
    private InicioActivity inicio;
    private boolean inicioCorrecto=false;


    public ControladorInicio(InicioActivity inicio) {
        this.inicio = inicio;
    }

    public boolean comprobacionInicio(Editable nombreUsuario, Editable contrasena){

            inicioCorrecto=false;
            URL url;
            HttpURLConnection conexion = null;

            try {
                url = new URL("http://192.168.1.138/Android/AppPabloProyectoQueHago/public/login/"+nombreUsuario+"/"+contrasena);
                System.out.println("************"+url+"************");
                conexion = (HttpURLConnection) url.openConnection();
                conexion.connect();


                //BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                InputStreamReader in = new InputStreamReader(conexion.getInputStream());
                BufferedReader br = new BufferedReader(in);

                String line = br.readLine();
                Log.d("HTTP-GET", line);
                if (line.equals("11")){
                    inicioCorrecto=true;
                }else{
                    inicioCorrecto=false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conexion != null) {
                    conexion.disconnect();
                }
            }


        return inicioCorrecto;
    }
}
