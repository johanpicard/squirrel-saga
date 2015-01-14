package com.squirrelsaga.controleur;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.squirrelsaga.modele.Quete;

/**
 * Created by Johan on 04/01/2015.
 */
public class Controleur {

    public static void setupDatabase() {
        setupQuests();
        setupTrees();

    }


    private static void setupQuests() {
        Log.i("SSAGA", "Setting up quests");

        Quete quete = new Quete("Trouver la princesse", 45.77740, 4.85521);
        quete.save();
        quete = new Quete("Manger des noisettes", 45.78740, 4.84521);
        quete.save();
    }

    private static void setupTrees() {
        Log.i("SSAGA", "Setting up trees");


    }


}
