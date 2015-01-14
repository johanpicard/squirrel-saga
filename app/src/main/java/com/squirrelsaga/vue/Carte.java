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

import android.os.Bundle;



public class Carte  extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //test persistance
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));



        map.addMarker(new MarkerOptions()
                .position(new LatLng(45.77740, 4.85521))
                .title("Quête")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_treasure))
                .anchor((float) 0.3, 1))
                ;


        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(),location.getLongitude())));
        map.moveCamera(CameraUpdateFactory.zoomTo(10));
    }
}
