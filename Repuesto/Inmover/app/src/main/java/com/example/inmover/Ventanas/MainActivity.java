package com.example.inmover.Ventanas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.inmover.R;

public class MainActivity extends Activity {
    public static final String TAG = "Inmover";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);

        Button btnVender = findViewById (R.id.btnVender);
        btnVender.setOnClickListener (view -> {
            Intent intent = new Intent (getBaseContext(), ComprasActivity.class);
            startActivity (intent);
        });

        Button btnFiltro = findViewById (R.id.btnFiltro);
        btnFiltro.setOnClickListener (view -> {
            Intent intent = new Intent (getBaseContext(), FiltrosActivity.class);
            startActivity (intent);
        });

        Button btnPublicar = findViewById (R.id.btnPublicar);
        btnPublicar.setOnClickListener (view -> {
            Intent intent = new Intent (getBaseContext(), PublicarActivity.class);
            startActivity (intent);
        });

        Log.i(TAG, "OnCreate");
    }

}