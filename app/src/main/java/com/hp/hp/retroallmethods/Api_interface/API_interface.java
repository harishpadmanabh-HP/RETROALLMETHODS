package com.hp.hp.retroallmethods.Api_interface;

import com.hp.hp.retroallmethods.Model.CreateEmployeeResponse;
import com.hp.hp.retroallmethods.Model.EmployeeDetails;
import com.hp.hp.retroallmethods.Model.EmployeeList;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface API_interface {

    @GET("employees")
    Call<List<EmployeeList>> employeeListCall ();

    @GET("employee/{id}")
    Call<EmployeeDetails> employeeDetailCall(@Path("id") String id);

    @POST("/create")
    Call<CreateEmployeeResponse> createEmployeeCall(@Body RequestBody requestBody);


}
