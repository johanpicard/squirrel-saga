package com.squirrelsaga.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lbillon on 1/19/15.
 */

public class QueteIntelligence extends AbstractQuete {

    private String  question = "";
    private String reponse = "";

    public QueteIntelligence(String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude, int noisette, int recompense, String question, String reponse) {
        super(titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude, noisette, recompense);
        this.question = question;
        this.reponse = reponse;
    }

    public QueteIntelligence(String titre, int intelligenceRequise, int vitesseRequise, int forceRequise, String texte, double latitude, double longitude, int noisette, int recompense) {
        super(titre, intelligenceRequise, vitesseRequise, forceRequise, texte, latitude, longitude, noisette, recompense);
    }
    public QueteIntelligence() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public String getIconeStandard() {
        return "icon_brain";
    }

}
