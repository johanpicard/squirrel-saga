package com.squirrelsaga.modele;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ecureuil {

    private String nom;
    private String cheminImage;
    private int nbNoisettes = 0;
    private int intelligence = 0;
    private int vitesse = 0;
    private int force = 0;

    public Ecureuil(String nom, String cheminImage) {
        this.nom = nom;
        this.cheminImage = cheminImage;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void mange(int nbNoisettes){
        this.nbNoisettes += nbNoisettes;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getForce() {
        return force;
    }

    public int forceLevelUp() {
        force = force++;
        return force;
    }

    public int intelligenceLevelUp(int recompense) {
        intelligence = intelligence+recompense;
        return intelligence;
    }

    public int vitesseLevelUp() {
        vitesse = vitesse++;
        return vitesse;
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
