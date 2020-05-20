package com.example.sportsapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticiasInfoActivity extends AppCompatActivity {
    private TextView titulotxt, descripciontxt;
    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_info);
        titulotxt = findViewById(R.id.txtTitulo);
        descripciontxt = findViewById(R.id.txtDescripcion);
        imagen = findViewById(R.id.imageView2);
        titulotxt.setText(getIntent().getStringExtra("Titulo"));
        descripciontxt.setText(getIntent().getStringExtra("Descripcion"));
    }
}
