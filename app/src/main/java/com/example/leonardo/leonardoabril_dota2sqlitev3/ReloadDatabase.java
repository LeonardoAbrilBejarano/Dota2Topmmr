package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.util.Log;

/**
 * Created by Leonardo on 12/02/2017.
 */

/*
Necesita ser un thread ya que necesita realizar 200 insert y realentiza el programa.
Android no deja realizar acciones que tengan un peso tan alto de procesamiento, asi que
en vez de secuencial, se realiza un thread aparte.
*/
public class ReloadDatabase implements Runnable {
    Sqlmanager sqlm;
    String[] datarows;
    public ReloadDatabase(Sqlmanager sqlm,String[] datarows){
        this.sqlm=sqlm;
        this.datarows=datarows;
    }
    @Override
    public void run() {
        reloadDB();
    }
    private void reloadDB(){
        JsonManager jm = new JsonManager();
        for(int s=0;s<datarows.length;s++){
            if (datarows!=null) {
                //parsea los datos a json
                jm.parseJson(datarows[s]);
                //Mete los datos json a la base de datos
                sqlm.insertPlayer(jm.getName(), Integer.parseInt(jm.getMmr()), jm.getCountry());
                Log.d("HolaMundo", "the data is " + jm.getName() + Integer.parseInt(jm.getMmr()));
                System.out.println("name : " + jm.getName() + " mmr : " + jm.getMmr());
            }
        }
    }
}
