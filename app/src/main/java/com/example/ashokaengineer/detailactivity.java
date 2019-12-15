package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashokaengineer.newreportsubmitmodels.Getreportsubmitformat;
import com.example.ashokaengineer.newreportsubmitmodels.Sendreportformat;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailactivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    String temp = "";
    String timedate="";
    private TextView showinreport;
    public EditText reporttext;
    private EditText reporttitle;
    //getting values from previous page or recycler viewer like poolname,area,location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);
        mtoolbar=findViewById(R.id.toolbar);

        Intent intent=getIntent();
        String plname=intent.getStringArrayExtra("ID_EXTRA")[0];
        String pllocate=intent.getStringArrayExtra("ID_EXTRA")[1];
        String plinvest=intent.getStringArrayExtra("ID_EXTRA")[2];
        String pldescription=intent.getStringArrayExtra("ID_EXTRA")[3];
        String plengineerid=intent.getStringArrayExtra("ID_EXTRA")[4];
        String plpoolid=intent.getStringArrayExtra("ID_EXTRA")[5];

        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showinreport=(TextView)findViewById(R.id.dreporttext);
        showinreport.setMovementMethod(new ScrollingMovementMethod());


        //connecting id of detailactivity to detail.java
        ImageView imgview=findViewById(R.id.detimg);
        TextView  pooln=findViewById(R.id.detpname);
        TextView poolloc=findViewById(R.id.detlocation);
        reporttitle=findViewById(R.id.denewreporttitle);
        reporttext=findViewById(R.id.denewreporttext);
        TextView poolinvest=findViewById(R.id.detinvest);
        TextView poolMember=findViewById(R.id.detailmemberscon);

        //for displaying images in the detail items.
        //not shown currently please change the image url

        //Update the image in detail activity
        //Picasso.with(this).load(imageUrl).fit().centerInside().into(imgview);
        Picasso.with(detailactivity.this).load(R.drawable.gardenland).placeholder(R.drawable.gardenland).into(imgview);
        pooln.setText(plname);
        poolloc.setText(pllocate);
        poolinvest.setText("Rs "+plinvest);
        //pool member has not been initialised properly please see this
        poolMember.setText(pldescription);

        poolreport();




    }

    //reports showing in report section
    private void poolreport() {

        Intent intent=getIntent();
        String plpoolid=intent.getStringArrayExtra("ID_EXTRA")[5];
        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");


        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Getpoolreportformat> call=apiInterface.getpoolreports(currenttoken,plpoolid);
        call.enqueue(new Callback<Getpoolreportformat>() {
            @Override
            public void onResponse(Call<Getpoolreportformat> call, Response<Getpoolreportformat> response) {
                if(response.isSuccessful())
                {
                    List<Report> poolreport=response.body().getReports();
                    for(int i=poolreport.size()-1;i>=0;i--)
                    {
                        double time=poolreport.get(i).getTimestamp();
                        Date date = new Date((long) time);
                        timedate= String.valueOf(date);

                       temp+=timedate+"\nTitle:"+poolreport.get(i).getTitle()+"\n"+"Description:"+poolreport.get(i).getDescription()+"\n"+"\n"+"\n";
                       showinreport.append(temp);
                       temp="";
                       timedate="";
                    }
                }
                else
                {
                    Toast.makeText(detailactivity.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getpoolreportformat> call, Throwable t) {
                Toast.makeText(detailactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //submit button clicking activity for report to be submitted in json format
    public void onclickreportbtn(View view) {

        Intent intent=getIntent();
        String plname=intent.getStringArrayExtra("ID_EXTRA")[0];
        String pllocate=intent.getStringArrayExtra("ID_EXTRA")[1];
        String plinvest=intent.getStringArrayExtra("ID_EXTRA")[2];
        String pldescription=intent.getStringArrayExtra("ID_EXTRA")[3];
        String plengineerid=intent.getStringArrayExtra("ID_EXTRA")[4];
        String plpoolid=intent.getStringArrayExtra("ID_EXTRA")[5];

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        //shared preferences ends
        String newreporttitle=reporttitle.getText().toString();
        String newreport=reporttext.getText().toString();
        if(newreport.isEmpty()||newreporttitle.isEmpty())
        {
            Toast.makeText(this, "No Content to send", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Updating Report", Toast.LENGTH_SHORT).show();
            //JSON FILE CREATION OF THE REPORT TO BE SEND OF THE newreport variable
            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);
            Sendreportformat sendreport=new Sendreportformat(plpoolid,newreporttitle,newreport);
            Call<Getreportsubmitformat> call=apiInterface.createreport(currenttoken,sendreport);
            call.enqueue(new Callback<Getreportsubmitformat>() {
                @Override
                public void onResponse(Call<Getreportsubmitformat> call, Response<Getreportsubmitformat> response) {
                    if(response.isSuccessful())
                    {

                        double time=response.body().getReport().getTimestamp();
                        Date date = new Date((long) time);
                        String desp=response.body().getReport().getDescription();
                        String m= String.valueOf(date);
                       // Toast.makeText(detailactivity.this,m, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(detailactivity.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Getreportsubmitformat> call, Throwable t) {
                    Toast.makeText(detailactivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }


    }
}
