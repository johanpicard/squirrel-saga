package com.squirrelsaga.modele;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteIntelligence extends AbstractQuete {
    public QueteIntelligence(String titre, double latitude, double longitude, int forceRequise, int agiliteRequise, int intelligenceRequise, String customMarker) {
        super(titre, latitude, longitude, forceRequise, agiliteRequise, intelligenceRequise, customMarker);
    }

    @Override
    public String getTypeMarker() {
        return "intelligence";
    }

    public QueteIntelligence() {
    }
}
