package com.example.sportsapps;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultadoAdapter extends BaseAdapter {
    private ArrayList<Resultado> datos;
    private Activity activity;

    public ResultadoAdapter(ArrayList<Resultado> datos, Activity activity) {
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
            convertView = activity.getLayoutInflater().inflate(R.layout.row2, null);
        }

        TextView equipo1 = convertView.findViewById(R.id.txtEquipo1);
        TextView equipo2 = convertView.findViewById(R.id.txtEquipo2);
        TextView goles1 = convertView.findViewById(R.id.txtGoles1);
        TextView goles2 = convertView.findViewById(R.id.txtGoles2);

        Resultado resultado = datos.get(position);

        equipo1.setText(resultado.getEquipoL());
        equipo2.setText(resultado.getEquipoV());
        goles1.setText(resultado.getGolesL()+"");
        goles2.setText(resultado.getGolesV()+"");

        if(resultado.getGolesL()>resultado.getGolesV()){
            goles1.setTextColor(Color.parseColor("#008000"));
            goles2.setTextColor(Color.parseColor("#8B0000"));
        }else if(resultado.getGolesL()<resultado.getGolesV()){
            goles1.setTextColor(Color.parseColor("#8B0000"));
            goles2.setTextColor(Color.parseColor("#008000"));
        }else{
            goles1.setTextColor(Color.parseColor("#FFA500"));
            goles2.setTextColor(Color.parseColor("#FFA500"));
        }


        return convertView;
    }
}
