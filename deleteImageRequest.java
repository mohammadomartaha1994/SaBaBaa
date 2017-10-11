package com.tampasst.tampass.tampass;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad on 3/23/2017.
 */

public class deleteImageRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://www.kufermalik.com/tampass/deleteimage.php";
    private Map<String,String> params;

    public deleteImageRequest(String user_ID,Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("id",user_ID+"");
    }

    @Override
    public  Map<String,String> getParams(){
        return params;
    }

}
