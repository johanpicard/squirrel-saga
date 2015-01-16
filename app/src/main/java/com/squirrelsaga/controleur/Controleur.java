package com.squirrelsaga.controleur;

import android.util.Log;

import com.squirrelsaga.modele.Arbre;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.Quete;

/**
 * Created by Johan on 04/01/2015.
 */
public class Controleur {

    private static Quete queteCourante;

    private static Ecureuil ecureuil;

    public static void setupDatabase() {
        setupQuests();
        setupTrees();

    }

    private static void setupQuests() {
        Log.i("SSAGA", "Setting up quests");

        Quete quete = new Quete("Trouver la princesse", 45.77740, 4.85521,0,0,0);
        quete.save();
        quete = new Quete("Manger des noisettes", 45.78740, 4.84521,0,0,0);
        quete.save();
    }

    /**
     * Charge dans la base de données les arbres remarquables.
     * Exécuté une seule fois à l'initialisation de l'application.
     */
    private static void setupTrees() {
        Log.i("SSAGA", "Setting up trees");
        Arbre arbre = new Arbre(1,"L'Araucaria", 45.773866, 4.856521, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(2,"Le Cèdre du Liban", 45.777702, 4.845253, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Charme", 45.784153, 4.856175, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Chêne pédonculé", 45.780080, 4.853999, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Chêne des Pyrénées", 45.777007, 4.857430, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Chicot du Canada", 45.773713, 4.855845, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Cyprès chauve", 45.775774, 4.854678, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"L'Erable de Montpellier", 45.780666, 4.856438, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"L'Erable Sycomore", 45.776508, 4.847589, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Gleditsia", 45.777611, 4.850740, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Gingko", 45.779242, 4.847372, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Hêtre", 45.783574, 4.854417, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Noyer d'Amérique", 45.777239, 4.848257, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"L'oranger des Osages", 45.773716, 4.856081, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Parrotia Persica", 45.783181, 4.855800, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Plaqueminier", 45.775686, 4.858102, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Pin à écorce de Platane", 45.774158, 4.857296, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Sequoia Gigantea", 45.781227, 4.848895, 5,"Conifère");
        arbre.save();
        arbre = new Arbre(1,"Le Tulipier de Virginie", 45.777915, 4.846491, 5,"Conifère");
        arbre.save();

    }

    public static Quete getQueteCourante() {
        return queteCourante;
    }

    public static Ecureuil getEcureuil() {
        return ecureuil;
    }

    public static void setEcureuil(Ecureuil ecur) {
        ecureuil = ecur;
    }


}
