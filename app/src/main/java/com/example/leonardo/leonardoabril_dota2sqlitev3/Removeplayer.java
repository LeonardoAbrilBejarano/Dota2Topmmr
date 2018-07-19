package com.example.leonardo.leonardoabril_dota2sqlitev3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Removeplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removeplayer);
    }

    public void onClickBorrar (View view){
        Sqlmanager sqlm = new Sqlmanager(this);
        String name;
        EditText etname =(EditText) findViewById(R.id.et1_name);
        name = etname.getText().toString();

        sqlm.deletePlayer(name);

        Toast.makeText(Removeplayer.this, "Jugador Borrado correctamente", Toast.LENGTH_LONG).show();

    }
}
