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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProfileMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
     String latetude;
     String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        final Button buttonMaps = (Button) findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileMap.this,Profile.class);
                intent.putExtra("lat",latetude);
                intent.putExtra("publish",getIntent().getStringExtra("publish"));

                intent.putExtra("lo",longitude);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("address",getIntent().getStringExtra("address"));
                intent.putExtra("number",getIntent().getStringExtra("number"));
                intent.putExtra("profileImage",getIntent().getStringExtra("profileImage"));
                startActivity(intent);
                finish();
            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
          latetude = getIntent().getStringExtra("lat");
          longitude = getIntent().getStringExtra("lo");
        final String name = getIntent().getStringExtra("name");
        Double late = Double.valueOf(latetude);
        Double longe = Double.valueOf(longitude);
        LatLng loc = new LatLng(late, longe);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 16));
        mMap.addMarker(new MarkerOptions().position(loc).title(name));


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
                mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,latLng.longitude)).title(getIntent().getStringExtra("name")));
                mMap.setBuildingsEnabled(true);
                if (ActivityCompat.checkSelfPermission(ProfileMap.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ProfileMap.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);

                latetude=String.valueOf(latLng.latitude);
                longitude=String.valueOf(latLng.longitude);

            }
        });
    }
}
