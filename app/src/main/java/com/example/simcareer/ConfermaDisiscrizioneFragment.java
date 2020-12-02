package com.example.simcareer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ConfermaDisiscrizioneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conferma_disiscrizione, container, false);

        Context x = getContext();

        Button goHome = (Button) v.findViewById(R.id.go_home);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(x, Home_Campionati.class);
                startActivity(i);
            }
        });
        return v;
    }
}