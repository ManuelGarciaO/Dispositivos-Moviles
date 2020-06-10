package com.example.sportsapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Register extends AppCompatActivity {
    private EditText name, email, pass;
    private Button register;
    private String nombre, correoE, contra, id;

    Map<String, Object> map;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.nombre);
        email = findViewById(R.id.correo);
        pass = findViewById(R.id.password);
        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = name.getText().toString();
                correoE = email.getText().toString();
                contra = pass.getText().toString();

                if(!nombre.isEmpty() && !correoE.isEmpty() && !contra.isEmpty()){
                    if(contra.length()>=6)  registerUser();
                    else Toast.makeText(Register.this, "La contraseña debe de ser de al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(Register.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(correoE, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    map = new HashMap<>();
                    map.put("name", nombre);
                    map.put("email", correoE);
                    map.put("password", contra);

                    id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Intent intento = new Intent(Register.this, SeleccionEquipoFavorito.class);
                                intento.putExtra("Id",id);
                                intento.putExtra("name",nombre);
                                intento.putExtra("email",correoE);
                                intento.putExtra("password",contra);
                                finish();
                                startActivity(intento);
                            } else Toast.makeText(Register.this, "Los datos no pudieron subirse a la base de datos", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else Toast.makeText(Register.this, "El usuario no se pudo registrar correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
