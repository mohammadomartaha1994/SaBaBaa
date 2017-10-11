package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Recently_Viewed extends AppCompatActivity {
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recently__viewed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbManager=new DBManager(this);
        LoadElement();
    }

    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    Recently_Viewed.MyCustomAdapter myadapter;
    void LoadElement() {
        listnewsData.clear();
        Cursor cursor = dbManager.queryR(null, null, null, DBManager.ID);
        if (cursor.moveToFirst()) {
            do {
                listnewsData.add(new AdapterItems(cursor.getString( cursor.getColumnIndex(DBManager.ID)),
                        cursor.getString( cursor.getColumnIndex(DBManager.NAME)),
                        cursor.getString( cursor.getColumnIndex(DBManager.CAT)),
                        cursor.getString( cursor.getColumnIndex(DBManager.CITY)),
                        cursor.getString( cursor.getColumnIndex(DBManager.CALL)),
                        cursor.getString( cursor.getColumnIndex(DBManager.ADDRESS))));
            } while (cursor.moveToNext());
        }
        myadapter = new Recently_Viewed.MyCustomAdapter(listnewsData);
        ListView lsNews = (ListView)findViewById(R.id.LVNews);
        lsNews.setAdapter(myadapter);
    }
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater ;
        public MyCustomAdapter(ArrayList<AdapterItems>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }
        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }
        @Override
        public String getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.recently_v_database, null);
            final AdapterItems s = listnewsDataAdpater.get(position);
            final TextView name = (TextView) myView.findViewById(R.id.name);
            name.setText(s.Bname);

            final TextView city = (TextView) myView.findViewById(R.id.city);
            city.setText(s.Bcity);

            final Button call = (Button) myView.findViewById(R.id.phone);
            call.setText("CALL - "+s.phoneN);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+s.phoneN));
                    if(intent.resolveActivity(getPackageManager()) != null ){
                        startActivity(intent);

                    }
                }
            });

            ImageView delete = (ImageView) myView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Recently_Viewed.this);
                    builder1.setMessage(getResources().getString(R.string.Delete_this_item));
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            getResources().getString(R.string.Delete),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String[] SelectionArgs={s.ID};
                                    dbManager.DeleteR("ID=?", SelectionArgs);
                                    LoadElement();
                                    dialog.cancel();
                                }
                            });
                    builder1.setNegativeButton(
                            getResources().getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });


            return myView;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent;
            intent = new Intent(this,Start.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
