package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProfileShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String profile_Image_String = getIntent().getStringExtra("profileImage");
        final String nameS = getIntent().getStringExtra("name");
        final String numberS = getIntent().getStringExtra("number");
        final String addressS = getIntent().getStringExtra("address");
        final String latetudeS = getIntent().getStringExtra("lat");
        final String longitudeS = getIntent().getStringExtra("lo");
        final String id = getIntent().getStringExtra("id");
        final String published = getIntent().getStringExtra("publish");
        //final String published="false";
        Session.setLo(getIntent().getStringExtra("lo"));
        Session.setLat(getIntent().getStringExtra("lat"));

////////
        if (published.equals("false")) {
            LayoutInflater layoutInflater = getLayoutInflater();
            final View promptView = layoutInflater.inflate(R.layout.publish, null);
            final AlertDialog alertD = new AlertDialog.Builder(ProfileShow.this).create();
            Button publishBtn = (Button)promptView.findViewById(R.id.publish);
            ImageButton exit = (ImageButton)promptView.findViewById(R.id.exit);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertD.dismiss();
                }
            });
            publishBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"published",Toast.LENGTH_LONG).show();
                }
            });
            alertD.setView(promptView);
            alertD.show();
        }
/////////////


        ImageView profileImage = (ImageView)findViewById(R.id.profileImage);
        Picasso.with(ProfileShow.this).load(profile_Image_String).into(profileImage);


        TextView name = (TextView)findViewById(R.id.name);
        name.setText(nameS);
        TextView number = (TextView)findViewById(R.id.num);
        number.setText(numberS);
        TextView address = (TextView)findViewById(R.id.address);
        address.setText(addressS);
        Button location = (Button)findViewById(R.id.locationOnMap);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileShow.this,ProfileShowMap.class);
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

        Button event = (Button)findViewById(R.id.event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProfileShow.this,Profile_Events.class);
                intent.putExtra("lat",latetudeS);
                intent.putExtra("lo",longitudeS);
                intent.putExtra("name",nameS);
                intent.putExtra("address",addressS);
                intent.putExtra("number",numberS);
                intent.putExtra("id",id);
                intent.putExtra("profileImage",profile_Image_String);
                intent.putExtra("publish",getIntent().getStringExtra("publish"));
                startActivity(intent);

            }
        });

        Button edit = (Button)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileShow.this,Profile.class);
                intent.putExtra("lat",latetudeS);
                intent.putExtra("lo",longitudeS);
                intent.putExtra("name",nameS);
                intent.putExtra("address",addressS);
                intent.putExtra("number",numberS);
                intent.putExtra("id",id);
                intent.putExtra("publish",getIntent().getStringExtra("publish"));
                intent.putExtra("profileImage",profile_Image_String);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,BusinessList.class);
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
