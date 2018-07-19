package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.app.ListActivity;
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

/**
 * Created by Leonardo on 22/02/2017.
 */
/*
Lo mismo que list manager pero en vez de get all rows, get selected rows.
 */
public class ListManagerQueryJugador extends ListActivity {
    SQLiteDatabase db ;
    String[] data;
    Sqlmanager sqlm;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Log.d("smt","Oncreate done");
        db = openOrCreateDatabase("dotatop",MODE_PRIVATE,null);
        sqlm = new Sqlmanager(this);
        data = sqlm.getSelectedRows();
        setContentView(R.layout.content_main);
        setListAdapter(new IconicAdapter());
    }


    public class IconicAdapter extends ArrayAdapter<String> {
        IconicAdapter() {
            super(ListManagerQueryJugador.this, R.layout.row, data);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.label);
            String[] datarow=data[position].split("#");
            label.setText(datarow[0] + "    "+ datarow[1]);

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
