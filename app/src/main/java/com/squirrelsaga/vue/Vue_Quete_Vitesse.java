package com.squirrelsaga.vue;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteVitesse;

import java.util.concurrent.TimeUnit;


public class Vue_Quete_Vitesse extends AbstractQueteActivity implements OnMapReadyCallback {

    TextView textViewTime;
    TextView textViewDistance;
    private LocationManager locationManager;
    private CounterClass timer;


    @Override
    /**
     * Initialise la vue en affichant le timer
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quete_vitesse);
        textViewTime = (TextView)findViewById(R.id.text_time);
        textViewDistance = (TextView)findViewById(R.id.text_distance);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 2, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                processNewLocation(location);
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

    private void processNewLocation(Location location) {
        double distance = location.distanceTo(getActiveQuest().getObjectifLocation());
        textViewDistance.setText(String.format("%.0f",distance)+" m");
        if(distance < 30){
            questSuccess();
        }
    }

    public void testShortcut(View view) {
        questSuccess();
    }

    private void questSuccess() {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tu as réussi la quête dans le temps imparti !")
                .setTitle("Bravo !")
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                finish();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });

        QueteVitesse quete = getActiveQuest();
        Ecureuil ecureuil = Controleur.getEcureuil();
        ecureuil.setAReussi(quete.getQueteId());
        ecureuil.mange(quete.getNoisette());
        ecureuil.vitesseLevelUp(quete.getRecompense());
        ecureuil.save();
        
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    private void questFailure() {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tu n'as pas été assez rapide.")
                .setTitle("Oh non !")
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
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
     * @return QueteVitesse
     */
    protected QueteVitesse getActiveQuest() {
        return QueteVitesse.findById(QueteVitesse.class, queteId);
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            questFailure();
        }
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            textViewTime.setText(hms);
        }
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

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(false);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.7791898, 4.8533830)));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));

        QueteVitesse quete = getActiveQuest();
        Marker marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(quete.latitude, quete.longitude))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_speed)));

        timer = new CounterClass(quete.getObjectifTemps()*1000,1000);
        timer.start();


        processNewLocation(quete.getLocation());
    }
}
