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

public class Categories extends AppCompatActivity {
    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    MyCustomAdapter myadapter;
    String TextSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView pup_cat = (ListView) findViewById(R.id.pop_cat);

        listnewsData.add(new AdapterItems("1","Hot New Businesses",getResources().getString(R.string.Hot_New_Businesses),"busse"));
        listnewsData.add(new AdapterItems("2","Delivery",getResources().getString(R.string.Delivery),"delivery"));
        listnewsData.add(new AdapterItems("3","Deals",getResources().getString(R.string.Deals),"deals"));
        listnewsData.add(new AdapterItems("4","Check-in Offers",getResources().getString(R.string.Check_in_Offers),"check"));
        listnewsData.add(new AdapterItems("5","Restaurants",getResources().getString(R.string.restaurants),"ic_restaurant_black_24dp"));
        listnewsData.add(new AdapterItems("6","Nightlife",getResources().getString(R.string.nightlife),"nightlife"));
        listnewsData.add(new AdapterItems("7","Coffee and Tea",getResources().getString(R.string.Coffee_and_Tea),"teaa"));
        listnewsData.add(new AdapterItems("8","Gas and Service Stations",getResources().getString(R.string.Gas_and_Service_Stations),"petroleum"));
        listnewsData.add(new AdapterItems("9","Drugstores",getResources().getString(R.string.Drugstores),"drugstores"));
        listnewsData.add(new AdapterItems("10","Shopping",getResources().getString(R.string.Shopping),"ic_shopping_cart_black_24dp"));
        listnewsData.add(new AdapterItems("11","Everything",getResources().getString(R.string.Everything),"evry"));

        myadapter = new MyCustomAdapter(listnewsData);
        pup_cat.setAdapter(myadapter);


//
//
//        everything = (LinearLayout)findViewById(R.id.everything);
//        everything.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="everything";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//            }
//        });
//
//
//        restaurants = (LinearLayout)findViewById(R.id.restaurants);
//        restaurants.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="restaurants";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        restaurants_ = (LinearLayout)findViewById(R.id.restaurants_);
//        restaurants_.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="restaurants";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//        nightlife = (LinearLayout)findViewById(R.id.nightlife);
//        nightlife.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="nightlife";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        nightlife_ = (LinearLayout)findViewById(R.id.nightlife_);
//        nightlife_.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="nightlife";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        shopping = (LinearLayout)findViewById(R.id.shopping);
//        shopping.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="shopping";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        shopping_ = (LinearLayout)findViewById(R.id.shopping_);
//        shopping_.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="shopping";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        food = (LinearLayout)findViewById(R.id.food);
//        food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="food";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        delivery = (LinearLayout)findViewById(R.id.delivery);
//        delivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="delivery";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        deals = (LinearLayout)findViewById(R.id.deals);
//        deals.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="deals";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        drugstores=(LinearLayout)findViewById(R.id.drugstores);
//        drugstores.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="drugstores";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        A_A_E = (LinearLayout)findViewById(R.id.A_A_E);
//        A_A_E.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="A_A_E";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        P_S = (LinearLayout)findViewById(R.id.P_S);
//        P_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="P_S";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        automotive = (LinearLayout)findViewById(R.id.automotive);
//        automotive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="automotive";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        H_A_T = (LinearLayout)findViewById(R.id.H_A_T);
//        H_A_T.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="H_A_T";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        education = (LinearLayout)findViewById(R.id.education);
//        education.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="education";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        R_E = (LinearLayout)findViewById(R.id.R_E);
//        R_E.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="R_E";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        pets = (LinearLayout)findViewById(R.id.pets);
//        pets.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="pets";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        F_S = (LinearLayout)findViewById(R.id.F_S);
//        F_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="F_S";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        P_S_A_G = (LinearLayout)findViewById(R.id.P_S_A_G);
//        P_S_A_G.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="P_S_A_G";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        M_M = (LinearLayout)findViewById(R.id.M_M);
//        M_M.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="M_M";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        R_O = (LinearLayout)findViewById(R.id.R_O);
//        R_O.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="R_O";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        H_A_M = (LinearLayout)findViewById(R.id.H_A_M);
//        H_A_M.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="H_A_M";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        B_A_S = (LinearLayout)findViewById(R.id.B_A_S);
//        B_A_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="B_A_S";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        H_S = (LinearLayout)findViewById(R.id.H_S);
//        H_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="H_S";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        L_S = (LinearLayout)findViewById(R.id.L_S);
//        L_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="L_S";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        E_P_A_S = (LinearLayout)findViewById(R.id.E_P_A_S);
//        E_P_A_S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="E_P_A_S";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        H_A_B = (LinearLayout)findViewById(R.id.H_N_B);
//        H_A_B.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="H_A_B";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//        C_I_O = (LinearLayout)findViewById(R.id.C_I_O);
//        C_I_O.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="C_I_O";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });
//
//
//        C_A_T = (LinearLayout)findViewById(R.id.C_A_T);
//        C_A_T.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextSend="C_A_T";
//                Bundle bundle=new Bundle();
//                bundle.putString("message", TextSend);
//                Categorie_Content fragment = new Categorie_Content();
//                fragment.setArguments(bundle);
//                getFragmentManager().beginTransaction().replace(R.id.content_start, fragment).commit();
//
//            }
//        });

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
           if (s.image == "busse")
            image.setImageResource(R.drawable.busse);
            else if (s.image == "delivery")
               image.setImageResource(R.drawable.delivery);
           else if (s.image == "deals")
               image.setImageResource(R.drawable.deals);
           else if (s.image == "check")
               image.setImageResource(R.drawable.check);
           else if (s.image == "ic_restaurant_black_24dp")
               image.setImageResource(R.drawable.ic_restaurant_black_24dp);
           else if (s.image == "nightlife")
               image.setImageResource(R.drawable.nightlife);
           else if (s.image == "teaa")
               image.setImageResource(R.drawable.teaa);
           else if (s.image == "petroleum")
               image.setImageResource(R.drawable.petroleum);
           else if (s.image == "drugstores")
               image.setImageResource(R.drawable.drugstores);
           else if (s.image == "ic_shopping_cart_black_24dp")
               image.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
           else if (s.image == "evry")
               image.setImageResource(R.drawable.evry);


            tvSalary.setText(s.name_show);
            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (Categories.this, CategorieContent.class);
                    intent.putExtra("message",s.name);
                    intent.putExtra("name_show",s.name_show);
                    //intent.putExtra("message",s.name);
                    intent.putExtra("activity","Categories");

                    startActivity(intent);
                }
            });
            return myView;
        }
    }
}
