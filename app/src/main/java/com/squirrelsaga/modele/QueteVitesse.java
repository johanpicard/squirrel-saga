package com.squirrelsaga.modele;

import android.location.Location;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteVitesse extends AbstractQuete {

    private double objectifLatitude;
    private double objectifLongitude;


    public QueteVitesse(Integer queteId, String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude, int noisette, int recompense, double objectifLatitude, double objectifLongitude) {
        super(queteId, titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude, noisette, recompense);
        this.objectifLatitude = objectifLatitude;
        this.objectifLongitude = objectifLongitude;
    }

    @Override
    public String getIconeStandard() {
        return "icon_speed";
    }

    public QueteVitesse() {
    }

    public Location getObjectifLocation(){
        Location location = new Location(titre);
        location.setLatitude(objectifLatitude);
        location.setLongitude(objectifLongitude);
        return  location;
    }
}
