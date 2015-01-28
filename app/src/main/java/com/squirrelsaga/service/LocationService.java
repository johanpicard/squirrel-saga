package com.squirrelsaga.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service implements LocationListener {

    private LocationManager locationManager;

    private Location location;

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Log.i("SSAGA", "locationOnStart");
        if (intent.getAction().equals("startListening")) {
            Log.i("SSAGA", "startListening");
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 2, this);
        }
        else {
            if (intent.getAction().equals("stopListening")) {
                Log.i("SSAGA", "stopListening");
                locationManager.removeUpdates(this);
                locationManager = null;
            }
        }

        return START_STICKY;

    }

    @Override
    public IBinder onBind(final Intent intent) {
        Log.i("SSAGA", "bound");
        return null;
    }

    public void onLocationChanged(final Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("SSAGA", "status"+status);
    }

    public void onProviderDisabled(final String provider) {
    }

    public void onProviderEnabled(final String provider) {
    }


}