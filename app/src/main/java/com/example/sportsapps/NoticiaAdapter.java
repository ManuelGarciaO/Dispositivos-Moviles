package com.example.sportsapps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder> {
    private ArrayList<Noticia> noticias;
    private View.OnClickListener listener;

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView titulotxt, descripciontxt;
        public NoticiaViewHolder(@NonNull View itemView){
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
            titulotxt = itemView.findViewById(R.id.txtTitulo);
            descripciontxt = itemView.findViewById(R.id.txtDescripcion);
        }
    }

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
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    /*public NoticiaAdapter(ArrayList<Noticia> datos, Activity activity) {
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

        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(convertView.getContext()).defaultDisplayImageOptions(defaultOptions).memoryCache(new WeakMemoryCache()).diskCacheSize(100 * 1024 * 1024).build();
        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP

        ImageView imagen = convertView.findViewById(R.id.imageView);
        TextView titulo = convertView.findViewById(R.id.txtTitulo);
        TextView descripcion = convertView.findViewById(R.id.txtDescripcion);

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(true).showImageForEmptyUri(null).showImageOnFail(null).showImageOnLoading(null).build();

        Noticia noticia = datos.get(position);

        titulo.setText(noticia.getTitulo());
        descripcion.setText(noticia.getDescripcion());
        imageLoader.displayImage(noticia.getUrl(), imagen, options);

        return convertView;
    }*/
}
