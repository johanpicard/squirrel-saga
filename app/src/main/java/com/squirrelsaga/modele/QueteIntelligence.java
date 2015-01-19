package com.squirrelsaga.modele;

import java.util.Map;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteIntelligence extends AbstractQuete {
    public QueteIntelligence(String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude) {
        super(titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude);
    }

    @Override
    public String getIconeStandard() {
        return "icon_brain";
    }

    public QueteIntelligence() {
    }
}
