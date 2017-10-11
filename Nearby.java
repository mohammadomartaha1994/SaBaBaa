package com.tampasst.tampass.tampass;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by tampass on 3/1/2017.
 */

public class Nearby extends Fragment implements View.OnClickListener {

    LinearLayout restaurants,H_A_M,shopping,food,delivery,reservations;
    String cat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nearby, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Log.i("location",""+isLocationEnabled(this)+"-"+LAT+"-"+LOG);


            restaurants = (LinearLayout) getActivity().findViewById(R.id.restaurants);
            restaurants.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cat = "Restaurants";

                    Intent intent = new Intent(getActivity(), CategorieContent.class);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show",getResources().getString(R.string.restaurants));
                    intent.putExtra("activity", "Start");
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else
                        startActivity(intent);


//                Bundle bundle=new Bundle();
//                bundle.putString("message", cat);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
                }
            });

            H_A_M = (LinearLayout) getActivity().findViewById(R.id.healthandmedical);
            H_A_M.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cat = "Health and Medical";
                    Intent intent = new Intent(getActivity(), CategorieContent.class);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show",getResources().getString(R.string.health_and_medical));
                    intent.putExtra("activity", "Start");
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else
                        startActivity(intent);
                }
            });

            shopping = (LinearLayout) getActivity().findViewById(R.id.shopping);
            shopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cat = "Shopping";
                    Intent intent = new Intent(getActivity(), CategorieContent.class);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show",getResources().getString(R.string.Shopping));

                    intent.putExtra("activity", "Start");
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else
                        startActivity(intent);
                }
            });

            food = (LinearLayout) getActivity().findViewById(R.id.food);
            food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cat = "Food";
                    Intent intent = new Intent(getActivity(), CategorieContent.class);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show",getResources().getString(R.string.food));

                    intent.putExtra("activity", "Start");
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else
                        startActivity(intent);
                }
            });

            delivery = (LinearLayout) getActivity().findViewById(R.id.delivery);
            delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cat = "Delivery";
                    Intent intent = new Intent(getActivity(), CategorieContent.class);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show",getResources().getString(R.string.Delivery));
                    intent.putExtra("activity", "Start");
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else
                        startActivity(intent);
                }
            });

            reservations = (LinearLayout) getActivity().findViewById(R.id.reservations);
            reservations.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cat = "Reservations";
                    Intent intent = new Intent(getActivity(), CategorieContent.class);
                    intent.putExtra("message", cat);
                    intent.putExtra("name_show",getResources().getString(R.string.Reservations));
                    intent.putExtra("activity", "Start");
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else
                        startActivity(intent);
                }
            });


            LinearLayout MoreCategories = (LinearLayout) getActivity().findViewById(R.id.MoreCategories);
            MoreCategories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Categories.class);
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else{
                        startActivity(intent);
                    getActivity().finish();}

//                CategoriesFrag fragment = new CategoriesFrag();
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
                }
            });


            LinearLayout puategories = (LinearLayout) getActivity().findViewById(R.id.all_cat);
            puategories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), All_Categories.class);
                    if (!isLocationEnabled(getActivity())){
                        Toast.makeText(getActivity(),getResources().getString(R.string.turn_On_location_services),Toast.LENGTH_LONG).show();
                    }
                    else {
                        startActivity(intent);
                        getActivity().finish();
                    }
//                CategoriesFrag fragment = new CategoriesFrag();
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
                }
            });

            //all_cat



    }


    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    @Override
    public void onClick(View v) {

    }
}
