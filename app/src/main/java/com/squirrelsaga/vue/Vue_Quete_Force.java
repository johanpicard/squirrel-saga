package com.squirrelsaga.vue;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.modele.AbstractQuete;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteForce;

import java.util.ArrayList;

public class Vue_Quete_Force extends AbstractQueteActivity {
    public final static float MAX_DISTANCE_BETWEEN_TREE_AND_PLAYER = 10;

    @Override
    /**
     * Initialise la vue en affichant la question
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quete_force);

        Log.i("SSAGA", getActiveQuest().toString());

        QueteForce quete = getActiveQuest();
        String objectif1 = quete.getObjectif1();
        int valeur1 = quete.getValeur1();
        String objectif2 = quete.getObjectif2();
        int valeur2 = quete.getValeur2();
        int valeur1Courante = 0;
        int valeur2Courante = 0;


        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");

        TextView text_info = (TextView)findViewById(R.id.text_info);
        text_info.setTypeface(font);
        TextView text_a_ramasser1 = (TextView)findViewById(R.id.text_a_ramasser1);
        text_a_ramasser1.setTypeface(font);
        TextView text_a_ramasser2 = (TextView)findViewById(R.id.text_a_ramasser2);
        text_a_ramasser2.setTypeface(font);

        text_a_ramasser1.setText(objectif1 + " " + valeur1Courante + "/" + valeur1);
        text_a_ramasser1.setText(objectif2 + " " + valeur2Courante + "/" + valeur2);

        Button btn_ramasser1 = (Button)findViewById(R.id.BtnRamasser1);
        btn_ramasser1.setTypeface(font);
        Button btn_ramasser2 = (Button)findViewById(R.id.BtnRamasser2);
        btn_ramasser2.setTypeface(font);

    }

    /**
     * Permet de récupérer la quête sélectionnée par l'utilisateur sur la carte
     * @return QueteIntelligence
     */
    protected QueteForce getActiveQuest() {
        return QueteForce.findById(QueteForce.class,queteId);
    }

    /**
     * Permet de vérifier la réponse
     * @param v View
     * @return int 0 si erreur ou 1 si vrai
     */
    public void rammasserObjet(View v){
        QueteForce quete = getActiveQuest();
        //isPlayerTooFar()

    }

    private boolean isPlayerTooFar(String objectif) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
        String typeArbre = "";
        if (objectif.equals("Glands"))
            typeArbre = "Chene";
        else if (objectif.equals("Aiguilles"))
            typeArbre = "Conifere";
        else if (objectif.equals("Pommes de pins"))
            typeArbre = "Pin";
        else if (objectif.equals("Feuilles d'érable"))
            typeArbre = "Erable";

        float minDistance = 9999999;
        float distance = 0;

        ArrayList<Location> positionArbres = Controleur.getPositionsArbres(typeArbre);

        for (int i = 0; i < positionArbres.size(); i++){
            distance = location.distanceTo(positionArbres.get(i));
            if (distance < minDistance)
                minDistance = distance;
        }
        Log.i("SSAGA", "Distance de l'arbre de type " + typeArbre + " le plus proche : " + String.format("%.2f", minDistance));
        return minDistance >= MAX_DISTANCE_BETWEEN_TREE_AND_PLAYER;
    }

    private void terminerQuete(QueteForce queteCourante) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Vue_Quete_Force.this);
        builder.setMessage("Vous avez réussi cette quête")
                .setTitle("Bravo")
                .setPositiveButton("Retour à la carte", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();


        Ecureuil ecureuil = Controleur.getEcureuil();
        ecureuil.setAReussi(queteCourante.getQueteId());
        ecureuil.mange(queteCourante.getNoisette());
        ecureuil.forceLevelUp(queteCourante.getRecompense());
        ecureuil.save();

    }

    public void retourNid(View view) {
        Intent intent = new Intent(this, Slide_ecureuil.class);
        startActivity(intent);
    }
}
