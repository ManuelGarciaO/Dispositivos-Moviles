package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class partidos extends AppCompatActivity implements  Handler.Callback{
    private Handler dataHandler;
    private RecyclerView recyclerView;
    private ArrayList<Partido> partidos;

    private String liga;
    private String fecha,fechaActual;
    //Para seleccionar la fecha
    private Calendar mCurrentDate;
    private TextView fechatxt,ligatxt, noRes;
    private int day, month, year, diaA,mesA,anioA;
    private ImageView btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidos);
        dataHandler = new Handler(Looper.getMainLooper(), this);
        liga=getIntent().getStringExtra("liga");
        ligatxt=findViewById(R.id.txtLiga);
        ligatxt.setText("Proximos partidos de la "+liga);
        fechatxt=findViewById(R.id.txtDate);
        noRes=findViewById(R.id.txtNoResultado);
        btnAtras=findViewById(R.id.btnAtras);
        mCurrentDate =Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        month =  month+1;

        diaA=day;
        mesA=month;
        anioA=year;
        fechaActual=day+"/"+month+"/"+year;
        setFecha();

        fechatxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(partidos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        fecha=dayOfMonth+"/"+monthOfYear+"/"+year;
                        if(dayOfMonth>=diaA && monthOfYear>=mesA && year>=anioA){
                            fechatxt.setText(fecha);
                            req();
                        }
                        else{
                            Toast.makeText(partidos.this, "Fecha no permitida", Toast.LENGTH_SHORT).show();
                        }
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });


        dataHandler = new Handler(Looper.getMainLooper(), this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        req();
    }

    private void setFecha(){
        fecha=day+"/"+month+"/"+year;
        fechatxt.setText(fecha);
        if(fecha.equals(fechaActual)){
            btnAtras.setVisibility(View.INVISIBLE);
            btnAtras.setClickable(false);
        }
        else{
            btnAtras.setVisibility(View.VISIBLE);
            btnAtras.setClickable(true);
        }
    }

    public void mas(View v){
        mCurrentDate.add(Calendar.DATE,1);
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        month =  month+1;
        this.setFecha();
        req();
    }
    public void menos(View v){
        mCurrentDate.add(Calendar.DATE,-1);
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        month =  month+1;
        if(day>=diaA && month>=mesA && year>=anioA){
            this.setFecha();
            req();
        }
    }

    private void req(){
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/partidos.json", dataHandler);
        r.start();
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        partidos =  new ArrayList<>();
        JSONArray datos = (JSONArray) msg.obj;
        try {
            for(int i = 0; i < datos.length(); i++){
                JSONObject actual = datos.getJSONObject(i);
                Log.wtf("prueba", "level1"+actual.getString("liga"));
                //Validar Liga
                if(actual.getString("liga").equals(liga)){
                    Log.wtf("prueba", "equals to liga1 = "+actual.getJSONArray("resultados"));
                    //Obtener resultados de Liga
                    JSONArray datosLiga = actual.getJSONArray("resultados");
                    for(int j = 0; j < datosLiga.length(); j++){
                        JSONObject actualLiga = datosLiga.getJSONObject(j);
                        Log.wtf("prueba", "datosLiga actual fecha = "+actualLiga.getString("fecha"));
                        //Validar fecha
                        if(actualLiga.getString("fecha").equals(fecha)){
                            Log.wtf("prueba", "equals to fecha = "+actualLiga.getString("fecha"));
                            //Obtener resultados de fecha
                            JSONArray datosFecha = actualLiga.getJSONArray("partidos");
                            Log.wtf("prueba2", "datosFecha actual partido = "+datosFecha.toString());
                            for (int k = 0; k < datosFecha.length(); k++){
                                JSONObject actualPartido = datosFecha.getJSONObject(k);
                                Log.wtf("prueba2", "datosFecha actual partido = "+actualPartido.toString());
                                partidos.add(new Partido(actualPartido.getString("equipoLocal"),actualPartido.getString("equipoVisitante"),actualPartido.getString("hora")));
                            }
                        }
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(partidos.size()>0){
            noRes.setVisibility(View.INVISIBLE);
        }else{
            noRes.setVisibility(View.VISIBLE);
        }
        PartidoAdapter adapter = new PartidoAdapter(partidos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        return true;
    }
}
