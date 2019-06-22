package com.hp.hp.retroallmethods.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hp.hp.retroallmethods.Api_client.Api_client;
import com.hp.hp.retroallmethods.Api_interface.API_interface;
import com.hp.hp.retroallmethods.Model.MortalKombatModel;
import com.hp.hp.retroallmethods.R;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mk extends Fragment {

    List<MortalKombatModel.PostsBean> kombatModelList;
    RecyclerView mklist;

    AlertDialog pd;

    public Mk() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mklist=view.findViewById(R.id.mklist);
        pd = new SpotsDialog(getActivity(),R.style.CustomAlert);
pd.show();
        API_interface api = Api_client.getMK().create(API_interface.class);
        Call<MortalKombatModel> mortalKombatModelCall=api.mkCall();
        mortalKombatModelCall.enqueue(new Callback<MortalKombatModel>() {
            @Override
            public void onResponse(Call<MortalKombatModel> call, Response<MortalKombatModel> response) {
                if(response.isSuccess())
                {

                     kombatModelList=response.body().getPosts();
                    LinearLayoutManager verticalLayoutmanager
                            = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false);
                    mklist.setLayoutManager(verticalLayoutmanager);

                    KombatAdapter recyclerAdapter=new KombatAdapter(kombatModelList,getContext());

                    mklist.setAdapter(recyclerAdapter);
                    pd.dismiss();

                }else{
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MortalKombatModel> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getContext(), "Call Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    class KombatAdapter extends RecyclerView.Adapter<KombatAdapter.KombatViewHolder>{

        List<MortalKombatModel.PostsBean> kombatModelList;

        Context context;

        public KombatAdapter(List<MortalKombatModel.PostsBean> kombatModelList, Context context) {
            this.kombatModelList = kombatModelList;
            this.context = context;
        }

        @NonNull
        @Override
        public KombatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemViewlayout = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mklist, parent, false);

            return new KombatViewHolder(itemViewlayout);
        }

        @Override
        public void onBindViewHolder(@NonNull KombatViewHolder holder, int position) {
            Glide
                    .with(getActivity())
                    .load(kombatModelList.get(position).getImage())
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.image);
            holder.name.setText(kombatModelList.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return kombatModelList.size();
        }

        class KombatViewHolder extends RecyclerView.ViewHolder{

            ImageView image;
            TextView name;
            View mView;
            public KombatViewHolder(@NonNull View itemView) {
                super(itemView);
                image=itemView.findViewById(R.id.mkimage);
                name=itemView.findViewById(R.id.mkname);
                mView=itemView;
            }
        }
    }
}
