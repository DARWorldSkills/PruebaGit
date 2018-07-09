package com.software.ragp.prueba.Controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.software.ragp.prueba.Models.GestorDB;
import com.software.ragp.prueba.Models.Score;
import com.software.ragp.prueba.R;

import java.util.ArrayList;
import java.util.List;

public class Puntuacion extends AppCompatActivity implements View.OnClickListener{
    RadioButton rbtnTiempo, rbtnMovientos, rbtnFacil, rbtnMedio, rbtnDificil;
    TextView txtPrimero, txtSegundo, txtTercero, txtCuarto, txtQuinto;
    List<Score> listresultados = new ArrayList<>();
    String modo, dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        dificultad="4";
        mostrarDatos();
    }

    private void inizialite() {
        rbtnTiempo = findViewById(R.id.rbtnTiempoP);
        rbtnMovientos = findViewById(R.id.rbtnMovimientosP);
        rbtnFacil = findViewById(R.id.rbtnFacilP);
        rbtnMedio = findViewById(R.id.rbtnMedioP);
        rbtnDificil = findViewById(R.id.rbtnDificilP);
        txtPrimero = findViewById(R.id.txtprimero);
        txtSegundo = findViewById(R.id.txtsegundo);
        txtTercero = findViewById(R.id.txttercero);
        txtCuarto = findViewById(R.id.txtcuarto);
        txtQuinto = findViewById(R.id.txtquinto);

        rbtnTiempo.setOnClickListener(this);
        rbtnMovientos.setOnClickListener(this);
        rbtnFacil.setOnClickListener(this);
        rbtnMedio.setOnClickListener(this);
        rbtnDificil.setOnClickListener(this);
    }

    public void mostrarDatos(){
        if (rbtnFacil.isChecked()){
            dificultad="4";
        }

        if (rbtnMedio.isChecked()){
            dificultad="6";
        }

        if (rbtnDificil.isChecked()){
            dificultad="8";
        }

        if (rbtnTiempo.isChecked()){
            modo="1";
        }

        if (rbtnMovientos.isChecked()){
            modo="2";
        }

        inputData();



    }

    private void inputData() {
        listresultados = consultarDatos();

        if (listresultados.size()==0){
            Toast.makeText(this, "No hay puntuaciones", Toast.LENGTH_SHORT).show();
        }

        if (listresultados.size()>0){
            txtPrimero.setText(listresultados.get(0).getNombre()+" "+listresultados.get(0).getPuntuacion());
        }else {
            txtPrimero.setText("");
        }

        if (listresultados.size()>1){
            txtSegundo.setText(listresultados.get(1).getNombre()+" "+listresultados.get(1).getPuntuacion());
        }else {
            txtSegundo.setText("");
        }

        if (listresultados.size()>2){
            txtTercero.setText(listresultados.get(2).getNombre()+" "+listresultados.get(2).getPuntuacion());
        }else {
            txtTercero.setText("");
        }

        if (listresultados.size()>3){
            txtCuarto.setText(listresultados.get(3).getNombre()+" "+listresultados.get(3).getPuntuacion());
        }else {
            txtCuarto.setText("");
        }

        if (listresultados.size()>4){
            txtQuinto.setText(listresultados.get(4).getNombre()+" "+listresultados.get(4).getPuntuacion());
        }else {
            txtQuinto.setText("");
        }


    }

    private List<Score> consultarDatos() {
        List<Score> scoreList = new ArrayList<>();
        GestorDB gestorDB = new GestorDB(this);
        SQLiteDatabase db = gestorDB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DATOS WHERE MODO='"+modo+"' AND DIFUCULTAD='"+dificultad+"' ORDER BY PUNTAJE ASC; ",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setNombre(cursor.getString(0));
                score.setPuntuacion(cursor.getString(1));
                score.setModo(cursor.getString(2));
                score.setDificultad(cursor.getString(3));

                scoreList.add(score);

            }while (cursor.moveToNext());
        }


        return scoreList;
    }




    public void enviar(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtnTiempoP:
                mostrarDatos();
                break;

            case R.id.rbtnMovimientosP:
                mostrarDatos();
                break;

            case R.id.rbtnFacilP:
                mostrarDatos();
                break;

            case R.id.rbtnMedioP:
                mostrarDatos();
                break;

            case R.id.rbtnDificilP:
                mostrarDatos();
                break;

        }
    }
}
