package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

public class Queryjugador extends AppCompatActivity {
    SeekBar seekBar;
    Spinner country;
    int mmrmin=0;
    int mmrmax=9999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queryjugador);
       // seekBar = (SeekBar) findViewById(R.id.seekbar_mmr);
        country = (Spinner) findViewById(R.id.sp_pais);
        loadSpinnerProvincias();
// get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.seekbar_mmr);

// get min and max text view
        final TextView tvMin = (TextView) findViewById(R.id.leftnumberseekbar);
        final TextView tvMax = (TextView) findViewById(R.id.rightnumberseekbar);

// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                mmrmin = minValue.intValue();
                Log.d("HolaMundo","the data mmrmin is: "+mmrmin);
                mmrmax = maxValue.intValue();
                Log.d("HolaMundo","the data mmrmax is: "+mmrmax);
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });
    }

    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_paises, R.layout.custom_spinner);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.country.setAdapter(adapter);
        // This activity implements the AdapterView.OnItemSelectedListener
        //this.country.setOnItemSelectedListener(this);
    }
    public void onClickQuery (View view){
        Sqlmanager sqlm = new Sqlmanager(this);
        String name="empty";
        EditText etname =(EditText) findViewById(R.id.et1_name);
        name = etname.getText().toString();
        String country;
        country = castCountry(this.country.getSelectedItem().toString());
        if(name.contentEquals("") || name == null){
            name = "empty";
        }
        sqlm.setqueryplayer(name,country,mmrmin,mmrmax);
        startActivity(new Intent(Queryjugador.this, ListManagerQueryJugador.class));

        Toast.makeText(Queryjugador.this, "Jugador Borrado correctamente", Toast.LENGTH_LONG).show();

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
        }else if (country.equals(this.getApplicationContext().getResources().getString(R.string.all))) {
            return "all";
        } else {
            return "XX";
        }
    }
}
