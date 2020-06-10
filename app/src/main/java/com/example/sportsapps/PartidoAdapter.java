package com.example.sportsapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidoViewHolder>{

    public static class PartidoViewHolder extends RecyclerView.ViewHolder{
        public TextView equipo1txt, equipo2txt, horatxt;
        public PartidoViewHolder(@NonNull View itemView) {
            super(itemView);
            equipo1txt=itemView.findViewById(R.id.txtEquipo1);
            equipo2txt=itemView.findViewById(R.id.txtEquipo2);
            horatxt=itemView.findViewById(R.id.txtHora);
        }
    }

    private ArrayList<Partido> partidos;
    public PartidoAdapter(ArrayList<Partido> partidos){
        this.partidos = partidos;
    }
    @NonNull
    @Override
    public PartidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.partidos_row, parent, false);
        //v.setOnClickListener(listener);
        PartidoAdapter.PartidoViewHolder pvh= new PartidoAdapter.PartidoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoViewHolder holder, int position) {
        holder.equipo1txt.setText(partidos.get(position).getEquipoL());
        holder.equipo2txt.setText(partidos.get(position).getEquipoV());
        holder.horatxt.setText(partidos.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return partidos.size();
    }


}
