package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.example.ashokaengineer.homepage.EXTRA_AREA;
import static com.example.ashokaengineer.homepage.EXTRA_LOCATION;
import static com.example.ashokaengineer.homepage.EXTRA_NAME;
import static com.example.ashokaengineer.homepage.EXTRA_URL;

public class detailactivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    private TextView derepottext;
    public EditText reporttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);
        mtoolbar=findViewById(R.id.toolbar);


        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        derepottext=(TextView)findViewById(R.id.dreporttext);
        derepottext.setMovementMethod(new ScrollingMovementMethod());

        //getting values from previous page or recycler viewer like poolname,area,location
        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra(EXTRA_URL);
        String poolname=intent.getStringExtra(EXTRA_NAME);
        String location=intent.getStringExtra(EXTRA_LOCATION);
        String area=intent.getStringExtra(EXTRA_AREA);

        //connecting id of detailactivity to detail.java
        ImageView imgview=findViewById(R.id.detimg);
        TextView  pooln=findViewById(R.id.detpname);
        TextView poolloc=findViewById(R.id.detlocation);
        reporttext=findViewById(R.id.denewreporttext);
        TextView poolarea=findViewById(R.id.detarea);
        TextView poolMember=findViewById(R.id.detailmemberscon);

        //for displaying images in the detail items.
        //not shown currently please change the image url

        //Update the image in detail activity
        //Picasso.with(this).load(imageUrl).fit().centerInside().into(imgview);
        Picasso.with(detailactivity.this).load(imageUrl).placeholder(R.drawable.gardenland).into(imgview);
        pooln.setText(poolname);
        poolloc.setText(location);
        poolarea.setText(area);
        //pool member has not been initialised properly please see this
        poolMember.setText("Harish:9878798222\nArun:7898789878");




    }

    //submit button clicking activity for report to be submitted in json format
    public void onclickreportbtn(View view) {
        String newreport=reporttext.getText().toString();
        if(newreport.isEmpty())
        {
            Toast.makeText(this, "No Content to send", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Updating Report", Toast.LENGTH_SHORT).show();
            //JSON FILE CREATION OF THE REPORT TO BE SEND OF THE newreport variable

        }


    }
}
