package com.tampasst.tampass.tampass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class AddProfileImage extends AppCompatActivity implements View.OnClickListener {
    private Button buttonChoose;
    private Button buttonUpload;

    private ImageView imageView;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ;

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_CATEGORY="category";
    private String KEY_NUMBER="number";
    private String KEY_ADDRESS="address";
    private String KEY_CITY="city";
    private String KEY_LAT="lat";
    private String KEY_LO="lo";

    String name,category,number,address,city,lat,lo,profile_Image_String;


////////////////////////////////////////////////////////////////////// not used



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name= getIntent().getStringExtra("name");
         category= getIntent().getStringExtra("category");
         number= getIntent().getStringExtra("number");
         address= getIntent().getStringExtra("address");
         city= getIntent().getStringExtra("city");
         lat=getIntent().getStringExtra("lat");
         lo=getIntent().getStringExtra("lo");
        final String id = getIntent().getStringExtra("id");
        profile_Image_String=getIntent().getStringExtra("profileImage");


        UPLOAD_URL ="http://www.kufermalik.com/tampass/Change_Profile_image.php?id="+id+"";


        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);

        imageView  = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        final ProgressBuilder loading=new ProgressBuilder(AddProfileImage.this);
        loading.showProgressDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(AddProfileImage.this, s , Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(AddProfileImage.this,BusinessList.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        Toast.makeText(getApplication()," Try Again ",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddProfileImage.this,BusinessList.class);
                        startActivity(intent);
                        finish();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);

                Map<String,String> params = new Hashtable<String, String>();
                params.put(KEY_IMAGE, image);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v == buttonChoose){
            showFileChooser();
        }

        if(v == buttonUpload){
            uploadImage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,Profile.class);
            intent.putExtra("lat",lat);
            intent.putExtra("lo",lo);
            intent.putExtra("id",id);
            intent.putExtra("name",name);
            intent.putExtra("address",address);
            intent.putExtra("number",number);
            intent.putExtra("profileImage",profile_Image_String);
            intent.putExtra("publish",getIntent().getStringExtra("publish"));


            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}