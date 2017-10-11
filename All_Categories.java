package com.tampasst.tampass.tampass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class All_Categories extends AppCompatActivity {
    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    All_Categories.MyCustomAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__categories);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView pup_cat = (ListView) findViewById(R.id.pop_cat);

        listnewsData.add(new AdapterItems("1","Restaurants",getResources().getString(R.string.restaurants),"ic_restaurant_black_24dp"));
        listnewsData.add(new AdapterItems("2","Nightlife",getResources().getString(R.string.nightlife),"nightlife"));
        listnewsData.add(new AdapterItems("3","Shopping",getResources().getString(R.string.Shopping),"ic_shopping_cart_black_24dp"));
        listnewsData.add(new AdapterItems("4","Food",getResources().getString(R.string.food),"ic_restaurant_black_24dp"));
        listnewsData.add(new AdapterItems("5","Health and Medical",getResources().getString(R.string.health_and_medical),"healthcare"));
        listnewsData.add(new AdapterItems("6","Beauty and Spas",getResources().getString(R.string.Beauty_and_Spas),"spas"));
        listnewsData.add(new AdapterItems("7","Home Services",getResources().getString(R.string.Home_Services),"ic_home_black_24dp"));
        listnewsData.add(new AdapterItems("8","Local Services",getResources().getString(R.string.Local_Services),"ic_vpn_key_black_24dp"));
        listnewsData.add(new AdapterItems("9","Event Planning and services",getResources().getString(R.string.Event_Planning_and_Services),"ic_event_black_24dp"));
        listnewsData.add(new AdapterItems("10","Arts and Entertainment",getResources().getString(R.string.Arts_and_Entertainment),"ic_music_note_black_24dp"));
        listnewsData.add(new AdapterItems("11","Professional Services",getResources().getString(R.string.Professional_Services),"professional"));
        listnewsData.add(new AdapterItems("12","Automotive",getResources().getString(R.string.Auromotive),"automotive"));
        listnewsData.add(new AdapterItems("13","Hotels and Travel",getResources().getString(R.string.Hotels_and_Travel),"ic_local_airport_black_24dp"));
        listnewsData.add(new AdapterItems("14","Education",getResources().getString(R.string.Education),"ic_library_books_black_24dp"));
        listnewsData.add(new AdapterItems("15","Real Estate",getResources().getString(R.string.Real_Estate),"realestate"));
        listnewsData.add(new AdapterItems("16","Pets",getResources().getString(R.string.Pets),"ic_pets_black_24dp"));
        listnewsData.add(new AdapterItems("17","Financial Services",getResources().getString(R.string.Financial_Services),"financial"));
        listnewsData.add(new AdapterItems("18","Public Services and Government",getResources().getString(R.string.Public_Services_and_Government),"governmet"));
        listnewsData.add(new AdapterItems("19","Mass Media",getResources().getString(R.string.Mass_Media),"multimedia"));
        listnewsData.add(new AdapterItems("20","Religious Organizations",getResources().getString(R.string.Religious_Organizations),"religion"));


        myadapter = new All_Categories.MyCustomAdapter(listnewsData);
        pup_cat.setAdapter(myadapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(this,Start.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
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
            View myView = mInflater.inflate(R.layout.cat_items, null);
            final AdapterItems s = listnewsDataAdpater.get(position);
            final TextView tvSalary = (TextView) myView.findViewById(R.id.text);
            final ImageView image = (ImageView)myView.findViewById(R.id.image);
            //String x = s.image;



            if (s.image == "ic_restaurant_black_24dp")
                image.setImageResource(R.drawable.ic_restaurant_black_24dp);
            else if (s.image == "nightlife")
                image.setImageResource(R.drawable.nightlife);
            else if (s.image == "ic_shopping_cart_black_24dp")
                image.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
            else if (s.image == "ic_restaurant_black_24dp")
                image.setImageResource(R.drawable.ic_restaurant_black_24dp);
            else if (s.image == "healthcare")
                image.setImageResource(R.drawable.healthcare);
            else if (s.image == "spas")
                image.setImageResource(R.drawable.spas);
            else if (s.image == "ic_home_black_24dp")
                image.setImageResource(R.drawable.ic_home_black_24dp);
            else if (s.image == "ic_vpn_key_black_24dp")
                image.setImageResource(R.drawable.ic_vpn_key_black_24dp);
            else if (s.image == "ic_event_black_24dp")
                image.setImageResource(R.drawable.ic_event_black_24dp);
            else if (s.image == "ic_music_note_black_24dp")
                image.setImageResource(R.drawable.ic_music_note_black_24dp);
            else if (s.image == "professional")
                image.setImageResource(R.drawable.professional);
            else if (s.image == "automotive")
                image.setImageResource(R.drawable.automotive);
            else if (s.image == "automotive")
                image.setImageResource(R.drawable.automotive);
            else if (s.image == "ic_local_airport_black_24dp")
                image.setImageResource(R.drawable.ic_local_airport_black_24dp);
            else if (s.image == "ic_library_books_black_24dp")
                image.setImageResource(R.drawable.ic_library_books_black_24dp);
            else if (s.image == "realestate")
                image.setImageResource(R.drawable.realestate);
            else if (s.image == "ic_pets_black_24dp")
                image.setImageResource(R.drawable.ic_pets_black_24dp);
            else if (s.image == "financial")
                image.setImageResource(R.drawable.financial);
            else if (s.image == "governmet")
                image.setImageResource(R.drawable.governmet);
            else if (s.image == "multimedia")
                image.setImageResource(R.drawable.multimedia);
            else if (s.image == "religion")
                image.setImageResource(R.drawable.religion);



            tvSalary.setText(s.name_show);
            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (All_Categories.this, CategorieContent.class);
                    intent.putExtra("message",s.name);
                    intent.putExtra("name_show",s.name_show);
                    intent.putExtra("activity","All_Categories");
                    startActivity(intent);

                }
            });
            return myView;
        }
    }
}
