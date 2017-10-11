package com.tampasst.tampass.tampass;

/**
 * Created by tampass on 9/15/2016.
 */
public class AdapterItems implements Comparable<AdapterItems>
{
    public  String ID;
    public  String image;
    public  String name;

    private double score;

    public  int id;
    public  String Bname;
    public  String cat;
    public  String Bcity;
    public  String phoneN;
    public  String address;
    public  String latitude;
    public  String longitude;
    public  String desc;
    public  String id_owner;
    public  String name_show;
    public  String publish;



    AdapterItems(String ID,String name,String image){
        this.ID=ID;
        this.name=name;
        this.image=image;

    }


    AdapterItems(String ID,String name,String name_show,String image){
        this.ID=ID;
        this.name=name;
        this.name_show=name_show;
        this.image=image;

    }

    AdapterItems(String ID ,String Bname , String cat ,
                 String Bcity , String phoneN, String address,
                 String latitude, String longitude, String image ){
        this.ID=ID;
        this.Bname=Bname;
        this.cat=cat;
        this.Bcity=Bcity;
        this.phoneN=phoneN;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
        this.image=image;
    }

    AdapterItems(String ID ,String Bname , String cat ,
                 String Bcity , String phoneN, String address){
        this.ID=ID;
        this.Bname=Bname;
        this.phoneN=phoneN;
        this.Bcity=Bcity;
        this.address=address;
        this.cat=cat;
    }




    AdapterItems(int id ,String Bname ,
                 String Bcity , String image ){
        this.id=id;
        this.Bname=Bname;
        this.Bcity=Bcity;
        this.image=image;
    }
    AdapterItems(String ID ,
                 String desc ,
                 String image ,
                 String name ,
                 String id_owner){
        this.ID=ID;
        this.name=name;
        this.desc=desc;
        this.image=image;
        this.id_owner=id_owner;
    }

    public AdapterItems(double score){
        this.score = score;
    }

    @Override
    public int compareTo(AdapterItems o) {

        return new Double(score).compareTo( o.score);

    }

    AdapterItems(String ID ,String Bname , String cat ,
                 String Bcity , String phoneN, String address,
                 String latitude, String longitude, String image ,Double score){
        this.ID=ID;
        this.Bname=Bname;
        this.cat=cat;
        this.Bcity=Bcity;
        this.phoneN=phoneN;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
        this.image=image;
        this.score=score;
    }

    AdapterItems(String ID ,String Bname , String cat ,
                 String Bcity , String phoneN, String address,
                 String latitude, String longitude, String image ,String publish){
        this.ID=ID;
        this.Bname=Bname;
        this.cat=cat;
        this.Bcity=Bcity;
        this.phoneN=phoneN;
        this.address=address;
        this.latitude=latitude;
        this.longitude=longitude;
        this.image=image;
        this.publish=publish;
    }

//    public  String LectureName;
//    public  String FromTime;
//    public  String ToTime;
//    public  int LectureDays;
//    public  String LectureNumber;
//    public  String EID;
//    public  String ExamName;
//    public  int ExamDay;
//    public  int ExamMonth;
//    public  String ExamType;
//    public  int hour;
//    public  int min;
//    public  int ID2;
//    public  int Ehour;
//    public  int Emin;
//    public  String ColSortTime;
//    public  int ColSat;
//    public  int ColSun;
//    public  int day;
//    public  int ColMon;
//    public  int ColTus;
//    public  String result;
//
//    public  int WIGHTGRADUE;
//    public  String TEXTGRADUE;
//    public  String LECTURENAME;
//    public  int GRADUE;
//    public  int FFROM;
//
//    public  int ColWen;
//    public  int ColThe;
//    public  int ColFri;
//    public  int DayAndMonth;


//    AdapterItems( String ID, String LectureName,String FromTime,
//                  String ToTime, String LectureNumber,String ColSortTime,
//                  int hour,int min,int Ehour,int Emin,
//                  int ColSat,int ColSun,int ColMon,int ColTus,int ColWen,int ColThe,int ColFri)
//    {
//        this.ID=ID;
//        this.LectureName=LectureName;
//        this.FromTime=FromTime;
//        this.ToTime=ToTime;
//        this.LectureNumber=LectureNumber;
//        this.ColSortTime=ColSortTime;
//        this.hour=hour;
//        this.min=min;
//        this.Ehour=Ehour;
//        this.Emin=Emin;
//        this.ColSat=ColSat;
//        this.ColSun=ColSun;
//        this.ColMon=ColMon;
//        this.ColTus=ColTus;
//        this.ColWen=ColWen;
//        this.ColThe=ColThe;
//        this.ColFri=ColFri;
//    }

//    AdapterItems( String ID, String ExamName,int ExamDay, int ExamMonth,int DayAndMonth, String ExamType)
//    {
//        this.ID=ID;
//        this.ExamName=ExamName;
//        this.ExamDay=ExamDay;
//        this.ExamMonth=ExamMonth;
//        this.DayAndMonth=DayAndMonth;
//        this.ExamType=ExamType;
//    }

//    AdapterItems(String ID,int ID2,String LectureName){
//        this.ID=ID;
//        this.ID2=ID2;
//        this.LectureName=LectureName;
//
//    }
//
//    AdapterItems(String ID,String LectureName){
//        this.ID=ID;
//        this.LectureName=LectureName;
//
//    }
//    AdapterItems(String ID ,int day , int hour , int min , String LectureName ){
//        this.ID=ID;
//        this.hour=hour;
//        this.min=min;
//        this.LectureName=LectureName;
//        this.day=day;
//    }
//
//


//



}


