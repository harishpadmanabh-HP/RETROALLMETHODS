package com.hp.hp.retroallmethods;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hp.hp.retroallmethods.Api_client.Api_client;
import com.hp.hp.retroallmethods.Api_interface.API_interface;
import com.hp.hp.retroallmethods.AppPreference.AppPreferences;
import com.hp.hp.retroallmethods.Model.EmployeeList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeListFragment extends Fragment {

    RecyclerView recyclerView;
    List<EmployeeList> employeeLists;
    AppPreferences appPreferences;
    FloatingActionButton floatingActionButton;


    public EmployeeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.employeelist);
       API_interface api = Api_client.getClient().create(API_interface.class);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        floatingActionButton=view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new emp
                Navigation.findNavController(v).navigate(R.id.actionCreatenew);


            }
        });

        Call<List<EmployeeList>> empcall = api.employeeListCall();
        empcall.enqueue(new Callback<List<EmployeeList>>() {
            @Override
            public void onResponse(Call<List<EmployeeList>> call, Response<List<EmployeeList>> response) {
                employeeLists=response.body();
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(verticalLayoutmanager);
                RecyclerAdapter recyclerAdapter=new RecyclerAdapter(getActivity(),employeeLists);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<EmployeeList>> call, Throwable t) {

            }
        });





    }
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.EmpViewHolder> {

        Context context;
        List<EmployeeList> employeeLists;


        public RecyclerAdapter(Context context, List<EmployeeList> employeeLists) {
            this.context = context;
            this.employeeLists = employeeLists;
        }

        @NonNull
        @Override
        public EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemViewlayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.singlelistitem, parent, false);

            return new EmpViewHolder(itemViewlayout);
        }

        @Override
        public void onBindViewHolder(@NonNull EmpViewHolder holder, final int position) {

            holder.name.setText(employeeLists.get(position).getEmployee_name());
            holder.salary.setText("Salary : "+employeeLists.get(position).getEmployee_salary());
            holder.age.setText("Age : "+employeeLists.get(position).getEmployee_age());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    appPreferences.saveData("employeeid",employeeLists.get(position).getId());
                    Navigation.findNavController(v).navigate(R.id.next_action);

                }
            });
        }

        @Override
        public int getItemCount() {
            return employeeLists.size();
        }

        class EmpViewHolder extends RecyclerView.ViewHolder{

            TextView name,age,salary;
            View mView;

            public EmpViewHolder(@NonNull View itemView) {
                super(itemView);
                mView=itemView;
                name=itemView.findViewById(R.id.name);
                age=itemView.findViewById(R.id.age);
                salary=itemView.findViewById(R.id.salary);
            }
        }


    }

}
