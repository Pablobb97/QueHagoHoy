package com.example.proyectoprueba1quehago.Models;

import com.example.proyectoprueba1quehago.Models.ActividadCultural;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DatosServiciosActividadesCulturales {

    //private JsonObject graph;
    @SerializedName("@id")
    private int id;
    private String c;
    private String link;
    private String title;

    private ActividadCultural actividadCultural;

    @SerializedName("@graph")
    private ArrayList<ActividadCultural> listaDatosServicios = new ArrayList<>();

    public DatosServiciosActividadesCulturales() {
        //parseJSON();
    }

    public ArrayList<ActividadCultural> getListaDatosServicios() {
        return listaDatosServicios;
    }

    public void setListaDatosServicios(ArrayList<ActividadCultural> listaDatosServicios) {
        this.listaDatosServicios = listaDatosServicios;
    }

    public String getTitle() {
        return title;
    }

    public ActividadCultural getActividadCultural() {
        return actividadCultural;
    }

    public void setActividadCultural(ActividadCultural actividadCultural) {
        this.actividadCultural = actividadCultural;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getC() {
        return c;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public static ActividadCultural parseJson(String response){
        Gson gson=new GsonBuilder().create();
        ActividadCultural datos=gson.fromJson(response,ActividadCultural.class);
        return datos;
    }
}
