package com.tampasst.tampass.tampass;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class Start extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    TextView nearby_text_;
    TextView search_text_;
    TextView activity_text_;
    TextView bookmarks_text_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


//




        //new LocationActivity(this);






//
        CurrentLocation currentLocation = new CurrentLocation(this);
        Double LAT = currentLocation.getLAT();
        Double LOG = currentLocation.getLONG();
        int zoom = currentLocation.getZoom();


        Log.d("LAT ",LAT+"");
        Log.d("LOG",LOG+"");
        Session.setLatitude(LAT);
        Session.setLongitude(LOG);
        Session.setZoom(zoom);


        Log.i("location",""+isLocationEnabled(this)+"-"+LAT+"-"+LOG);
        if (!isLocationEnabled(this)){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
        }

        Nearby fragment = new Nearby();
        getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nearby_text_ = (TextView)findViewById(R.id.nearby_text_);
        search_text_ = (TextView)findViewById(R.id.search_text_);
        activity_text_ = (TextView)findViewById(R.id.activity_text_);
        bookmarks_text_ = (TextView)findViewById(R.id.bookmarks_text_);

        nearby_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        final LinearLayout liner_one = (LinearLayout)findViewById(R.id.liner_one);
        liner_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearby_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                search_text_.setBackgroundColor(Color.WHITE);
                activity_text_.setBackgroundColor(Color.WHITE);
                bookmarks_text_.setBackgroundColor(Color.WHITE);
                Nearby fragment = new Nearby();
                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
            }
        });

        final LinearLayout liner_tow = (LinearLayout)findViewById(R.id.liner_tow);
        liner_tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nearby_text_.setBackgroundColor(Color.WHITE);
                activity_text_.setBackgroundColor(Color.WHITE);
                bookmarks_text_.setBackgroundColor(Color.WHITE);
                Search fragment = new Search();
                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
            }
        });

        final LinearLayout liner_three = (LinearLayout)findViewById(R.id.liner_three);
        liner_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearby_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                search_text_.setBackgroundColor(Color.WHITE);
                activity_text_.setBackgroundColor(Color.WHITE);
                bookmarks_text_.setBackgroundColor(Color.WHITE);
                Intent intent = new Intent(Start.this,Maps.class);
                startActivity(intent);
                finish();
            }
        });
        final LinearLayout liner_fuore = (LinearLayout)findViewById(R.id.liner_fuore);
        liner_fuore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarks_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nearby_text_.setBackgroundColor(Color.WHITE);
                search_text_.setBackgroundColor(Color.WHITE);
                activity_text_.setBackgroundColor(Color.WHITE);
                Bookmarks fragment = new Bookmarks();
                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
            }
        });


        buildGoogleApiClient();
//
        mGoogleApiClient.connect();

    }


    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nearby, menu);
//        SearchView searchView = (SearchView)menu.findItem(R.id.searchbar).getActionView();
//        //searchView.getMaxWidth();
//        //searchView.setBackgroundColor(Color.WHITE);
//        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
//                //autoCompleteTextView.setText("Search");
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getApplicationContext(),newText,Toast.LENGTH_LONG).show();
//                //autoCompleteTextView.setTextColor(Color.BLACK);// error
//
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.searchbar) {
            Search fragment = new Search();
            search_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            nearby_text_.setBackgroundColor(Color.WHITE);
            activity_text_.setBackgroundColor(Color.WHITE);
            bookmarks_text_.setBackgroundColor(Color.WHITE);
            getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nearby_menu) {
            nearby_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            search_text_.setBackgroundColor(Color.WHITE);
            activity_text_.setBackgroundColor(Color.WHITE);
            bookmarks_text_.setBackgroundColor(Color.WHITE);
            Nearby fragment = new Nearby();
            getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();

        } else if (id == R.id.search_menu) {

            search_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            nearby_text_.setBackgroundColor(Color.WHITE);
            activity_text_.setBackgroundColor(Color.WHITE);
            bookmarks_text_.setBackgroundColor(Color.WHITE);
            Search fragment = new Search();
            getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();

        } else if (id == R.id.activity_menu) {
            nearby_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            search_text_.setBackgroundColor(Color.WHITE);
            activity_text_.setBackgroundColor(Color.WHITE);
            bookmarks_text_.setBackgroundColor(Color.WHITE);
            Intent intent = new Intent(Start.this,Maps.class);
            startActivity(intent);

        } else if (id == R.id.bookmarks_menu) {
            bookmarks_text_.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            nearby_text_.setBackgroundColor(Color.WHITE);
            search_text_.setBackgroundColor(Color.WHITE);
            activity_text_.setBackgroundColor(Color.WHITE);
            Bookmarks fragment = new Bookmarks();
            getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();

        } else if (id == R.id.profile) {
            Intent intent = new Intent(Start.this,FirstStepCreateAcount.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.events) {
            Intent intent = new Intent(this,All_Events.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.recentlyViewed){
            Intent intent = new Intent(this,Recently_Viewed.class);
            startActivity(intent);
            finish();        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Log.d("lat on location changed",String.valueOf(mLastLocation.getLatitude()));
            Log.d("lat on location changed",String.valueOf(mLastLocation.getLongitude()));
            Log.d("LAT ",String.valueOf(mLastLocation.getLatitude())+"");
            Log.d("LOG",String.valueOf(mLastLocation.getLongitude())+"");
            Session.setLat(String.valueOf(mLastLocation.getLatitude()));
            Session.setLo(String.valueOf(mLastLocation.getLongitude()));
            Session.setZoom(13);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); //10 seconds
        mLocationRequest.setFastestInterval(5000); //5 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setSmallestDisplacement(1); //1 meter

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) Start.this);


    }

    @Override
    public void onConnectionSuspended(int i) {
        //Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    protected synchronized void buildGoogleApiClient() {
        //Toast.makeText(this, "buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
}

