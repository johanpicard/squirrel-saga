package com.squirrelsaga.modele;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteForce extends AbstractQuete {

    public QueteForce(Integer queteId, String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude, int noisette, int recompense) {
        super(queteId, titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude, noisette, recompense);
    }

    @Override
    public String getIconeStandard() {
        return "icon_strength";
    }



    public QueteForce() {
    }


}
