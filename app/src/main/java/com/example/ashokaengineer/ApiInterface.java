package com.example.ashokaengineer;

import com.example.ashokaengineer.loginmodels.Getloginformat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("signin")
    Call<Getloginformat> sendlogincredentials(@Body Loginsendformat loginsendformat);
}
