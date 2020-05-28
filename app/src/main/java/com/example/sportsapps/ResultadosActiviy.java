package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class ResultadosActiviy extends AppCompatActivity implements  Handler.Callback,  View.OnClickListener{
    private Handler dataHandler;
    private RecyclerView recyclerView;
    private ArrayList<Resultado> resultados;


    private String liga;
    private String fecha;
    //Para seleccionar la fecha
    private Calendar mCurrentDate;
    private TextView fechatxt,ligatxt;
    int day, month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_activiy);
        liga=getIntent().getStringExtra("liga");
        ligatxt=findViewById(R.id.txtLiga);
        ligatxt.setText("Resultados de la "+liga);
        fechatxt=findViewById(R.id.txtDate);
        mCurrentDate =Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        month =  month+1;
        setFecha();


        fechatxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ResultadosActiviy.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        fecha=dayOfMonth+"/"+monthOfYear+"/"+year;
                        fechatxt.setText(fecha);
                        req();
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
    }
    private void req(){
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/resultados2.json", dataHandler);
        r.start();
    }


    @Override
    public boolean handleMessage(@NonNull Message msg) {
        resultados =  new ArrayList<>();
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
                            JSONArray datosFecha = actualLiga.getJSONArray("resultados");
                            for (int k = 0; k < datosFecha.length(); k++){
                                JSONObject actualPartido = datosFecha.getJSONObject(k);
                                Log.wtf("prueba", "datosFecha actual partido = "+actualPartido.toString());
                                resultados.add(new Resultado(actualPartido.getString("equipoLocal"),actualPartido.getString("equipoVisitante"),actualPartido.getString("golesLocal"),actualPartido.getString("golesVisitante"),actualPartido.getString("faltasLocal"),actualPartido.getString("faltasVisitante"),actualPartido.getString("tarAmarillasL"),actualPartido.getString("tarAmarillasV"),actualPartido.getString("tarRojasL"),actualPartido.getString("tarRojasV"),actualPartido.getString("fdlugarL"),actualPartido.getString("fdlugarV"),actualPartido.getString("esquinasL"),actualPartido.getString("esquinasV"),actualPartido.getString("salvadasL"),actualPartido.getString("salvadasV"),actualPartido.getString("pctPosL"),actualPartido.getString("pctPosV"),actualPartido.getString("tirosL"),actualPartido.getString("tirosV")));

                            }
                        }
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ResultadoAdapter adapter = new ResultadoAdapter(resultados,this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        return true;
    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        Intent intento= new Intent(this,ResultadosInfoActivity.class);
        intento.putExtra("equipo1", resultados.get(pos).getEquipoL());
        intento.putExtra("equipo2", resultados.get(pos).getEquipoV());
        intento.putExtra("goles1", resultados.get(pos).getGolesL());
        intento.putExtra("goles2", resultados.get(pos).getGolesV());
        intento.putExtra("faltas1", resultados.get(pos).getFaltasL());
        intento.putExtra("faltas2", resultados.get(pos).getFaltasV());
        intento.putExtra("amarillas1", resultados.get(pos).getTarAmarillasL());
        intento.putExtra("amarillas2", resultados.get(pos).getTarAmarillasV());
        intento.putExtra("rojas1", resultados.get(pos).getTarRojasL());
        intento.putExtra("rojas2", resultados.get(pos).getTarRojasV());
        intento.putExtra("fdLugar1", resultados.get(pos).getFdLugarL());
        intento.putExtra("fdLugar2", resultados.get(pos).getFdLugarV());
        intento.putExtra("esquinas1", resultados.get(pos).getEsquinasL());
        intento.putExtra("esquinas2", resultados.get(pos).getEsquinasV());
        intento.putExtra("salvadas1", resultados.get(pos).getSalvadasL());
        intento.putExtra("salvadas2", resultados.get(pos).getSalvadasV());
        intento.putExtra("pct1", resultados.get(pos).getPctPosL());
        intento.putExtra("pct2", resultados.get(pos).getPctPosV());
        intento.putExtra("tiros1", resultados.get(pos).getTirosL());
        intento.putExtra("tiros2", resultados.get(pos).getTirosV());

        startActivity(intento);
    }
}
