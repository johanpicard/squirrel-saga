package com.squirrelsaga.vue;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squirrelsaga.controleur.Controleur;
import com.squirrelsaga.modele.AbstractQuete;
import com.squirrelsaga.modele.Ecureuil;
import com.squirrelsaga.modele.QueteForce;
import com.squirrelsaga.modele.QueteIntelligence;
import com.squirrelsaga.modele.QueteVitesse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Carte extends FragmentActivity implements OnMapReadyCallback {
    public final static String QUETE_ID = "com.squirrelsaga.QUETE_ID";
    //TODO : changer pour la release
    public final static float MAX_DISTANCE_BETWEEN_QUEST_AND_PLAYER = 5000;

    private AbstractQuete queteSelected = null;
    Map<String, AbstractQuete> markersQuetes = new HashMap<String, AbstractQuete>();
    private Button button_go;
    Ecureuil ecureuil = Controleur.getEcureuil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        button_go = (Button) findViewById(R.id.map_button_go);



    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(false);

        setupCustomInfoWindows(map);

        showQuestsOnMap(map);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.7791898, 4.8533830)));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                selectQuete(markersQuetes.get(marker.getId()));
                return false;
            }
        })        ;
    }

    private void setupCustomInfoWindows(GoogleMap map) {
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.infobulle_quete, null);
                AbstractQuete quete = markersQuetes.get(marker.getId());

                ((TextView) v.findViewById(R.id.infobulle_quete_text_titre)).setText(quete.getTitre());
                ((TextView) v.findViewById(R.id.infobulle_quete_text_texte)).setText(quete.getTexte());
                String texteCompetences = "Intelligence " + ecureuil.getIntelligence() + " / " + quete.getIntelligenceRequise() + " \nForce " + ecureuil.getForce() + " / " + quete.getForceRequise() + " \nVitesse " + ecureuil.getVitesse() + " / " + quete.getVitesseRequise() + " \n";
                ((TextView) v.findViewById(R.id.infobulle_quete_text_competences)).setText(texteCompetences);
                return v;
            }
        });
    }

    private void showQuestsOnMap(GoogleMap map) {

        Resources resources = getResources();

        List<AbstractQuete> quetes = new ArrayList<AbstractQuete>();

        quetes.addAll(AbstractQuete.listAll(QueteIntelligence.class));
        quetes.addAll(AbstractQuete.listAll(QueteForce.class));
        quetes.addAll(AbstractQuete.listAll(QueteVitesse.class));
        for (AbstractQuete quete : quetes) {
            Log.i("SSAGA", quete.toString());
            int iconeId = resources.getIdentifier(quete.getIcone(), "drawable", getPackageName());

            if(quete.getStatut(ecureuil) == AbstractQuete.Statut.REUSSIE ||
                    quete.getStatut(ecureuil) == AbstractQuete.Statut.PREREQUIS_INSATISFAIT){
                continue;
            }

            Bitmap icone = BitmapFactory.decodeResource(resources, iconeId);
            if (quete.getStatut(ecureuil) == AbstractQuete.Statut.COMPETENCES_INSUFFISANTES) {
                icone = convertToGrayscale(icone);
            }

            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(quete.latitude, quete.longitude))
                    .title(quete.getStatut(ecureuil) + " - " + quete.titre)
                    .snippet(quete.getTexte())
                    .icon(BitmapDescriptorFactory.fromBitmap(icone)));
            markersQuetes.put(marker.getId(), quete);

        }
    }

    private Bitmap convertToGrayscale(Bitmap icone) {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        Canvas c = new Canvas();
        Paint p = new Paint();
        ColorMatrix cm = new ColorMatrix();

        cm.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        p.setColorFilter(filter);

        Bitmap iconeNB = Bitmap.createBitmap(icone.getWidth(), icone.getHeight(), icone.getConfig());
        Canvas canvas = new Canvas(iconeNB);

        canvas.drawBitmap(icone, 0f, 0f, p);

        return iconeNB;
    }

    private void selectQuete(AbstractQuete quete) {
        queteSelected = quete;
        if (quete.getStatut(ecureuil) == AbstractQuete.Statut.DISPONIBLE) {
            button_go.setText("Commencer la quête !");
            button_go.setEnabled(true);
        } else if (quete.getStatut(ecureuil) == AbstractQuete.Statut.COMPETENCES_INSUFFISANTES) {
            button_go.setText("Compétences insuffisantes");
            button_go.setEnabled(false);
        }


    }

    public void startQuest(View view) {
        if(queteSelected==null){
            return;
        }
        if (isPlayerTooFar(queteSelected)) {
            showTooFarDialog(this,queteSelected);
        } else {

            Class targetActivity = null;
            if (queteSelected instanceof QueteIntelligence) {
                targetActivity = VueQueteIntelligence.class;
            } else if (queteSelected instanceof QueteForce) {
                return;
            } else if (queteSelected instanceof QueteVitesse) {
                return;
            } else {
                return;
            }
            Intent intent = new Intent(this, targetActivity);
            intent.putExtra(QUETE_ID, queteSelected.getId());
            startActivity(intent);
        }
    }

    private boolean isPlayerTooFar(AbstractQuete quete) {
        float distance = getDistanceBetweenQuestAndPlayer(quete);
        return distance >= MAX_DISTANCE_BETWEEN_QUEST_AND_PLAYER;
    }

    private float getDistanceBetweenQuestAndPlayer(AbstractQuete quete) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
        return location.distanceTo(quete.getLocation());
    }

    private void showTooFarDialog(Activity activity, AbstractQuete quete) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Tu es trop loin du départ de la quête.")
                .setTitle("Rapproche-toi !");

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
