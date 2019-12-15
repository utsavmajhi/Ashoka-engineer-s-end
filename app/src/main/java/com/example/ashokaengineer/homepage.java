package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ashokaengineer.Authorisedpoolsmodel.Getauthpoolformat;
import com.example.ashokaengineer.Authorisedpoolsmodel.Pool;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class homepage extends AppCompatActivity implements pooladapter.onitemclicklistener {
    private Toolbar mtoolbar;
   // private DrawerLayout drlay;



    private RecyclerView mRecyclerView;
    private pooladapter mpoolAdapter;
    private ArrayList<poolitems> mpoollist;
    public static String storenopool;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mRecyclerView=findViewById(R.id.recyclerpoolview);
        mRecyclerView.setHasFixedSize(true);
        mtoolbar=findViewById(R.id.toolbar);

        //get shared prefrences parameters

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        //get shared preferences end


       // drlay=findViewById(R.id.drawer);
        //NAVIGATION BAR DIRECTLY IMPORTED FROM MIKEPENZ
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(currentusername).withEmail(currentemail).withIcon(getResources().getDrawable(R.drawable.propic2))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();



        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Profile");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Logout");



        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mtoolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withSavedInstance(savedInstanceState)
                .withDisplayBelowStatusBar(false)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1, item2

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch (position)
                        {
                            case 1:
                                //pass token for getting user details
                                Intent p1=new Intent(homepage.this,profileactivity.class);
                                p1.putExtra("noofauthpool",storenopool);
                                //pass the token or required details
                                startActivity(p1);
                                break;
                            case 2:

                                Toast.makeText(homepage.this, "Logout", Toast.LENGTH_SHORT).show();
                                //during logout
                                //clear shared preferences
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("Secrets", MODE_PRIVATE);
                                SharedPreferences.Editor editor=pref.edit();
                                editor.clear();
                                editor.apply();
                                //
                                startActivity(new Intent(homepage.this,MainActivity.class));
                                finish();
                                break;
                            //during logout activity ends


                        }
                        return true;
                    }
                })
                .build();
        //NAVIGATION DRAWER ENDS

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mpoollist=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        //get shared preferences end
        //JSON URL (NOW ITS A DUMMY)
       /*
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
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

        */

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Getauthpoolformat> call=apiInterface.getauthpools(currenttoken);
        call.enqueue(new Callback<Getauthpoolformat>() {
            @Override
            public void onResponse(Call<Getauthpoolformat> call, Response<Getauthpoolformat> response) {
                if(response.isSuccessful())
                {
                    List<Pool> authpool=response.body().getPools();
                    storenopool= String.valueOf(authpool.size());
                    for(int i=0;i<authpool.size();i++)
                    {
                        String totalinvest= String.valueOf(authpool.get(i).getTotalInvestment());
                        String poolname=authpool.get(i).getName();
                        String poollocation=authpool.get(i).getLocation();
                        String poolid=authpool.get(i).getId();
                        String pooldescrip=authpool.get(i).getDescription();
                        String poolenginnerid=authpool.get(i).getEngineerId();
                        String report=null;

                        mpoollist.add(new poolitems(poolid,poolname,totalinvest,poollocation,report,pooldescrip,poolenginnerid));
                    }
                    mpoolAdapter = new pooladapter(homepage.this, mpoollist);
                    mRecyclerView.setAdapter(mpoolAdapter);
                    mpoolAdapter.setOnItemClickListener(homepage.this);
                }
                else
                {
                    Toast.makeText(homepage.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getauthpoolformat> call, Throwable t) {
                Toast.makeText(homepage.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//clicking activity of items in recycler view
    @Override
    public void onItemClick(int position) {
        Intent detailIntent=new Intent(homepage.this,detailactivity.class);
        poolitems clickedItem=mpoollist.get(position);
        detailIntent.putExtra("ID_EXTRA", new String[] {clickedItem.getPoolname(),clickedItem.getLocation(),clickedItem.getTotalivestment(),clickedItem.getDescription(),clickedItem.getEngineerid(),clickedItem.getPoolid()});
        //poolname,poollocation,poolinvestments,pooldescription,poolengineerid,poolid
        startActivity(detailIntent);

    }
/*
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
    */

}
