package com.example.sportsapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultadosInfoActivity extends AppCompatActivity {

    private TextView equipo1,equipo2,goles1,goles2,faltas1,faltas2, amar1,amar2,rojas1,rojas2,fdl1,fdl2,esquinas1,esquinas2,salvadas1,salvadas2,pct1,pxt2,tiros1,tiros2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_info);
        equipo1 = findViewById(R.id.txtEquipo1);
        equipo2 = findViewById(R.id.txtEquipo2);
        goles1 = findViewById(R.id.txtGoles1);
        goles2 = findViewById(R.id.txtGoles2);
        faltas1 = findViewById(R.id.txtFaltas1);
        faltas2 = findViewById(R.id.txtFaltas2);
        amar1= findViewById(R.id.txtAma1);
        amar2= findViewById(R.id.txtAma2);
        rojas1= findViewById(R.id.txtRojas1);
        rojas2= findViewById(R.id.txtRojas2);
        fdl1 = findViewById(R.id.txtfdlugar1);
        fdl2 = findViewById(R.id.txtfdlugar2);
        esquinas1 = findViewById(R.id.txtesquinas1);
        esquinas2= findViewById(R.id.txtesquinas2);
        salvadas1= findViewById(R.id.txtsalvadas1);
        salvadas2= findViewById(R.id.txtsalvadas2);
        pct1= findViewById(R.id.txtpct1);
        pxt2= findViewById(R.id.txtpct2);
        tiros1= findViewById(R.id.txtTiros1);
        tiros2= findViewById(R.id.txtTiros2);
        equipo1.setText(getIntent().getStringExtra("equipo1"));
        equipo2.setText(getIntent().getStringExtra("equipo2"));
        goles1.setText(getIntent().getStringExtra("goles1"));
        goles2.setText(getIntent().getStringExtra("goles2"));
        faltas1.setText(getIntent().getStringExtra("faltas1"));
        faltas2.setText(getIntent().getStringExtra("faltas2"));
        amar1.setText(getIntent().getStringExtra("amarillas1"));
        amar2.setText(getIntent().getStringExtra("amarillas2"));
        rojas1.setText(getIntent().getStringExtra("rojas1"));
        rojas2.setText(getIntent().getStringExtra("rojas2"));
        fdl1.setText(getIntent().getStringExtra("fdLugar1"));
        fdl2.setText(getIntent().getStringExtra("fdLugar2"));
        esquinas1.setText(getIntent().getStringExtra("esquinas1"));
        esquinas2.setText(getIntent().getStringExtra("esquinas2"));
        salvadas1.setText(getIntent().getStringExtra("salvadas1"));
        salvadas2.setText(getIntent().getStringExtra("salvadas2"));
        pct1.setText(getIntent().getStringExtra("pct1"));
        pxt2.setText(getIntent().getStringExtra("pct2"));
        tiros1.setText(getIntent().getStringExtra("tiros1"));
        tiros2.setText(getIntent().getStringExtra("tiros2"));
    }
}
