package com.hp.hp.retroallmethods.Api_client;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/**
 * Created by srishtiinnovative on 08/06/17.
 */

public class Api_client {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
            //    .baseUrl("http://www.schoolman.in/api/Teacher/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit getDeals() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://sicsglobal.com/App_projects/dyetcash/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit getMarvel() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Retrofit getMK() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/harishpadmanabh-HP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
