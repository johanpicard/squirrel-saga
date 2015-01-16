package com.squirrelsaga.modele;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ecureuil {

    private String nom;
    private String cheminImage;
    private Map<String,Integer> competences;
    private int nbNoisettes;


    public Ecureuil(String nom, String cheminImage) {
        this.nom = nom;
        this.cheminImage = cheminImage;
        competences = new HashMap<String,Integer>();
        competences.put("Force", 0);
        competences.put("Intelligence",0);
        competences.put("Agilite",0);
    }

    public void mange(int nbNoisettes){
        this.nbNoisettes += nbNoisettes;
    }

    public Map<String, Integer> getCompetences() {
        return competences;
    }

    public int getForce() {
        return competences.get("Force");
    }

    public int getIntelligence() {
        return competences.get("Intelligence");
    }

    public int getAgilite() {
        return competences.get("Agilite");
    }

    public int forceLevelUp() {
        return competences.put("Force", competences.get("Force") + 1);
    }

    public int intelligenceLevelUp() {
        return competences.put("Intelligence", competences.get("Intelligence") + 1);
    }

    public int agiliteLevelUp() {
        return competences.put("Agilite", competences.get("Agilite") + 1);
    }

    public int getNbNoisettes() {
        return nbNoisettes;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
