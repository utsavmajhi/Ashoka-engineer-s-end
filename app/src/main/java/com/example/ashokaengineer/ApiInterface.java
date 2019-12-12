package com.example.ashokaengineer;

import com.example.ashokaengineer.Authorisedpoolsmodel.Getauthpoolformat;
import com.example.ashokaengineer.loginmodels.Getloginformat;
import com.example.ashokaengineer.loginmodels.Loginsendformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    //for login activity
    @POST("signin")
    Call<Getloginformat> sendlogincredentials(@Body Loginsendformat loginsendformat);


    //for all pools he has been authorised
    @GET("engineers/pools")
    Call<Getauthpoolformat> getauthpools(@Header("Authorization") String header);

    //create a report
    @POST("engineers/report")
    Call<Getreportsubmitformat> createreport(@Header("Authorization") String header,@Body Sendreportformat sendreportformat);

}
