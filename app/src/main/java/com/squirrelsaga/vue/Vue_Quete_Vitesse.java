package com.squirrelsaga.vue;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteVitesse;

import java.util.concurrent.TimeUnit;

/**
 * Activité permettant de réaliser les quêtes "Vitesse"
 */
public class Vue_Quete_Vitesse extends AbstractQueteActivity implements OnMapReadyCallback {

    private TextView textViewTime;
    private TextView textViewDistance;
    private CounterClass questTimer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();

        setupLocationManager();

    }

    @Override
    protected void onResume() {
        super.onResume();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        configureMapOptions(map);
        QueteVitesse quete = getActiveQuest();
        addQuestMarker(map, quete);
        createAndStartQuestTimer(quete);

        updatePlayerLocation(quete.getLocation());
    }


    /**
     * Initialise le service de géolocalisation
     */
    private void setupLocationManager() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 2, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updatePlayerLocation(location);
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
        });
    }


    /**
     * Met à jour la position du joueur
     *
     * @param location Nouvelle position
     */
    private void updatePlayerLocation(Location location) {
        double distance = location.distanceTo(getActiveQuest().getObjectifLocation());
        textViewDistance.setText(String.format("%.0f", distance) + " m");
        if (distance < 30) {
            questSuccess();
        }
    }

    /**
     * Initialise les composants de la vue
     */
    private void setupViews() {
        setContentView(R.layout.activity_quete_vitesse);
        textViewTime = (TextView) findViewById(R.id.text_time);
        textViewDistance = (TextView) findViewById(R.id.text_distance);
    }


    /**
     * Configure la carte
     *
     */
    private void configureMapOptions(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(false);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.7791898, 4.8533830)));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    /**
     * Démarre le compte à rebours
     *
     */
    private void createAndStartQuestTimer(QueteVitesse quete) {
        questTimer = new CounterClass(quete.getObjectifTemps() * 1000);
        questTimer.start();
    }

    /**
     * Ajoute un marqueur de quête à la carte
     *
     */
    private void addQuestMarker(GoogleMap map, QueteVitesse quete) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(quete.getObjectifLocation().getLatitude(), quete.getObjectifLocation().getLongitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_speed)));
    }


    /**
     * Debug method for testing victory
     *
     */
    @SuppressWarnings("UnusedParameters")
    public void testShortcut(View view) {
        questSuccess();
    }

    /**
     * Le joueur à gagné
     */
    private void questSuccess() {
        cancelTimer();


        updateSquirrelAttributes();

        displaySuccessDialogAndClose();


    }

    /**
     * Arrêt du compte à rebours
     */
    private void cancelTimer() {
        questTimer.cancel();
    }

    /**
     * Met à jour les statistiques de l'écureuil
     */
    private void updateSquirrelAttributes() {
        QueteVitesse quete = getActiveQuest();
        Ecureuil ecureuil = Controleur.getEcureuil();
        ecureuil.setAReussi(quete.getQueteId());
        ecureuil.mange(quete.getNoisette());
        ecureuil.vitesseLevelUp(quete.getRecompense());
        ecureuil.save();
    }

    private void displaySuccessDialogAndClose() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tu as réussi la quête dans le temps imparti !")
                .setTitle("Bravo !")
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Le joueur a perdu
     */
    private void questFailure() {
        cancelTimer();
        displayFailureDialogAndClose();


    }

    private void displayFailureDialogAndClose() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tu n'as pas été assez rapide.")
                .setTitle("Oh non !")
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Permet de récupérer la quête sélectionnée par l'utilisateur sur la carte
     *
     * @return QueteVitesse
     */
    QueteVitesse getActiveQuest() {
        return QueteVitesse.findById(QueteVitesse.class, queteId);
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture) {
            super(millisInFuture, (long) 1000);
        }

        @Override
        public void onFinish() {
            questFailure();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            textViewTime.setText(hms);
        }
    }


}
