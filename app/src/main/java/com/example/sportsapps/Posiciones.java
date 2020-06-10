package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Posiciones extends AppCompatActivity implements Handler.Callback {

    private Handler dataHandler;
    private TableLayout tabla;
    private ArrayList<DatosPosicion> lstPosiciones;
    private TableRow fila;
    private TextView equipo, jj, jg, je, jp, dif, gf, gc, pts, l;
    private ImageView logo;
    private String liga = "Liga1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posiciones);
        liga=getIntent().getStringExtra("liga");
        dataHandler = new Handler(Looper.getMainLooper(), this);

        lstPosiciones = new ArrayList<DatosPosicion>();
        tabla = (TableLayout)findViewById(R.id.TableLayout);

        Request r = new Request("https://manuel19299.github.io/SportsApp/data/posiciones2.json", dataHandler);
        r.start();

    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        JSONArray datos = (JSONArray) msg.obj;
        try {
            for(int i = 0; i < datos.length(); i++){
                JSONObject actual = datos.getJSONObject(i);
                if(actual.getString("liga").equals(this.liga)){
                    JSONArray posiciones = actual.getJSONArray("posiciones");
                    for(int j = 0; j < posiciones.length(); j++){

                        JSONObject equipoActual = posiciones.getJSONObject(j);

                        lstPosiciones.add(new DatosPosicion(equipoActual.getString("equipo"), equipoActual.getInt("juegosJugados"), equipoActual.getInt("juegosGanados"), equipoActual.getInt("juegosEmpatados"), equipoActual.getInt("juegosPerdidos"), equipoActual.getInt("golesAFavor"), equipoActual.getInt("golesEnContra"), equipoActual.getInt("diferenciaDeGoles"), equipoActual.getInt("puntos"), equipoActual.getString("logo")));
                        Log.wtf("prueba",lstPosiciones.size()+"");
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutLogo = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutEquipo = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutJJ = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutJG = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutJE = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutJP = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutDif = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutGF = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutGC = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutPts = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        for(int i = -1; i < lstPosiciones.size(); i++){
            fila = new TableRow(this);
            fila.setLayoutParams(layoutFila);

            if(i == -1){
                l = new TextView(this);
                l.setText("");
                l.setGravity(Gravity.LEFT);
                l.setBackgroundColor(Color.BLACK);
                l.setPadding(10, 10, 10, 10);
                l.setLayoutParams(layoutLogo);
                fila.addView(l);

                equipo = new TextView(this);
                equipo.setText("EQUIPO");
                equipo.setGravity(Gravity.LEFT);
                equipo.setBackgroundColor(Color.BLACK);
                equipo.setTextColor(Color.WHITE);
                equipo.setPadding(10, 10, 10, 10);
                equipo.setLayoutParams(layoutEquipo);
                fila.addView(equipo);

                jj = new TextView(this);
                jj.setText("JJ");
                jj.setGravity(Gravity.CENTER);
                jj.setBackgroundColor(Color.BLACK);
                jj.setTextColor(Color.WHITE);
                jj.setPadding(10, 10, 10, 10);
                jj.setLayoutParams(layoutJJ);
                fila.addView(jj);

                jg = new TextView(this);
                jg.setText("JG");
                jg.setGravity(Gravity.CENTER);
                jg.setBackgroundColor(Color.BLACK);
                jg.setTextColor(Color.WHITE);
                jg.setPadding(10, 10, 10, 10);
                jg.setLayoutParams(layoutJG);
                fila.addView(jg);

                je = new TextView(this);
                je.setText("JE");
                je.setGravity(Gravity.CENTER);
                je.setBackgroundColor(Color.BLACK);
                je.setTextColor(Color.WHITE);
                je.setPadding(10, 10, 10, 10);
                je.setLayoutParams(layoutJE);
                fila.addView(je);

                jp = new TextView(this);
                jp.setText("JP");
                jp.setGravity(Gravity.CENTER);
                jp.setBackgroundColor(Color.BLACK);
                jp.setTextColor(Color.WHITE);
                jp.setPadding(10, 10, 10, 10);
                jp.setLayoutParams(layoutJP);
                fila.addView(jp);

                dif = new TextView(this);
                dif.setText("DIF");
                dif.setGravity(Gravity.CENTER);
                dif.setBackgroundColor(Color.BLACK);
                dif.setTextColor(Color.WHITE);
                dif.setPadding(10, 10, 10, 10);
                dif.setLayoutParams(layoutDif);
                fila.addView(dif);

                gf = new TextView(this);
                gf.setText("GF");
                gf.setGravity(Gravity.CENTER);
                gf.setBackgroundColor(Color.BLACK);
                gf.setTextColor(Color.WHITE);
                gf.setPadding(10, 10, 10, 10);
                gf.setLayoutParams(layoutGF);
                fila.addView(gf);

                gc = new TextView(this);
                gc.setText("GC");
                gc.setGravity(Gravity.CENTER);
                gc.setBackgroundColor(Color.BLACK);
                gc.setTextColor(Color.WHITE);
                gc.setPadding(10, 10, 10, 10);
                gc.setLayoutParams(layoutGC);
                fila.addView(gc);

                pts = new TextView(this);
                pts.setText("PTS");
                pts.setGravity(Gravity.CENTER);
                pts.setBackgroundColor(Color.BLACK);
                pts.setTextColor(Color.WHITE);
                pts.setPadding(10, 10, 10, 10);
                pts.setLayoutParams(layoutPts);
                fila.addView(pts);

                tabla.addView(fila);
            } else {
                logo = new ImageView(this);
                logo.setPadding(10, 10, 10, 10);
                logo.setLayoutParams(layoutLogo);
                Picasso.get().load(lstPosiciones.get(i).getLogo()).into(logo);
                fila.addView(logo);

                equipo = new TextView(this);
                equipo.setGravity(Gravity.LEFT);
                equipo.setText(lstPosiciones.get(i).getEquipo());
                equipo.setPadding(10, 10, 10, 10);
                equipo.setLayoutParams(layoutEquipo);
                fila.addView(equipo);

                jj = new TextView(this);
                jj.setGravity(Gravity.CENTER);
                jj.setText(String.valueOf(lstPosiciones.get(i).getJj()));
                jj.setPadding(10, 10, 10, 10);
                jj.setLayoutParams(layoutJJ);
                fila.addView(jj);

                jg = new TextView(this);
                jg.setGravity(Gravity.CENTER);
                jg.setText(String.valueOf(lstPosiciones.get(i).getJg()));
                jg.setPadding(10, 10, 10, 10);
                jg.setLayoutParams(layoutJG);
                fila.addView(jg);

                je = new TextView(this);
                je.setGravity(Gravity.CENTER);
                je.setText(String.valueOf(lstPosiciones.get(i).getJe()));
                je.setPadding(10, 10, 10, 10);
                je.setLayoutParams(layoutJE);
                fila.addView(je);

                jp = new TextView(this);
                jp.setGravity(Gravity.CENTER);
                jp.setText(String.valueOf(lstPosiciones.get(i).getJp()));
                jp.setPadding(10, 10, 10, 10);
                jp.setLayoutParams(layoutJP);
                fila.addView(jp);

                dif = new TextView(this);
                dif.setGravity(Gravity.CENTER);
                dif.setText(String.valueOf(lstPosiciones.get(i).getDif()));
                dif.setPadding(10, 10, 10, 10);
                dif.setLayoutParams(layoutDif);
                fila.addView(dif);

                gf = new TextView(this);
                gf.setGravity(Gravity.CENTER);
                gf.setText(String.valueOf(lstPosiciones.get(i).getGf()));
                gf.setPadding(10, 10, 10, 10);
                gf.setLayoutParams(layoutGF);
                fila.addView(gf);

                gc = new TextView(this);
                gc.setGravity(Gravity.CENTER);
                gc.setText(String.valueOf(lstPosiciones.get(i).getGc()));
                gc.setPadding(10, 10, 10, 10);
                gc.setLayoutParams(layoutGC);
                fila.addView(gc);

                pts = new TextView(this);
                pts.setGravity(Gravity.CENTER);
                pts.setText(String.valueOf(lstPosiciones.get(i).getPts()));
                pts.setPadding(10, 10, 10, 10);
                pts.setLayoutParams(layoutPts);
                fila.addView(pts);

                tabla.addView(fila);
            }
        }
        return true;
    }
}

