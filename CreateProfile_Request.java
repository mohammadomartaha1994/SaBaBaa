package com.tampasst.tampass.tampass;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tampass on 3/11/2017.
 */

public class CreateProfile_Request extends StringRequest {

    private static final String REGISTER_REQUEST_URL="http://www.kufermalik.com/tampass/CreateProfile_Request.php";
    private Map<String,String> params;

    public CreateProfile_Request(String username,String password,String mail,Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("mail",mail);
    }
    @Override
    public  Map<String,String> getParams(){
        return params;
    }

}
