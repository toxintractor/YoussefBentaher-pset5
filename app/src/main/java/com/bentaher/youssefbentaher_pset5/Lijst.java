package com.bentaher.youssefbentaher_pset5;

import java.io.Serializable;

/**
 * Created by mocro on 07/10/2017.
 */

public class Lijst implements Serializable{

    private String lijst;
    private String beschrijving;
    private int id;
    private int taakid;
    private String ischecked;

    public Lijst(int taakID, String lijstNaam, String lijstBeschrijving, String lijstchecked){
        taakid = taakID;
        lijst = lijstNaam;
        beschrijving = lijstBeschrijving;
        ischecked = lijstchecked;
    }

    public Lijst(int taakID, String lijstNaam, String lijstBeschrijving, String lijstchecked, int lijstId){
        taakid = taakID;
        lijst = lijstNaam;
        beschrijving = lijstBeschrijving;
        ischecked = lijstchecked;
        id = lijstId;
    }

    public String getLijst(){
        return lijst;
    }

    public String getBeschrijving(){
        return beschrijving;
    }

    public void setLijst(String newLijst){
        lijst = newLijst;
    }

    public void setBeschrijving(String newBeschrijving){
        beschrijving = newBeschrijving;
    }

    public int getID(){
        return id;
    }

    public int getTaakID(){
        return taakid;
    }


    public String getIschecked(){
        return ischecked;
    }

    public void setIschecked(String newchecked){
        ischecked = newchecked;
    }
}
