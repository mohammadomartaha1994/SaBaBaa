package com.tampasst.tampass.tampass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProfileShowMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_show_map);
        final Button buttonMaps = (Button) findViewById(R.id.buttonMaps);
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileShowMap.this,ProfileShow.class);
                intent.putExtra("lat",getIntent().getStringExtra("lat"));
                intent.putExtra("lo",getIntent().getStringExtra("lo"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("address",getIntent().getStringExtra("address"));
                intent.putExtra("number",getIntent().getStringExtra("number"));
                intent.putExtra("profileImage",getIntent().getStringExtra("profileImage"));
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("publish",getIntent().getStringExtra("publish"));
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
        final String latetude = getIntent().getStringExtra("lat");
        final String longitude = getIntent().getStringExtra("lo");
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
    }
}
