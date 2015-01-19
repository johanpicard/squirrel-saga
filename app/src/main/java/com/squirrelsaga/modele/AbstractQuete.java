package com.squirrelsaga.modele;

import android.content.Context;

import com.orm.SugarRecord;
import com.squirrelsaga.controleur.Controleur;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by lbillon on 1/14/2015.
 */
public abstract class AbstractQuete extends SugarRecord<AbstractQuete> {
    public String titre;

    public final class Statut {
        public static final String REUSSIE = "reussie";
        public static final String DISPONIBLE = "disponible";
        public static final String PREREQUIS_INSATISFAIT= "prerequis insatisfait";
        public static final String COMPETENCES_INSUFFISANTES= "competences insuffisantes";
    }

    private AbstractQuete prerequis;

    public int intelligenceRequise = 0;
    public int vitesseRequise = 0;
    public int forceRequise = 0;

    private boolean reussie= false;
    public String texte;

    public double latitude;
    public double longitude;
    private String icone;

    public AbstractQuete(){
    }

    protected AbstractQuete(String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude) {
        this.titre = titre;
        this.intelligenceRequise = intelligenceRequise;
        this.vitesseRequise = vitesseRequise;
        this.forceRequise = forceRequise;
        this.texte = texte;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStatut(Ecureuil ecureuil){
        if(reussie){
            return Statut.REUSSIE;
        }

        if(null !=prerequis) {
            if (!(prerequis.getStatut(ecureuil) == Statut.REUSSIE)) {
                return Statut.PREREQUIS_INSATISFAIT;
            }
            ;
        }
        if(!competencesSuffisantes(ecureuil)){
            return Statut.COMPETENCES_INSUFFISANTES;
        }

        return Statut.DISPONIBLE;
    }

    private boolean competencesSuffisantes(Ecureuil ecureuil) {
        boolean competencesOK = (ecureuil.getIntelligence() >= intelligenceRequise) && (ecureuil.getVitesse() >= vitesseRequise) && (ecureuil.getForce() >= forceRequise);
        return competencesOK;
    }

    public String getIcone(){
        if(this.icone == null){
            return getIconeStandard();
        }else{
            return icone;
        }
    }

    public abstract String getIconeStandard();

    @Override
    public String toString() {
        return "AbstractQuete{" +
                "titre='" + titre + '\'' +
                ", reussie=" + reussie +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", icone='" + icone + '\'' +
                '}';
    }
}

