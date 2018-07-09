package com.software.ragp.prueba.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.software.ragp.prueba.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    Button btnJugar, btnPuntacion, btnConfiguracion;
    public static int dificultad =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntacion = findViewById(R.id.btnPuntuacion);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);

        btnJugar.setOnClickListener(this);
        btnPuntacion.setOnClickListener(this);
        btnConfiguracion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnJugar:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View vista = layoutInflater.inflate(R.layout.dificultad_layout,null);
                final RadioButton rbtnfacil = vista.findViewById(R.id.rbtnFacilD);
                final RadioButton rbtnmedio = vista.findViewById(R.id.rbtnMedioD);
                final RadioButton rbtndificil = vista.findViewById(R.id.rbtnDificilD);
                rbtnfacil.setChecked(true);

                builder.setTitle("Elija la difucultad");
                builder.setView(vista);
                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (rbtnfacil.isChecked()) {
                            dificultad = 4;
                        }
                        if (rbtnmedio.isChecked()) {
                            dificultad = 6;
                        }
                        if (rbtndificil.isChecked()) {
                            dificultad = 8;
                        }

                        Intent intent = new Intent(Menu.this, Juego.class);
                        startActivity(intent);



                    }
                });

                builder.show();

                break;

            case R.id.btnConfiguracion:
                Intent intent = new Intent(Menu.this, Configuracion.class);
                startActivity(intent);
                break;

            case R.id.btnPuntuacion:
                Intent intent3 = new Intent(Menu.this, Puntuacion.class);
                startActivity(intent3);
                break;

        }
    }
    

}
