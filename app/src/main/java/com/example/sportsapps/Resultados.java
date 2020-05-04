package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity implements  Handler.Callback {

    private Handler dataHandler;
    private ArrayList<Resultado> resultados;
    private ListView listaResultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        listaResultados=findViewById(R.id.lstResultados);

        resultados = new ArrayList<>();
        dataHandler = new Handler(Looper.getMainLooper(), this);
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/resultados.json", dataHandler);
        r.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        JSONArray datos = (JSONArray) msg.obj;
        try {
            for(int i = 0; i < datos.length(); i++){
                JSONObject actual = datos.getJSONObject(i);
                Log.wtf("JSON", actual.getString("equipoLocal"));
                Log.wtf("JSON", actual.getInt("golesLocal") + "");
                Log.wtf("JSON", actual.getString("equipoVisitante"));
                Log.wtf("JSON", actual.getInt("golesVisitante") + "");
                resultados.add(new Resultado(actual.getString("equipoLocal"),actual.getString("equipoVisitante"),actual.getInt("golesLocal"),actual.getInt("golesVisitante")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ResultadoAdapter adaptador = new ResultadoAdapter(resultados, this);
        listaResultados.setAdapter(adaptador);
        return true;
    }
}
