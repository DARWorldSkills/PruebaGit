package com.software.ragp.prueba.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.software.ragp.prueba.R;

public class Juego extends AppCompatActivity implements View.OnClickListener{
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

    TextView txtnombreJ1, txtnombreJ2, txtpuntajeJ1, txtpuntajeJ2;
    Button btnColor1, btnColor2, btnColor3, btnColor4;
    int movimientos, pos1=-1, pos2=-1;
    GridView contenedor;
    int nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
    }

    private void inizialite() {
        txtnombreJ1 = findViewById(R.id.txtJugador2);
        txtnombreJ2 = findViewById(R.id.txtnombreJ2);
        txtpuntajeJ1 = findViewById(R.id.txtpuntajeJ1);
        txtpuntajeJ2 = findViewById(R.id.txtpuntajeJ2);

        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
