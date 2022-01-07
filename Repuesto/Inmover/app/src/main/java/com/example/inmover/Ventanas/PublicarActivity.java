package com.example.inmover.Ventanas;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PublicarActivity extends Activity {
    public static final int PHOTO = 1;
    Spinner spInmueble2, spTipo2, spLugar2;
    EditText edtPrecio2;
    ImageView img1, img2, img3, img4, img5, img6;
    Uri uri;
    StorageReference storage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_activity);

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


        Button btnPrincipal2 = findViewById (R.id.btnPrincipal2);
        btnPrincipal2.setOnClickListener (view -> {
            Intent intent = new Intent (getBaseContext(), MainActivity.class);
            startActivity (intent);
        });

        img1 = findViewById(R.id.img1);
        img1.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO);
        });

        img2 = findViewById(R.id.img1);
        img2.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO);
        });

        img3 = findViewById(R.id.img1);
        img3.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO);
        });

        img4 = findViewById(R.id.img1);
        img4.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO);
        });

        img5 = findViewById(R.id.img1);
        img5.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO);
        });

        img6 = findViewById(R.id.img1);
        img6.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO);
        });


        Button btnSubirFoto = findViewById(R.id.btnSubirFoto);
        btnSubirFoto.setOnClickListener(view -> {
            if(uri == null) {
                Toast.makeText(PublicarActivity.this, "Selecciona una imagen", Toast.LENGTH_SHORT).show();
            }
            else {
                subirFotoBD(uri);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "inicioCondicion");
        if(requestCode==PHOTO && resultCode==RESULT_OK && data!=null) {
            uri = data.getData();
            img1.setImageURI(uri);
            img2.setImageURI(uri);
            img3.setImageURI(uri);
            img4.setImageURI(uri);
            img5.setImageURI(uri);
            img6.setImageURI(uri);
//            StorageReference filePath = storage.child(uri.getLastPathSegment());
//            filePath.putFile(uri).addOnSuccessListener(taskSnapshot ->
//                    Toast.makeText(PublicarActivity.this, "La foto se subio exitosamente", Toast.LENGTH_SHORT).show()
//            );
        }
        Log.d("TAG", "finCondicion");
    }

        private void addHouse() {
        Casa casa = new Casa();
        StorageReference storage;

        casa.inmueble = spInmueble2.getSelectedItem().toString();
        casa.tipo = spTipo2.getSelectedItem().toString();
        casa.precio = Integer.parseInt(String.valueOf(edtPrecio2.getText()));
        casa.lugar = spLugar2.getSelectedItem().toString();

        storage = FirebaseStorage.getInstance().getReference();

    }

    public void subirFotoBD(Uri uri) {
        StorageReference filePath = storage.child(System.currentTimeMillis()+ "."+getFileExtention(uri));
        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PublicarActivity.this, "Subiendo directorio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtention(Uri myUri) {
        ContentResolver content = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(content.getType(myUri));
    }


//    private Bitmap getBitmapFromDrawable (Drawable drble) {
//        if (drble instanceof BitmapDrawable) {
//            return  ((BitmapDrawable) drble).getBitmap ();
//        }
//        Bitmap bitmap = Bitmap.createBitmap (drble.getIntrinsicWidth (), drble.getIntrinsicHeight (), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        drble.setBounds (0, 0, canvas.getWidth (), canvas.getHeight ());
//        drble.draw (canvas);
//
//        return bitmap;
//    }
}