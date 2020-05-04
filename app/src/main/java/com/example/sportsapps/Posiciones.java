package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Posiciones extends AppCompatActivity implements Handler.Callback {

    private Handler dataHandler;
    private TextView contenido;
    private ListView listaPosiciones;
    private List a ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posiciones);
        dataHandler = new Handler(Looper.getMainLooper(), this);
        a = new ArrayList();
        listaPosiciones=findViewById(R.id.lstPosiciones);
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/posiciones.json", dataHandler);
        r.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        JSONArray datos = (JSONArray) msg.obj;
        try {
            String liga="LigaMX";

            for(int i = 0; i < datos.length(); i++){

                JSONObject actual = datos.getJSONObject(i);

                if(actual.getString("liga").equals(liga)){
                    JSONArray posiciones = actual.getJSONArray("posiciones");

                    for(int j = 0; j < posiciones.length(); j++){
                        Log.wtf("JSON", posiciones.getString(i) + "");
                        a.add(posiciones.getString(j));
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                a
        );

        listaPosiciones.setAdapter(adapter);
        return true;
    }
}
