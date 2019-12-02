package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    String fakename="iitpatna";
    String fakepass="1234";

    Button loginbtn;
    EditText usern;
    EditText userpass;
    private ProgressDialog mprogressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usern=(EditText)findViewById(R.id.lgname);
        userpass=(EditText)findViewById(R.id.lgpass);
        loginbtn=(Button)findViewById(R.id.lgbtn);
    }


    //login button clicking activity
    public void lgclickbtn(View view) {
        mprogressdialog=new ProgressDialog(this);
        mprogressdialog.setTitle("Loading User Data");
        mprogressdialog.setMessage("Verifying Credentials");
        mprogressdialog.setCanceledOnTouchOutside(false);
        mprogressdialog.show();
        String loginname=usern.getText().toString();
        String loginpass=userpass.getText().toString();
        //checkig if username or password foelds are empty
        if(loginname.isEmpty()||loginpass.isEmpty())
        {
            mprogressdialog.dismiss();
            Toast.makeText(this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
        }
        else//if both username and password are not empty
        {
            mprogressdialog.dismiss();
            //JSON POST METHOD TO VERIFY THE CREDENTIALS
            //FOR NOW JUST CHECKING WITH FAKE NAME AND FAKE PASSWORD
            if(loginname.equals(fakename)&&loginpass.equals(fakepass))
            {
                //Also save the token that you are getting from json file
                //goto next page
                startActivity(new Intent(this,homepage.class));
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
            }

        }


        mprogressdialog.dismiss();


    }
}
