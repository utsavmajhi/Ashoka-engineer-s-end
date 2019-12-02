package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {
    private Toolbar mtoolbar;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mpoollist=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON()
    {
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String poolname = hit.getString("user");
                                String mImageurl= hit.getString("tags");
                                String area = hit.getString("imageHeight");
                                String location = hit.getString("views");

                                //remember maintain the same order as in poolitemslist.java
                                mpoollist.add(new poolitems(mImageurl,poolname,area,location));
                            }
                            mpoolAdapter = new pooladapter(homepage.this, mpoollist);
                            mRecyclerView.setAdapter(mpoolAdapter);

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



}
