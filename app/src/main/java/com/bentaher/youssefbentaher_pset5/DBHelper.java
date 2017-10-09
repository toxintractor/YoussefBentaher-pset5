package com.bentaher.youssefbentaher_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by mocro on 25/09/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ToDoD";
    public static  String TABLE_NAME = "ToDoTabel";
    public static  String TABLE_LIST = "ToDoList";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "id";
    public static final String TAAK_ID = "taakid";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String IS_CHECKED = "checked";

    public static DBHelper sinstance;

    public static synchronized DBHelper getInstance(Context context){
        if(sinstance == null){
            sinstance = new DBHelper(context.getApplicationContext());
        }
        return sinstance;
    }


    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "create table " + TABLE_NAME +" " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,description TEXT,checked TEXT)";

        String createLST = "create table " + TABLE_LIST +" " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,taakid TEXT,name TEXT,description TEXT,checked TEXT)";

        db.execSQL(createDB);
        db.execSQL(createLST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgradeDB = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String upgradeLST = "DROP TABLE IF EXISTS " + TABLE_LIST;

        db.execSQL(upgradeDB);
        db.execSQL(upgradeLST);
        onCreate(db);
    }

    /*
    public boolean insertData(Taak taak){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, taak.getTaak());
        contentValues.put(KEY_DESCRIPTION, taak.getBeschrijving());
        contentValues.put(IS_CHECKED, taak.getIschecked());
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }
    }

    public ArrayList<Taak> getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Taak> taken = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                String taaknaam = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String beschrijving = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
                String ischecked = cursor.getString(cursor.getColumnIndex(IS_CHECKED));
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Taak taak = new Taak(taaknaam, beschrijving, ischecked, id);
                taken.add(taak);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return taken;
    }

    public int update(Taak taak){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, taak.getTaak());
        contentValues.put(KEY_DESCRIPTION, taak.getBeschrijving());
        contentValues.put(IS_CHECKED, taak.getIschecked());
        return db.update(TABLE_NAME, contentValues, KEY_ID + " =  ? ", new String[] {String.valueOf(taak.getID())});

    }

    public void delete(Taak taak){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, " " + KEY_ID + " =  ? ", new String[] {String.valueOf(taak.getID())});
        db.close();
    }
    */
}
