package com.software.ragp.prueba.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.software.ragp.prueba.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

public class Splash extends AppCompatActivity {
    public static String jugador1, jugador2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.jugadores_layout,null);
                builder.setView(view);
                final TextView txtjugador1 = view.findViewById(R.id.txtJugador1);
                final TextView txtjugador2 = view.findViewById(R.id.txtJugador2);
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jugador1 = txtjugador1.getText().toString();
                        jugador2 = txtjugador2.getText().toString();

                        Intent intent = new Intent(Splash.this, Menu.class);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.show();
            }
        }.start();
    }
}
