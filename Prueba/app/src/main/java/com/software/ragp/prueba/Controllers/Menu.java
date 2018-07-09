package com.software.ragp.prueba.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.software.ragp.prueba.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    Button btnJugar, btnPuntacion, btnConfiguracion;
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
                Intent intent = new Intent(Menu.this, Juego.class);
                startActivity(intent);
                break;

            case R.id.btnConfiguracion:
                break;

            case R.id.btnPuntuacion:

        }
    }
}
