package com.squirrelsaga.modele;

import com.orm.SugarRecord;

/**
 * Created by lbillon on 1/14/2015.
 */
public class Quete extends SugarRecord<Quete> {
    public String titre;

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

