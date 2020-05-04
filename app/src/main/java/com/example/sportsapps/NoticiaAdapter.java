package com.example.sportsapps;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticiaAdapter extends BaseAdapter {
    private ArrayList<Noticia> datos;
    private Activity activity;

    public NoticiaAdapter(ArrayList<Noticia> datos, Activity activity) {
        this.datos = datos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView titulo = convertView.findViewById(R.id.txtTitulo);
        TextView descripcion = convertView.findViewById(R.id.txtDescripcion);

        Noticia noticia = datos.get(position);

        titulo.setText(noticia.getTitulo());
        descripcion.setText(noticia.getDescripcion());


        return convertView;
    }
}
