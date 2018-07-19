package com.example.leonardo.leonardoabril_dota2sqlitev3;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class Insertplayer extends AppCompatActivity  {
    Spinner country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertuser);
        //Busca el id del spinner
        country = (Spinner) findViewById(R.id.sp_pais);
        loadSpinnerProvincias();
    }
    private void loadSpinnerProvincias() {
        // Creación de un array adapter con el array de paises y un custom spinner con color text blanco
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_paises, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.country.setAdapter(adapter);
    }
    /*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
                               long id) {
        switch (parent.getId()) {
            case R.id.sp_pais:
                // Retrieves an array
                TypedArray arrayPaises = getResources().obtainTypedArray(
                        R.array.array_paises);
                CharSequence[] paises = arrayPaises.getTextArray(pos);
                arrayPaises.recycle();
                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, paises);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                this.country.setAdapter(adapter);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    */
    public void onClickInsert(View view){
        Sqlmanager sqlm = new Sqlmanager(this);
        String name;
        String country;
        EditText etname =(EditText) findViewById(R.id.et1_name);
        name = etname.getText().toString();
        int mmr;
        EditText etmmr = (EditText)findViewById(R.id.et2_mmr);
        mmr = Integer.parseInt(etmmr.getText().toString());
        //Cast nombre de ciudad en codigo de city para que luego funcione en la petición
        country = castCountry(this.country.getSelectedItem().toString());
        sqlm.insertPlayer(name,mmr,country);
        Toast.makeText(Insertplayer.this, "Jugador Introducido correctamente " + country, Toast.LENGTH_LONG).show();
    }

    private String castCountry(String country){
        if (country.equals(this.getApplicationContext().getResources().getString(R.string.al))) {
            return "al";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.au))) {
            return "au";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.bg))) {
            return "bg";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.bt))) {
            return "bt";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.by))) {
            return "by";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.cn))) {
            return "cn";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.de))) {
            return "de";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.dk))) {
            return "dk";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.ee))) {
            return "ee";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.es))) {
            return "es";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.fi))) {
            return "fi";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.fo))) {
            return "fo";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.fr))) {
            return "fr";
        } else        if (country.equals(this.getApplicationContext().getResources().getString(R.string.gb))) {
            return "gb";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.ge))) {
            return "ge";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.gr))) {
            return "gr";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.hr))) {
            return "hr";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.il))) {
            return "il";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.jo))) {
            return "jo";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.kr))) {
            return "kr";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.kz))) {
            return "kz";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.lk))) {
            return "lk";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.lt))) {
            return "lt";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.mn))) {
            return "mn";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.my))) {
            return "my";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.nl))) {
            return "nl";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.no))) {
            return "no";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.pl))) {
            return "pl";
        } else  if (country.equals(this.getApplicationContext().getResources().getString(R.string.ro))) {
            return "ro";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.rs))) {
            return "rs";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.ru))) {
            return "ru";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.se))) {
            return "se";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.si))) {
            return "si";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.sk))) {
            return "sk";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.tr))) {
            return "tr";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.ua))) {
            return "ua";
        } else if (country.equals(this.getApplicationContext().getResources().getString(R.string.us))) {
            return "us";
        } else {
            return "XX";
        }



    }
}