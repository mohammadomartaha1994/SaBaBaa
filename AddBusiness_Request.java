package com.tampasst.tampass.tampass;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad on 3/18/2017.
 */

public class AddBusiness_Request extends StringRequest {
    private String KEY_NAME = "name";
    private String KEY_CATEGORY="category";
    private String KEY_NUMBER="number";
    private String KEY_ADDRESS="address";
    private String KEY_CITY="city";
    private String KEY_LAT="lat";
    private String KEY_LO="lo";
    private String KEY_ID_WONER="id_preson";
    private static final String REGISTER_REQUEST_URL="http://www.kufermalik.com/tampass/createBusiness.php";
    private Map<String,String> params;

    public AddBusiness_Request(String name,String category ,String address,
                               String city,String number,String lat,
                               String lo,int id_preson,
                               Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put(KEY_NAME, name);
        params.put(KEY_CATEGORY, category);
        params.put(KEY_ADDRESS, address);
        params.put(KEY_CITY, city);
        params.put(KEY_NUMBER, number);
        params.put(KEY_LAT,lat);
        params.put(KEY_LO,lo);
        params.put(KEY_ID_WONER,id_preson+"");
    }
    @Override
    public  Map<String,String> getParams(){
        return params;
    }

}

