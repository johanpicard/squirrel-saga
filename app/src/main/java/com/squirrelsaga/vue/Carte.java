package com.squirrelsaga.vue;

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
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Carte des quêtes
 */
public class Carte extends FragmentActivity implements OnMapReadyCallback {
    public final static String QUETE_ID = "com.squirrelsaga.QUETE_ID";
    //TODO : changer pour la release
    private final static float MAX_DISTANCE_BETWEEN_QUEST_AND_PLAYER = 30;
    private final Map<String, AbstractQuete> markersQuetes = new HashMap<>();
    private AbstractQuete queteSelected = null;
    private Button button_go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        button_go = (Button) findViewById(R.id.map_button_go);

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
        getQuestsFromDatabaseAndShowThemOnMap(map);

    }

    /**
     * Initialise les paramètres de la carte
     */
    private void configureMapOptions(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(false);

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                selectQuest(markersQuetes.get(marker.getId()));
                return false;
            }
        });

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Ecureuil ecureuil = Controleur.getEcureuil();
                ViewGroup parent = (ViewGroup) findViewById(R.id.map);
                View v = getLayoutInflater().inflate(R.layout.infobulle_quete, parent, false);
                AbstractQuete quete = markersQuetes.get(marker.getId());

                ((TextView) v.findViewById(R.id.infobulle_quete_text_titre)).setText(quete.getTitre());
                ((TextView) v.findViewById(R.id.infobulle_quete_text_texte)).setText(quete.getTexte());
                String texteCompetences = "Intelligence " + ecureuil.getIntelligence() + " / " + quete.getIntelligenceRequise() + " \nForce " + ecureuil.getForce() + " / " + quete.getForceRequise() + " \nVitesse " + ecureuil.getVitesse() + " / " + quete.getVitesseRequise() + " \n";
                ((TextView) v.findViewById(R.id.infobulle_quete_text_competences)).setText(texteCompetences);
                return v;
            }
        });

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.7791898, 4.8533830)));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    /**
     * Place les marqueurs de quêtes sur la carte
     */
    private void getQuestsFromDatabaseAndShowThemOnMap(GoogleMap map) {

        map.clear();
        Resources resources = getResources();

        List<AbstractQuete> quetes = new ArrayList<>();
        quetes.addAll(AbstractQuete.listAll(QueteIntelligence.class));
        quetes.addAll(AbstractQuete.listAll(QueteForce.class));
        quetes.addAll(AbstractQuete.listAll(QueteVitesse.class));

        for (AbstractQuete quete : quetes) {
            addQuestToMap(map, resources, quete);
        }
    }

    /**
     * Place un marqueur de quête sur la carte
     */
    private void addQuestToMap(GoogleMap map, Resources resources, AbstractQuete quete) {
        int iconeId = resources.getIdentifier(quete.getIcone(), "drawable", getPackageName());
        Ecureuil ecureuil = Controleur.getEcureuil();

        if (quete.getStatut(ecureuil).equals(AbstractQuete.Statut.REUSSIE) ||
                quete.getStatut(ecureuil).equals(AbstractQuete.Statut.PREREQUIS_INSATISFAIT)) {
            return;
        }

        Bitmap icone = BitmapFactory.decodeResource(resources, iconeId);
        if (quete.getStatut(ecureuil).equals(AbstractQuete.Statut.COMPETENCES_INSUFFISANTES)) {
            icone = convertToGrayscale(icone);
        }

        Marker marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(quete.latitude, quete.longitude))
                .title(quete.getStatut(ecureuil) + " - " + quete.titre)
                .snippet(quete.getTexte())
                .icon(BitmapDescriptorFactory.fromBitmap(icone)));
        markersQuetes.put(marker.getId(), quete);
    }

    /**
     * Convertit un bitmap en nuances de gris
     */
    private Bitmap convertToGrayscale(Bitmap icone) {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);


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

    /**
     * Sélectionner une quête lorsque le joueur clique sur son marqueur
     */
    private void selectQuest(AbstractQuete quete) {
        Ecureuil ecureuil = Controleur.getEcureuil();
        queteSelected = quete;
        if (quete.getStatut(ecureuil).equals(AbstractQuete.Statut.DISPONIBLE)) {
            button_go.setText("Commencer la quête !");
            button_go.setEnabled(true);
        } else if (quete.getStatut(ecureuil).equals(AbstractQuete.Statut.COMPETENCES_INSUFFISANTES)) {
            button_go.setText("Compétences insuffisantes");
            button_go.setEnabled(false);
        }


    }

    /**
     * Lance la quête sélectionnée
     */
    @SuppressWarnings("UnusedParameters")
    public void startSelectedQuest(View view) {
        if (queteSelected == null) {
            return;
        }
        if (isPlayerTooFar(queteSelected)) {
            showTooFarDialog();
        } else {

            Class targetActivity;
            if (queteSelected instanceof QueteIntelligence) {
                targetActivity = Vue_Quete_Intelligence.class;
            } else if (queteSelected instanceof QueteForce) {
                targetActivity = Vue_Quete_Force.class;
            } else if (queteSelected instanceof QueteVitesse) {
                targetActivity = Vue_Quete_Vitesse.class;
            } else {
                return;
            }
            Intent intent = new Intent(this, targetActivity);
            intent.putExtra(QUETE_ID, queteSelected.getId());
            startActivity(intent);
        }
    }

    /**
     * Vérifie si le joueur est trop loin pour lancer une quête
     */
    private boolean isPlayerTooFar(AbstractQuete quete) {
        float distance = getDistanceBetweenQuestAndPlayer(quete);
        return distance >= MAX_DISTANCE_BETWEEN_QUEST_AND_PLAYER;
    }

    /**
     * Obtient la distance entre le joueur et une quête
     */
    private float getDistanceBetweenQuestAndPlayer(AbstractQuete quete) {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
        float distance = 10000;
        if (location != null) {
            distance = location.distanceTo(quete.getLocation());
        } else {
            Toast.makeText(this, "Position GPS introuvable", Toast.LENGTH_LONG).show();
        }
        return distance;
    }

    /**
     * Indique au joueur qu'il est trop loin pour lancer une quête
     */
    private void showTooFarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tu es trop loin du départ de la quête.")
                .setTitle("Rapproche-toi !");

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
