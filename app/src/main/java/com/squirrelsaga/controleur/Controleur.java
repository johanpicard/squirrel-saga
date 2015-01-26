package com.squirrelsaga.controleur;

import android.util.Log;

import com.squirrelsaga.modele.AbstractQuete;
import com.squirrelsaga.modele.Arbre;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteForce;
import com.squirrelsaga.modele.QueteIntelligence;
import com.squirrelsaga.modele.QueteVitesse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Johan on 04/01/2015.
 */
public class Controleur {

    private static AbstractQuete queteCourante;

    private static Ecureuil ecureuil;

    public static void setupDatabase() {
        setupQuests();
        setupTrees();
        setupBob();
    }

    private static void setupQuests() {
        Log.i("SSAGA", "Setting up quests");
        AbstractQuete queteForce = new QueteForce("Trouver la princesse", 0,0,0,"Elle est là !",45.7767953, 4.8482761, 10, 10);
        queteForce.save();
        Log.i("SSAGA", queteForce.toString());
        AbstractQuete queteVitesse = new QueteVitesse("Manger des noisettes",1,0,0,"Miam", 45.7813447, 4.8513660, 10, 10);
        queteVitesse.setPrerequis(queteForce);
        queteVitesse.save();

        Log.i("SSAGA", queteVitesse.toString());
        AbstractQuete queteIntelligence = new QueteIntelligence("Résoudre l'énigme du hibou",1,0,0,"Trop cool !", 45.7760769,	4.8562584, 10,10,
                "Quelle est la couleur du cheval blanc d'Henri IV?","Blanc");
        queteIntelligence.save();
        Log.i("SSAGA", queteIntelligence.toString());
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

    /**
     * Création d'un écureuil si c'est le premier lancement
     */
    private static void setupBob() {
        Log.i("SSAGA", "Setting up Bob");
        ecureuil = new Ecureuil("Bob","");
        ecureuil.intelligenceLevelUp(3);
        ecureuil.save();
    }

    public static AbstractQuete getQueteCourante() {
        return queteCourante;
    }

    public static Ecureuil getEcureuil() {
        if (null==ecureuil){
            List<Ecureuil> ecureuils = Ecureuil.find(Ecureuil.class, "",null);
            ecureuil = ecureuils.get(0);
        }
        return ecureuil;
    }

    public static void setEcureuil(Ecureuil ecur) {
        ecureuil = ecur;
    }


}
