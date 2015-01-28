package com.squirrelsaga.controleur;

import android.location.Location;
import android.util.Log;

import com.squirrelsaga.modele.AbstractQuete;
import com.squirrelsaga.modele.Arbre;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteForce;
import com.squirrelsaga.modele.QueteIntelligence;
import com.squirrelsaga.modele.QueteVitesse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 04/01/2015.
 */
public class Controleur {

    private static AbstractQuete queteCourante;

    private static Ecureuil ecureuil;

    public static ArrayList<String> objetsARecup;

    /**
     * Lors du premier lancement on initialise la base
     */
    public static void setupDatabase() {
        setupQuests();
        setupTrees();
        setupBob();
    }

    /**
     * Insertion dans la base des quêtes
     */
    private static void setupQuests() {
        objetsARecup = new ArrayList<>();
        objetsARecup.add("Glands");
        objetsARecup.add("Aiguilles");
        objetsARecup.add("Pommes de pins");
        objetsARecup.add("Feuilles d'érable");
        Log.i("SSAGA", "Setting up quests");
        int[] objets = {3,2,0,0};
        AbstractQuete queteForce = new QueteForce(0,"Construire son nid", 0,0,0,"Aller chercher de quoi construire un nid",45.7767953, 4.8482761, 10, 10,objets);
        queteForce.save();
        Log.i("SSAGA", queteForce.toString());

        AbstractQuete queteVitesse0 = new QueteVitesse(1,"Manger des noisettes",0,0,0,"Ton écureuil a faim, dépêche toi !", 45.7813447, 4.8513660, 10, 10,45.7813447, 4.8513660,15);
        Log.i("SSAGA", queteVitesse0.toString());
        queteVitesse0.save();

        AbstractQuete queteVitesse1 = new QueteVitesse(2,"Aller à l'Érable de Montpellier",0,0,0,"Vite !",  45.779831, 4.857700, 10, 10,45.780666, 4.856438,3);
        Log.i("SSAGA", queteVitesse1.toString());
        queteVitesse1.save();

        int[] objets2 = {2,1,0,0};
        AbstractQuete quetePrincipale0 = new QueteForce(3,"Porter des branches",0,5,5,"Rend ton écureuil plus fort !", 45.7853447, 4.8563660, 10, 10,objets2);
        Log.i("SSAGA", quetePrincipale0.toString());
        quetePrincipale0.save();

        AbstractQuete quetePrincipale1 = new QueteVitesse(4,"Sauver la princesse",10,10,10,"Elle est là !", 45.7853447, 4.8563660, 10, 10,45.7767953, 4.8482761,10);
        Log.i("SSAGA", quetePrincipale1.toString());
        quetePrincipale1.setPrerequis(quetePrincipale0.getQueteId());
        quetePrincipale1.save();

        QueteIntelligence queteIntelligence = new QueteIntelligence(5,"Résoudre l'énigme du hibou",5,0,0,"Rend ton écureuil plus intelligent !", 45.7760769, 4.8562584, 10,10,
                "Quelle est la couleur du cheval blanc d'Henri IV?","Blanc");
        queteIntelligence.save();
        Log.i("SSAGA", queteIntelligence.toString());

        AbstractQuete queteIntelligence2 = new QueteIntelligence(6,"L'Érable de Montpellier",0,0,0,"Devine le diamètre de son tronc !", 45.780666, 4.856438, 10,10,
                "Quel est, en mètres, le diamètre de cet arbre ?","5");
        queteIntelligence2.save();
        Log.i("SSAGA", queteIntelligence2.toString());

        AbstractQuete queteIntelligence3 = new QueteIntelligence(7,"Il se porte comme un charme",10,0,5,"Mais qui est-il ?", 45.784153, 4.856175, 10,10,
                "Quel est le nom de cet arbre ?","Charme");
        queteIntelligence3.setPrerequis(queteIntelligence2.getQueteId());
        queteIntelligence3.save();
        Log.i("SSAGA", queteIntelligence3.toString());


        Log.i("SSAGA", queteIntelligence2.toString());
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
        //todo enlever pour la release
        Log.i("SSAGA", "Setting up Bob");
        ecureuil = new Ecureuil("Bob","");
        ecureuil.intelligenceLevelUp(3);
        ecureuil.save();
    }

    public static ArrayList<Location> getPositionsArbres(String typeArbre){
        List<Arbre> arbreConcernes = Arbre.find(Arbre.class, "type = ?", typeArbre);
        ArrayList<Location> listeRetournee = new ArrayList<Location>();

        if(arbreConcernes.size() != 0) {
            for (Arbre a : arbreConcernes) {
                Location positionArbre = new Location(a.get_arbre_nom());
                positionArbre.setLatitude(a.get_arbre_posx());
                positionArbre.setLongitude(a.get_arbre_posy());
                listeRetournee.add(positionArbre);
            }
            return listeRetournee;
        } else {
            return null;
        }


    }

    public static AbstractQuete getQueteCourante() {
        return queteCourante;
    }

    /**
     * Permet de récupérer l'écureuil de l'utilisateur.
     * @return Ecureuil
     */
    public static Ecureuil getEcureuil() {
        Log.i("SSAGA", "récupération de l'écureuil");
        //Si on l'a déjà récupéré depuis la base alors on retourne la variable, sinon on le récupère
        if (null==ecureuil){
            List<Ecureuil> ecureuils = Ecureuil.listAll(Ecureuil.class);
            ecureuil = ecureuils.get(0);
        }
        return ecureuil;
    }

    public static void setEcureuil(Ecureuil ecur) {
        ecureuil = ecur;
    }


}
