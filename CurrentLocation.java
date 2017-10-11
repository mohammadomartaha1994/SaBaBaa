package com.tampasst.tampass.tampass;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by tampass on 3/13/2017.
 */

public class CurrentLocation extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    //location object to hold best location
    private Location myBestLocation;

    //Location listener
    private LocationListener myLocListener;

    //Location manager
    private LocationManager myLocManager;

    private static float MIN_ACCURACY = 10;
    private static float MIN_DISTANCE = 10;
    private static float TWO_MINUTES = 120000;

    private static int POLLING_FREQ = 60000;
    private static int MEASURE_TIME = 60000;

    double latitude = 31.55653650346271;
    double longitude = 34.87547468394041;



    Context context;
    String city = "palestine";
    int zoom = 8;

    public CurrentLocation(Context context) {
        this.context = context;
    }

    public Double getLAT() {


        myLocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (myLocManager == null)
            finish();


        myLocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (myBestLocation == null || location.getAccuracy() < myBestLocation.getAccuracy()) {
                    myBestLocation = location;
                }
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

        List<String> matchingProviders = myLocManager.getAllProviders();
        for (String provider : matchingProviders) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return 0.0;
            }
            Location location = myLocManager.getLastKnownLocation(provider);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }


        return latitude;
    }

    public Double getLONG() {


        myLocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (myLocManager == null)
            finish();


        myLocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (myBestLocation == null || location.getAccuracy() < myBestLocation.getAccuracy()) {
                    myBestLocation = location;
                }
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

        List<String> matchingProviders = myLocManager.getAllProviders();
        for (String provider : matchingProviders) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return 0.0;
            }
            Location location = myLocManager.getLastKnownLocation(provider);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }

        return longitude;
    }

    public int getZoom() {


        myLocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (myLocManager == null)
            finish();


        myLocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (myBestLocation == null || location.getAccuracy() < myBestLocation.getAccuracy()) {
                    myBestLocation = location;
                }
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

        List<String> matchingProviders = myLocManager.getAllProviders();
        for (String provider : matchingProviders) {

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return 0;
            }
            Location location = myLocManager.getLastKnownLocation(provider);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                zoom = 13;
            }
        }

        return zoom;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission
                        (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myLocManager.removeUpdates(myLocListener);
    }

    protected void onResume() {
        super.onResume();
        if (null == myBestLocation
                || myBestLocation.getAccuracy() > MIN_ACCURACY
                || myBestLocation.getTime() < System.currentTimeMillis() - TWO_MINUTES) {
            if (null != myLocManager
                    .getProvider(LocationManager.NETWORK_PROVIDER)) {

            }
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            myLocManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, POLLING_FREQ,
                    MIN_DISTANCE, myLocListener);
        }
        if (null != myLocManager
                .getProvider(LocationManager.GPS_PROVIDER)) {
            myLocManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, POLLING_FREQ,
                    MIN_DISTANCE, myLocListener);
        }

        Executors.newScheduledThreadPool(1).schedule(new Runnable() {
            @Override
            public void run() {

                if (ActivityCompat.checkSelfPermission
                        (getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission
                                (getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                myLocManager.removeUpdates(myLocListener);
            }
        }, MEASURE_TIME, TimeUnit.MILLISECONDS);

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {


        Log.d("onConnected","Connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("onConnectionSuspended","Stopd");

    }
}
