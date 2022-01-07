package com.example.inmover.Ventanas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.inmover.R;

public class BuscadosActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscados);

        Button btnPrincipal3 = findViewById (R.id.btnPrincipal);
        btnPrincipal3.setOnClickListener (view -> {
            Intent intent = new Intent (getBaseContext(), MainActivity.class);
            startActivity (intent);
        });

    }
}