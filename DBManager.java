package com.tampasst.tampass.tampass;

/**
 * Created by tampass on 9/15/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBName="Bookmarks";

    static final String TableName="Nearby_Categories";
    static final String TableNameR="Recently_Viewed";

    static final String ID="ID";
    static final String NAME="name";
    static final String CITY="city";
    static final String CALL="call";
    static final String CAT="cat";
    static final String ADDRESS="address";

    static final int DBVersion=6;


    static final String CreateTable="CREATE TABLE IF NOT EXISTS " +
            TableName+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME+ " Text , "+
            CALL+ " Text , "+
            CITY+ " Text , "+
            ADDRESS+ " Text , "+
            CAT+" Text );";

    static final String CreateTableR="CREATE TABLE IF NOT EXISTS " +
            TableNameR+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME+ " Text , "+
            CALL+ " Text , "+
            CITY+ " Text , "+
            ADDRESS+ " Text , "+
            CAT+" Text );";


    static class DatabaseHelperUser extends SQLiteOpenHelper{
        Context context;
        DatabaseHelperUser(Context context){
            super(context,DBName,null,DBVersion);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            db.execSQL(CreateTableR);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF EXISTS "+TableName);
            db.execSQL("Drop table IF EXISTS "+TableNameR);
            onCreate(db);

        }
    }

    public DBManager(Context context){
        DatabaseHelperUser db=new DatabaseHelperUser(context);
        sqlDB = db.getWritableDatabase();
    }



    public long Insert(ContentValues values){
        long ID = sqlDB.insert(TableName,"",values);
        return ID;
    }

    public long InsertR(ContentValues values){
        long ID = sqlDB.insert(TableNameR,"",values);
        return ID;
    }




    public Cursor query(String[] Projection,String Selection, String[] SelectionArgs,String SortOrder){
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableName);
        Cursor cursor=qb.query(sqlDB, Projection, Selection, SelectionArgs, null, null, SortOrder+" DESC ");
        return cursor;
    }


    public Cursor queryR(String[] Projection,String Selection, String[] SelectionArgs,String SortOrder){
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TableNameR);
        Cursor cursor=qb.query(sqlDB, Projection, Selection, SelectionArgs, null, null, SortOrder+" DESC LIMIT 15 ");
        return cursor;
    }



    public int Delete(String Selection,String[] SelectionArgs){
        int count = sqlDB.delete(TableName, Selection, SelectionArgs);
        return count;
    }

    public int DeleteR(String Selection,String[] SelectionArgs){
        int count = sqlDB.delete(TableNameR, Selection, SelectionArgs);
        return count;
    }



    public  int Update(ContentValues values,String Selection,String[] SelectionArgs){
        int count=sqlDB.update(TableName,values,Selection,SelectionArgs);
        return count;

    }

    public  int UpdateR(ContentValues values,String Selection,String[] SelectionArgs){
        int count=sqlDB.update(TableNameR,values,Selection,SelectionArgs);
        return count;

    }



}
