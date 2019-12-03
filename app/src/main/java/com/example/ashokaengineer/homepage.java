package com.example.ashokaengineer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class homepage extends AppCompatActivity implements pooladapter.onitemclicklistener,NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mtoolbar;
    private DrawerLayout drlay;
    private NavigationView navview;
    public static final String EXTRA_URL="imageurl";
    public static final String EXTRA_NAME="user";
    public static final String EXTRA_LOCATION="views";
    //added extra datas for manipulating later(report is not initialised currently)
    public static final String EXTRA_REPORT="imageurl";
    public  static final String EXTRA_AREA="imageHeight";

    private RecyclerView mRecyclerView;
    private pooladapter mpoolAdapter;
    private ArrayList<poolitems> mpoollist;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mRecyclerView=findViewById(R.id.recyclerpoolview);
        mRecyclerView.setHasFixedSize(true);
        mtoolbar=findViewById(R.id.toolbar);
        drlay=findViewById(R.id.drawer);
        navview=findViewById(R.id.navi);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mpoollist=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON()
    {
        //JSON URL (NOW ITS A DUMMY)
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //getting values from fake json file
                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String poolname = hit.getString("user");
                                String mImageurl= hit.getString("tags");
                                String area = hit.getString("imageHeight");
                                String location = hit.getString("views");
                                String Report=hit.getString("userImageURL");

                                //remember maintain the same order as in poolitemslist.java
                                mpoollist.add(new poolitems(mImageurl,poolname,area,location));
                            }
                            mpoolAdapter = new pooladapter(homepage.this, mpoollist);
                            mRecyclerView.setAdapter(mpoolAdapter);
                            mpoolAdapter.setOnItemClickListener(homepage.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);


    }

//clicking activity of items in recycler view
    @Override
    public void onItemClick(int position) {
        Intent detailIntent=new Intent(homepage.this,detailactivity.class);
        poolitems clickedItem=mpoollist.get(position);
        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageurl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getPoolname());
        detailIntent.putExtra(EXTRA_AREA,clickedItem.getArea());
        detailIntent.putExtra(EXTRA_LOCATION,clickedItem.getLocation());

        startActivity(detailIntent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.navprofile:{
                break;
            }
            case R.id.navsettings:{
                break;
            }
            case R.id.navlogout:{
                break;
            }
        }
        menuItem.setCheckable(true);
        drlay.closeDrawer(GravityCompat.START);

        return true;
    }
}
