package com.example.sportsapps;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

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
    }
}
