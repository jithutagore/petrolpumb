package com.jithu.newone;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class homefrag extends Fragment {

private androidx.appcompat.widget.AppCompatButton fuelstation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_homefrag, container, false);
        fuelstation=view.findViewById(R.id.fuelstation);
        fuelstation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment llf = new home();
                ft.replace(R.id.homefrag, llf);
                ft.commit();
            }
        });
        return view;
    }
}