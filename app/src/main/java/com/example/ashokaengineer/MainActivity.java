package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ashokaengineer.loginmodels.Getloginformat;
import com.example.ashokaengineer.loginmodels.Loginsendformat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



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

        SharedPreferences sharedPreferences2=getSharedPreferences("Secrets",MODE_PRIVATE);
        String tk=sharedPreferences2.getString("token","");
        if(tk!="")
        {
            startActivity(new Intent(this,homepage.class));
            finish();
        }


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

            //JSON POST METHOD TO VERIFY THE CREDENTIALS
            //FOR NOW JUST CHECKING WITH FAKE NAME AND FAKE PASSWORD

            //backend starts
            Retrofit.Builder builder=new Retrofit.Builder()
                    .baseUrl("https://ashokabackend.herokuapp.com/users/")//change it afterwards when everthing is hosted
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit=builder.build();
            ApiInterface apiInterface=retrofit.create(ApiInterface.class);
            Loginsendformat lgcred=new Loginsendformat(loginname,loginpass);
            Call<Getloginformat> call=apiInterface.sendlogincredentials(lgcred);
            call.enqueue(new Callback<Getloginformat>() {
                @Override
                public void onResponse(Call<Getloginformat> call, Response<Getloginformat> response) {
                    if(response.isSuccessful())
                    {
                        String token=response.body().getToken();
                        String username=response.body().getUser().getName();
                        String aadhar=response.body().getUser().getAadhaar();
                        String email=response.body().getUser().getEmail();
                        String phone=response.body().getUser().getPhone();
                        String id=response.body().getUser().getId();


                        SharedPreferences sharedPreferences=getSharedPreferences("Secrets",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();

                        editor.putString("token",token);
                        editor.putString("username",username);
                        editor.putString("aadhar",aadhar);
                        editor.putString("email",email);
                        editor.putString("phone",phone);
                        editor.putString("id",id);
                        editor.apply();
                        mprogressdialog.dismiss();
                        //successfully logged in
                        Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,homepage.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                        mprogressdialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Getloginformat> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    mprogressdialog.dismiss();
                }
            });


        }


        mprogressdialog.dismiss();


    }
}
