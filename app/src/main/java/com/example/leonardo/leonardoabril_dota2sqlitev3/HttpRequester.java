package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Leonardo on 11/02/2017.
 */

public class HttpRequester extends AsyncTask<Void,Void,Void> {
    URL url;
    String datatotal;
    Bitmap img;
    int process=-1;//0 for JSON, 1 for image,-1 for null

    HttpRequester(){

    }


    public void setProcess(int p){
        process=p;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(process==0){
            datatotal=getData();
        }else if(process==1){
            try {
                img=this.getImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getDatatotal(){
        return datatotal;
    }

    public void settheUrl(String txt){
        try{
            url = new URL(txt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get data players
    public String getData(){
        String dataglobal="asd";

        HttpURLConnection urlConnection = null;
        try {
           urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
                dataglobal=dataglobal+current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return dataglobal;
    }

    //Get data image
    public Bitmap getImage() throws IOException {
        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

}
