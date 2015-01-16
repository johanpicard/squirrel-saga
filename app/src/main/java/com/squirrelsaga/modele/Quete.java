package com.squirrelsaga.modele;

import com.orm.SugarRecord;
import com.squirrelsaga.controleur.Controleur;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by lbillon on 1/14/2015.
 */
public class Quete extends SugarRecord<Quete> {
    public String titre;

    private Quete prerequis;
    private Map<String,Integer> competencesRequises;
    private boolean termine;
    private String texte;


    public double latitude;
    public double longitude;

    public Quete(){
    }

    public boolean estDispo() {
        boolean competencesOK = true;
        Map<String,Integer> competences = Controleur.getEcureuil().getCompetences();

        for (Map.Entry<String, Integer> entry : competencesRequises.entrySet()) {
           if (competences.get(entry.getKey())< entry.getValue()) {
               competencesOK = false;
           }
        }
        return !termine && competencesOK && prerequis.estTermine();
    }

    public Quete(String titre, double latitude, double longitude, int forceRequise, int agiliteRequise, int intelligenceRequise ){
        this.titre = titre;
        this.latitude = latitude;
        this.longitude = longitude;
        competencesRequises = new HashMap<String,Integer>();
        competencesRequises.put("Force", forceRequise);
        competencesRequises.put("Intelligence",agiliteRequise);
        competencesRequises.put("Agilite",intelligenceRequise);
    }

    public boolean estTermine() {
        return termine;
    }
}

