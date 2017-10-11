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

public class ChooseLocationForBusiness extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String lat,lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_location_for_business);
        final Button buttonMaps = (Button) findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ChooseLocationForBusiness.this,AddBusiness.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lo",lo);
                startActivity(intent);
                finish();
            }
        });

        final Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLocationForBusiness.this,AddBusiness.class);
                startActivity(intent);
                finish();
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //CurrentLocation currentLocation = new CurrentLocation(this);
        int zoom=Session.getZoom();
        //String city = Session.getCity();

//        Double late = Double.valueOf(Session.getLat());
//        Double longe = Double.valueOf(Session.getLo());
//        LatLng Palestine = new LatLng(late, longe);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Palestine, zoom));
//


        try {
            Log.i("lat",Session.getLat());
            LatLng Palestine = new LatLng(Double.valueOf(Session.getLat()),Double.valueOf(Session.getLo()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Palestine, zoom));
        }catch (Exception e){
            LatLng Palestine = new LatLng(31.55653650346271,34.87547468394041);
            Log.i("lat","jgjk");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Palestine, zoom));
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.Can_not_found_your_location),Toast.LENGTH_LONG).show();
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setBuildingsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title("mark"));
                mMap.setBuildingsEnabled(true);
                if (ActivityCompat.checkSelfPermission(ChooseLocationForBusiness.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ChooseLocationForBusiness.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                lat=latLng.latitude+"";
                lo=latLng.longitude+"";
            }
        });

    }
}
