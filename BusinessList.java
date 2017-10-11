package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class BusinessList extends AppCompatActivity {
    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    BusinessList.MyCustomAdapter myadapter;
    TextView Business_name,Business_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



            String currentUserName;
            String currentEmail;
            int currentId;

            currentUserName = Session.getUsername(); //session
            currentEmail=Session.getEmail();
            currentId=Session.getId();


        Button addBusiness = (Button)findViewById(R.id.addBusiness);
        addBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessList.this,AddBusiness.class);
                intent.putExtra("lat","");
                intent.putExtra("lo","");
                startActivity(intent);
                finish();
            }
        });


        myadapter=new BusinessList.MyCustomAdapter(listnewsData);
        ListView lsNews=(ListView)findViewById(R.id.listBusiness);
        lsNews.setAdapter(myadapter);


        String url="http://www.kufermalik.com/tampass/businessList.php?id_preson="+currentId+"";

        new BusinessList.MyAsyncTaskgetNews().execute(url);

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
            View myView = mInflater.inflate(R.layout.layout_ticket_businesslist_a, null);

            final AdapterItems s = listnewsDataAdpater.get(position);

            Business_name = (TextView) myView.findViewById(R.id.name);
            Business_name.setText(s.Bname);

            Business_city = (TextView) myView.findViewById(R.id.city);
            Business_city.setText(s.Bcity);

            ImageView image = (ImageView) myView.findViewById(R.id.image);
            Picasso.with(BusinessList.this).load(s.image).into(image);

            TextView publish = (TextView) myView.findViewById(R.id.publish);
            if (s.publish.equals("ture")) {
                publish.setText("Published");
                publish.setTextColor(Color.GREEN);
            }
            else{
                publish.setText("Unpublished");
                publish.setTextColor(Color.RED);
            }


            LinearLayout item = (LinearLayout)myView.findViewById(R.id.item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BusinessList.this,ProfileShow.class);
                    intent.putExtra("profileImage",s.image);
                    intent.putExtra("name",s.Bname);
                    intent.putExtra("number",s.phoneN);
                    intent.putExtra("address",s.address);
                    intent.putExtra("lat",s.latitude);
                    intent.putExtra("lo",s.longitude);
                    intent.putExtra("id",s.ID);
                    intent.putExtra("publish",s.publish);
                    startActivity(intent);
                    finish();
                }
            });




            return myView;
        }
    }

    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        final ProgressBuilder loading=new ProgressBuilder(BusinessList.this);
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
                            user.getString("Bname"),user.getString("cat"),user.getString("Bcity"),
                            user.getString("phoneN"),user.getString("address"),
                            user.getString("latitude"),user.getString("longitude"),
                            user.getString("image"),user.getString("publish")));
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,FirstStepCreateAcount.class);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.home_icon){
            Intent intent = new Intent(this,Start.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
