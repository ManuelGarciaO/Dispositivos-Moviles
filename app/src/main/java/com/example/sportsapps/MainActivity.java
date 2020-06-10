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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener{
    private EditText email, pass;
    private Button Login;
    private String Sname, Semail, Spass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.correo);
        pass = findViewById(R.id.pass);
        Login = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        /*if(FirebaseAuth.getInstance().getCurrentUser() != null){
            FirebaseAuth.getInstance().getCurrentUser().getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                @Override
                public void onSuccess(GetTokenResult getTokenResult) {

                }
            });
        }*/

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
    }

    private void loginUser(){

        mAuth.signInWithEmailAndPassword(Semail, Spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intento = new Intent(MainActivity.this, Principal.class);
                    startActivity(intento);
                } else Toast.makeText(MainActivity.this, "No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goRegister(View v){
        Intent intento = new Intent(this, Register.class);
        startActivity(intento);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.getCurrentUser().getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                @Override
                public void onSuccess(GetTokenResult getTokenResult) {
                    Intent intento = new Intent(MainActivity.this, Principal.class);
                    startActivity(intento); 
                }
            });
        }
    }
}
