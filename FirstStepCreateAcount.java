package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FirstStepCreateAcount extends AppCompatActivity {

    EditText username,password;
    CheckBox savePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_step_create_acount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String UserNameShared=SharedPreferencesHelper.getStringSharedPref(getBaseContext(),"login_Username_sababa");
        String UserPassShared=SharedPreferencesHelper.getStringSharedPref(getBaseContext(),"P_sababa");

        savePass = (CheckBox)findViewById(R.id.savePassword);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        username.setText(UserNameShared);
        password.setText(UserPassShared);

        Button signup = (Button)findViewById(R.id.signup);
        Button logIn = (Button)findViewById(R.id.logIn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInternetConnected(getApplicationContext())){       // IF INTERNET IS ACCESS

                    Intent intent = new Intent(FirstStepCreateAcount.this,CreateProfile.class);
                startActivity(intent);
                finish(); }
                else                                                     /// IF INTERNET IS NOT ACCESS
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInternetConnected(getApplicationContext())){       // IF INTERNET IS ACCESS



                final String UserName = username.getText().toString();
                final String Password = password.getText().toString();
                final ProgressBuilder loading=new ProgressBuilder(FirstStepCreateAcount.this);
                loading.showProgressDialog();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                int id = jsonResponse.getInt("id");
                                String username = jsonResponse.getString("username");
                                String mail = jsonResponse.getString("mail");

                                loading.dismiss();



                                Intent intent = new Intent(FirstStepCreateAcount.this,BusinessList.class);
                                startActivity(intent);
                                finish();
                                Session.setId(id);
                                Session.setUsername(username);
                                Session.setEmail(mail);

                                if (savePass.isChecked()){
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"login_Username_sababa",UserName);
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"P_sababa",Password);
                                }
                                else{
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"login_Username_sababa","");
                                    SharedPreferencesHelper.setSharePref(getBaseContext(),"P_sababa","");
                                }

                                finish();

                            }else{
                                loading.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(FirstStepCreateAcount.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest LoginRequest = new LoginRequest(UserName,Password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(FirstStepCreateAcount.this);
                queue.add(LoginRequest);


                }
                else                                                     /// IF INTERNET IS NOT ACCESS
                {
                    Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
                }



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
            Intent intent = new Intent(this,Start.class);
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


    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();}    /// FUNCTION TO CHECK IF THE INTERNET IS ACCESS OR NOT

}
