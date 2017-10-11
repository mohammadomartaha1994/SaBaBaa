package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    EditText na,nu,ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        na=(EditText)findViewById(R.id.name);
        nu=(EditText)findViewById(R.id.num);
        ad=(EditText)findViewById(R.id.address);

        final String profile_Image_String = getIntent().getStringExtra("profileImage");
        final String nameS = getIntent().getStringExtra("name");
        final String numberS = getIntent().getStringExtra("number");
        final String addressS = getIntent().getStringExtra("address");
        final String latetudeS = getIntent().getStringExtra("lat");
        final String longitudeS = getIntent().getStringExtra("lo");
        final String id = getIntent().getStringExtra("id");



        ImageView profileImage = (ImageView)findViewById(R.id.profileImage);
        //Picasso.with(Profile.this).load(profile_Image_String).into(profileImage);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,AddProfileImage.class);
                intent.putExtra("lat",latetudeS);
                intent.putExtra("lo",longitudeS);
                intent.putExtra("name",nameS);
                intent.putExtra("address",addressS);
                intent.putExtra("number",numberS);
                intent.putExtra("profileImage",profile_Image_String);
                intent.putExtra("id",id);
                intent.putExtra("publish",getIntent().getStringExtra("publish"));
                startActivity(intent);
            }
        });


        EditText name = (EditText)findViewById(R.id.name);
        name.setText(nameS);
        EditText number = (EditText)findViewById(R.id.num);
        number.setText(numberS);
        EditText address = (EditText)findViewById(R.id.address);
        address.setText(addressS);
        Button location = (Button)findViewById(R.id.locationOnMap);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,ProfileMap.class);
                intent.putExtra("lat",latetudeS);
                intent.putExtra("lo",longitudeS);
                intent.putExtra("name",nameS);
                intent.putExtra("address",addressS);
                intent.putExtra("number",numberS);
                intent.putExtra("publish",getIntent().getStringExtra("publish"));
                intent.putExtra("profileImage",profile_Image_String);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final ProgressBuilder loading=new ProgressBuilder(Profile.this);
                loading.showProgressDialog();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                loading.dismiss();
                                Intent intent = new Intent(Profile.this, BusinessList.class);
                                startActivity(intent);
                                finish();
                            } else {
                                loading.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                                builder.setMessage("Try Again")
                                        .setNegativeButton("Retry", null)
                                        .create().show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };



                String new_name=na.getText().toString();
                String new_number=nu.getText().toString();
                String new_address=ad.getText().toString();


                Profile_Edit_Request registerRequest = new Profile_Edit_Request(id, new_name, new_number, new_address, latetudeS+"",longitudeS+"" , responseListener);
                RequestQueue queue = Volley.newRequestQueue(Profile.this);
                queue.add(registerRequest);



            }
        });

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile.this,BusinessList.class);
                startActivity(intent);
                finish();

            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,ProfileShow.class);
            intent.putExtra("lat",getIntent().getStringExtra("lat"));
            intent.putExtra("lo",getIntent().getStringExtra("lo"));
            intent.putExtra("name",getIntent().getStringExtra("name"));
            intent.putExtra("address",getIntent().getStringExtra("address"));
            intent.putExtra("number",getIntent().getStringExtra("number"));
            intent.putExtra("profileImage",getIntent().getStringExtra("profileImage"));
            intent.putExtra("id", getIntent().getStringExtra("id"));
            intent.putExtra("publish",getIntent().getStringExtra("publish"));
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


