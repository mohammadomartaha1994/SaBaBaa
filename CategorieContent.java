package com.tampasst.tampass.tampass;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategorieContent extends AppCompatActivity  implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    CategorieContent.MyCustomAdapter myadapter;
    String cat ,activity;
    TextView Categorie;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    String name_show;
    String url;
    String value = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoriecontent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buildGoogleApiClient();

        mGoogleApiClient.connect();
        //CurrentLocation currentLocation = new CurrentLocation(this);
        //currentLocation.getCity();

        CurrentLocation currentLocation = new CurrentLocation(this);

        Log.i("lat : log",currentLocation.getLAT()+":"+currentLocation.getLONG());

        cat=getIntent().getStringExtra("message");
        name_show=getIntent().getStringExtra("name_show");
        activity=getIntent().getStringExtra("activity");

        Categorie=(TextView)findViewById(R.id.Categorie);
        Categorie.setText("~ "+name_show+" ~");

        myadapter=new CategorieContent.MyCustomAdapter(listnewsData);
        ListView lsNews=(ListView)findViewById(R.id.listBusiness);
        lsNews.setAdapter(myadapter);



        if(isInternetConnected(getApplicationContext())){       // IF INTERNET IS ACCESS

            String catX=cat;
            try {
                catX= URLEncoder.encode( catX, "utf-8");
            } catch (UnsupportedEncodingException e) {
                Log.d("ddd","not work");
            }
            if (cat.equals("Everything")) {
                Log.d("TRUE",catX);
                url = "http://www.kufermalik.com/tampass/Content_LIST_everthing.php";
            }

            else{
                Log.d("FALSE",catX);
                url="http://www.kufermalik.com/tampass/Content_LIST_counties.php?cat="+catX+"";
                Log.d("url",url);
            }
            new CategorieContent.MyAsyncTaskgetNews().execute(url);


         }
        else                                                     /// IF INTERNET IS NOT ACCESS
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
        }


    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();}    /// FUNCTION TO CHECK IF THE INTERNET IS ACCESS OR NOT


    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_ticket_businesslist, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            TextView name = (TextView) myView.findViewById(R.id.name);
            name.setText(s.Bname);

            TextView city = (TextView) myView.findViewById(R.id.city);
            city.setText(s.Bcity);

            ImageView image = (ImageView) myView.findViewById(R.id.image);
            Picasso.with(CategorieContent.this).load(s.image).into(image);


            //Log.i("sort"," "+s.score);

            LinearLayout item = (LinearLayout)myView.findViewById(R.id.item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategorieContent.this,ShowContent.class);
                    intent.putExtra("profileImage",s.image);
                    intent.putExtra("name",s.Bname);
                    intent.putExtra("number",s.phoneN);
                    intent.putExtra("address",s.address);
                    intent.putExtra("lat",s.latitude);
                    intent.putExtra("lo",s.longitude);
                    intent.putExtra("id",s.ID);
                    intent.putExtra("city",s.Bcity);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show", getIntent().getStringExtra("name_show"));
                    intent.putExtra("activity", getIntent().getStringExtra("activity"));
                    startActivity(intent);
                    finish();
                }
            });



            return myView;
        }
    }

    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        final ProgressBuilder loading=new ProgressBuilder(CategorieContent.this);
        @Override
        protected void onPreExecute() {

            loading.showProgressDialog();
        }
        @Override
        protected String  doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                String NewsData;
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(7000);

                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    NewsData = ConvertInputToStringNoChange(in);
                    publishProgress(NewsData);
                } finally {
                    urlConnection.disconnect();
                }

            }catch (Exception ex){}
            return null;
        }
        protected void onProgressUpdate(String... progress) {
            listnewsData.clear();
            try {
                JSONArray json=new JSONArray(progress[0]);
                for(int i=0;i<json.length();i++){
                    JSONObject user=json.getJSONObject(i);
                    Double sort =getDistance(Double.valueOf(Session.getLat()),Double.valueOf(Session.getLo()),
                            Double.valueOf(user.getString("latitude")),
                            Double.valueOf(user.getString("longitude")));
                    Log.i("sort",sort+" - "+user.getString("Bname"));

                    Log.i("sort"," - "+Double.valueOf(Session.getLat())+" - "+Double.valueOf(Session.getLo())+" - "+
                            Double.valueOf(user.getString("latitude"))+" - "+
                            Double.valueOf(user.getString("longitude")));

                    listnewsData.add(new AdapterItems(user.getString("id"),
                            user.getString("Bname"),user.getString("cat"),user.getString("Bcity"),
                            user.getString("phoneN"),user.getString("address"),
                            user.getString("latitude"),user.getString("longitude"),
                            user.getString("image"),sort));
                }

                Collections.sort(listnewsData);
                //Collections.reverse(listnewsData);

                myadapter.notifyDataSetChanged();

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.No_Results),Toast.LENGTH_LONG).show();
            }

        }

        protected void onPostExecute(String  result2){
            loading.dismiss();
        }


    }

    public static String ConvertInputToStringNoChange(InputStream inputStream) {

        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String linereultcal="";

        try{
            while((line=bureader.readLine())!=null) {

                linereultcal+=line;

            }
            inputStream.close();


        }catch (Exception ex){}

        return linereultcal;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_all_cat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent;
            if (activity.equals("Start")){
                 intent = new Intent(this,Start.class);
            }
            else if (activity.equals("Categories")){
                 intent = new Intent(this,Categories.class);
            }
            else if (activity.equals("All_Categories")){
                 intent = new Intent(this,All_Categories.class);
            }
            else {
                intent = new Intent(this,Start.class);
            }
            startActivity(intent);
            finish();

        }
        else if (id == R.id.all){

            LayoutInflater layoutInflater1 = LayoutInflater.from(CategorieContent.this);
            final View promptView = layoutInflater1.inflate(R.layout.catigories_dialog_city, null);
            final AlertDialog alert = new AlertDialog.Builder(CategorieContent.this).create();
            final RadioGroup cate = (RadioGroup)promptView.findViewById(R.id.cat);

            cate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    value = ((RadioButton)promptView.findViewById(cate.getCheckedRadioButtonId())).getText().toString();
                    if (value.equals(getResources().getString(R.string.Ramallah)))
                        Log.i("Good","true");
                    url="http://www.kufermalik.com/tampass/Content_LIST.php?cat="+cat+"&city="+value+"";
                    new CategorieContent.MyAsyncTaskgetNews().execute(url);

                    alert.dismiss();
                }
            });

            alert.setCancelable(true);
            alert.setView(promptView);
            alert.show();




        }
        return super.onOptionsItemSelected(item);
    }

    private double getDistance(double fromLat, double fromLon, double toLat, double toLon){
        double radius = 6371;   // Earth radius in km
        double deltaLat = Math.toRadians(toLat - fromLat);
        double deltaLon = Math.toRadians(toLon - fromLon);
        double lat1 = Math.toRadians(fromLat);
        double lat2 = Math.toRadians(toLat);
        double aVal = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
                Math.sin(deltaLon/2) * Math.sin(deltaLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double cVal = 2*Math.atan2(Math.sqrt(aVal), Math.sqrt(1-aVal));

        double distance = radius*cVal;
        Log.d("distance","radius * angle = " +distance);
        return distance;
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


