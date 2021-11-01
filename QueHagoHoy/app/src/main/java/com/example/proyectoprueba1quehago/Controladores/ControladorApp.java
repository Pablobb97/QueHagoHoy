package com.example.proyectoprueba1quehago.Controladores;

import android.widget.Toast;

import com.example.proyectoprueba1quehago.API.API;
import com.example.proyectoprueba1quehago.API.APIServices.ActividadCultutalServicio;
import com.example.proyectoprueba1quehago.API.APIServices.Servicios;
import com.example.proyectoprueba1quehago.Activities.AppActivity;
import com.example.proyectoprueba1quehago.Models.ActividadCulturalExtensa;
import com.example.proyectoprueba1quehago.Models.BBDDRestaurantes;
import com.example.proyectoprueba1quehago.Models.DatosServiciosActividadesCulturales;
import com.example.proyectoprueba1quehago.Models.DatosServiciosActividadesCulturalesExtenso;
import com.example.proyectoprueba1quehago.Models.Restaurante;

import java.text.Normalizer;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControladorApp {
    private AppActivity appActivity;
    private DatosServiciosActividadesCulturalesExtenso datosServiciosActividadesCulturalesExtenso;
    private DatosServiciosActividadesCulturales datosServiciosActividadesCulturales;
    private boolean servicioCulturalSeleccionado=false;
    private boolean popMenuPulsado=false;
    private String url="";
    private BBDDRestaurantes bbddRestaurantes;

    private ArrayList<ActividadCulturalExtensa> lista_GridView=new ArrayList<ActividadCulturalExtensa>();
    ArrayList<ActividadCulturalExtensa> lista_GridView_Manana=new ArrayList<>();
    ArrayList<ActividadCulturalExtensa> lista_GridView_Tarde=new ArrayList<>();

    private ArrayList<ActividadCulturalExtensa> lista_AC_Extensa=new ArrayList<ActividadCulturalExtensa>();
    private ArrayList<Restaurante> listaRestaurantes=new ArrayList<>();



    //Mañana
    private String tituloManana="";
    private String descriptionManana="";
    private String fechaManana="";
    private String localizacionManana="";
    private String precioManana="";
    //comida
    private String nombreComida="";
    private String localizacionComida="";
    private String precioComida="";
    private String distritoComida="";
    private String productosComida="";
    private String puntuacionComida="";
    //tarde
    private String tituloTarde="";
    private String descriptionTarde="";
    private String fechaTarde="";
    private String localizacionTarde="";
    private String precioTarde="";
    //cena
    private String nombreCena="";
    private String localizacionCena="";
    private String precioCena="";
    private String distritoCena="";
    private String productosCena="";
    private String puntuacionCena="";

    public ControladorApp(AppActivity appActivity) {
        this.appActivity = appActivity;
        bbddRestaurantes=new BBDDRestaurantes();
        recogerRestaurantes();
    }

    //Se encarga de recoger los datos ofrecidos por la API, la cual nos muestra únicamente el nombre(titulo) de la actividad
    //el id(link) que nos permite abrir un nuevo archivo desde el que podemos acceder al resto de información de la actividad
    //por lo que en este apartado recogemos todos los servicios con la información básico, creamos nuestro modelo datosServiciosActividadesCulturales
    // que se encargara de recoger cada uno de los servicios y almacenarlos en un ArrayList y llamamos a recogerTodosLosDatosAC().
    public void recogerTodosLosServicios(){
        servicioCulturalSeleccionado=true;
        Servicios service= API.getApi().create(Servicios.class);
        Call<DatosServiciosActividadesCulturales> distritoCall=service.getServicios("");
        distritoCall.enqueue(new Callback<DatosServiciosActividadesCulturales>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturales> call, Response<DatosServiciosActividadesCulturales> response) {
                datosServiciosActividadesCulturales = response.body();
                recogerTodosLosDatosAC();
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturales> call, Throwable t) {
                appActivity.mensaje("Error");
            }
        });
    }

    //Desde este método recorremos el contenido del JSON obtenido guardado en un
    // ArrayList y llamaremos a la función llamaActividadCultural().
    private void recogerTodosLosDatosAC(){
        for (int i=0;i<datosServiciosActividadesCulturales.getListaDatosServicios().size();i++){
            popMenuPulsado=false;
            url=datosServiciosActividadesCulturales.getListaDatosServicios().get(i).getId();
            llamaActividadCultural();
        }
    }

    //Esta función se encarga de recoger los datos extensos que podemos encontrar abriendo el id(link),
    //y así poder recoger el total de la información de cada actividad y almacenarla en un ArrayList de
    // un nuevo modelo que consistirá en la Actividad Cultural Extensa.
    private void llamaActividadCultural() {
        ActividadCultutalServicio service=API.getApi().create(ActividadCultutalServicio.class);
        Call<DatosServiciosActividadesCulturalesExtenso> actividadCulturalExtensaCallCall=service.getActividadCulturalExtensa(url+"");
        actividadCulturalExtensaCallCall.enqueue(new Callback<DatosServiciosActividadesCulturalesExtenso>() {
            @Override
            public void onResponse(Call<DatosServiciosActividadesCulturalesExtenso> call, Response<DatosServiciosActividadesCulturalesExtenso> response) {
                datosServiciosActividadesCulturalesExtenso = response.body();
                lista_AC_Extensa.add(datosServiciosActividadesCulturalesExtenso.getListaDatosServicios().get(0));
            }

            @Override
            public void onFailure(Call<DatosServiciosActividadesCulturalesExtenso> call, Throwable t) {
                appActivity.mensaje("Error");
            }
        });
    }

    //Tanto este método como el de separarDia sirven para separar los valores de Dia y Hora.
    public String separarHora(String fecha) {
        String fechaDia="";
        String fechaHora="";
        String[] listaFecha = fecha.split(" ");
        fechaDia = listaFecha[0];
        fechaHora = listaFecha[1];

        String fechaHoraLimpia="";

        String[] listaFecha2 = fechaHora.split(":");
        fechaHoraLimpia = listaFecha2[0];
        return fechaHoraLimpia;
    }

    public String separarDia(String fecha) {
        String fechaDia="";
        String fechaHora="";
        String[] listaFecha = fecha.split(" ");
        fechaDia = listaFecha[0];
        fechaHora = listaFecha[1];

        String fechaDiaLimpia="";

        String[] listaFecha2 = fechaDia.split("-");
        fechaDiaLimpia = listaFecha2[2];
        return fechaDiaLimpia;
    }

    //Este metodo recoge los datos de los restaurantes que estan creados de manera manual en la clase BBDDRestaurantes
    private void recogerRestaurantes(){
        listaRestaurantes=bbddRestaurantes.getListaRestaurantes();
    }

    public void mostrarRestaurantes(){
        appActivity.mostrarGridViewComidas(listaRestaurantes);
    }

    public ArrayList<ActividadCulturalExtensa> getLista_AC_Extensa() {
        return lista_AC_Extensa;
    }

    public void setPopMenuPulsado(boolean popMenuPulsado) {
        this.popMenuPulsado = popMenuPulsado;
    }

    public boolean isServicioCulturalSeleccionado() {
        return servicioCulturalSeleccionado;
    }

    public void setServicioCulturalSeleccionado(boolean servicioCulturalSeleccionado) {
        this.servicioCulturalSeleccionado = servicioCulturalSeleccionado;
    }

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    public ArrayList<ActividadCulturalExtensa> getLista_GridView() {
        return lista_GridView;
    }

    public void setLista_GridView(ArrayList<ActividadCulturalExtensa> lista_GridView) {
        this.lista_GridView = lista_GridView;
    }

    public ArrayList<ActividadCulturalExtensa> getLista_GridView_Manana() {
        return lista_GridView_Manana;
    }

    public void setLista_GridView_Manana(ArrayList<ActividadCulturalExtensa> lista_GridView_Manana) {
        this.lista_GridView_Manana = lista_GridView_Manana;
    }

    public ArrayList<ActividadCulturalExtensa> getLista_GridView_Tarde() {
        return lista_GridView_Tarde;
    }

    public void setLista_GridView_Tarde(ArrayList<ActividadCulturalExtensa> lista_GridView_Tarde) {
        this.lista_GridView_Tarde = lista_GridView_Tarde;
    }

    //Recoger Valores Guardar sirve para una vez tengamos los servicios solicitados este elimine los
    // caracteres inválidos (',(),/, ...) que no se pueden añadir en la barra de búsqueda a la hora de guardar
    // todos los servicios seleccionados en la base de datos.
    public void recogerValoresGuardar() {
        if (appActivity.getNombreServicioMananaSolicitado()!=null){
            for (int i=0;i<lista_GridView.size();i++){
                if (lista_GridView.get(i).getTitulo().equals(appActivity.getNombreServicioMananaSolicitado())){

                    String resultado=lista_GridView.get(i).getTitulo();
                    if (!resultado.equals("") && resultado!=null){
                        tituloManana=resultado.replaceAll("\\s+","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("/","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("'","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("\\(","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("\\)","_");
                        resultado=tituloManana;
                        tituloManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(tituloManana, Normalizer.Form.NFD);
                        tituloManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        tituloManana="nada";
                    }

                    resultado=lista_GridView.get(i).getDescription();
                    if (!resultado.equals("") && resultado!=null){
                        descriptionManana=resultado.replaceAll("\\s+","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("/","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("'","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("\\(","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("\\)","_");
                        resultado=descriptionManana;
                        descriptionManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(descriptionManana, Normalizer.Form.NFD);
                        descriptionManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        descriptionManana="nada";
                    }

                    resultado=lista_GridView.get(i).getFecha();
                    if (!resultado.equals("") && resultado!=null){
                        fechaManana=resultado.replaceAll("\\s+","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("/","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("'","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("\\(","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("\\)","_");
                        resultado=fechaManana;
                        fechaManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(fechaManana, Normalizer.Form.NFD);
                        fechaManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        fechaManana="nada";
                    }

                    String resultado2=lista_GridView.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionManana=resultado.replaceAll("\\s+","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("/","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("'","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("\\(","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("\\)","_");
                        resultado=localizacionManana;
                        localizacionManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionManana, Normalizer.Form.NFD);
                        localizacionManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionManana="nada";
                    }
                    if (localizacionManana.equals("")){
                        localizacionManana="nada";
                    }

                    resultado=lista_GridView.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioManana=resultado.replaceAll("\\s+","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("/","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("'","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("\\(","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("\\)","_");
                        resultado=precioManana;
                        precioManana=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioManana, Normalizer.Form.NFD);
                        precioManana = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioManana="nada";
                    }
                }
            }
        }
        if (appActivity.getNombreRestauranteComidaSolicitado()!=null){
            for (int i=0;i<listaRestaurantes.size();i++){
                if (listaRestaurantes.get(i).getNombre().equals(appActivity.getNombreRestauranteComidaSolicitado())){

                    String resultado=listaRestaurantes.get(i).getNombre();
                    if (!resultado.equals("") && resultado!=null){
                        nombreComida=resultado.replaceAll("\\s+","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("/","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("'","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("\\(","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("\\)","_");
                        resultado=nombreComida;
                        nombreComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(nombreComida, Normalizer.Form.NFD);
                        nombreComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        nombreComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionComida=resultado.replaceAll("\\s+","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("/","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("'","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("\\(","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("\\)","_");
                        resultado=localizacionComida;
                        localizacionComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionComida, Normalizer.Form.NFD);
                        localizacionComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioComida=resultado.replaceAll("\\s+","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("/","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("'","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("\\(","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("\\)","_");
                        resultado=precioComida;
                        precioComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioComida, Normalizer.Form.NFD);
                        precioComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getDistrito();
                    if (!resultado.equals("") && resultado!=null){
                        distritoComida=resultado.replaceAll("\\s+","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("/","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("'","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("\\(","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("\\)","_");
                        resultado=distritoComida;
                        distritoComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(distritoComida, Normalizer.Form.NFD);
                        distritoComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        distritoComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        productosComida=resultado.replaceAll("\\s+","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("/","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("'","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("\\(","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("\\)","_");
                        resultado=productosComida;
                        productosComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(productosComida, Normalizer.Form.NFD);
                        productosComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        productosComida="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        puntuacionComida=resultado.replaceAll("\\s+","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("/","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("'","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("\\(","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("\\)","_");
                        resultado=puntuacionComida;
                        puntuacionComida=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(puntuacionComida, Normalizer.Form.NFD);
                        puntuacionComida = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        puntuacionComida="nada";
                    }

                }
            }
        }
        if (appActivity.getNombreServicioTardeSolicitado()!=null){
            for (int i=0;i<lista_GridView.size();i++){
                if (lista_GridView.get(i).getTitulo().equals(appActivity.getNombreServicioTardeSolicitado())){

                    String resultado=lista_GridView.get(i).getTitulo();
                    if (!resultado.equals("") && resultado!=null){
                        tituloTarde=resultado.replaceAll("\\s+","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("/","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("'","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("\\(","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("\\)","_");
                        resultado=tituloTarde;
                        tituloTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(tituloTarde, Normalizer.Form.NFD);
                        tituloTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        tituloTarde="nada";
                    }

                    resultado=lista_GridView.get(i).getDescription();
                    if (!resultado.equals("") && resultado!=null){
                        descriptionTarde=resultado.replaceAll("\\s+","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("/","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("'","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("\\(","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("\\)","_");
                        resultado=descriptionTarde;
                        descriptionTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(descriptionTarde, Normalizer.Form.NFD);
                        descriptionTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        descriptionTarde="nada";
                    }

                    resultado=lista_GridView.get(i).getFecha();
                    if (!resultado.equals("") && resultado!=null){
                        fechaTarde=resultado.replaceAll("\\s+","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("/","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("'","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("\\(","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("\\)","_");
                        resultado=fechaTarde;
                        fechaTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(fechaTarde, Normalizer.Form.NFD);
                        fechaTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        fechaTarde="nada";
                    }

                    String resultado2=lista_GridView.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionTarde=resultado.replaceAll("\\s+","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("/","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("'","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("\\(","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("\\)","_");
                        resultado=localizacionTarde;
                        localizacionTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionTarde, Normalizer.Form.NFD);
                        localizacionTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionTarde="nada";
                    }
                    if (localizacionTarde.equals("")){
                        localizacionTarde="nada";
                    }

                    resultado=lista_GridView.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioTarde=resultado.replaceAll("\\s+","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("/","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("'","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("\\(","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("\\)","_");
                        resultado=precioTarde;
                        precioTarde=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioTarde, Normalizer.Form.NFD);
                        precioTarde = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioTarde="nada";
                    }
                }
            }
        }
        if (appActivity.getNombreRestauranteCenaSolicitado()!=null){
            for (int i=0;i<listaRestaurantes.size();i++){
                if (listaRestaurantes.get(i).getNombre().equals(appActivity.getNombreRestauranteCenaSolicitado())){

                    String resultado=listaRestaurantes.get(i).getNombre();
                    if (!resultado.equals("") && resultado!=null){
                        nombreCena=resultado.replaceAll("\\s+","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("/","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("'","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("\\(","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("\\)","_");
                        resultado=nombreCena;
                        nombreCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(nombreCena, Normalizer.Form.NFD);
                        nombreCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        nombreCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getLocalizacion();
                    if (!resultado.equals("") && resultado!=null){
                        localizacionCena=resultado.replaceAll("\\s+","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("/","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("'","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("\\(","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("\\)","_");
                        resultado=localizacionCena;
                        localizacionCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(localizacionCena, Normalizer.Form.NFD);
                        localizacionCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        localizacionCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getPrecio();
                    if (!resultado.equals("") && resultado!=null){
                        precioCena=resultado.replaceAll("\\s+","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("/","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("'","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("\\(","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("\\)","_");
                        resultado=precioCena;
                        precioCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(precioCena, Normalizer.Form.NFD);
                        precioCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        precioCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getDistrito();
                    if (!resultado.equals("") && resultado!=null){
                        distritoCena=resultado.replaceAll("\\s+","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("/","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("'","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("\\(","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("\\)","_");
                        resultado=distritoCena;
                        distritoCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(distritoCena, Normalizer.Form.NFD);
                        distritoCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        distritoCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        productosCena=resultado.replaceAll("\\s+","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("/","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("'","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("\\(","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("\\)","_");
                        resultado=productosCena;
                        productosCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(productosCena, Normalizer.Form.NFD);
                        productosCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        productosCena="nada";
                    }

                    resultado=listaRestaurantes.get(i).getProductos();
                    if (!resultado.equals("") && resultado!=null){
                        puntuacionCena=resultado.replaceAll("\\s+","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("/","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("'","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("\\(","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("\\)","_");
                        resultado=puntuacionCena;
                        puntuacionCena=resultado.replaceAll("\\?","_");
                        resultado = Normalizer.normalize(puntuacionCena, Normalizer.Form.NFD);
                        puntuacionCena = resultado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    }else{
                        puntuacionCena="nada";
                    }
                }
            }
        }
    }

    public void vaciarListaGridView() {
        lista_GridView.clear();
    }

    public void anadirListaGridView(ActividadCulturalExtensa actividadCulturalExtensa, int i) {
        lista_GridView.add(lista_AC_Extensa.get(i));
    }

    public void vaciarListaGridView_Manana() {
        lista_GridView_Manana.clear();
    }

    public void vaciarListaGridView_Tarde() {
        lista_GridView_Tarde.clear();
    }

    public void anadirListaGridView_Manana(ActividadCulturalExtensa actividadCulturalExtensa, int i) {
        lista_GridView_Manana.add(lista_GridView.get(i));
    }

    public void anadirListaGridView_Tarde(ActividadCulturalExtensa actividadCulturalExtensa, int i) {
        lista_GridView_Tarde.add(lista_GridView.get(i));
    }

    public String getTituloManana() {
        return tituloManana;
    }

    public String getDescriptionManana() {
        return descriptionManana;
    }

    public String getFechaManana() {
        return fechaManana;
    }

    public String getLocalizacionManana() {
        return localizacionManana;
    }

    public String getPrecioManana() {
        return precioManana;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public String getLocalizacionComida() {
        return localizacionComida;
    }

    public String getPrecioComida() {
        return precioComida;
    }

    public String getDistritoComida() {
        return distritoComida;
    }

    public String getProductosComida() {
        return productosComida;
    }

    public String getPuntuacionComida() {
        return puntuacionComida;
    }

    public String getTituloTarde() {
        return tituloTarde;
    }

    public String getDescriptionTarde() {
        return descriptionTarde;
    }

    public String getFechaTarde() {
        return fechaTarde;
    }

    public String getLocalizacionTarde() {
        return localizacionTarde;
    }

    public String getPrecioTarde() {
        return precioTarde;
    }

    public String getNombreCena() {
        return nombreCena;
    }

    public String getLocalizacionCena() {
        return localizacionCena;
    }

    public String getPrecioCena() {
        return precioCena;
    }

    public String getDistritoCena() {
        return distritoCena;
    }

    public String getProductosCena() {
        return productosCena;
    }

    public String getPuntuacionCena() {
        return puntuacionCena;
    }
}
