package com.example.inmover.Ventanas;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.inmover.R;

public class DetailsActivity extends Activity {
    public static final String TAG = "MyInmover";
    ImageView imgFoto;
    TextView tvwIdCasa, tvwInmueble, tvwLugar, tvwPrecio, tvwTipo, tvwDescripcion;
    Casa itemDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_details);

        initViews();
        initValues();

        Log.i(TAG, "OnCreate");
    }

    private void initViews() {
        imgFoto = findViewById(R.id.imgCasaDH);
        tvwIdCasa = findViewById(R.id.tvwIdCasaDH);
        tvwInmueble = findViewById(R.id.tvwInmuebleDH);
        tvwLugar = findViewById(R.id.tvwLugarDH);
        tvwPrecio = findViewById(R.id.tvwPrecio);
        tvwTipo = findViewById(R.id.tvwTipoDH);
        //tvwDescripcion = findViewById(R.id.tvwDescripcionDH);
    }

    private void initValues() {
        /* Aqui por alguna razon hay un problema con el que se me detiene la aplicacion despues de
         dar click al recyclerview, deberia vizualizarnos la informacion en otra ventana pero no*/
        itemDetail = (Casa) getIntent().getExtras().getSerializable("itemDetail");
        //imgFoto.setImageResource(Integer.parseInt(itemDetail.foto));
        tvwIdCasa.setText(itemDetail.idCasa);
        tvwInmueble.setText(itemDetail.inmueble);
        tvwLugar.setText(itemDetail.lugar);
        tvwPrecio.setText(itemDetail.precio);
        tvwTipo.setText(itemDetail.tipo);
        //tvwDescripcion.setText(itemDetail.descripcion);
    }
}
