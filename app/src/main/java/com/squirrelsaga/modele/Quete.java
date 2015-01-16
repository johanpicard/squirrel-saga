package com.squirrelsaga.modele;

import com.orm.SugarRecord;

import java.util.Map;

/**
 * Created by lbillon on 1/14/2015.
 */
public class Quete extends SugarRecord<Quete> {
    public String titre;

    private Quete prerequis;
    private Map<String,Integer> competencesRequises;
    private boolean dispo;
    private String texte;

    public double latitude;
    public double longitude;

    public Quete(){
    }

    public Quete(String titre, double latitude, double longitude){
        this.titre = titre;
        this.latitude = latitude;
        this.longitude = longitude;

    }
}

