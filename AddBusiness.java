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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddBusiness extends AppCompatActivity {

    EditText Bname,number,address;
    Button location,Bcategories,send;
    Spinner city;
    String city_;
    String value = "";
    String Business_name_SP;
    String Business_Number_SP;
    String Business_Address_SP;
    int Business_city;
    String lat="",lo;
    int id_preson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         id_preson = Session.getId();
        Bname=(EditText)findViewById(R.id.Bname);
        number=(EditText)findViewById(R.id.number);
        address=(EditText)findViewById(R.id.address);

        lat= getIntent().getStringExtra("lat");
        lo= getIntent().getStringExtra("lo");



        Business_name_SP=SharedPreferencesHelper.getStringSharedPref(getBaseContext(),"Business_name_SP");
         Business_Number_SP=SharedPreferencesHelper.getStringSharedPref(getBaseContext(),"Business_Number_SP");
         Business_Address_SP=SharedPreferencesHelper.getStringSharedPref(getBaseContext(),"Business_Address_SP");
         value=SharedPreferencesHelper.getStringSharedPref(getBaseContext(),"Business_category");
         Business_city=SharedPreferencesHelper.getIntSharedPref(getBaseContext(),"Business_city");

//
//        Business_name_SP=Bname.getText().toString();
//        Business_Number_SP=number.getText().toString();
//        Business_Address_SP=address.getText().toString();

        Bname.setText(Business_name_SP);
        number.setText(Business_Number_SP);
        address.setText(Business_Address_SP);



        Bcategories=(Button)findViewById(R.id.Bcategories);
        Bcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater1 = LayoutInflater.from(AddBusiness.this);
                final View promptView = layoutInflater1.inflate(R.layout.catigories_dialog, null);
                final AlertDialog alert = new AlertDialog.Builder(AddBusiness.this).create();
                final RadioGroup cat = (RadioGroup)promptView.findViewById(R.id.cat);

                cat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        value = ((RadioButton)promptView.findViewById(cat.getCheckedRadioButtonId())).getText().toString();
                        SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_category",value);

                    }
                });


                Button cancel = (Button)promptView.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value="";
                        alert.dismiss();
                    }
                });
                Button save = (Button)promptView.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });
                alert.setView(promptView);
                alert.show();
            }
        });


        city=(Spinner)findViewById(R.id.city);
        city.setSelection(Business_city);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_ = city.getSelectedItem().toString();
                SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_city",position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city_="";

            }
        });



        location=(Button)findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_name_SP",Bname.getText().toString());
                SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_Number_SP",number.getText().toString());
                SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_Address_SP",address.getText().toString());

                Intent intent = new Intent(AddBusiness.this,ChooseLocationForBusiness.class);
                startActivity(intent);
                finish();
            }
        });

        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Bname.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.insert_your_business_name),Toast.LENGTH_LONG).show();
                else if (value.equals(""))
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.Choose_your_business_category),Toast.LENGTH_LONG).show();
                else if (number.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.insert_your_business_number),Toast.LENGTH_LONG).show();
                else if (address.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.insert_your_business_address),Toast.LENGTH_LONG).show();
                else if(city_.equals("City"))
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.Choose_your_business_city),Toast.LENGTH_LONG).show();
                else if (lat.equals(""))
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.set_your_Location),Toast.LENGTH_LONG).show();
                else{

//                    Intent intent = new Intent(AddBusiness.this,AddProfileImage.class);
//                    intent.putExtra("name",Bname.getText().toString() );
//                    intent.putExtra("category",value);
//                    intent.putExtra("address",address.getText().toString() );
//                    intent.putExtra("city",city_ );
//                    intent.putExtra("number",number.getText().toString() );
//                    intent.putExtra("lat",lat);
//                    intent.putExtra("lo",lo);
//                    startActivity(intent);
//


                    final ProgressBuilder loading=new ProgressBuilder(AddBusiness.this);
                    loading.showProgressDialog();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    loading.dismiss();

                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_name_SP","");
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_Number_SP","");
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_Address_SP","");
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_city",0);
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"Business_category","");

                                    Intent intent = new Intent(AddBusiness.this, BusinessList.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    loading.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(AddBusiness.this);
                                    builder.setMessage("check your internet connection")
                                            .setNegativeButton("Retry", null)
                                            .create().show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    AddBusiness_Request addBusiness_Request = new AddBusiness_Request(Bname.getText().toString()
                            , value, address.getText().toString(),
                            city_,number.getText().toString(),lat,lo,id_preson
                            , responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AddBusiness.this);
                    queue.add(addBusiness_Request);

                }


            }
        });

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
