package com.example.sportsapps.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sportsapps.Posiciones;
import com.example.sportsapps.R;
import com.example.sportsapps.ResultadosActiviy;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private Button btnResultados, btnPosiciones;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        btnResultados = root.findViewById(R.id.btnResultados);
        btnPosiciones = root.findViewById(R.id.btnPosiciones);
        btnResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), ResultadosActiviy.class);
                String string = getString(R.string.liga_2);
                intento.putExtra("liga", string);
                startActivity(intento);
            }
        });
        btnPosiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getContext(), Posiciones.class);
                String string = getString(R.string.liga_2);
                intento.putExtra("liga", string);
                startActivity(intento);
            }
        });
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}
