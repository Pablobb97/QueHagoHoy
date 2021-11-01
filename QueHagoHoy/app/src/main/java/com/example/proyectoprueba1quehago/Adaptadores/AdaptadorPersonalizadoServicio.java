package com.example.proyectoprueba1quehago.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoprueba1quehago.Models.ActividadCulturalExtensa;
import com.example.proyectoprueba1quehago.Models.Restaurante;
import com.example.proyectoprueba1quehago.R;

import java.util.ArrayList;

public class AdaptadorPersonalizadoServicio extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> listaNombres;
    private ArrayList<String> listaFechas;
    private ArrayList<ActividadCulturalExtensa> listaDatosServiciosActividadesCulturales;
    private ArrayList<Restaurante> listaRestaurantes;

    public AdaptadorPersonalizadoServicio(Context context, int layout, ArrayList<ActividadCulturalExtensa> listaDatosServiciosActividadesCulturales) {
        this.context = context;
        this.layout = layout;
        this.listaDatosServiciosActividadesCulturales = listaDatosServiciosActividadesCulturales;
    }

    public AdaptadorPersonalizadoServicio(Context context, int layout, ArrayList<String> listaNombres, ArrayList<String> listaFechas) {
        this.context = context;
        this.layout = layout;
        this.listaNombres = listaNombres;
        this.listaFechas = listaFechas;
    }

    @Override
    public int getCount() {
        return listaDatosServiciosActividadesCulturales.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDatosServiciosActividadesCulturales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdaptadorPersonalizadoHolderServicio holder;
        if (convertView == null){
            LayoutInflater layoutInflater=LayoutInflater.from(this.context);
            convertView=layoutInflater.inflate(this.layout,null);
            holder=new AdaptadorPersonalizadoHolderServicio();
            holder.nombreServicio=(TextView) convertView.findViewById(R.id.idTextViewServicioNombreDistrito2);
            //holder.nombreFecha=(TextView) convertView.findViewById(R.id.idTextViewServicioFecha);
            convertView.setTag(holder);
        }else{
            holder=(AdaptadorPersonalizadoHolderServicio) convertView.getTag();
        }

        String nNombre= listaDatosServiciosActividadesCulturales.get(position).getTitulo();
        holder.nombreServicio.setText(nNombre);

        /*String nFecha= listaDatosServiciosActividadesCulturales.get(position).getFecha();
        holder.nombreFecha.setText(nFecha);*/

        return convertView;
    }
}

