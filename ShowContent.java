package com.tampasst.tampass.tampass;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ShowContent extends AppCompatActivity {
    String cat ,activity;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbManager=new DBManager(this);




        TextView name = (TextView)findViewById(R.id.name);
        name.setText("~"+getIntent().getStringExtra("name")+"~");

        TextView address = (TextView)findViewById(R.id.address);
        address.setText(getIntent().getStringExtra("address"));

        TextView city = (TextView)findViewById(R.id.city);
        city.setText(getIntent().getStringExtra("city"));


        ImageView map = (ImageView)findViewById(R.id.locationOnMap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowContent.this,Show_Content_Map.class);
                intent.putExtra("profileImage",getIntent().getStringExtra("profileImage"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("number",getIntent().getStringExtra("number"));
                intent.putExtra("address",getIntent().getStringExtra("address"));
                intent.putExtra("lat",getIntent().getStringExtra("lat"));
                intent.putExtra("lo",getIntent().getStringExtra("lo"));
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("city",getIntent().getStringExtra("city"));
                intent.putExtra("message", getIntent().getStringExtra("message"));
                intent.putExtra("activity", getIntent().getStringExtra("activity"));
                intent.putExtra("name_show", getIntent().getStringExtra("name_show"));
                startActivity(intent);
                finish();
            }
        });

        ImageView events = (ImageView)findViewById(R.id.events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowContent.this,ShowContent_Events.class);
                intent.putExtra("profileImage",getIntent().getStringExtra("profileImage"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("number",getIntent().getStringExtra("number"));
                intent.putExtra("address",getIntent().getStringExtra("address"));
                intent.putExtra("lat",getIntent().getStringExtra("lat"));
                intent.putExtra("lo",getIntent().getStringExtra("lo"));
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("city",getIntent().getStringExtra("city"));
                intent.putExtra("name_show", getIntent().getStringExtra("name_show"));
                intent.putExtra("message", getIntent().getStringExtra("message"));
                intent.putExtra("activity", getIntent().getStringExtra("activity"));
                startActivity(intent);
                finish();
            }
        });



        Button call = (Button)findViewById(R.id.Call);
        call.setText("call - "+getIntent().getStringExtra("number"));
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+getIntent().getStringExtra("number")));
                if(intent.resolveActivity(getPackageManager()) != null ){
                    startActivity(intent);

                }
            }
        });

        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(ShowContent.this).load(getIntent().getStringExtra("profileImage")).into(image);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bookmark, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            ContentValues values=new ContentValues();
            values.put(DBManager.NAME,getIntent().getStringExtra("name"));
            values.put(DBManager.CALL,getIntent().getStringExtra("number"));
            values.put(DBManager.CITY,getIntent().getStringExtra("city"));
            values.put(DBManager.CAT,getIntent().getStringExtra("message"));
            values.put(DBManager.ADDRESS,getIntent().getStringExtra("address"));
            dbManager.InsertR(values);

            Intent intent = new Intent(this,CategorieContent.class);
            intent.putExtra("message", getIntent().getStringExtra("message"));
            intent.putExtra("activity", getIntent().getStringExtra("activity"));
            intent.putExtra("name_show", getIntent().getStringExtra("name_show"));
            startActivity(intent);
            finish();
        }
        else if (id == R.id.home_icon){
            ContentValues values=new ContentValues();
            values.put(DBManager.NAME,getIntent().getStringExtra("name"));
            values.put(DBManager.CALL,getIntent().getStringExtra("number"));
            values.put(DBManager.CITY,getIntent().getStringExtra("city"));
            values.put(DBManager.CAT,getIntent().getStringExtra("message"));
            values.put(DBManager.ADDRESS,getIntent().getStringExtra("address"));
            dbManager.InsertR(values);

            Intent intent = new Intent(this,Start.class);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.bookmark){
            ContentValues values=new ContentValues();
            values.put(DBManager.NAME,getIntent().getStringExtra("name"));
            values.put(DBManager.CALL,getIntent().getStringExtra("number"));
            values.put(DBManager.CITY,getIntent().getStringExtra("city"));
            values.put(DBManager.CAT,getIntent().getStringExtra("message"));
            values.put(DBManager.ADDRESS,getIntent().getStringExtra("address"));
            Long count=dbManager.Insert(values);
            if (count > 0)
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.Added_To_Bookmark),Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
