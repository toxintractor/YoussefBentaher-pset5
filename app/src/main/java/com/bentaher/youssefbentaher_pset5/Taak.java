package com.bentaher.youssefbentaher_pset5;

import java.io.Serializable;

/**
 * Created by mocro on 26/09/2017.
 */

public class Taak implements Serializable {

    private String taak;
    private String beschrijving;
    private int id;
    private String ischecked;

    public Taak(String taakNaam, String taakBeschrijving, String taakchecked){
        taak = taakNaam;
        beschrijving = taakBeschrijving;
        ischecked = taakchecked;
    }

    public Taak(String taakNaam, String taakBeschrijving, String taakchecked, int taakId){
        taak = taakNaam;
        beschrijving = taakBeschrijving;
        ischecked = taakchecked;
        id = taakId;
    }

    public String getTaak(){
        return taak;
    }

    public String getBeschrijving(){
        return beschrijving;
    }

    public void setTaak(String newTaak){
        taak = newTaak;
    }

    public void setBeschrijving(String newBeschrijving){
        beschrijving = newBeschrijving;
    }

    public int getID(){
        return id;
    }

    public String getIschecked(){
        return ischecked;
    }

    public void setIschecked(String newchecked){
        ischecked = newchecked;
    }
}
