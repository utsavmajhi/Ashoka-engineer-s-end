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

import com.squareup.picasso.Picasso;

import java.security.Timestamp;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class detailactivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    private TextView derepottext;
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
        Toast.makeText(this, plpoolid, Toast.LENGTH_SHORT).show();
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        derepottext=(TextView)findViewById(R.id.dreporttext);
        derepottext.setMovementMethod(new ScrollingMovementMethod());


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
                    .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
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
