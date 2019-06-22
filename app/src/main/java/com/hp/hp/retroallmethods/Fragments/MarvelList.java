package com.hp.hp.retroallmethods.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hp.hp.retroallmethods.Api_client.Api_client;
import com.hp.hp.retroallmethods.Api_interface.API_interface;
import com.hp.hp.retroallmethods.AppPreference.AppPreferences;
import com.hp.hp.retroallmethods.Model.MarvelModel;
import com.hp.hp.retroallmethods.R;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarvelList extends Fragment {


    RecyclerView marvelRecyclerView;
    AppPreferences appPreferences;
    AlertDialog pd;
    List<MarvelModel> marvelModelList;

    public MarvelList() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        marvelRecyclerView=view.findViewById(R.id.marvelList);
        API_interface api = Api_client.getMarvel().create(API_interface.class);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(),R.style.CustomAlert);
        pd.show();
        Call<List<MarvelModel>> marvelCall=api.marvelCall();
        marvelCall.enqueue(new Callback<List<MarvelModel>>() {
            @Override
            public void onResponse(Call<List<MarvelModel>> call, Response<List<MarvelModel>> response) {
              marvelModelList=response.body();
                LinearLayoutManager verticalLayoutmanager
                        = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
                marvelRecyclerView.setLayoutManager(verticalLayoutmanager);
                MarvelAdapter marvelAdapter=new MarvelAdapter(marvelModelList,getActivity());
                marvelRecyclerView.setAdapter(marvelAdapter);
                pd.dismiss();


            }

            @Override
            public void onFailure(Call<List<MarvelModel>> call, Throwable t) {

            }
        });


    }

    class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder> {
        List<MarvelModel> marvelModelList;
        Context context;

        public MarvelAdapter(List<MarvelModel> marvelModelList, Context context) {
            this.marvelModelList = marvelModelList;
            this.context = context;
        }

        @NonNull
        @Override
        public MarvelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemViewlayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.marvelitem, parent, false);

            return new MarvelViewHolder(itemViewlayout);
        }

        @Override
        public void onBindViewHolder(@NonNull MarvelViewHolder holder, final int position) {
            holder.name.setText(marvelModelList.get(position).getName());
            Glide
                    .with(getActivity())
                    .load(marvelModelList.get(position).getImageurl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.image);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appPreferences.saveData("marvelname",marvelModelList.get(position).getName());
                    appPreferences.saveData("marvelrealname",marvelModelList.get(position).getRealname());
                    appPreferences.saveData("marvelimage",marvelModelList.get(position).getImageurl());
                    appPreferences.saveData("marvelbio",marvelModelList.get(position).getBio());
                    appPreferences.saveData("marvelteam",marvelModelList.get(position).getTeam());
                    Navigation.findNavController(v).navigate(R.id.marvelDetailaction);




                }
            });


        }

        @Override
        public int getItemCount() {
            return marvelModelList.size();
        }

        class MarvelViewHolder extends RecyclerView.ViewHolder{
            ImageView image;
            TextView name;
            View mView;

            public MarvelViewHolder(@NonNull View itemView) {
                super(itemView);
                image=itemView.findViewById(R.id.marvelimage);
                name=itemView.findViewById(R.id.marvelname);
                mView=itemView;

            }
        }
    }
}
