package com.bentaher.youssefbentaher_pset5;

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

public class Main2Activity extends AppCompatActivity {


    EditText naam, beschrijving, lsttaak, lstbeschrijving;
    TextView txtBeschrijving;
    Button upDate, deLete, invoegen;
    ListView lstView;
    Taak taakObject;
    Lijst lijst;
    //DBHelper db;
    TaakManager db;
    private ArrayList<Lijst> taakArray;
    private listTaakAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //db = DBHelper.getInstance(this);
        db = new TaakManager(this);
        taakObject = (Taak) getIntent().getExtras().getSerializable("data");

        beschrijving = (EditText) findViewById(R.id.taakBeschrijving);
        naam = (EditText) findViewById(R.id.taakNaam);
        upDate  = (Button) findViewById(R.id.updaten);
        deLete = (Button) findViewById(R.id.deleten);
        txtBeschrijving = (TextView) findViewById(R.id.textBeschrijving);
        lsttaak = (EditText) findViewById(R.id.lijsttaak);
        lstbeschrijving = (EditText) findViewById(R.id.lijstbeschrijving);
        invoegen = (Button) findViewById(R.id.lijsttoevoegen);



        deLete.setOnClickListener(new Main2Activity.DeLete());
        upDate.setOnClickListener(new Main2Activity.UpDate());
        txtBeschrijving.setText(taakObject.getBeschrijving());
        invoegen.setOnClickListener(new Main2Activity.putWords());


        taakArray = db.getAllDataList(taakObject.getID());
        String length  = String.valueOf(taakArray.size());
        Log.i("check", length);

        for (Lijst ding: taakArray
             ) {
            Log.i("check", String.valueOf(ding.getTaakID()));
        }

        setAdapter();



    }

    public class putWords implements View.OnClickListener {

        public putWords(){
        }

        @Override
        public void onClick(View view) {
            lijst = new Lijst(taakObject.getID(), lsttaak.getText().toString(), lstbeschrijving.getText().toString(), "false");
            db.open();
            boolean check = db.insertDataList(lijst);
            lsttaak.setText("");
            lstbeschrijving.setText("");
            Log.i("check", String.valueOf(check));
            //db.open();
            //taakArray = db.getAllData();
            //setAdapter();
        }
    }

    public  void setAdapter(){
        adapter = new listTaakAdapter(this, taakArray);
        lstView = (ListView) findViewById(R.id.lstView);
        lstView.setAdapter(adapter);

    }



    public class UpDate implements View.OnClickListener {

        public UpDate(){
        }

        @Override
        public void onClick(View view) {
            String snaam = naam.getText().toString();
            String sbeschrijving = beschrijving.getText().toString();

            if(!snaam.matches("")){
                taakObject.setTaak(naam.getText().toString());
            }

            if(!sbeschrijving.matches("")){
                taakObject.setBeschrijving(beschrijving.getText().toString());
            }


            db.update(taakObject);
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
            db.delete(taakObject);

            Intent jumppage = new Intent(Main2Activity.this, Main2Activity.class);
            startActivity(jumppage);

        }
    }

    public void onBackPressed() {
        Intent jumppage = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(jumppage);

    }


}
