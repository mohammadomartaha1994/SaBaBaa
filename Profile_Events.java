package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Profile_Events extends AppCompatActivity {
    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    Profile_Events.MyCustomAdapter myadapter;
    String id,profile_Image_String,nameS,numberS,addressS,latetudeS,longitudeS,id_f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile__events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          profile_Image_String = getIntent().getStringExtra("profileImage");
          nameS = getIntent().getStringExtra("name");
          numberS = getIntent().getStringExtra("number");
          addressS = getIntent().getStringExtra("address");
          latetudeS = getIntent().getStringExtra("lat");
          longitudeS = getIntent().getStringExtra("lo");
          id = getIntent().getStringExtra("id");
          id_f=id;


        Button add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Events.this,Profile_Events_Add.class);
                intent.putExtra("lat",latetudeS);
                intent.putExtra("lo",longitudeS);
                intent.putExtra("publish",getIntent().getStringExtra("publish"));
                intent.putExtra("name",nameS);
                intent.putExtra("address",addressS);
                intent.putExtra("number",numberS);
                intent.putExtra("id",id);
                intent.putExtra("profileImage",profile_Image_String);
                startActivity(intent);
                finish();
            }
        });


        myadapter=new Profile_Events.MyCustomAdapter(listnewsData);
        ListView lsNews=(ListView)findViewById(R.id.listBusiness);
        lsNews.setAdapter(myadapter);


        String url="http://www.kufermalik.com/tampass/events_list.php?id="+getIntent().getStringExtra("id")+"";

        new Profile_Events.MyAsyncTaskgetNews().execute(url);
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
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.profileeventlist, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            TextView Business_name = (TextView) myView.findViewById(R.id.name);
            Business_name.setText(s.name);

            TextView Business_city = (TextView) myView.findViewById(R.id.desc);
            Business_city.setText(s.desc);

            ImageView image = (ImageView) myView.findViewById(R.id.image);
            Picasso.with(Profile_Events.this).load(s.image).into(image);



            Button delete = (Button) myView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /////////////////////////////////////////////////////////////



                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Profile_Events.this);
                    builder1.setMessage(getResources().getString(R.string.Delete_this_Event));
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            getResources().getString(R.string.Delete),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Toast.makeText(getApplication(),getResources().getString(R.string.Deleted),Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Profile_Events.this,Profile_Events.class);
                                    intent.putExtra("lat",latetudeS);
                                    intent.putExtra("lo",longitudeS);
                                    intent.putExtra("name",nameS);
                                    intent.putExtra("address",addressS);
                                    intent.putExtra("publish",getIntent().getStringExtra("publish"));
                                    intent.putExtra("number",numberS);
                                    intent.putExtra("id",id_f);
                                    intent.putExtra("profileImage",profile_Image_String);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Events.this);
                                    builder.setMessage("Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    };
                    deleteImageRequest deleteImageRequest = new deleteImageRequest(s.ID, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Profile_Events.this);
                    queue.add(deleteImageRequest);




                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            getResources().getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();







                    /////////////////////////////////////////////////////////


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
        final ProgressBuilder loading=new ProgressBuilder(Profile_Events.this);
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

            try {
                JSONArray json=new JSONArray(progress[0]);
                for(int i=0;i<json.length();i++){
                    JSONObject user=json.getJSONObject(i);
                    listnewsData.add(new AdapterItems(user.getString("id"),
                            user.getString("descc"),
                            user.getString("image"),
                            user.getString("name"),
                            user.getString("id_owner")));
                }
                myadapter.notifyDataSetChanged();

            } catch (Exception ex) {
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,ProfileShow.class);
            intent.putExtra("lat",latetudeS);
            intent.putExtra("lo",longitudeS);
            intent.putExtra("name",nameS);
            intent.putExtra("publish",getIntent().getStringExtra("publish"));
            intent.putExtra("address",addressS);
            intent.putExtra("number",numberS);
            intent.putExtra("id", getIntent().getStringExtra("id"));
            intent.putExtra("profileImage",profile_Image_String);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
