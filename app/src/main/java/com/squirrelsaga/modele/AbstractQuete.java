package com.squirrelsaga.modele;

import android.content.Context;
import android.location.Location;

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



    protected Integer queteId;

    public Integer getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(Integer prerequis) {
        this.prerequis = prerequis;
    }

    public Integer prerequis = null;

    public int intelligenceRequise = 0;
    public int vitesseRequise = 0;
    public int forceRequise = 0;

    public String texte;

    public double latitude;
    public double longitude;
    private String icone;

    public int recompense;
    public int noisette;

    public int getRecompense() {
        return recompense;
    }

    public int getNoisette() {
        return noisette;
    }

    public AbstractQuete(){
    }

    protected AbstractQuete(Integer queteId,String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude, int noisette, int recompense) {
        this.queteId = queteId;
        this.titre = titre;
        this.intelligenceRequise = intelligenceRequise;
        this.vitesseRequise = vitesseRequise;
        this.forceRequise = forceRequise;
        this.texte = texte;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recompense = recompense;
        this.noisette = noisette;
    }



    public String getStatut(Ecureuil ecureuil){
        if(ecureuil.getAReussi(this.queteId)){
            return Statut.REUSSIE;
        }

        if(null !=prerequis) {
            if (!ecureuil.getAReussi(prerequis)) {
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

    public Location getLocation(){
        Location location = new Location(titre);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return  location;
    }

    public abstract String getIconeStandard();

    public String getTitre() {
        return titre;
    }

    public String getTexte() {
        return texte;
    }




    public int getIntelligenceRequise() {
        return intelligenceRequise;
    }

    public int getVitesseRequise() {
        return vitesseRequise;
    }

    public int getForceRequise() {
        return forceRequise;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Integer getQueteId() {
        return queteId;
    }


    @Override
    public String toString() {
        return "AbstractQuete{" +
                "titre='" + titre + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", icone='" + icone + '\'' +
                '}';
    }
}

