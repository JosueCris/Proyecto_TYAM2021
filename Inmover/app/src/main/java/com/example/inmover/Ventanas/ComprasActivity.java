package com.example.inmover.Ventanas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmover.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class ComprasActivity extends Activity {
    public static final String TAG = "MyInmover";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button btnPrincipal = findViewById(R.id.btnPrincipal);
        btnPrincipal.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        });

        RecyclerView rvFotosMostradas = findViewById(R.id.rvFotosMostradas);
        rvFotosMostradas.addItemDecoration (new DividerItemDecoration (this, DividerItemDecoration.VERTICAL));
        rvFotosMostradas.setItemAnimator (new DefaultItemAnimator ());
        rvFotosMostradas.setLayoutManager (new LinearLayoutManager (this, RecyclerView.VERTICAL, false));

        ArrayList<Casa> listCasas = new ArrayList<>();

        CasasBD casasBD = new CasasBD(getApplicationContext());
        infoAdapter infoAdapter = new infoAdapter(casasBD.getAllCasas(), R.layout.lista_info);
        rvFotosMostradas.setAdapter(infoAdapter);


        Log.i(TAG, "onCreate");
    }
}
// Clase modelo de una casa
class Casa implements Serializable {
    public int idCasa;
    public String foto;
    public String inmueble;
    public String lugar;
    public int precio;
    public String tipo;
    //public String descripcion;
}

class infoAdapter extends RecyclerView.Adapter<infoAdapter.ViewHolder> {
    ArrayList<Casa> casaList;
    int resource;

    public infoAdapter(ArrayList<Casa> casaList, int resource) {
        this.casaList = casaList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.lista_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Casa casa = casaList.get(position);
        Log.i("TAG", "Almacenando foto... " + casa.foto);
        /* Nunca se supo la razon por la que picasso nunca nos coloreo la foto, con el lod de arriba
        pudimos corroborar que realmente si le llega la foto a diferencia de la version  firebase
        que habiamos hehco antes */
        Picasso.get()
                .load(casa.foto)
                .fit()
                .centerCrop()
                .into(holder.imgFoto);
        holder.tvwIdCasa.setText(String.valueOf(casa.idCasa));
        holder.tvwInmueble.setText(String.valueOf(casa.inmueble));
        holder.tvwLugar.setText(String.valueOf(casa.lugar));
        holder.tvwPrecio.setText(String.valueOf(casa.precio));
        holder.tvwTipo.setText(String.valueOf(casa.tipo));

    // Se supone que esta parte es para darle un evento de click al recyclerview lo cual si admite pero...
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
            intent.putExtra("itemDetail", casa);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return casaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Componentes de esta clase
        ImageView imgFoto;
        TextView tvwIdCasa, tvwInmueble, tvwLugar, tvwPrecio, tvwTipo;

        // Aqui se hacen las referencias de los componentes de esta clase con los del xml
        public ViewHolder(@NonNull View view) {
            super (view);
            imgFoto = view.findViewById(R.id.imgCasa);
            tvwIdCasa = view.findViewById(R.id.tvwIdCasa);
            tvwInmueble = view.findViewById(R.id.tvwInmueble);
            tvwTipo = view.findViewById(R.id.tvwTipo);
            tvwPrecio = view.findViewById(R.id.tvwPrecio);
            tvwLugar = view.findViewById(R.id.tvwLugar);
        }
    }
}
