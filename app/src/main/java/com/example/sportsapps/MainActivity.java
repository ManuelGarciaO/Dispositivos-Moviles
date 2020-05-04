package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText email, pass;
    private Button Login;
    private final String ARCHIVO = "Login";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String Sname, Semail, Spass;
    //private Map<String, ?> preferences;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.correo);
        pass = findViewById(R.id.pass);
        Login = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Semail = email.getText().toString();
                Spass = pass.getText().toString();

                if(!Semail.isEmpty() && !Spass.isEmpty()){
                    loginUser();
                } else Toast.makeText(MainActivity.this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
        //cargarPrefs();
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(Semail, Spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intento = new Intent(MainActivity.this, Menu.class);
                    startActivity(intento);
                } else Toast.makeText(MainActivity.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void goMenu(View v){
        boolean b = false;
        for(Map.Entry<String, ?> entry : preferences.entrySet()) {
            if(entry.getKey().equals("name")) Sname = prefs.getString("name", "");
            if(entry.getKey().equals("email")){
                if(email.getText().toString().equals(Semail)) {
                    Semail = prefs.getString("email", "");
                    b = true;
                }
            }
            if(b) Spass = prefs.getString("password", "");
        }*
        //if(!b) Toast.makeText(this, "EL CORREO NO EXISTE", Toast.LENGTH_SHORT).show();
        if(email.getText().toString().equals(Semail) && pass.getText().toString().equals(Spass)) {
            Toast.makeText(this, "INGRESO CORRECTO", Toast.LENGTH_SHORT).show();
            Intent intento = new Intent(this, Menu.class);
            startActivity(intento);
        } else Toast.makeText(this,"CORREO Y/O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();
    }*/

    public void goRegister(View v){
        Intent intento = new Intent(this, Register.class);
        startActivity(intento);
    }

    /*public void cargarPrefs(){
        prefs = getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE);
        editor = prefs.edit();
        //preferences = prefs.getAll();
        Sname = prefs.getString("name","");
        Semail = prefs.getString("email","");
        Spass = prefs.getString("password","");
    }*/
}
