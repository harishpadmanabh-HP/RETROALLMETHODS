package com.hp.hp.retroallmethods.Api_interface;

import com.hp.hp.retroallmethods.Model.CreateEmployeeResponse;
import com.hp.hp.retroallmethods.Model.DealsModel;
import com.hp.hp.retroallmethods.Model.EmployeeDetails;
import com.hp.hp.retroallmethods.Model.EmployeeList;
import com.hp.hp.retroallmethods.Model.MarvelModel;
import com.hp.hp.retroallmethods.Model.UpdateResponse;

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
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface API_interface {
    /*
    * #	Route	    Method	     Type	        Full route                                       	Description	Details

1	/employee	      GET	     JSON	    http://dummy.restapiexample.com/api/v1/employees	Get all employee data	Details

2	/employee/{id}	  GET	     JSON	    http://dummy.restapiexample.com/api/v1/employee/1	Get a single employee data	Details

3	/create	          POST	     JSON	    http://dummy.restapiexample.com/api/v1/create	Create new record in database	Details

4	/update/{id}	  PUT	     JSON	    http://dummy.restapiexample.com/api/v1/update/21	Update an employee record	Details

5	/delete/{id}	  DELETE	 JSON	    http://dummy.restapiexample.com/api/v1/update/2	Delete an employee record	Details
    * */

    @GET("employees")
    Call<List<EmployeeList>> employeeListCall ();

    @GET("employee/{id}")
    Call<EmployeeDetails> employeeDetailCall(@Path("id") String id);

    @POST("/create")
    Call<CreateEmployeeResponse> createEmployeeCall(@Body RequestBody requestBody);

    @PUT("/update/{id}")
    Call<UpdateResponse> updateCall(@Body RequestBody requestBody,@Path("id") String id);

//http://sicsglobal.com/App_projects/dyetcash/api/v1/deals

    @GET("deals")
    Call<DealsModel> dealsCall();

    //https://simplifiedcoding.net/demos/marvel/

    @GET("marvel/")
    Call<List<MarvelModel>> marvelCall();
}
