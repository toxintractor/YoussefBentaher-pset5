package com.bentaher.youssefbentaher_pset5;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mocro on 09/10/2017.
 */

public class listTaakAdapter extends ArrayAdapter {
    private ArrayList<Lijst> taken;
    private Context context;
    //private Main2Activity listActivity;
    //DBHelper db;
    TaakManager db;
    CheckBox theCHeckBox;
    Lijst tk;


    public listTaakAdapter(Context context, ArrayList<Lijst> data) {
        super(context, 0 , data);
        this.taken  = data;
        //this.listActivity = (Main2Activity) context;
        this.context = context;

        //db = DBHelper.getInstance(context);
        db = new TaakManager(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listlayout, parent, false);
        }


        final Lijst tk = taken.get(position);
        final String taakNaam = tk.getLijst();

        final TextView theTextView = (TextView) view.findViewById(R.id.taaktext);

        theTextView.setText(taakNaam);


        final CheckBox theCHeckBox = (CheckBox) view.findViewById(R.id.checklist);
        theCHeckBox.setChecked(Boolean.parseBoolean(tk.getIschecked()));

        theCHeckBox.setOnClickListener(new keepCHeck(theCHeckBox, tk));
        theTextView.setOnClickListener(new goItem(tk));

        return view;
    }

    public int getCount(){
        return  super.getCount();
    }

    class keepCHeck implements View.OnClickListener {

        CheckBox ckBox;
        Lijst tk1;
        public keepCHeck(CheckBox chBox, Lijst tk2){
            ckBox = chBox;
            tk1 = tk2;
        }

        @Override
        public void onClick(View view) {
            db.open();
            if(ckBox.isChecked()){
                Toast.makeText(context, "het is gecheckt", Toast.LENGTH_SHORT).show();
                tk1.setIschecked("true");
                db.updateList(tk1);
            }
            else{
                Toast.makeText(context, "het is niet gecheckt", Toast.LENGTH_SHORT).show();
                tk1.setIschecked("false");
                db.updateList(tk1);
            }

        }
    }

    class goItem implements View.OnClickListener {
        Lijst tk1;
        public goItem(Lijst tk2){
            tk1 = tk2;
        }

        @Override
        public void onClick(View view) {
            db.open();
            //Toast.makeText(context, tk1, Toast.LENGTH_SHORT).show();
            Intent jumppage = new Intent(context, Main2Activity.class);
            jumppage.putExtra("data", tk1);
            context.startActivity(jumppage);

        }
    }


}