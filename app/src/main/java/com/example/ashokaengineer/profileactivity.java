package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class profileactivity extends AppCompatActivity {

    private Toolbar protoolbar;
    private TextView profilename,profileemail,profileaadhar,profilephn,pronoauthpools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
        protoolbar=findViewById(R.id.protoolbar);
        profilename=findViewById(R.id.proname);
        profilephn=findViewById(R.id.prophn);
        profileemail=findViewById(R.id.proemail);
        profileaadhar=findViewById(R.id.proaadhar);
        pronoauthpools=findViewById(R.id.pronumpools);




        setSupportActionBar(protoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get shared prefrences parameters

        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");
        //get shared preferences parameters

        //setting parameters from shared prefrences values
        profilename.setText(currentusername);
        profileemail.setText(currentemail);
        profileaadhar.setText(currentaadhar);
        profilephn.setText(currentph);




    }
}
