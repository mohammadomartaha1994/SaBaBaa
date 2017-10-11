package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateProfile extends AppCompatActivity {
    EditText username;
    EditText pass;
    EditText passT;
    EditText mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button send = (Button)findViewById(R.id.send);
        username = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.pass);
        passT = (EditText)findViewById(R.id.passT);
        mail = (EditText)findViewById(R.id.mail);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String UserName = username.getText().toString();
                final String Password = pass.getText().toString();
                final String PasswordT = passT.getText().toString();
                final String Mail = mail.getText().toString();


                if(!UserName.isEmpty() && !Password.isEmpty() && !Mail.isEmpty()){
                if (Password.equals(PasswordT)){


                    final ProgressBuilder loading=new ProgressBuilder(CreateProfile.this);
                    loading.showProgressDialog();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                int id = jsonResponse.getInt("id");
                                String username = jsonResponse.getString("username");
                                String mail = jsonResponse.getString("mail");
                                loading.dismiss();
                                Session.setId(id);
                                Session.setUsername(username);
                                Session.setEmail(mail);
                                SharedPreferencesHelper.setSharePref(getBaseContext(),"login_Username_sababa",UserName);
                                SharedPreferencesHelper.setSharePref(getBaseContext(),"P_sababa",Password);
                                Intent intent = new Intent(CreateProfile.this, BusinessList.class);
                                startActivity(intent);
                                finish();
                            } else {
                                loading.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateProfile.this);
                                builder.setMessage(getResources().getString(R.string.Exists))
                                        .setNegativeButton("Retry", null)
                                        .create().show();


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                CreateProfile_Request registerRequest = new CreateProfile_Request(UserName, Password, Mail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateProfile.this);
                queue.add(registerRequest);


                }
                else{
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.Retype),Toast.LENGTH_LONG).show();

                }

                }else{

                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.invalid),Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,FirstStepCreateAcount.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
