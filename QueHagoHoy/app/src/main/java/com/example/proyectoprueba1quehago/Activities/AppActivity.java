package com.example.proyectoprueba1quehago.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba1quehago.API.API;
import com.example.proyectoprueba1quehago.API.APIServices.ActividadCultutalServicio;
import com.example.proyectoprueba1quehago.API.APIServices.Servicios;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoRestaurante;
import com.example.proyectoprueba1quehago.Adaptadores.AdaptadorPersonalizadoServicio;
import com.example.proyectoprueba1quehago.Controladores.ControladorApp;
import com.example.proyectoprueba1quehago.Controladores.PaginaGuardar;
import com.example.proyectoprueba1quehago.Models.ActividadCulturalExtensa;
import com.example.proyectoprueba1quehago.Models.BBDDRestaurantes;
import com.example.proyectoprueba1quehago.Models.DatosServiciosActividadesCulturales;
import com.example.proyectoprueba1quehago.Models.DatosServiciosActividadesCulturalesExtenso;
import com.example.proyectoprueba1quehago.Models.Restaurante;
import com.example.proyectoprueba1quehago.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppActivity extends AppCompatActivity {
    private ControladorApp controladorApp;
    private AdaptadorPersonalizadoServicio adaptadorPersonalizadoServicio;
    private AdaptadorPersonalizadoRestaurante adaptadorPersonalizadoRestaurante;
    private GridView gridView;
    private ArrayList<String> listaJsonServicioAC = new ArrayList<>();
    private String distritoSeleccionado ="ARGANZUELA";
    private boolean gridviewVisible=false;
    private LinearLayout layoutServicio;
    private String linkAC="";
    private String fechaEscogida="";
    private String fechaEscogidaDia="";
    private String fechaEscogidaMes="";
    private String fechaEscogidaAnio="";
    private ActividadCulturalExtensa actividadCulturalExtensa;
    private ImageButton botonManana;
    private ImageButton botonComida;
    private ImageButton botonTarde;
    private ImageButton botonCena;
    private Boolean primerInicio=true;
    private Boolean pantallaManana=true;
    private Boolean pantallaComida=false;
    private Boolean pantallaTarde=false;
    private Boolean pantallaCena=false;
    private String nombreRestauranteComidaSolicitado="";
    private String nombreRestauranteCenaSolicitado="";
    private String nombreServicioMananaSolicitado="";
    private String nombreServicioTardeSolicitado="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_app);
        gridView=findViewById(R.id.idGridView);
        controladorApp=new ControladorApp(this);
        recogerBotones();
        Toast.makeText(AppActivity.this, "POR FAVOR ESPERE UNOS SEGUNDOS HASTA QUE SE RECOJAN TODOS LOS SERVICIOS", Toast.LENGTH_LONG).show();
        Toast.makeText(AppActivity.this, "POR FAVOR SELECCIONE UNA FECHA.", Toast.LENGTH_SHORT).show();

        controladorApp.recogerTodosLosServicios();

        registerForContextMenu(gridView);
        comprobarBotones();
        if (primerInicio){
            botonManana.setImageResource(R.mipmap.ic_logo_manana_pulsado_foreground);
        }


        seleccionaServicio();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //vista
    private void recogerBotones() {
        botonManana=(ImageButton) findViewById(R.id.idButtonManana);
        botonComida=(ImageButton) findViewById(R.id.idButtonComida);
        botonTarde=(ImageButton) findViewById(R.id.idButtonTarde);
        botonCena=(ImageButton) findViewById(R.id.idButtonCena);
    }

    //SeleccionaDia recoge el valor obtenido abriendo el calendario que nos permite escoger la fecha,
    // también se encarga de generar el propio calendario, este método se ejecutara pulsando
    // el botón que aparece en el menú con forma de calendario.
    public void seleccionaDia(MenuItem item) {
        primerInicio=false;
        gridviewVisible=false;
        Calendar calendario= Calendar.getInstance();
        int fecha_anio=calendario.get(calendario.YEAR);
        int fecha_mes=calendario.get(calendario.MONTH);
        int fecha_dia=calendario.get(calendario.DAY_OF_MONTH);

        DatePickerDialog dpd= new DatePickerDialog(AppActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fechaEscogida = dayOfMonth + "-" + month + "-" + year;
                fechaEscogidaDia=dayOfMonth+"";
                fechaEscogidaMes=month+"";
                fechaEscogidaAnio=year+"";
                Toast.makeText(AppActivity.this, "Esta es La fecha: "+fechaEscogida, Toast.LENGTH_SHORT).show();
            }
        },fecha_anio,fecha_mes,fecha_dia);
        dpd.show();
    }

    //Esta función se encarga de agregar al ArrayList de servicios que se mostraran en el GridView, aquellos
    //que compartan la fecha que ha escogido el usuario.
    private void crearGridViewAdaptado() {
        controladorApp.vaciarListaGridView();
        for (int i=0;i<controladorApp.getLista_AC_Extensa().size();i++){
            String dia=controladorApp.separarDia(controladorApp.getLista_AC_Extensa().get(i).getFecha());
            if (dia.equals(fechaEscogidaDia)){
                controladorApp.anadirListaGridView(controladorApp.getLista_AC_Extensa().get(i),i);
            }
        }
        mostrarGridView();
        adaptadorPersonalizadoServicio.notifyDataSetChanged();
        controladorApp.setPopMenuPulsado(true);
    }

    //Se encarga de mostrar el GridView con los datos que queremos, como los que se realizan por la mañana
    // y por la tarde y así poder distinguirlos.
    private void mostrarGridView(){
        if(pantallaManana){
            controladorApp.vaciarListaGridView_Manana();
            for (int i=0;i<controladorApp.getLista_GridView().size();i++){
                if (Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(i).getFecha()))<14 && Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(i).getFecha()))!=0 ){
                    controladorApp.anadirListaGridView_Manana(controladorApp.getLista_GridView().get(i),i);
                }
            }
            adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,controladorApp.getLista_GridView_Manana());
            gridView.setAdapter(adaptadorPersonalizadoServicio);
            gridviewVisible=true;
        }
        if(pantallaTarde){
            controladorApp.vaciarListaGridView_Tarde();
            for (int i=0;i<controladorApp.getLista_GridView().size();i++){
                if (Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(i).getFecha()))>=14 || Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(i).getFecha()))==0 ){
                    controladorApp.anadirListaGridView_Tarde(controladorApp.getLista_GridView().get(i),i);
                }
            }
            adaptadorPersonalizadoServicio=new AdaptadorPersonalizadoServicio(this,R.layout.list_item_layout_servicio2,controladorApp.getLista_GridView_Tarde());
            gridView.setAdapter(adaptadorPersonalizadoServicio);
            gridviewVisible=true;
        }
    }

    //Este método tiene como objetivo que al pulsar sobre cualquier servicio mostrado en el GridView,
    //y al pulsar sobre él nos abra un popMenu que nos muestre más información sobre dicho servicio.
    private void seleccionaServicio() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // TODO Auto-generated method stub
            if (controladorApp.isServicioCulturalSeleccionado()){
                abrirPopMenu(position);
            }else{
                abrirPopMenuRestaurante(position);
            }
        });
        gridView.setOnItemLongClickListener((parent, view, position, id) -> {
            // TODO Auto-generated method stub
            if (controladorApp.isServicioCulturalSeleccionado()){
                //abrirPopMenu(position);
            }else{
                //abrirPopMenuRestaurante(position);
            }
            return false;
        });
    }

    //Se encarga de abrir el popMenu, creando el modelo y enviándole los datos necesarios.
    private void abrirPopMenu(int position) {
        Intent intent=new Intent(AppActivity.this,PopUpActividadCultural.class);
        if(pantallaManana){
            intent.putExtra("nombre", controladorApp.getLista_GridView_Manana().get(position).getTitulo());
            intent.putExtra("descripcion", controladorApp.getLista_GridView_Manana().get(position).getDescription());
            intent.putExtra("localizacion", controladorApp.getLista_GridView_Manana().get(position).getLocalizacion());
            intent.putExtra("precio", controladorApp.getLista_GridView_Manana().get(position).getPrecio());
            intent.putExtra("fecha", controladorApp.getLista_GridView_Manana().get(position).getFecha());
        }
        if (pantallaTarde){
            intent.putExtra("nombre", controladorApp.getLista_GridView_Tarde().get(position).getTitulo());
            intent.putExtra("descripcion", controladorApp.getLista_GridView_Tarde().get(position).getDescription());
            intent.putExtra("localizacion", controladorApp.getLista_GridView_Tarde().get(position).getLocalizacion());
            intent.putExtra("precio", controladorApp.getLista_GridView_Tarde().get(position).getPrecio());
            intent.putExtra("fecha", controladorApp.getLista_GridView_Tarde().get(position).getFecha());
        }
        startActivity(intent);
    }

    //Restaurantes


    //Al igual que el anterior se encarga de mostrar el gridView, pero este unicamente para los Restarantes.
    public void mostrarGridViewComidas(ArrayList<Restaurante> listaRestaurantes) {
        adaptadorPersonalizadoRestaurante=new AdaptadorPersonalizadoRestaurante(this,R.layout.list_item_layout_servicio2,listaRestaurantes);
        gridView.setAdapter(adaptadorPersonalizadoRestaurante);
    }


    //Igual que el anterior pero para los restaurantes.
    private void abrirPopMenuRestaurante(int position) {
        Intent intent=new Intent(AppActivity.this,PopUpRestaurante.class);

        intent.putExtra("nombre", controladorApp.getListaRestaurantes().get(position).getNombre());
        intent.putExtra("localizacion", controladorApp.getListaRestaurantes().get(position).getLocalizacion());
        intent.putExtra("precio", controladorApp.getListaRestaurantes().get(position).getPrecio());
        intent.putExtra("distrito", controladorApp.getListaRestaurantes().get(position).getDistrito());
        intent.putExtra("productos", controladorApp.getListaRestaurantes().get(position).getProductos());
        intent.putExtra("puntuacion", controladorApp.getListaRestaurantes().get(position).getPuntuacion());
        startActivity(intent);
    }

    //Se encarga constante de saber en qué pantalla queremos situarnos, si en la Mañana, comida ...
    //Cuando abres una se encarga de mostrar una imagen diferenciada y devolver a los botones su
    // imagen original para distinguir óptimamente en que pantalla nos encontramos.
    private void comprobarBotones() {
        botonManana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pantallaManana=true;
                pantallaComida=false;
                pantallaTarde=false;
                pantallaCena=false;
                botonManana.setImageResource(R.mipmap.ic_logo_manana_pulsado_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde1_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_foreground);
                controladorApp.setServicioCulturalSeleccionado(true);
                mostrarGridView();
                //crearListViewAdaptado();
            }
        });

        botonComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerInicio=false;
                pantallaManana=false;
                pantallaComida=true;
                pantallaTarde=false;
                pantallaCena=false;
                botonManana.setImageResource(R.mipmap.ic_logo_manana1_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_pulsado_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde1_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_foreground);
                controladorApp.setServicioCulturalSeleccionado(false);
                controladorApp.mostrarRestaurantes();
            }
        });

        botonTarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerInicio=false;
                pantallaManana=false;
                pantallaComida=false;
                pantallaTarde=true;
                pantallaCena=false;
                botonManana.setImageResource(R.mipmap.ic_logo_manana1_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde_pulsado_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_foreground);
                controladorApp.setServicioCulturalSeleccionado(true);
                mostrarGridView();
                //crearListViewAdaptado();
            }
        });

        botonCena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerInicio=false;
                pantallaManana=false;
                pantallaComida=false;
                pantallaTarde=false;
                pantallaCena=true;
                botonManana.setImageResource(R.mipmap.ic_logo_manana1_foreground);
                botonComida.setImageResource(R.mipmap.ic_logo_comida_foreground);
                botonTarde.setImageResource(R.mipmap.ic_logo_tarde1_foreground);
                botonCena.setImageResource(R.mipmap.ic_logo_cena_pulsado_foreground);
                controladorApp.setServicioCulturalSeleccionado(false);
                controladorApp.mostrarRestaurantes();
            }
        });
    }

    //Esta es una de las funciones mas importantes ya que tiene como objetivo escoger de manera aleatoria
    // los distintos servicios y resturantes
    private void escogerServiciosRestaurantesAutomaticos() {
        if (fechaEscogida.equals("")){
            Toast.makeText(AppActivity.this, "POR FAVOR, ESCOJA UNA FECHA", Toast.LENGTH_SHORT).show();
        }else{
            crearGridViewAdaptado();
            if (controladorApp.getLista_GridView().size()==0){
                Toast.makeText(AppActivity.this, "NO ES POSIBLE, SELECCIONE OTRA FECHA", Toast.LENGTH_SHORT).show();
            }else{
                int numeroAleatorio=0;
                controladorApp.vaciarListaGridView_Manana();
                for (int j=0;j<controladorApp.getLista_GridView().size();j++){
                    if (Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(j).getFecha()))<14 && Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(j).getFecha()))!=0 ){
                        controladorApp.anadirListaGridView_Manana(controladorApp.getLista_GridView().get(j),j);
                    }
                }
                numeroAleatorio= (int)(Math.random()*controladorApp.getLista_GridView_Manana().size()+0);
                nombreServicioMananaSolicitado=controladorApp.getLista_GridView_Manana().get(numeroAleatorio).getTitulo();

                numeroAleatorio= (int)(Math.random()*controladorApp.getListaRestaurantes().size()+0);
                nombreRestauranteComidaSolicitado=controladorApp.getListaRestaurantes().get(numeroAleatorio).getNombre();

                controladorApp.vaciarListaGridView_Tarde();
                for (int j=0;j<controladorApp.getLista_GridView().size();j++){
                    if (Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(j).getFecha()))<14 && Integer.parseInt(controladorApp.separarHora(controladorApp.getLista_GridView().get(j).getFecha()))!=0 ){
                        controladorApp.anadirListaGridView_Tarde(controladorApp.getLista_GridView().get(j),j);
                    }
                }
                numeroAleatorio= (int)(Math.random()*controladorApp.getLista_GridView_Tarde().size()+0);
                nombreServicioTardeSolicitado=controladorApp.getLista_GridView_Tarde().get(numeroAleatorio).getTitulo();

                numeroAleatorio= (int)(Math.random()*controladorApp.getListaRestaurantes().size()+0);
                nombreRestauranteCenaSolicitado=controladorApp.getListaRestaurantes().get(numeroAleatorio).getNombre();
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toast.makeText(this, "Aqui", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case R.id.idButtonMenuFecha:
                adaptadorPersonalizadoServicio.notifyDataSetChanged();
                return true;
            case R.id.idButtonMenuActualizar:
                actualizarGridView();
                return true;
            case R.id.idButtonMenuGuardar:
                Toast.makeText(AppActivity.this, "GUARDAR", Toast.LENGTH_SHORT).show();
                controladorApp.recogerValoresGuardar();
                PaginaGuardar paginaGuardar=new PaginaGuardar(controladorApp.getTituloManana(), controladorApp.getDescriptionManana(), controladorApp.getFechaManana(), controladorApp.getLocalizacionManana(), controladorApp.getPrecioManana(), controladorApp.getNombreComida(), controladorApp.getLocalizacionComida(), controladorApp.getPrecioComida(), controladorApp.getDistritoComida(), controladorApp.getProductosComida(), controladorApp.getPuntuacionComida(), controladorApp.getTituloTarde(), controladorApp.getDescriptionTarde(), controladorApp.getFechaTarde(), controladorApp.getLocalizacionTarde(), controladorApp.getPrecioTarde(), controladorApp.getNombreCena(), controladorApp.getLocalizacionCena(), controladorApp.getPrecioCena(), controladorApp.getDistritoCena(), controladorApp.getProductosCena(), controladorApp.getPuntuacionCena());
                Toast.makeText(AppActivity.this, "->"+controladorApp.getTituloManana()+'\n'+controladorApp.getDescriptionManana()+'\n'+controladorApp.getFechaManana()+'\n'+controladorApp.getLocalizacionManana()+'\n'+controladorApp.getPrecioManana(), Toast.LENGTH_LONG).show();
                Toast.makeText(AppActivity.this, "->"+controladorApp.getNombreComida()+'\n'+controladorApp.getLocalizacionComida()+'\n'+controladorApp.getPrecioComida()+'\n'+controladorApp.getDistritoComida()+'\n'+controladorApp.getProductosComida()+'\n'+controladorApp.getPuntuacionComida(), Toast.LENGTH_LONG).show();
                Toast.makeText(AppActivity.this, "->"+controladorApp.getTituloTarde()+'\n'+controladorApp.getDescriptionTarde()+'\n'+controladorApp.getFechaTarde()+'\n'+controladorApp.getLocalizacionManana()+'\n'+controladorApp.getPrecioManana(), Toast.LENGTH_LONG).show();
                Toast.makeText(AppActivity.this, "->"+controladorApp.getNombreComida()+'\n'+controladorApp.getLocalizacionCena()+'\n'+controladorApp.getPrecioCena()+'\n'+controladorApp.getDistritoCena()+'\n'+controladorApp.getProductosCena()+'\n'+controladorApp.getPuntuacionCena(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.idButtonMenuAutomatico:
                escogerServiciosRestaurantesAutomaticos();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actualizarGridView() {
        crearGridViewAdaptado();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater2 =getMenuInflater();
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        inflater2.inflate(R.menu.menu_pulsacion,menu);
    }

    //Este metodo nos permite que al mantener la pulsacion sobre un servicio o restaurante nos muestre
    //un menu unico que pone solictar para asi guardar el nombre del servicio que hemos solicitado.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.idButtonMenuPulsacionSolicitar:
                if (pantallaManana){
                    nombreServicioMananaSolicitado=controladorApp.getLista_GridView().get(info.position).getTitulo();
                }
                if (pantallaComida){
                    nombreRestauranteComidaSolicitado=controladorApp.getListaRestaurantes().get(info.position).getNombre();
                }
                if (pantallaTarde){
                    nombreServicioTardeSolicitado=controladorApp.getLista_GridView().get(info.position).getTitulo();
                }
                if (pantallaComida){
                    nombreRestauranteCenaSolicitado=controladorApp.getListaRestaurantes().get(info.position).getNombre();
                }

                return true;
        }
        return super.onContextItemSelected(item);
    }

    public void mensaje(String mensaje) {
        Toast.makeText(AppActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public String getNombreRestauranteComidaSolicitado() {
        return nombreRestauranteComidaSolicitado;
    }

    public String getNombreRestauranteCenaSolicitado() {
        return nombreRestauranteCenaSolicitado;
    }

    public String getNombreServicioMananaSolicitado() {
        return nombreServicioMananaSolicitado;
    }

    public String getNombreServicioTardeSolicitado() {
        return nombreServicioTardeSolicitado;
    }

}
