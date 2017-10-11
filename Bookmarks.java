package com.tampasst.tampass.tampass;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tampass on 3/1/2017.
 */

public class Bookmarks extends Fragment implements View.OnClickListener {
    DBManager dbManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bookmarks, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbManager=new DBManager(getActivity());
        LoadElement();

    }

    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    MyCustomAdapter myadapter;
    void LoadElement() {
        listnewsData.clear();
        Cursor cursor = dbManager.query(null, null, null, DBManager.ID);
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
        myadapter = new MyCustomAdapter(listnewsData);
        ListView lsNews = (ListView)getActivity().findViewById(R.id.LVNews);
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
            LayoutInflater mInflater = getActivity().getLayoutInflater();
            View myView = mInflater.inflate(R.layout.bookmark_database, null);
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
                    if(intent.resolveActivity(getActivity().getPackageManager()) != null ){
                        startActivity(intent);

                    }
                }
            });

            final TextView address = (TextView) myView.findViewById(R.id.Address);
            address.setText(s.address);

            final TextView cat = (TextView) myView.findViewById(R.id.cat);
            cat.setText(s.cat);

            ImageView delete = (ImageView) myView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Delete this item !");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String[] SelectionArgs={s.ID};
                                    dbManager.Delete("ID=?", SelectionArgs);
                                    LoadElement();
                                    dialog.cancel();
                                }
                            });
                    builder1.setNegativeButton(
                            "Cancel",
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
    @Override
    public void onClick(View v) {

    }
}

