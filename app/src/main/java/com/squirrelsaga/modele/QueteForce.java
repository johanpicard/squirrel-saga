package com.squirrelsaga.modele;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteForce extends AbstractQuete {
    public QueteForce(String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude) {
        super(titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude);
    }

    @Override
    public String getIconeStandard() {
        return "icon_strength";
    }

    public QueteForce() {
    }
}
