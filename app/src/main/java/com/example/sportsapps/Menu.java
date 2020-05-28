package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

public class Menu extends AppCompatActivity implements  Handler.Callback {

    private Handler dataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        dataHandler = new Handler(Looper.getMainLooper(), this);
        //Request r = new Request("https://api.github.com/users", dataHandler);
        //r.start();
    }

    public void goNoticias(View v) {
        Intent intento = new Intent(this, Noticias.class);
        startActivity(intento);
    }

    public void goPosiciones(View v) {
        Intent intento = new Intent(this, Posiciones.class);
        startActivity(intento);
    }

    public void goResultados(View v) {
        Intent intento = new Intent(this, ResultadosActiviy.class);
        startActivity(intento);
    }


    @Override
    public boolean handleMessage(@NonNull Message msg) {
        JSONArray datos = (JSONArray) msg.obj;
        Toast.makeText(this, datos.toString(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
