package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Leonardo on 10/02/2017.
 */

public class Sqlmanager  extends SQLiteOpenHelper {
    /*Estas variables son necesarias ya que para el query de jugadores que se mostrará en una lista,
    la recolección de datos y la generacion de la lista se realizan en actividades diferentes. Para
    la comunicación entre estas actividades, las variables estaticas han sido unas de las pocas so-
    luciones a este problema de comunicación.

     */
    static String name ;
    static String country;
    static int mmrmin;
    static int mmrmax;
    public Sqlmanager(Context context){
        super(context, "dotatop", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  void setqueryplayer( String name ,String country , int mmrmin ,int mmrmax){
        this.name = name;
        this.country = country;
        this.mmrmin = mmrmin;
        this.mmrmax = mmrmax;
    }

    public void insertPlayer(String user_value , int mmr,String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user_value);
        contentValues.put("mmr", mmr);
        contentValues.put("country",country);
        db.insert("playertop", null, contentValues);
        db.close();
    }

    public void deletePlayer(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("playertop", "username" + "=? ", new String[]{name});
    }

    //Query all rows. Lo llama el show 200 top players. Shard-separated values.
    public String[] getAllRows() {
        ArrayList<String> datarows = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + "playertop order by mmr desc", null);
        c.moveToFirst();
        String row="";
        while (!c.isAfterLast()) {
            row=row + c.getString(c.getColumnIndex("username")) +"#"+ c.getString(c.getColumnIndex("mmr")) +"#"+ c.getString(c.getColumnIndex("country"));
            datarows.add(row);
            row="";
            c.moveToNext();
        }
        return convertArrayListtoString(datarows);
    }

    //Query selected rows. Lo llama el queryjugador
    public String[] getSelectedRows() {
        ArrayList<String> datarows = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        if(country.contentEquals("all")){
            if(name.contentEquals("empty")){
                c = db.rawQuery("SELECT * FROM " + "playertop where mmr > "+ this.mmrmin +" and mmr < "+this.mmrmax+" order by mmr desc", null);
            }else{
                c = db.rawQuery("SELECT * FROM " + "playertop where username = '"+this.name +"' and mmr > "+ this.mmrmin +" and mmr < "+this.mmrmax+" order by mmr desc", null);
            }
        }else{
            if(name.contentEquals("empty")){
                c = db.rawQuery("SELECT * FROM " + "playertop where mmr > "+ this.mmrmin +" and mmr < "+this.mmrmax+" and country = '"+country+"' order by mmr desc", null);
            }else{
                c = db.rawQuery("SELECT * FROM " + "playertop where username = '"+this.name +"' and mmr > "+ this.mmrmin +" and mmr < "+this.mmrmax+"  and country = '"+country+"' order by mmr desc", null);
            }
        }
        //playertop(id integer primary key,username VARCHAR,mmr VARCHAR,country VARCHAR)
        Log.d("HolaMundo","the data name is:"+name);
        c.moveToFirst();
        String row="";
        while (!c.isAfterLast()) {
            row=row + c.getString(c.getColumnIndex("username")) +"#"+ c.getString(c.getColumnIndex("mmr")) +"#"+ c.getString(c.getColumnIndex("country"));
            datarows.add(row);
            row="";
            c.moveToNext();
        }
        return convertArrayListtoString(datarows);
    }


    public String[] getCustomRows(String name,String country,int minmmr,int maxmmr) {
        ArrayList<String> datarows = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + "playertop where  order by mmr desc", null);
        c.moveToFirst();
        String row="";
        while (!c.isAfterLast()) {
            row=row + c.getString(c.getColumnIndex("username")) +"#"+ c.getString(c.getColumnIndex("mmr")) +"#"+ c.getString(c.getColumnIndex("country"));
            datarows.add(row);
            row="";
            c.moveToNext();
        }
        return convertArrayListtoString(datarows);
    }

    public String[] convertArrayListtoString(ArrayList data){
        String[] datarows=new String[data.size()];
        for(int i=0;i<data.size();i++){
            datarows[i] =(String) data.get(i);
        }
        return datarows;
    }
}
