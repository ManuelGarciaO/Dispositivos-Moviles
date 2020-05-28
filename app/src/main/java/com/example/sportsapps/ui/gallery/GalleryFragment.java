package com.example.sportsapps.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sportsapps.Noticias;
import com.example.sportsapps.Posiciones;
import com.example.sportsapps.R;
import com.example.sportsapps.Resultado;
import com.example.sportsapps.ResultadosActiviy;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private Button btnResultados, btnPosiciones;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        btnResultados = root.findViewById(R.id.btnResultados);
        btnPosiciones = root.findViewById(R.id.btnPosiciones);
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), ResultadosActiviy.class);
                String string = getString(R.string.liga_1);
                intento.putExtra("liga", string);
                startActivity(intento);
            }
        });
        btnPosiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), Posiciones.class);
                String string = getString(R.string.liga_1);
                intento.putExtra("liga", string);
                startActivity(intento);
            }
        });
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        return root;
    }
}
