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
import com.hp.hp.retroallmethods.Model.CreateEmployeeRequest;
import com.hp.hp.retroallmethods.Model.CreateEmployeeResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**

 */
public class CreateUSerFrag extends Fragment {

    TextInputLayout name,age,salary;
    ExtendedFloatingActionButton create;
    String Json;
    RequestBody requestBody=null;

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
        final API_interface api = Api_client.getClient().create(API_interface.class);

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

                        Navigation.findNavController(mView).navigate(R.id.actionbacktohome);

                    }

                    @Override
                    public void onFailure(Call<CreateEmployeeResponse> call, Throwable t) {

                    }
                });

            }
        });

    }
}
