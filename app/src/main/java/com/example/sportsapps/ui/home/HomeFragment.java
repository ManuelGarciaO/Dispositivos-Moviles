package com.example.sportsapps.ui.home;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sportsapps.MainActivity;
import com.example.sportsapps.Noticias;
import com.example.sportsapps.Partido;
import com.example.sportsapps.R;
import com.example.sportsapps.ResultadosActiviy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment implements  Handler.Callback{

    private HomeViewModel homeViewModel;
    private Button btnNoticias;
    private Handler dataHandler;
    private String fechaActual;
    private Calendar mCurrentDate;
    private int day, month, year;
    String team="team";
    ArrayList<String> equipos;
    boolean juegaHoy=false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        equipos=new ArrayList<>();
        dataHandler = new Handler(Looper.getMainLooper(), this);
        mCurrentDate =Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        month =  month+1;
        fechaActual=day+"/"+month+"/"+year;


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        btnNoticias = root.findViewById(R.id.btnNoticias);
        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), Noticias.class);
                startActivity(intento);
            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        Request r = new Request("https://manuel19299.github.io/SportsApp/data/partidos.json", dataHandler);
        r.start();

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference();

        dr.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    final String favoriteTeam;
                    favoriteTeam = dataSnapshot.child("favoriteTeam").getValue().toString();
                    Log.wtf("Equipo", favoriteTeam);
                    Log.wtf("Equipo", team);
                    for (int i = 0; i < equipos.size(); i++){
                        if(equipos.get(i).equals(favoriteTeam)){
                            juegaHoy=true;
                        }
                    }
                    if(juegaHoy){
                        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                        String id = "my_channel_01";
                        CharSequence name = "my_channel_01";
                        String description = "my_channel_01_description";
                        int importance = NotificationManager.IMPORTANCE_LOW;
                        NotificationChannel mChannel = new NotificationChannel(id, name,importance);
                        mChannel.setDescription(description);
                        mChannel.enableLights(true);
                        mChannel.setLightColor(Color.RED);
                        mChannel.enableVibration(true);
                        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                        mNotificationManager.createNotificationChannel(mChannel);
                        mNotificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                        int notifyID = 1;
                        String CHANNEL_ID = "my_channel_01";
                        String message ="Tú equipo favorito jugará el día de hoy";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                                getContext()
                        ).
                                setSmallIcon(R.drawable.soccer)
                                .setContentTitle(favoriteTeam+" juega hoy")
                                .setContentText(message)
                                .setAutoCancel(true)
                                .setChannelId(CHANNEL_ID);
                        NotificationManager notificationManager =(NotificationManager)getActivity().getSystemService(
                                Context.NOTIFICATION_SERVICE
                        );
                        notificationManager.notify(0,builder.build());
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;
    }
    @Override
    public boolean handleMessage(@NonNull Message msg) {

        String equipoL, equipoV;
        JSONArray datos = (JSONArray) msg.obj;
        try {
            for(int i = 0; i < datos.length(); i++){
                JSONObject actual = datos.getJSONObject(i);
                    //Obtener resultados de Liga
                    JSONArray datosLiga = actual.getJSONArray("partidos");
                    for(int j = 0; j < datosLiga.length(); j++){
                        JSONObject actualLiga = datosLiga.getJSONObject(j);
                        //Validar fecha
                        if(actualLiga.getString("fecha").equals(fechaActual)){
                            //Obtener resultados de fecha
                            JSONArray datosFecha = actualLiga.getJSONArray("partidos");
                            for (int k = 0; k < datosFecha.length(); k++){
                                JSONObject actualPartido = datosFecha.getJSONObject(k);
                                equipoL=actualPartido.getString("equipoLocal");
                                equipoV=actualPartido.getString("equipoVisitante");
                                equipos.add(equipoL);
                                equipos.add(equipoV);
                            }
                        }
                    }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
