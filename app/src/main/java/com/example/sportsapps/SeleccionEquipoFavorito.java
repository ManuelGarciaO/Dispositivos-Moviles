package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeleccionEquipoFavorito extends AppCompatActivity implements Handler.Callback{

    private Handler dataHandler;
    ArrayList<Equipo> equipos;
    Map<String, Object> map;
    private String id;
    DatabaseReference mRootReference;
    private Spinner selectedTeam;
    private Button continuar;
    private ArrayList<String> nombreEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_equipo_favorito);
        selectedTeam = (Spinner) findViewById(R.id.teams);
        continuar = (Button) findViewById(R.id.continuar);

        equipos = new ArrayList<>();
        mRootReference = FirebaseDatabase.getInstance().getReference();
        id = getIntent().getStringExtra("Id");
        dataHandler = new Handler(Looper.getMainLooper(), this);
        map = new HashMap<>();

        map.put("name", getIntent().getStringExtra("name"));
        map.put("email", getIntent().getStringExtra("email"));
        map.put("password", getIntent().getStringExtra("password"));

        Request r = new Request("https://manuel19299.github.io/SportsApp/data/equipos.json", dataHandler);
        r.start();
        nombreEquipos = new ArrayList<>();
        for(int i = 0; i < equipos.size(); i++){
            nombreEquipos.add(equipos.get(i).getEquipo());
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nombreEquipos);
        selectedTeam.setAdapter(adapter);
    }

    @Override
    public boolean handleMessage(@NonNull Message msg){
        JSONArray datos = (JSONArray) msg.obj;
        try{
            for(int i = 0; i < datos.length(); i++ ){
                JSONObject actual = datos.getJSONObject(i);
                equipos.add(new Equipo(actual.getString("equipo"), actual.getString("liga")));
            }
        }catch(JSONException ex){
            ex.getMessage();
        }
        return true;
    }

    public void goMenu(){
        map.put("favoriteTeam", selectedTeam.getSelectedItem().toString());
        for(int i = 0; i < equipos.size(); i++){
            if(selectedTeam.getSelectedItem().toString().equals(equipos.get(i).getEquipo())){
                map.put("liga", equipos.get(i).getLiga());
                break;
            }
        }
        mRootReference.child("Users").child(id).setValue(map);
        Intent intent = new Intent(SeleccionEquipoFavorito.this, Menu.class);
        finish();
        startActivity(intent);
    }
}
