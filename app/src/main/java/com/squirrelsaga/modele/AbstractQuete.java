package com.squirrelsaga.modele;

import com.orm.SugarRecord;
import com.squirrelsaga.controleur.Controleur;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by lbillon on 1/14/2015.
 */
public abstract class AbstractQuete extends SugarRecord<AbstractQuete> {
    public String titre;

    private AbstractQuete prerequis;
    private Map<String,Integer> competencesRequises;
    private boolean termine;
    private String texte;


    public double latitude;
    public double longitude;
    private String customMarker;

    public AbstractQuete(){
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

    public AbstractQuete(String titre, double latitude, double longitude, int forceRequise, int agiliteRequise, int intelligenceRequise, String customMarker){
        this.titre = titre;
        this.latitude = latitude;
        this.longitude = longitude;
        this.customMarker = customMarker;
        competencesRequises = new HashMap<String,Integer>();
        competencesRequises.put("Force", forceRequise);
        competencesRequises.put("Intelligence",agiliteRequise);
        competencesRequises.put("Agilite",intelligenceRequise);

    }

    public String getMarker(){
        if(this.customMarker == null){
            return getTypeMarker();
        }else{
            return customMarker;
        }
    }

    public abstract String getTypeMarker();

    public boolean estTermine() {
        return termine;
    }
}

