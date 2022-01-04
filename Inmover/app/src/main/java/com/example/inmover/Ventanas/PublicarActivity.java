package com.example.inmover.Ventanas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.inmover.R;

public class PublicarActivity extends Activity {
    public static final int PHOTO = 1;
    public static final int REQUEST_CODE_CAMERA = 2;
    public static final int REQUEST_CODE_READ_STORAGE = 3;
    Spinner spInmueble2, spTipo2, spLugar2;
    EditText edtIdCasa, edtPrecio2;
    //ImageView img1, img2, img3, img4, img5, img6;
    Uri uri;
    CasasBD casasBD;
    int permission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);

        Button btnPrincipal2 = findViewById (R.id.btnPrincipal2);
        btnPrincipal2.setOnClickListener (view -> {
            Intent intent = new Intent (getBaseContext(), MainActivity.class);
            startActivity (intent);
        });

        edtIdCasa = findViewById(R.id.edtIdCasa);


        // Aqui cargamos la lista de inmuebles al spinner que lo referenciamos con una variable de esta clase
        spInmueble2 = findViewById(R.id.spInmueble2);
        ArrayAdapter<CharSequence> adapterInmueble = ArrayAdapter.createFromResource(this, R.array.combo_inmuebles, android.R.layout.simple_spinner_item);
        spInmueble2.setAdapter(adapterInmueble);
        spInmueble2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spTipo2 = findViewById(R.id.spTipo2);
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(this, R.array.combo_tipo, android.R.layout.simple_spinner_item);
        spTipo2.setAdapter(adapterTipo);
        spTipo2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        edtPrecio2 = findViewById(R.id.edtPrecio);

        spLugar2 = findViewById(R.id.spLugar2);
        ArrayAdapter<CharSequence> adapterLugar = ArrayAdapter.createFromResource(this, R.array.combo_lugar, android.R.layout.simple_spinner_item);
        spLugar2.setAdapter(adapterLugar);
        spLugar2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    /* En esta parte instanciamos la clase donde creamos nuestra BD y en la lambda de btnPublicar le
       pasamos los datos que se guardaran en la tabla */
        casasBD = new CasasBD(this);
//        Button btnPublicar = findViewById(R.id.btnPublicar);
//        btnPublicar.setOnClickListener(view -> {
//           // casasBD.addCasa(edtIdCasa.getText().toString(), , spInmueble2.getSelectedItem().toString(), spTipo2.getSelectedItem().toString(), edtPrecio2.getText().toString(), spLugar2.getSelectedItem().toString());
//        });

    // En este boton subiremos las fotos en sus respectiov ImageView
        Button btnSubirFoto = findViewById(R.id.btnSubirFoto);
        btnSubirFoto.setOnClickListener(view -> {
            permission = checkSelfPermission((Manifest.permission.READ_EXTERNAL_STORAGE));
            if (permission != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_READ_STORAGE);
                return;
            }else {
                selectImage();
            }
        });
    }
// Este metodo es para poder subir una foto mediante la camara pero aun no lo manejamos con un boton,
    public void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_CAMERA);
        }
    }
// En este si lo manejamos con un boton y es para seleccionar una foto de la galeria directamente
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(
                "content://media/internal/images/media"
        ));
        startActivityForResult(intent, PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== PHOTO && resultCode==RESULT_OK) {
            Uri uri = data.getData();
            String path = getPath(uri);
            int id = Integer.parseInt(edtIdCasa.getText().toString());
            String inmueble = spInmueble2.getSelectedItem().toString();
            String tipo = spTipo2.getSelectedItem().toString();
            int precio = Integer.parseInt(edtPrecio2.getText().toString());
            String lugar = spLugar2.getSelectedItem().toString();

            Log.i("TAG", "Inicio del if ");
            if (casasBD.addCasa(id, path, inmueble, tipo, precio, lugar))
                Toast.makeText(getBaseContext(), "Successfull", Toast.LENGTH_SHORT).show();
            else {
                Log.i("TAG", "No se agrega nada");
                Toast.makeText(getBaseContext(), "Not Successfull", Toast.LENGTH_SHORT).show();
            }
            Log.i("TAG", "Fin del if ");
//            Toast.makeText(PublicarActivity.this, path, Toast.LENGTH_SHORT).show();
        }
    }
// Con este metodo obtenemos la direccion de nuestra foto, es decir la ruta
    public String getPath(Uri uri) {
        if (uri == null)
            return null;
        String [] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(columnIndex);
        }
        return uri.getPath();
    }
// Esto es para qure nos habilite el cuadro de texto de los permisos, en este caso alcanzamos el de la camara y galeria
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0){
            if (requestCode == REQUEST_CODE_CAMERA){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    takePhoto();
                else
                    Toast.makeText(PublicarActivity.this, "Se necesita usar la camara para tomar una foto instantanea", Toast.LENGTH_LONG).show();

            }
            if (requestCode == REQUEST_CODE_READ_STORAGE){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    selectImage();
                else
                    Toast.makeText(PublicarActivity.this, "Se necesita leer el almacenamiento para elegir una imagen existente", Toast.LENGTH_LONG).show();
            }
        }
    }

/*
    private Bitmap getBitmapFromDrawable (Drawable drble) {
        if (drble instanceof BitmapDrawable) {
            return  ((BitmapDrawable) drble).getBitmap ();
        }
        Bitmap bitmap = Bitmap.createBitmap (drble.getIntrinsicWidth (), drble.getIntrinsicHeight (), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drble.setBounds (0, 0, canvas.getWidth (), canvas.getHeight ());
        drble.draw (canvas);

        return bitmap;
    }
*/
}