package com.example.inmover.Ventanas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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

        FirebaseDatabase database = FirebaseDatabase.getInstance ();
        DatabaseReference casas = database.getReference ("Casas");

        casas.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Log.e(TAG, "cicloInicio");
                    listCasas.clear();
                    for (DataSnapshot item: dataSnapshot.getChildren ()) {
                        Log.d (TAG, item.getKey() + " - " +  item.getValue ().toString());
                        Casa c = item.getValue (Casa.class);
                        listCasas.add (c);
                    }
                    Log.e(TAG, "cicloFin");
                    infoAdapter iAdapter = new infoAdapter(listCasas, R.layout.lista_info);
                    rvFotosMostradas.setAdapter (iAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, error.getMessage());
            }
        });
        Log.i(TAG, "onCreate");
    }
}
// Clase modelo de una casa
class Casa {
    public String foto;
    public String inmueble;
    public String lugar;
    public int precio;
    public String tipo;
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
        Log.i("TAG", "¿Llegas o que hdtptm foto? " +casa.foto);
        Picasso.get()
                .load(casa.foto)
                //.fit()
               // .centerCrop()
                .into(holder.imgFoto);
        //Glide.with(activity).load(casa.foto).into(holder.imgFoto);
        Log.i("TAG", "¿Llegas o que hdtptm inmueble? " +casa.inmueble);
        holder.tvwInmueble.setText(String.valueOf(casa.inmueble));
        Log.i("TAG", "¿Llegas o que hdtptm lugar? " +casa.inmueble);
        holder.tvwLugar.setText(String.valueOf(casa.lugar));
        Log.i("TAG", "¿Llegas o que hdtptm precio? " +casa.inmueble);
        holder.tvwPrecio.setText(String.valueOf(casa.precio));
        Log.i("TAG", "¿Llegas o que hdtptm tipo? " +casa.inmueble);
        holder.tvwTipo.setText(String.valueOf(casa.tipo));
    }

    @Override
    public int getItemCount() {
        return casaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView tvwInmueble, tvwLugar, tvwPrecio, tvwTipo;

        public ViewHolder(@NonNull View view) {
            super (view);
            imgFoto = view.findViewById(R.id.imgCasa);
            tvwInmueble = view.findViewById(R.id.tvwInmueble);
            tvwTipo = view.findViewById(R.id.tvwTipo);
            tvwPrecio = view.findViewById(R.id.tvwPrecio);
            tvwLugar = view.findViewById(R.id.tvwLugar);
        }
    }
}
