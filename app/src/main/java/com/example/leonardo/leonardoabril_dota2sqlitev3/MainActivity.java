package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SQLiteDatabase db ;
    Sqlmanager sqlm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permisos para activar conexiones a internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Cojemos el elemento videoview, le indicamos la url de donde tenemos el video y lo iniciamos
        VideoView videoView = (VideoView) findViewById(R.id.videoView_video);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        Uri path = Uri.parse("android.resource://com.example.leonardo.leonardoabril_dota2sqlitev3/"+ R.raw.dotaintro);
        videoView.setVideoURI(path);
        videoView.start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Abrimos o creamos la base de datos del movil
        db = openOrCreateDatabase("dotatop",MODE_PRIVATE,null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Creamos la tabla si no existe
        db.execSQL("CREATE TABLE IF NOT EXISTS playertop(id integer primary key,username VARCHAR,mmr integer,country VARCHAR);");
        sqlm = new Sqlmanager(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //Si el item seleccionado coincide con alguna de las id de los items
        if (id == R.id.addplayer) {
            startActivity(new Intent(MainActivity.this, Insertplayer.class));
        } else if (id == R.id.removeplayer) {
            startActivity(new Intent(MainActivity.this, Removeplayer.class));
        } else if (id == R.id.selectplayer) {
            startActivity(new Intent(MainActivity.this, ListManager.class));
        } else if (id == R.id.dbupdate) {
            Log.d("HolaMundo","the data is "+"entrando a red");
            //Iniciamos el http requestes
            HttpRequester hr = new HttpRequester();
            //Le indicamos que va a ser un data request
            hr.setProcess(0);
            //Le indicamos la url en que se recibira un json de una peticion get
            hr.settheUrl("http://www.dota2.com/webapi/ILeaderboard/GetDivisionLeaderboard/v0001?division=europe");
            //get waits the aplication too get the full data from de http get request
            try {
                //Ejecutamos la conexion al servidor
                hr.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.d("HolaMundo","the data is "+hr.getDatatotal());
            //Recojemos los datos de la conexion
            String data = hr.getDatatotal();
            //parsea los datos recojidos
            parseJsonToarray(data);
        }else if (id == R.id.cleanbd) {
            db.execSQL("drop table playertop");
            db.execSQL("CREATE TABLE IF NOT EXISTS playertop(id integer primary key,username VARCHAR,mmr integer,country VARCHAR);");
        }else if (id == R.id.queryplayer) {
            startActivity(new Intent(MainActivity.this, Queryjugador.class));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void parseJsonToarray(String dataglobal){

        Sqlmanager sqlm = new Sqlmanager(this);

        int n=0;
        while(dataglobal.charAt(n)!='['){
            n++;
        }
        dataglobal=dataglobal.substring(n+1, dataglobal.length()-2);
        //Coje los dato solo del array de datos y  lo mete en un array de strings
        String[] datarows= new String[201];
        int cont=0;
        int m=0;
        for(n=0;n<dataglobal.length();n++){
            if(dataglobal.charAt(n)=='{'){
                while(dataglobal.charAt(n+m)!='}'){
                    m++;
                }
                m++;
                datarows[cont]=dataglobal.substring(n,n+m);
                n=n+m;
                m=0;
                cont++;
            }
        }
        //mandamos los datos para realizar los inserts
        ReloadDatabase rd = new ReloadDatabase(sqlm,datarows);
        new Thread(rd).start();

    }
}
