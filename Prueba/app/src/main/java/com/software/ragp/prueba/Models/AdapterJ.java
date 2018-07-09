package com.software.ragp.prueba.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdapterJ extends BaseAdapter{
    private int [] imagenesJuego;
    private int alto;
    private int ancho;
    private Context context;

    public AdapterJ(int[] imagenesJuego, int alto, int ancho, Context context) {
        this.imagenesJuego = imagenesJuego;
        this.alto = alto;
        this.ancho = ancho;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagenesJuego.length;
    }

    @Override
    public Object getItem(int position) {
        return imagenesJuego[position];
    }

    @Override
    public long getItemId(int position) {
        return imagenesJuego[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView item = new ImageView(context);
        item.setScaleType(ImageView.ScaleType.FIT_XY);
        item.setPadding(8,8,8,8);
        BitmapFactory.Options op = new  BitmapFactory.Options();
        op.inSampleSize=3;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),imagenesJuego[position], op);
        item.setImageBitmap(bitmap);

        return null;
    }
}
