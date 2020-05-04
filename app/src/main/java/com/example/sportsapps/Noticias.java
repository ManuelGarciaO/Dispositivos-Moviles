package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Noticias extends AppCompatActivity implements  Handler.Callback {

    private Handler dataHandler;
    private ArrayList<Noticia> noticias;
    private ListView listaNoticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        listaNoticias = findViewById(R.id.lstNoticias);

        noticias = new ArrayList<>();
        dataHandler = new Handler(Looper.getMainLooper(), this);
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/noticias.json", dataHandler);
        r.start();

    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        JSONArray datos = (JSONArray) msg.obj;
        try {
            for(int i = 0; i < datos.length(); i++){
                JSONObject actual = datos.getJSONObject(i);
                noticias.add(new Noticia((actual.getString("titulo")),actual.getString("descripcion")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        NoticiaAdapter adaptador = new NoticiaAdapter(noticias, this);
        listaNoticias.setAdapter(adaptador);
        return true;
    }
}
