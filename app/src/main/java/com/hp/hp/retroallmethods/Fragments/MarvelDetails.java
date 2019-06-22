package com.hp.hp.retroallmethods.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.hp.hp.retroallmethods.AppPreference.AppPreferences;
import com.hp.hp.retroallmethods.R;

import dmax.dialog.SpotsDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelDetails extends Fragment {

    AppPreferences appPreferences;
    AlertDialog pd;
    KenBurnsView image;
    TextView name,realname,team,bio;

    public MarvelDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marvel_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(),R.style.CustomAlert);
        pd.show();
        image=view.findViewById(R.id.marveldisplayimage);
        name=view.findViewById(R.id.displayname);
        realname=view.findViewById(R.id.displayrealname);
        team=view.findViewById(R.id.displayteam);
        bio=view.findViewById(R.id.displaybio);


        Glide
                .with(getActivity())
                .load(appPreferences.getData("marvelimage"))
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(image);
        name.setText("NAME : "+appPreferences.getData("marvelname"));
        realname.setText("REAL NAME : "+appPreferences.getData("marvelrealname"));
        team.setText("TEAM : "+appPreferences.getData("marvelteam"));
        bio.setText("BIO : \n"+appPreferences.getData("marvelbio"));

        pd.dismiss();



    }
}
