package com.hp.hp.retroallmethods.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.hp.hp.retroallmethods.Api_client.Api_client;
import com.hp.hp.retroallmethods.Api_interface.API_interface;
import com.hp.hp.retroallmethods.AppPreference.AppPreferences;
import com.hp.hp.retroallmethods.Model.DealsModel;
import com.hp.hp.retroallmethods.R;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealsFragment extends Fragment {

RecyclerView dealrecyclerView;
AppPreferences appPreferences;
AlertDialog pd;
List<DealsModel.DealsBean> dealsModelList;
String imageURL="http://sicsglobal.com/App_projects/dyetcash/uploads/deals/";


    public DealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dealrecyclerView=view.findViewById(R.id.dealsList);
        API_interface api = Api_client.getDeals().create(API_interface.class);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(),R.style.CustomAlert);
        pd.show();

        API_interface apideals = Api_client.getDeals().create(API_interface.class);


        final Call<DealsModel> dealsModelCall=apideals.dealsCall();
        dealsModelCall.enqueue(new Callback<DealsModel>() {
            @Override
            public void onResponse(Call<DealsModel> call, Response<DealsModel> response) {


                dealsModelList=response.body().getDeals();
              //  Toast.makeText(getContext(), "Deals "+dealsModelList.get(0).getD_image(), Toast.LENGTH_SHORT).show();
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
               dealrecyclerView.setLayoutManager(verticalLayoutmanager);
                //
                // for grid
                // dealrecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

               DealsAdapter dealsAdapter=new DealsAdapter(getActivity(),dealsModelList);
                dealrecyclerView.setAdapter(dealsAdapter);
                pd.dismiss();

            }

            @Override
            public void onFailure(Call<DealsModel> call, Throwable t) {

            }
        });


    }

    class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.MyViewHolder>
    {
        Context context;
        List<DealsModel.DealsBean> dealsModelList;

        public DealsAdapter(Context context, List<DealsModel.DealsBean> dealsModelList) {
            this.context = context;
            this.dealsModelList = dealsModelList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemViewlayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dealitem, parent, false);

            return new MyViewHolder(itemViewlayout);        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.dealname.setText(dealsModelList.get(position).getD_name());
            Glide
                    .with(getActivity())
                    .load(imageURL+dealsModelList.get(position).getD_image())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.dealimage);

        }

        @Override
        public int getItemCount() {
            return dealsModelList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            KenBurnsView dealimage;
            TextView dealname;
            View mView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                dealimage=itemView.findViewById(R.id.dealimage);
                dealname=itemView.findViewById(R.id.dealname);
                mView=itemView;
            }
        }
    }
}
