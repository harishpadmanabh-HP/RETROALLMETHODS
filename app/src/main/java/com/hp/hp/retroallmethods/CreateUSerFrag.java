package com.hp.hp.retroallmethods;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.hp.hp.retroallmethods.Api_client.Api_client;
import com.hp.hp.retroallmethods.Api_interface.API_interface;
import com.hp.hp.retroallmethods.AppPreference.AppPreferences;
import com.hp.hp.retroallmethods.Model.CreateEmployeeRequest;
import com.hp.hp.retroallmethods.Model.CreateEmployeeResponse;
import com.hp.hp.retroallmethods.Model.UpdateRequest;
import com.hp.hp.retroallmethods.Model.UpdateResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**

 */
public class CreateUSerFrag extends Fragment {

    TextInputLayout name,age,salary;
    TextView heading;
    ExtendedFloatingActionButton create,update;
    String Json;
    RequestBody requestBody=null;
    AppPreferences    appPreferences;
    public CreateUSerFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_u, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name=view.findViewById(R.id.name);
        age=view.findViewById(R.id.age);
        salary=view.findViewById(R.id.salary);
        create=view.findViewById(R.id.create);
        update=view.findViewById(R.id.updateuser);
        heading=view.findViewById(R.id.head);
        final API_interface api = Api_client.getClient().create(API_interface.class);

        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

    if(appPreferences.getInt("update")!=1)
    {
        update.setVisibility(View.INVISIBLE);
    }
    else if(appPreferences.getInt("update")==1)
    {
        heading.setText("Update Details ");
        create.setVisibility(View.INVISIBLE);
    }


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View mView=v;
                CreateEmployeeRequest request=new CreateEmployeeRequest();
                request.setName(name.getEditText().getText().toString());
                request.setSalary(salary.getEditText().getText().toString());
                request.setAge(age.getEditText().getText().toString());
                try {
                    Gson gson = new Gson();
                    Json = gson.toJson(request).trim();
                    System.out.println("FinalData................\n" + Json.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    requestBody = RequestBody.create(MediaType.parse("application/json"), Json.getBytes("UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Call<CreateEmployeeResponse> createEmployeeResponseCall=api.createEmployeeCall(requestBody);
                createEmployeeResponseCall.enqueue(new Callback<CreateEmployeeResponse>() {
                    @Override
                    public void onResponse(Call<CreateEmployeeResponse> call, Response<CreateEmployeeResponse> response) {

                        Toast.makeText(getContext(), "Employee record created", Toast.LENGTH_SHORT).show();

                        //pass click view in  findNavController
                        Navigation.findNavController(mView).navigate(R.id.actionbacktohome);

                    }

                    @Override
                    public void onFailure(Call<CreateEmployeeResponse> call, Throwable t) {

                    }
                });

            }
        });


    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v1) {
            final View mViewhome=v1;
           String id=appPreferences.getData("employeeid");

            UpdateRequest request =new UpdateRequest();
            request.setName(name.getEditText().getText().toString());
            request.setSalary(salary.getEditText().getText().toString());
            request.setAge(age.getEditText().getText().toString());
            try {
                Gson gson = new Gson();
                Json = gson.toJson(request).trim();
                System.out.println("FinalData................\n" + Json.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                requestBody = RequestBody.create(MediaType.parse("application/json"), Json.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Call<UpdateResponse> updateResponseCall=api.updateCall(requestBody,id);
            updateResponseCall.enqueue(new Callback<UpdateResponse>() {
                @Override
                public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                    Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(mViewhome).navigate(R.id.actionbacktohome);

                }

                @Override
                public void onFailure(Call<UpdateResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "API FAILURE", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(mViewhome).navigate(R.id.actionbacktohome);

                }
            });

        }
    });

    }
}
