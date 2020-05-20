package com.example.resultadosposicionesparcial2;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultadoAdapter extends RecyclerView.Adapter<ResultadoAdapter.ResultadoViewHolder>{



    public static class ResultadoViewHolder extends RecyclerView.ViewHolder{
        public TextView equipo1txt, equipo2txt,goles1txt,goles2txt;
        public ResultadoViewHolder(@NonNull View itemView) {
            super(itemView);
            equipo1txt=itemView.findViewById(R.id.txtEquipo1);
            equipo2txt=itemView.findViewById(R.id.txtEquipo2);
            goles1txt=itemView.findViewById(R.id.txtGoles1);
            goles2txt=itemView.findViewById(R.id.txtGoles2);
        }

    }
    private ArrayList<Resultado> resultados;
    private View.OnClickListener listener;
    public ResultadoAdapter(ArrayList<Resultado> resultados,View.OnClickListener listener) {
        this.resultados = resultados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.resultados_row, parent, false);
        v.setOnClickListener(listener);
        ResultadoViewHolder rvh= new ResultadoViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        holder.equipo1txt.setText(resultados.get(position).getEquipoL());
        holder.equipo2txt.setText(resultados.get(position).getEquipoV());
        holder.goles1txt.setText(resultados.get(position).getGolesL());
        holder.goles2txt.setText(resultados.get(position).getGolesV());

        int gles1=Integer.parseInt(resultados.get(position).getGolesL());
        int goles2=Integer.parseInt(resultados.get(position).getGolesV());

        if(gles1>goles2){
            holder.goles1txt.setTextColor(Color.parseColor("#008000"));
            holder.goles2txt.setTextColor(Color.parseColor("#8B0000"));
        }else if(gles1<goles2){
            holder.goles1txt.setTextColor(Color.parseColor("#8B0000"));
            holder.goles2txt.setTextColor(Color.parseColor("#008000"));
        }else{
            holder.goles1txt.setTextColor(Color.parseColor("#FFA500"));
            holder.goles2txt.setTextColor(Color.parseColor("#FFA500"));
        }
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }
}
