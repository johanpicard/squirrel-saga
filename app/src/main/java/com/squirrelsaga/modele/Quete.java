package com.squirrelsaga.modele;

import com.orm.SugarRecord;

/**
 * Created by lbillon on 1/14/2015.
 */
public class Quete extends SugarRecord<Quete> {
    String titre;

    public Quete(){
    }

    public Quete(String titre, String edition){
        this.titre = titre;

    }
}

