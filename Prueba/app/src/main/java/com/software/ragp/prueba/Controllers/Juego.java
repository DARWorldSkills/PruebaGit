package com.software.ragp.prueba.Controllers;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ragp.prueba.Models.AdapterJ;
import com.software.ragp.prueba.Models.GestorDB;
import com.software.ragp.prueba.Models.Score;
import com.software.ragp.prueba.R;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity implements View.OnClickListener{
    private int fondoJuego = R.drawable.boton;

    private int[] imgFondo, imgAleatorias;
    private List<Integer> imgSelec;
    private int [] imagenesJuego = {
            R.drawable.alemania,
            R.drawable.argentina,
            R.drawable.brasil,
            R.drawable.colombia,
            R.drawable.eeuu,
            R.drawable.japon,
            R.drawable.mexico,
            R.drawable.panama,
            R.drawable.peru,
            R.drawable.uruguay,
    };

    TextView txtnombreJ1, txtnombreJ2, txtpuntajeJ1, txtpuntajeJ2, txttiempo;
    int movimientos, pos1=-1, pos2=-1, canselec=0, salir=4;
    GridView contenedor;
    boolean bandera = true;
    int modo_juego, inicioJugador, puntuacionJ1, puntuacionJ2;
    int nivel=4;
    int ancho = 100;
    int alto = 100;
    SharedPreferences jugar;
    ImageView imagen1, imagen2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        puntuacionJ1=0;
        puntuacionJ2=0;
        jugar = getSharedPreferences("juegoC",MODE_PRIVATE);
        nivel = Menu.dificultad;
        obtenerMedidas();
        start_game();
        final AdapterJ adapterJ = new AdapterJ(imagenesJuego, alto, ancho, this);
        contenedor.setAdapter(adapterJ);
        contenedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView item = (ImageView) view;
                canselec++;
                if (pos1==position || pos2==position){
                    canselec--;
                    return;
                }
                if (canselec==1){
                    imagen1 = item;
                    pos1 = position;
                }

                if (canselec==2){
                    imagen2 = item;
                    pos2 = position;
                }

                mostarImagen(item, position);
            }
        });

        if (inicioJugador==1){
            txtnombreJ1.setTextColor(getColor(R.color.colorVerde));
            Toast.makeText(Juego.this, "Empieza jugador 1", Toast.LENGTH_SHORT).show();
            txtpuntajeJ1.setText("Puntuación: "+puntuacionJ1);
            txtpuntajeJ2.setText("Puntuación: "+puntuacionJ2);
        }

        if (inicioJugador==2){
            txtnombreJ2.setTextColor(getColor(R.color.colorVerde));
            Toast.makeText(Juego.this, "Empieza jugador 2", Toast.LENGTH_SHORT).show();
            txtpuntajeJ1.setText("Puntuación: "+puntuacionJ1);
            txtpuntajeJ2.setText("Puntuación: "+puntuacionJ2);
        }
        inputData();
        bandera=true;
        turno();
    }

    private void mostarImagen(ImageView item, int position) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inSampleSize = 5;
        Bitmap imagen = BitmapFactory.decodeResource(getResources(), imgAleatorias[position], opt);
        item.setImageBitmap(imagen);
        if (canselec == 2) {
            movimientos++;
            if (modo_juego==2){
                txttiempo.setText("Movimientos: "+movimientos);
            }

            new validarJuego().execute();
        }
    }

    public class validarJuego extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            contenedor.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (inicioJugador==1){
                txtnombreJ1.setTextColor(getColor(R.color.colorGris));
                txtnombreJ2.setTextColor(getColor(R.color.colorVerde));
                Toast.makeText(Juego.this, "Continua jugador 2", Toast.LENGTH_SHORT).show();
                inicioJugador=2;
                txtpuntajeJ1.setText("Puntuación: "+puntuacionJ1);
            }else {

                if (inicioJugador == 2) {
                    txtnombreJ2.setTextColor(getColor(R.color.colorGris));
                    txtnombreJ1.setTextColor(getColor(R.color.colorVerde));
                    Toast.makeText(Juego.this, "Continua jugador 1", Toast.LENGTH_SHORT).show();
                    inicioJugador = 1;
                }
            }
            if (imgAleatorias[pos1] == imgAleatorias[pos2]) {
                imagen1.setVisibility(View.INVISIBLE);
                imagen1.setVisibility(View.INVISIBLE);
                imagen1 = null;
                imagen1 = null;
                salir--;

                if (inicioJugador==2){
                    puntuacionJ1+=100;
                    txtpuntajeJ1.setText("Puntuación: "+puntuacionJ1);

                }

                if (inicioJugador==1){
                    puntuacionJ2+=100;
                    txtpuntajeJ2.setText("Puntuación: "+puntuacionJ2);
                }

                if (salir==0){
                    bandera=false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Juego.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.juego_terminado_layout, null);
                    TextView txtJugador1 = view.findViewById(R.id.txtNombreJ1L);
                    TextView txtJugador2 = view.findViewById(R.id.txtNombreJ2L);
                    TextView txtpuntuacion1 = view.findViewById(R.id.txtPuntuacionJ1L);
                    TextView txtpuntuacion2 = view.findViewById(R.id.txtPuntuacionJ2L);
                    TextView txttiempo1 = view.findViewById(R.id.txtTiempoP);

                    txtJugador1.setText(txtnombreJ1.getText().toString());
                    txtJugador2.setText(txtnombreJ2.getText().toString());
                    txtpuntuacion1.setText(txtpuntajeJ1.getText().toString());
                    txtpuntuacion2.setText(txtpuntajeJ2.getText().toString());
                    txttiempo1.setText(txttiempo1.getText().toString());


                    builder.setView(view);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setTitle("Juego terminado");


                    insertarResultados();
                    builder.show();

                }
            }

            if (imagen1 != null && imagen2 != null) {
                BitmapFactory.Options opt = new BitmapFactory.Options();
                opt.inSampleSize = 2;
                Bitmap imagen = BitmapFactory.decodeResource(getResources(), fondoJuego, opt);
                imagen1.setImageBitmap(imagen);
                imagen2.setImageBitmap(imagen);

                if (inicioJugador==2){
                    puntuacionJ1-=1;
                    txtpuntajeJ1.setText("Puntuación: "+puntuacionJ1);

                }

                if (inicioJugador==1){
                    puntuacionJ2-=1;
                    txtpuntajeJ2.setText("Puntuación: "+puntuacionJ2);
                }
            }
            canselec = 0;
            pos1 = -1;
            pos2 = -1;
            contenedor.setEnabled(true);

        }

        public void insertarResultados(){
            GestorDB gestorDB = new GestorDB(Juego.this);
            SQLiteDatabase db = gestorDB.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            Score score = new Score();
            score.setNombre(txtnombreJ1.getText().toString());
            score.setDificultad(Integer.toString(nivel));
            score.setModo(Integer.toString(modo_juego));
            score.setPuntuacion(Integer.toString(puntuacionJ1));
            contentValues.put("JUGADOR",score.getNombre());
            contentValues.put("PUNTAJE",score.getPuntuacion());
            contentValues.put("MODO",score.getModo());
            contentValues.put("DIFICULTAD",score.getDificultad());
            db.insert("PUNTUACION",null,contentValues);


            contentValues = new ContentValues();
            score = new Score();
            score.setNombre(txtnombreJ1.getText().toString());
            score.setDificultad(Integer.toString(nivel));
            score.setModo(Integer.toString(modo_juego));
            score.setPuntuacion(Integer.toString(puntuacionJ2));
            contentValues.put("JUGADOR",score.getNombre());
            contentValues.put("PUNTAJE",score.getPuntuacion());
            contentValues.put("MODO",score.getModo());
            contentValues.put("DIFICULTAD",score.getDificultad());
            db.insert("PUNTUACION",null,contentValues);


        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }



    private void obtenerMedidas() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = (int) getResources().getDisplayMetrics().density;
        int dpH = (int) metrics.heightPixels/density;
        int dpW = (int) metrics.widthPixels/density;

        salir=nivel;
        if (nivel==4){
            ancho=dpW/3;
            alto=dpH/5;
            ancho+=30;
            alto+=30;
            contenedor.setPadding(10,10,10,10);
            contenedor.setNumColumns(2);

        }

        if (nivel==6){
            ancho=dpW/4;
            alto=dpH/5;
            ancho+=30;
            alto+=30;
            contenedor.setPadding(10,10,10,10);
            contenedor.setNumColumns(3);

        }

        if (nivel==8){
            ancho=dpW/5;
            alto=dpH/5;
            ancho+=30;
            alto+=30;
            contenedor.setPadding(10,10,10,10);
            contenedor.setNumColumns(4);

        }

    }

    private void turno() {
        inicioJugador = (int) (Math.random() *2) +1;

    }

    private void inputData() {
        txtnombreJ1.setText(Splash.jugador1);
        txtnombreJ2.setText(Splash.jugador2);
        modo_juego = jugar.getInt("modo",1);
    }

    private void inizialite() {
        contenedor = findViewById(R.id.contenedor);
        txtnombreJ1 = findViewById(R.id.txtJugador2);
        txtnombreJ2 = findViewById(R.id.txtnombreJ2);
        txtpuntajeJ1 = findViewById(R.id.txtpuntajeJ1);
        txtpuntajeJ2 = findViewById(R.id.txtpuntajeJ2);
        txttiempo = findViewById(R.id.txttiempo);
    }

    public void generarFondo() {
        imgFondo = new int[nivel * 2];
        for (int i = 0; i < imgFondo.length; i++) {
            imgFondo[i] = fondoJuego;
        }
    }

    public void generarSeleccionadas() {
        imgSelec = new ArrayList<>();
        for (int i = 0; i < nivel; i++) {
            int aux = (int) (Math.random() * nivel);
            if (!imgSelec.contains(imagenesJuego[aux])) {
                imgSelec.add(imagenesJuego[aux]);
            } else {
                i--;
            }
        }

    }

    public void generaAleatorias() {
        imgAleatorias = new int[nivel * 2];
        for (int i = 0; i < nivel; i++) {
            int aux = 0;
            do {
                int val = (int) (Math.random() * nivel * 2);
                if (imgAleatorias[val] == 0) {
                    imgAleatorias[val] = imgSelec.get(i);
                    aux++;
                }
            } while (aux < 2);
        }
    }

    public void start_game() {
        generarFondo();
        generarSeleccionadas();
        generaAleatorias();
        tiempo();

    }

    private void tiempo() {
        final int[] segundos = {0};
        if (modo_juego==1){
            txttiempo.setText("Tiempo: "+(segundos[0]));
        }else {
            txttiempo.setText("Movimientos: "+(segundos[0]));
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0] +=1;
                            if (modo_juego==1){
                                txttiempo.setText("Tiempo: "+(segundos[0]));
                            }

                        }
                    });

                }

            }
        });
        thread.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
