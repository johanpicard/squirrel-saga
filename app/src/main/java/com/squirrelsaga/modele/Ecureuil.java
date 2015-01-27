package com.squirrelsaga.modele;


import com.orm.SugarRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ecureuil extends SugarRecord<Ecureuil> {

    private String nom;
    private String cheminImage;
    private int nbNoisettes = 0;

    private int intelligence = 0;
    private int vitesse = 0;
    private int force = 0;

    public Ecureuil() {
    }

    private List<Integer> quetesReussies= new ArrayList<>();

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

    /**
     * Caractéristique intelligence de l'écureuil, entre 0 et 100
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Caractéristique vitesse de l'écureuil, entre 0 et 100
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Caractéristique force de l'écureuil, entre 0 et 100
     */
    public int getForce() {
        return force;
    }

    public int forceLevelUp(int recompense) {
        force += recompense;
        return force;
    }

    public int intelligenceLevelUp(int recompense) {
        intelligence += recompense;
        return intelligence;
    }

    public int vitesseLevelUp(int recompense) {
        vitesse += recompense;
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

    public void setAReussi(Integer queteId) {
        this.quetesReussies.add(queteId);
    }

    public boolean getAReussi(Integer queteId) {
        return this.quetesReussies.contains(queteId);
    }
}
