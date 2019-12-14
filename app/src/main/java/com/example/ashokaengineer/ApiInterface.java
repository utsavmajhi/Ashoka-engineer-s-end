package com.example.ashokaengineer;

import com.example.ashokaengineer.Authorisedpoolsmodel.Getauthpoolformat;
import com.example.ashokaengineer.loginmodels.Getloginformat;
import com.example.ashokaengineer.loginmodels.Loginsendformat;
import com.example.ashokaengineer.newreportsubmitmodels.Getreportsubmitformat;
import com.example.ashokaengineer.newreportsubmitmodels.Sendreportformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    //for login activity
    @POST("signin")
    Call<Getloginformat> sendlogincredentials(@Body Loginsendformat loginsendformat);


    //for all pools he has been authorised
    @GET("engineers/pools")
    Call<Getauthpoolformat> getauthpools(@Header("Authorization") String header);

    //create a report
    @POST("engineers/report")
    Call<Getreportsubmitformat> createreport(@Header("Authorization") String header, @Body Sendreportformat sendreportformat);

    //get report for a pool
    @GET("engineers/getpoolsreports/{poolid}")
    Call<Getpoolreportformat> getpoolreports(@Header("Authorization") String header, @Path("poolid") String poolid);

}
