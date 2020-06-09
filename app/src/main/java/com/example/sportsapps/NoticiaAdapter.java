package com.example.sportsapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder> {

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView titulotxt, descripciontxt;
        public NoticiaViewHolder(@NonNull View itemView){
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imageView);
            titulotxt = itemView.findViewById(R.id.txtTitulo);
            descripciontxt = itemView.findViewById(R.id.txtDescripcion2);
        }
    }

    private ArrayList<Noticia> noticias;
    private View.OnClickListener listener;
    public NoticiaAdapter(ArrayList<Noticia> noticias, View.OnClickListener listener){
        this.noticias = noticias;
        this.listener= listener;
    }

    @NonNull
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_noticias, parent, false);
        v.setOnClickListener(listener);
        NoticiaViewHolder nvh = new NoticiaViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        holder.titulotxt.setText(noticias.get(position).getTitulo());
        holder.descripciontxt.setText(noticias.get(position).getDescripcion());
        Picasso.get().load(noticias.get(position).getUrl()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }
}
