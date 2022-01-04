package com.example.inmover.Ventanas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CasasBD extends SQLiteOpenHelper {
    public static final String TAG = "MyInmover";
    private static final String DATABASE = "Inmover.bd";
    private static final int VERSION = 1;
    private static final String TABLA_CASAS = "Casas";

    private static final String KEY_ID = "idCasa";
    private static final String KEY_FOTO = "Foto";
    private static final String KEY_INMUEBLE = "Inmueble";
    private static final String KEY_TIPO = "Tipo";
    private static final String KEY_PRECIO = "Precio";
    private static final String KEY_LUGAR = "Lugar";

    private static String CREAR_TABLA_CASAS = "CREATE TABLE "+TABLA_CASAS+ "("+
            KEY_ID+ " INTEGER PRIMARY KEY, " +
            KEY_FOTO+" BLOB NOT NULL, "+
            KEY_INMUEBLE+" TEXT, "+
            KEY_TIPO+" TEXT, "+
            KEY_PRECIO+" INTEGER, "+
            KEY_LUGAR+" TEXT)";

    private static String DROP_CASAS_TABLE = "DROP TABLE IF EXISTS " + TABLA_CASAS;

    public CasasBD(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (CREAR_TABLA_CASAS);
        Log.d (TAG, "Creating Database...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CASAS_TABLE);
        onCreate (db);
        Log.d (TAG, "Upgrading database...");
    }

    // No tocar nada ya funciona DEJALO!!!
    public boolean addCasa(int idCasa, String foto, String inmueble, String tipo, int precio, String lugar) {
        try (SQLiteDatabase database = this.getWritableDatabase()) {
            try {
                FileInputStream fis = new FileInputStream(foto);
                byte [] photobyte = new byte[fis.available()];
                fis.read(photobyte);
                ContentValues content = new ContentValues();
                content.put(KEY_ID, idCasa);
                content.put(KEY_FOTO, photobyte);
                content.put(KEY_INMUEBLE, inmueble);
                content.put(KEY_TIPO, tipo);
                content.put(KEY_PRECIO, precio);
                content.put(KEY_LUGAR, lugar);
                database.insert(TABLA_CASAS, null, content);
                fis.close();
                database.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        }
    }

    public ArrayList<Casa> getAllCasas () {
        ArrayList<Casa> casaList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLA_CASAS;

        SQLiteDatabase database = this.getReadableDatabase ();
        Cursor cursor = database.rawQuery (query, null);

        if (cursor != null && cursor.moveToFirst ()) {
            do {
                Casa casa = new Casa ();
                casa.idCasa = cursor.getInt (0);
                casa.foto = String.valueOf(cursor.getBlob (1));
                casa.inmueble = cursor.getString (2);
                casa.tipo = cursor.getString (3);
                casa.precio = cursor.getInt (4);
                casa.lugar = cursor.getString (5);

                casaList.add (casa);
            } while (cursor.moveToNext ());
            cursor.close ();
        }
        database.close ();
        return casaList;
    }

    public int countCasas () {
        String countQuery = "SELECT * FROM " + TABLA_CASAS;

        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (countQuery, null);
        int count = cursor.getCount ();

        cursor.close ();
        db.close ();

        return count;
    }
// De esta linea para abajo aun no se adapta a la aplicacion
    public int updateContact (Casa casa) {
        SQLiteDatabase db = this.getWritableDatabase ();

        ContentValues values = new ContentValues ();
        values.put (KEY_ID, casa.idCasa);
        values.put (KEY_FOTO, casa.foto);
        values.put (KEY_INMUEBLE, casa.inmueble);
        values.put (KEY_TIPO, casa.tipo);
        values.put (KEY_PRECIO, casa.precio);
        values.put (KEY_LUGAR, casa.lugar);

        String whereClause = KEY_ID+ " = ?";
        String [] whereArgs = {String.valueOf (casa.idCasa)};

        int result = db.update (TABLA_CASAS, values, whereClause, whereArgs);

        db.close ();
        return result;
    }

    public void deleteContact (Casa casa) {
        SQLiteDatabase db = this.getWritableDatabase ();

        String whereClause = KEY_ID + " = ?";
        String [] whereArgs = {String.valueOf (casa.idCasa)};
        db.delete (TABLA_CASAS, whereClause, whereArgs);
        db.close ();
    }

}
