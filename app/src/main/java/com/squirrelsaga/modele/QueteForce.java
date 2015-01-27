package com.squirrelsaga.modele;

import com.squirrelsaga.controleur.Controleur;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteForce extends AbstractQuete {

    String objectif1;
    int valeur1;
    String objectif2;
    int valeur2;

    /**
     * Le parametre "objets" est un tableau du nombre d'objets à récupérer sous la forme [NbGlands,NbAiguilles,NbPommesDePins,NbFeuillesErable]
     * Il doit toujours contenir exactement 2 valeurs non nulles
     */
    public QueteForce(Integer queteId, String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude, int noisette, int recompense, int[] objets) {
        super(queteId, titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude, noisette, recompense);
        boolean premier = true;
        for(int i = 0; i < objets.length; i++){
            if (objets[i] != 0 && premier){
                objectif1 = Controleur.objetsARecup.get(i);
                valeur1 = objets[i];
                premier = false;
            } else if (objets[i] != 0 && !premier){
                objectif2 = Controleur.objetsARecup.get(i);
                valeur2 = objets[i];
            }
        }
    }

    @Override
    public String getIconeStandard() {
        return "icon_strength";
    }

    public String getObjectif1() {
        return objectif1;
    }

    public int getValeur1() {
        return valeur1;
    }

    public String getObjectif2() {
        return objectif2;
    }

    public int getValeur2() {
        return valeur2;
    }

    public QueteForce() {
    }


}
