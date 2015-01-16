package com.squirrelsaga.modele;


import java.util.ArrayList;
import java.util.Map;

public class Ecureuil {

        private String nom;
        private String type;
        private Map<String,Integer> competences;
        private ArrayList<Salle> nid;
        private int nbNoisettes;

        public void mange(int nbNoisettes){
            this.nbNoisettes += nbNoisettes;
        }
}
