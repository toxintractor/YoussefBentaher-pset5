package com.bentaher.youssefbentaher_pset5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {


    EditText naam, beschrijving;
    TextView txtBeschrijving;
    Button upDate, deLete;
    Lijst taakObject;
    TaakManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //db = DBHelper.getInstance(this);
        db = new TaakManager(this);
        taakObject = (Lijst) getIntent().getExtras().getSerializable("data");

        beschrijving = (EditText) findViewById(R.id.taakBng);
        naam = (EditText) findViewById(R.id.taakNm);
        upDate  = (Button) findViewById(R.id.updt);
        deLete = (Button) findViewById(R.id.delt);
        txtBeschrijving = (TextView) findViewById(R.id.textBng);



        deLete.setOnClickListener(new Main3Activity.DeLete());
        upDate.setOnClickListener(new Main3Activity.UpDate());
        txtBeschrijving.setText(taakObject.getBeschrijving());

    }


    public class UpDate implements View.OnClickListener {

        public UpDate(){
        }

        @Override
        public void onClick(View view) {
            String snaam = naam.getText().toString();
            String sbeschrijving = beschrijving.getText().toString();

            if(!snaam.matches("")){
                taakObject.setLijst(naam.getText().toString());
            }

            if(!sbeschrijving.matches("")){
                taakObject.setBeschrijving(beschrijving.getText().toString());
            }


            db.updateList(taakObject);
            beschrijving.setText("");
            naam.setText("");
            txtBeschrijving.setText(taakObject.getBeschrijving());


        }
    }

    public class DeLete implements View.OnClickListener {

        public DeLete(){
        }

        @Override
        public void onClick(View view) {
            db.deleteList(taakObject);

            Intent jumppage = new Intent(Main3Activity.this, Main3Activity.class);
            startActivity(jumppage);

        }
    }

    public void onBackPressed() {
        Intent jumppage = new Intent(Main3Activity.this, Main2Activity.class);
        startActivity(jumppage);

    }


}