package com.squirrelsaga.vue;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Carte extends FragmentActivity implements OnMapReadyCallback {
    public final static String QUETE_ID= "com.squirrelsaga.QUETE_ID";

    private AbstractQuete queteSelected = null;
    Map<String, AbstractQuete> markersQuetes = new HashMap<String, AbstractQuete>();
    private TextView legende;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    @Override
    public void onMapReady(GoogleMap map) {
        legende= (TextView)findViewById(R.id.legende);

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));

        showQuestsOnMap(map);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(45.7791898,	4.8533830)));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                legende.setText(marker.getTitle());
                selectQuete(markersQuetes.get(marker.getId()));
                return false;
            }
        } )

        ;
    }

    private void showQuestsOnMap(GoogleMap map) {
        Ecureuil ecureuil = Controleur.getEcureuil();
        Resources resources = getResources();

        List<AbstractQuete> quetes = new ArrayList<AbstractQuete>();

        quetes.addAll(AbstractQuete.listAll(QueteIntelligence.class));
        quetes.addAll(AbstractQuete.listAll(QueteForce.class));
        quetes.addAll(AbstractQuete.listAll(QueteVitesse.class));
        for (AbstractQuete quete : quetes) {
            Log.i("SSAGA", quete.toString());
            int iconeId = resources.getIdentifier(quete.getIcone(), "drawable",getPackageName());

            Bitmap icone = BitmapFactory.decodeResource(resources, iconeId);
            if (quete.getStatut(ecureuil)== AbstractQuete.Statut.COMPETENCES_INSUFFISANTES){
                icone=convertToGrayscale(icone);
            }

            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(quete.latitude, quete.longitude))
                    .title(quete.getStatut(ecureuil) + " - " + quete.titre)
                    .snippet(quete.getTexte())
                    .icon(BitmapDescriptorFactory.fromBitmap(icone)))
            ;
            markersQuetes.put(marker.getId(),quete);

        }
    }

    private Bitmap convertToGrayscale(Bitmap icone){

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

    private void selectQuete(AbstractQuete quete){
        queteSelected = quete;
        legende.setText(quete.getTitre());

    }

    public void startQuest(View view){
        Intent intent = new Intent(this,Quete.class);
        intent.putExtra(QUETE_ID, queteSelected.getId());
        startActivity(intent);
    }


}
