package com.bentaher.youssefbentaher_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by mocro on 07/10/2017.
 */

public class TaakManager {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public TaakManager(Context c){
        context = c;
    }

    public TaakManager open() throws SQLException{
        dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public boolean insertData(Taak taak){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_NAME, taak.getTaak());
        contentValues.put(DBHelper.KEY_DESCRIPTION, taak.getBeschrijving());
        contentValues.put(DBHelper.IS_CHECKED, taak.getIschecked());
        long result = db.insert(DBHelper.TABLE_NAME, null, contentValues);
        db.close();

        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }
    }

    public ArrayList<Taak> getAllData(){
        open();
        ArrayList<Taak> taken = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                String taaknaam = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME));
                String beschrijving = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION));
                String ischecked = cursor.getString(cursor.getColumnIndex(DBHelper.IS_CHECKED));
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
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
        open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME, taak.getTaak());
        contentValues.put(DBHelper.KEY_DESCRIPTION, taak.getBeschrijving());
        contentValues.put(DBHelper.IS_CHECKED, taak.getIschecked());
        return db.update(DBHelper.TABLE_NAME, contentValues, DBHelper.KEY_ID + " =  ? ", new String[] {String.valueOf(taak.getID())});

    }

    public void delete(Taak taak){
        open();
        db.delete(DBHelper.TABLE_NAME, " " + DBHelper.KEY_ID + " =  ? ", new String[] {String.valueOf(taak.getID())});
        db.delete(DBHelper.TABLE_LIST, " " + DBHelper.TAAK_ID + " =  ? ", new String[] {String.valueOf(taak.getID())});
        db.close();
    }

    public boolean insertDataList(Lijst lijst){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TAAK_ID, lijst.getTaakID());
        contentValues.put(DBHelper.KEY_NAME, lijst.getLijst());
        contentValues.put(DBHelper.KEY_DESCRIPTION, lijst.getBeschrijving());
        contentValues.put(DBHelper.IS_CHECKED, lijst.getIschecked());
        long result = db.insert(DBHelper.TABLE_LIST, null, contentValues);
        db.close();

        if(result == -1){
            return  false;
        }
        else{
            return  true;
        }
    }

    public ArrayList<Lijst> getAllDataList(int checkid){
        open();
        ArrayList<Lijst> lijsten = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_LIST  + " where " + DBHelper.TAAK_ID + " = "
                + checkid, null);

        if(cursor.moveToFirst()){
            do{
                int taakid = cursor.getInt(cursor.getColumnIndex(DBHelper.TAAK_ID));
                String taaknaam = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME));
                String beschrijving = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION));
                String ischecked = cursor.getString(cursor.getColumnIndex(DBHelper.IS_CHECKED));
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
                Lijst lijst = new Lijst(taakid, taaknaam, beschrijving, ischecked, id);
                lijsten.add(lijst);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijsten;
    }

    public int updateList(Lijst lijst){
        open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.KEY_NAME, lijst.getLijst());
        contentValues.put(DBHelper.KEY_DESCRIPTION, lijst.getBeschrijving());
        contentValues.put(DBHelper.IS_CHECKED, lijst.getIschecked());
        return db.update(DBHelper.TABLE_LIST, contentValues, DBHelper.KEY_ID + " =  ? ", new String[] {String.valueOf(lijst.getID())});

    }

    public void deleteList(Lijst lijst){
        open();
        db.delete(DBHelper.TABLE_LIST, " " + DBHelper.KEY_ID + " =  ? ", new String[] {String.valueOf(lijst.getID())});
        db.close();
    }
}
