package com.example.leonardo.leonardoabril_dota2sqlitev3;

/**
 * Created by Leonardo on 11/02/2017.
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.Reader;


public class JsonManager {
    JsonObject jo;
    public JsonManager() {
    }

    //Conversion del string a jsonobject y lo guarda en variable global
    public void parseJson(String dataglobal){
        if(dataglobal!=null) {
            JsonElement jelement = new JsonParser().parse(dataglobal);
            JsonObject jobject = jelement.getAsJsonObject();
            jo = jobject;
        }
        //String result = jobject.get("name").toString();
    }

    //Metodos para extraer name mmr y pais
    public String getName(){
        return jo.get("name").toString().substring(1,jo.get("name").toString().length()-1);
    }

    public String getMmr(){
        return jo.get("solo_mmr").toString();
    }

    public String getCountry() {
        if (jo.get("country") == null) {
            return "XX";
        } else {
            return  jo.get("country").toString().substring(1,jo.get("country").toString().length()-1);
        }
    }

}
