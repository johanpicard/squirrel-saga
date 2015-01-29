package com.squirrelsaga.vue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.modele.AbstractQuete;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteForce;

import java.util.ArrayList;

public class Vue_Quete_Force extends AbstractQueteActivity {
    public final static float MAX_DISTANCE_BETWEEN_TREE_AND_PLAYER = 10;

    private String objectif1 = "";
    private int valeur1 = 0;
    private String objectif2 = "";
    private int valeur2 = 0;
    private int valeur1Courante = 0;
    private int valeur2Courante = 0;
    private int tooFar1 = -1;
    private int tooFar2 = -1;

    @Override
    /**
     * Initialise la vue en affichant la question
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quete_force);

        Log.i("SSAGA", getActiveQuest().toString());

        QueteForce quete = getActiveQuest();
        objectif1 = quete.getObjectif1();
        valeur1 = quete.getValeur1();
        objectif2 = quete.getObjectif2();
        valeur2 = quete.getValeur2();
        valeur1Courante = 0;
        valeur2Courante = 0;


        Typeface font = Typeface.createFromAsset(getAssets(), "GrandHotel-Regular.otf");

        TextView text_info = (TextView)findViewById(R.id.text_info);
        text_info.setTypeface(font);
        TextView text_a_ramasser1 = (TextView)findViewById(R.id.text_a_ramasser1);
        text_a_ramasser1.setTypeface(font);
        TextView text_a_ramasser2 = (TextView)findViewById(R.id.text_a_ramasser2);
        text_a_ramasser2.setTypeface(font);

        text_a_ramasser1.setText(objectif1 + "  " + valeur1Courante + "/" + valeur1);
        text_a_ramasser2.setText(objectif2 + "  " + valeur2Courante + "/" + valeur2);

        Button btn_ramasser1 = (Button)findViewById(R.id.BtnRamasser1);
        btn_ramasser1.setTypeface(font);
        Button btn_ramasser2 = (Button)findViewById(R.id.BtnRamasser2);
        btn_ramasser2.setTypeface(font);

        setupLocationManager();
    }

    /**
     * Permet de récupérer la quête sélectionnée par l'utilisateur sur la carte
     * @return QueteForce
     */
    protected QueteForce getActiveQuest() {
        return QueteForce.findById(QueteForce.class,queteId);
    }

    /**
     * Permet de ramasser l'objet désiré si l'on est à portée
     * @param view View
     */
    public void ramasserObjet1(View view){
        if (tooFar1 == -1){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
            if (location != null) {
                tooFar1 = isPlayerTooFar(objectif1, location);
            }
        }
        if(tooFar1 == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(Vue_Quete_Force.this);
            builder.setMessage("Il n'y a pas de \""+ objectif1.toLowerCase() + "\" à proximité !")
                    .setTitle("Trop loin").
                    setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if(tooFar1 == 0) {
            QueteForce quete = getActiveQuest();
            if (valeur1Courante < valeur1) {
                valeur1Courante++;
                if (valeur1Courante == valeur1 && valeur2Courante == valeur2) {
                    terminerQuete(quete);
                }
            }
        } else {
            Toast.makeText(this, "Position GPS introuvable", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Permet de ramasser l'objet désiré si l'on est à portée
     * @param view View
     */
    public void ramasserObjet2(View view){
        if (tooFar2 == -1){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
            if (location != null) {
                tooFar2 = isPlayerTooFar(objectif2, location);
            }
        }
        if(tooFar2 == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(Vue_Quete_Force.this);
            builder.setMessage("Il n'y a pas de \""+ objectif2.toLowerCase() + "\" à proximité !")
                    .setTitle("Trop loin").
                    setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if(tooFar1 == 0){
            QueteForce quete = getActiveQuest();
            if (valeur2Courante < valeur2) {
                valeur2Courante++;
                if (valeur1Courante == valeur1 && valeur2Courante == valeur2) {
                    terminerQuete(quete);
                }
            }
        } else {
            Toast.makeText(this, "Position GPS introuvable", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * Initialise le service de géolocalisation
     */
    private void setupLocationManager() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                tooFar1 = isPlayerTooFar(objectif1, location);
                tooFar2 = isPlayerTooFar(objectif2, location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, locationListener);
    }

    /**
     * Teste si le joueur est trop loin d'un objectif (un type d'arbre)
     * @param objectif l'objectif a atteindre (l'objet à rammasser, lié à un type d'arbre)
     * @param location la localisationa actuelle du joueur
     * @return
     */
    private int isPlayerTooFar(String objectif, Location location) {
        String typeArbre = "";
        if (objectif.equals("Glands"))
            typeArbre = "Chêne";
        else if (objectif.equals("Aiguilles"))
            typeArbre = "Conifère";

        float minDistance = 9999999;
        float distance = 0;

        ArrayList<Location> positionArbres = Controleur.getPositionsArbres(typeArbre);

        if (positionArbres != null) {
            for (int i = 0; i < positionArbres.size(); i++){
                distance = location.distanceTo(positionArbres.get(i));
                if (distance < minDistance)
                    minDistance = distance;
            }
            Log.i("SSAGA", "Distance de l'arbre de type " + typeArbre + " le plus proche : " + String.format("%.2f", minDistance));
            if (minDistance >= MAX_DISTANCE_BETWEEN_TREE_AND_PLAYER)
                return 1;
            else
                return 0;
        } else {
            return 0;
        }

    }

    private void terminerQuete(QueteForce queteCourante) {

        AlertDialog.Builder builder = new AlertDialog.Builder(Vue_Quete_Force.this);
        builder.setMessage("Tu as réussi cette quête ! Tu as gagné "+ queteCourante.getRecompense() + " points de force et " + queteCourante.getNoisette() + " noisettes !")
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

    /**
     * Debug method for testing victory
     *
     */
    @SuppressWarnings("UnusedParameters")
    public void testShortcut(View view) {
        QueteForce quete = getActiveQuest();
        terminerQuete(quete);
    }
}
