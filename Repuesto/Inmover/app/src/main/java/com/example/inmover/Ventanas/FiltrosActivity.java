package com.example.inmover.Ventanas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.inmover.R;

public class FiltrosActivity extends Activity {
    Button btnBuscar;
    Spinner spInmueble, spTipo, spPrecio, spLugar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        // Aqui cargamos la lista de inmuebles al spinner que lo referenciamos con una variable de esta clase
        spInmueble = findViewById(R.id.spInmueble);
        ArrayAdapter<CharSequence> adapterInmueble = ArrayAdapter.createFromResource(this, R.array.combo_inmuebles, android.R.layout.simple_spinner_item);
        spInmueble.setAdapter(adapterInmueble);
        spInmueble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Opciones...")) {
                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(), "Selected: "+item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spTipo = findViewById(R.id.spTipo);
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this, R.array.combo_tipo, android.R.layout.simple_spinner_item);
        spTipo.setAdapter(adapterTipo);
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Opciones...")) {
                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(), "Selected: "+item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Aqui cargamos la lista de precios al spinner que lo referenciamos con una variable de esta clase
        spPrecio = findViewById(R.id.spPrecio);
        ArrayAdapter<CharSequence> adapterPrecio = ArrayAdapter.createFromResource(this, R.array.combo_precio, android.R.layout.simple_spinner_item);
        spPrecio.setAdapter(adapterPrecio);
        spPrecio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Opciones...")) {
                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(), "Selected: "+item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Aqui cargamos la lista de lugares al spinner que lo referenciamos con una variable de esta clase
        spLugar = findViewById(R.id.spLugar);
        ArrayAdapter<CharSequence> adapterLugar = ArrayAdapter.createFromResource(this, R.array.combo_lugar, android.R.layout.simple_spinner_item);
        spLugar.setAdapter(adapterLugar);
        spLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Opciones...")) {
                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(), "Selected: "+item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // Esto es para referenciar mi variable con el boton del xml y por medio de la lambda implementamos que accion realizara
        btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), BuscadosActivity.class);
            startActivity(intent);
        });
    }
}
