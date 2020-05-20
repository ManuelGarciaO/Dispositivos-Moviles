package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Noticias extends AppCompatActivity implements Handler.Callback, View.OnClickListener {

    private Handler dataHandler;
    private ArrayList<Noticia> noticias;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        dataHandler = new Handler(Looper.getMainLooper(), this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        noticias = new ArrayList<>();
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/noticias.json", dataHandler);
        r.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        JSONArray datos = (JSONArray) msg.obj;
        try {
            for(int i = 0; i < datos.length(); i++){
                JSONObject actual = datos.getJSONObject(i);
                noticias.add(new Noticia(actual.getString("titulo"),actual.getString("descripcion"),actual.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NoticiaAdapter adapter = new NoticiaAdapter(noticias, this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        return true;
    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Intent intento = new Intent(this, NoticiasInfoActivity.class);
        intento.putExtra("Titulo", noticias.get(pos).getTitulo());
        intento.putExtra("Descripcion", noticias.get(pos).getDescripcion());
        intento.putExtra("Url", noticias.get(pos).getUrl());
        startActivity(intento);
    }
}
