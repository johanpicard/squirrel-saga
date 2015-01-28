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

    /**
     * @param intelligence Caractéristique intelligence de l'écureuil, entre 0 et 100
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * @param vitesse Caractéristique vitesse de l'écureuil, entre 0 et 100
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * @param force Caractéristique force de l'écureuil, entre 0 et 100
     */
    public void setForce(int force) {
        this.force = force;
    }

    public void mange(int nbNoisettes){
        this.nbNoisettes += nbNoisettes;
    }

    /**
     * @return Caractéristique intelligence de l'écureuil, entre 0 et 100
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return Caractéristique vitesse de l'écureuil, entre 0 et 100
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     *  @return Caractéristique force de l'écureuil, entre 0 et 100
     */
    public int getForce() {
        return force;
    }

    /**
     * Augmente la caractéristique Force de l'écureuil par la valeur passée en paramètre
     */
    public int forceLevelUp(int recompense) {
        force += recompense;
        if (force > 100)
            force = 100;
        return force;
    }

    /**
     * Augmente la caractéristique Intelligence de l'écureuil par la valeur passée en paramètre
     */
    public int intelligenceLevelUp(int recompense) {
        intelligence += recompense;
        if (intelligence > 100)
            intelligence = 100;
        return intelligence;
    }

    /**
     * Augmente la caractéristique Vitesse de l'écureuil par la valeur passée en paramètre
     */
    public int vitesseLevelUp(int recompense) {
        vitesse += recompense;
        if (vitesse > 100)
            vitesse = 100;
        return vitesse;
    }

    /**
     *  @return Le nombre de noisettes mangées par l'écureuil
     */
    public int getNbNoisettes() {
        return nbNoisettes;
    }

    /**
     *  @return Le nom de l'image de l'écureuil
     */
    public String getCheminImage() {
        return cheminImage;
    }

    /**
     *  @param cheminImage Le nom de l'image de l'écureuil
     */
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    /**
     *  @return Le nom de l'écureuil
     */
    public String getNom() {
        return nom;
    }

    /**
     *  @param nom Le nom de l'écureuil
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Enregistre la quète passée en paramètre dans la liste des quetes validées de l'écureuil.
     * ATTENTION, l'ORM SugarRush ne supporte pas les tableaux, ceci ne sera donc pas persisté.
     * @param queteId
     */
    public void setAReussi(Integer queteId) {
        //TODO Changer la façon d'enregistrer les quetes réussies (par exemple dans un fichier) ou bien l'ORM
        this.quetesReussies.add(queteId);
    }

    /**
     * Récupère la liste des quetes validées par l'écureuil.
     * ATTENTION, l'ORM SugarRush ne supporte pas les tableaux, ceci n'a donc aps été persisté.
     * @param queteId
     */
    public boolean getAReussi(Integer queteId) {
        return this.quetesReussies.contains(queteId);
    }
}
