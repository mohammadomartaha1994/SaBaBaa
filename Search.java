package com.tampasst.tampass.tampass;

import android.app.Fragment;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Collections;

/**
 * Created by tampass on 3/1/2017.
 */
public class Search extends Fragment implements View.OnClickListener {
    android.support.v7.widget.SearchView searchView;
    Context context;
    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    public MyCustomAdapter myadapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        searchView = (android.support.v7.widget.SearchView) getActivity().findViewById(R.id.searchView);
        searchView.setQueryHint(getResources().getString(R.string.search));
        searchView.setFocusable(true);
        final TextView name = (TextView)getActivity().findViewById(R.id.name);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                if(isInternetConnected(getActivity())){       // IF INTERNET IS ACCESS
                    //String url="http://www.mohammadomartaha.com/tampass/search.php?name="+query+"";
                    try {
                        query= URLEncoder.encode( query, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        Log.d("ddd","not work");
                    }
                    String url="http://www.kufermalik.com/tampass/search.php?name="+query+"";
                    Log.d("url",url);
                    new MyAsyncTaskgetNews().execute(url);
                }
                else                                                     /// IF INTERNET IS NOT ACCESS
                {
                    Toast.makeText(getActivity(),getResources().getString(R.string.No_Internet_Connection), Toast.LENGTH_LONG).show();
                }


                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                name.setText(newText);
                return true;
            }
        });



            myadapter=new MyCustomAdapter(listnewsData);
            ListView lsNews=(ListView)getActivity().findViewById(R.id.listBusiness);
            lsNews.setAdapter(myadapter);


    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();}    /// FUNCTION TO CHECK IF THE INTERNET IS ACCESS OR NOT


    @Override
    public void onClick(View v) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }




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
            LayoutInflater mInflater = getActivity().getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_ticket_businesslist, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            TextView name = (TextView) myView.findViewById(R.id.name);
            name.setText(s.Bname);

            TextView city = (TextView) myView.findViewById(R.id.city);
            city.setText(s.Bcity);

            ImageView image = (ImageView) myView.findViewById(R.id.image);
            Picasso.with(getActivity()).load(s.image).into(image);


            LinearLayout item = (LinearLayout)myView.findViewById(R.id.item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ShowContent_Search.class);
                    intent.putExtra("profileImage",s.image);
                    intent.putExtra("name",s.Bname);
                    intent.putExtra("number",s.phoneN);
                    intent.putExtra("address",s.address);
                    intent.putExtra("lat",s.latitude);
                    intent.putExtra("lo",s.longitude);
                    intent.putExtra("id",s.ID);
                    intent.putExtra("city",s.Bcity);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

//            Button AddFriendBtn = (Button) myView.findViewById(R.id.AddFriendBtn);
//            AddFriendBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    {
//
//
//                        Response.Listener<String> responseListener = new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//
//                                try {
//                                    JSONObject jsonResponse = new JSONObject(response);
//                                    boolean success = jsonResponse.getBoolean("success");
//                                    if (success) {
//                                        Toast.makeText(getApplication(), "Friend Deleted", Toast.LENGTH_LONG).show();
//
//                                    } else {
//                                        AlertDialog.Builder builder = new AlertDialog.Builder(Friendlist.this);
//                                        builder.setMessage("Register Failed")
//                                                .setNegativeButton("Retry", null)
//                                                .create()
//                                                .show();
//                                    }
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//
//                                }
//
//
//                            }
//                        };
//                        nunFriendRequest nunfriendRequest = new nunFriendRequest(currentID, s.ID, responseListener);
//                        RequestQueue queue = Volley.newRequestQueue(Friendlist.this);
//                        queue.add(nunfriendRequest);
//
//                        Intent refresh = new Intent(Friendlist.this, Friendlist.class);
//                        startActivity(refresh);//Start the same Activity
//                        finish(); //finish Activity.
//
//                    }
//
//
//                }
//            });


//            myView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final Intent theItem = new Intent(BusinessList.this, FriendProfileAdded.class);
//                    theItem.putExtra("id", s.id);
//                    theItem.putExtra("name", s.name);
//                    theItem.putExtra("city", s.Bcity);
//                    theItem.putExtra("address", s.address);
//                    theItem.putExtra("image", s.image);
//                    theItem.putExtra("latitude", s.latitude);
//                    theItem.putExtra("longitude", s.longitude);
//                    theItem.putExtra("phone", s.phoneN);
//                    theItem.putExtra("category", s.cat);
//                    startActivity(theItem);
//                }
//            });


            return myView;
        }
    }

    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        final ProgressBuilder loading=new ProgressBuilder(getActivity());
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

                    listnewsData.add(new AdapterItems(user.getString("id"),
                            user.getString("Bname"),user.getString("cat"),user.getString("Bcity"),
                            user.getString("phoneN"),user.getString("address"),
                            user.getString("latitude"),user.getString("longitude"),
                            user.getString("image")));
                }
                Collections.sort(listnewsData);
                myadapter.notifyDataSetChanged();

            } catch (Exception ex) {
                Toast.makeText(getActivity(),getResources().getString(R.string.No_Results),Toast.LENGTH_LONG).show();
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
}
