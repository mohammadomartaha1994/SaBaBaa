package com.tampasst.tampass.tampass;

import android.app.Application;

/**
 * Created by tampass on 10/14/2016.
 */
public class Session extends Application {

    private static int id;
    private static String username;
    private static String Email;
    private static String City;
    private static Double Latitude;
    private static Double Longitude;
    private static int Zoom;
    private static String ID_EDIT;
    private static String Lat;
    private static String Lo;

//    String currentUserName;
//    currentUserName = Session.getUsername();
//    Session.setUsername(username);


    @Override
    public void onCreate() {
        super.onCreate();
        id=0;
        ID_EDIT="";
        username="";
        Email="";
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }


//    public static String getID_EDIT() {
//        return ID_EDIT;
//    }
//
//    public static void setID_EDIT(String  ID_EDIT) {
//        Session.ID_EDIT = ID_EDIT;
//    }

    public static int getZoom() {
        return Zoom;
    }

    public static void setZoom(int Zoom) {
        Session.Zoom = Zoom;
    }


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        Session.Email = Email;
    }



    public static Double getLatitude() {
        return Latitude;
    }

    public static void setLatitude(Double Latitude) {
        Session.Latitude = Latitude;
    }


    public static Double getLongitude() {
        return Longitude;
    }


    public static void setLongitude(Double Longitude) {
        Session.Longitude = Longitude;
    }


//
//    public static String getCity() {
//        return City;
//    }
//
//    public static void setCity(String City) {
//        Session.City = City;
//    }



    public static String getLat() {
        return Lat;
    }

    public static void setLat(String Lat) {
        Session.Lat = Lat;
    }


    public static String getLo() {
        return Lo;
    }

    public static void setLo(String Lo) {
        Session.Lo = Lo;
    }




}