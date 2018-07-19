package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Created by Leonardo on 10/02/2017.
 */

public class ListManager extends ListActivity {
    SQLiteDatabase db ;
    String[] data;
    Sqlmanager sqlm;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Log.d("smt","Oncreate done");
        db = openOrCreateDatabase("dotatop",MODE_PRIVATE,null);
        sqlm = new Sqlmanager(this);
        data = sqlm.getAllRows();
        setContentView(R.layout.content_main);
        setListAdapter(new IconicAdapter());
    }


    public class IconicAdapter extends ArrayAdapter<String> {
        IconicAdapter() {
            super(ListManager.this, R.layout.row, data);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.label);
            //Separa values por shards
            String[] datarow=data[position].split("#");
            //Set el texto por nombre y mmr
            label.setText(datarow[0] + "    "+ datarow[1]);
            //Indica al hr que se pedira imagenes
            ImageView icon=(ImageView)row.findViewById(R.id.icon);
            HttpRequester hr =new HttpRequester();
            hr.setProcess(1);
            hr.settheUrl("http://steamcommunity-a.akamaihd.net/public/images/countryflags/"+datarow[2]+".gif");
            hr.execute();
            Bitmap country=null;
            try {
                country = hr.getImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //if (data[position].length()>4) {
                icon.setImageBitmap(country);
            //}
            //else {
                //icon.setImageResource(R.drawable.add);
            //}

            return(row);
        }
    }
}
