package com.hp.hp.retroallmethods.Fragments;

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
import com.hp.hp.retroallmethods.Api_client.Api_client;
import com.hp.hp.retroallmethods.Api_interface.API_interface;
import com.hp.hp.retroallmethods.AppPreference.AppPreferences;
import com.hp.hp.retroallmethods.Model.EmployeeDetails;
import com.hp.hp.retroallmethods.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDetailsFragment extends Fragment {
    AppPreferences appPreferences;
    String id;
    TextView name,age,salary;
    ExtendedFloatingActionButton update;

    public EmployeeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        API_interface api = Api_client.getClient().create(API_interface.class);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        id=appPreferences.getData("employeeid");

        update=view.findViewById(R.id.update);
        name=view.findViewById(R.id.displayname);
        age=view.findViewById(R.id.dispage);
        salary=view.findViewById(R.id.displaysalary);

        Call<EmployeeDetails> employeeDetailsCall =api.employeeDetailCall("49054");
        employeeDetailsCall.enqueue(new Callback<EmployeeDetails>() {
            @Override
            public void onResponse(Call<EmployeeDetails> call, Response<EmployeeDetails> response) {

                name.setText(response.body().getEmployee_name());
                age.setText("Employee Age : "+response.body().getEmployee_age());
                salary.setText("Employee Salary : "+response.body().getEmployee_salary());




            }

            @Override
            public void onFailure(Call<EmployeeDetails> call, Throwable t) {

                Toast.makeText(getContext(), "API FAILURE", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_employeeDetailsFragment_to_createUSerFrag);
                appPreferences.saveInt("update",1);

            }
        });




    }
}
