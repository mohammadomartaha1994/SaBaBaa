package com.tampasst.tampass.tampass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        final Button buttonMaps = (Button) findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Maps.this,Start.class);
                startActivity(intent);
                finish();
            }
        });

        CurrentLocation currentLocation = new CurrentLocation(this);
        //currentLocation.getLAT();
        //currentLocation.getLONG();
        Log.d("lat : log",currentLocation.getLAT()+" "+currentLocation.getLONG());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
         mMap = googleMap;
        googleMap = mMap;
        CurrentLocation currentLocation = new CurrentLocation(this);
        int zoom=Session.getZoom();
        //String city = Session.getCity();
        try {
            Log.i("lat",Session.getLat());
            LatLng Palestine = new LatLng(Double.valueOf(Session.getLat()),Double.valueOf(Session.getLo()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Palestine, zoom));
        }catch (Exception e){
            LatLng Palestine = new LatLng(31.55653650346271,34.87547468394041);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Palestine, zoom));
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.Can_not_found_your_location),Toast.LENGTH_LONG).show();
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng nablus = new LatLng(32.2243083,35.2651889);
        mMap.addMarker(new MarkerOptions().position(nablus).title("Nablus City"));

        LatLng Ramallah = new LatLng(31.9039761,35.2056896);
        mMap.addMarker(new MarkerOptions().position(Ramallah).title("Ramallah City"));

        LatLng Hebron = new LatLng(31.5332274,35.1082803);
        mMap.addMarker(new MarkerOptions().position(Hebron).title("Hebron City"));



        LatLng Tulkarm = new LatLng(32.307929,35.0461382);
        mMap.addMarker(new MarkerOptions().position(Tulkarm).title("Tulkarm City"));

        LatLng Jenin = new LatLng(32.4572483,35.3197237);
        mMap.addMarker(new MarkerOptions().position(Jenin).title("Jenin City"));

        LatLng Bethleem = new LatLng(31.7053821,35.2046312);
        mMap.addMarker(new MarkerOptions().position(Bethleem).title("Bethleem City"));






        LatLng Qalqilya = new LatLng(32.196015,34.9837017);
        mMap.addMarker(new MarkerOptions().position(Qalqilya).title("Qalqilya City"));


        LatLng Jericho = new LatLng(31.8594711,35.4820175);
        mMap.addMarker(new MarkerOptions().position(Jericho).title("Jericho City"));


        LatLng Jerusalem = new LatLng(31.7780191,35.2375966);
        mMap.addMarker(new MarkerOptions().position(Jerusalem).title("Jerusalem City"));


        LatLng Salfit  = new LatLng(32.085094,35.1830207);
        mMap.addMarker(new MarkerOptions().position(Salfit).title("Salfit  City"));


        LatLng Gaza = new LatLng(31.5016951,34.484354);
        mMap.addMarker(new MarkerOptions().position(Gaza).title("Gaza City"));


        LatLng KhanYunis = new LatLng(31.3462005,34.3061873);
        mMap.addMarker(new MarkerOptions().position(KhanYunis).title("Khan Yunis City"));


        LatLng Rafah = new LatLng(31.29678,34.2456707);
        mMap.addMarker(new MarkerOptions().position(Rafah).title("Rafah City"));


        LatLng DeirBalah = new LatLng(31.417139,34.3531197);
        mMap.addMarker(new MarkerOptions().position(DeirBalah).title("Deir al-Balah City"));


        LatLng BeitHanoun = new LatLng(31.7053821,35.2046312);
        mMap.addMarker(new MarkerOptions().position(BeitHanoun).title("Beit Hanoun City"));




        mMap.setBuildingsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
////                mMap.clear();
////                mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("mark"));
////                mMap.setBuildingsEnabled(true);
////                if (ActivityCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
////                    return;
////                }
////                mMap.setMyLocationEnabled(true);
////                mMap.getUiSettings().setZoomControlsEnabled(true);
////                Toast.makeText(getApplicationContext(),""+latLng.latitude+"  "+latLng.longitude,Toast.LENGTH_LONG).show();
//            }
//        });

    }
}
