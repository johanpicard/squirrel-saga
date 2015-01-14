package com.squirrelsaga.vue;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.squirrelsaga.modele.Quete;

import android.os.Bundle;
import android.util.Log;

import java.util.List;


public class Carte extends FragmentActivity implements OnMapReadyCallback {
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
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));

        showQuestsOnMap(map);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        map.moveCamera(CameraUpdateFactory.zoomTo(10));
    }

    private void showQuestsOnMap(GoogleMap map) {
        List<Quete> quetes = Quete.listAll(Quete.class);
        for (Quete quete : quetes) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(quete.latitude, quete.longitude))
                    .title(quete.titre)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_treasure))
                    .anchor((float) 0.3, 1))
            ;
        }
    }


}
