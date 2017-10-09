package com.bentaher.youssefbentaher_pset5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText naamTxt, beschrijvingTxt;
    Button invr;
    //DBHelper db;
    TaakManager db;
    Taak taak;
    ListView lstView;
    private ArrayList<Taak> taakArray;
    private listAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //db = DBHelper.getInstance(this);
        db = new TaakManager(this);

        naamTxt = (EditText) findViewById(R.id.taakNaam);
        beschrijvingTxt = (EditText) findViewById(R.id.taakBeschrijving);
        invr = (Button) findViewById(R.id.invoeren);


        invr.setOnClickListener(new putWords());

        db.open();
        taakArray = db.getAllData();

        setAdapter();
    }

    public class putWords implements View.OnClickListener {

        public putWords(){
        }

        @Override
        public void onClick(View view) {
            taak = new Taak(naamTxt.getText().toString(), beschrijvingTxt.getText().toString(), "false");
            db.open();
            boolean check = db.insertData(taak);
            naamTxt.setText("");
            beschrijvingTxt.setText("");
            db.open();
            taakArray = db.getAllData();
            setAdapter();
        }
    }

    public  void setAdapter(){
        adapter = new listAdapter(this, taakArray);
        lstView = (ListView) findViewById(R.id.lstView);
        lstView.setAdapter(adapter);

    }

    public class getList implements View.OnClickListener {

        public getList(){
        }

        @Override
        public void onClick(View view) {
            db.open();
            ArrayList<Taak> taken = db.getAllData();

            Intent jumppage = new Intent(MainActivity.this, Main2Activity.class);
            jumppage.putExtra("data", taken);
            startActivity(jumppage);

        }
    }

}
