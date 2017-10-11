package com.tampasst.tampass.tampass;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad on 3/22/2017.
 */

public class Profile_Edit_Request  extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://www.kufermalik.com/tampass/edit_profile.php";
    private Map<String,String> params;

    public Profile_Edit_Request(String id,String name,String number,String address,String lat , String lo,Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("id",id);
        params.put("name",name);
        params.put("address",address);
        params.put("number",number);
        params.put("lat",lat);
        params.put("lo",lo);
    }

    @Override
    public  Map<String,String> getParams(){
        return params;
    }

}
