package com.example.simcareer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ConfermaIscrizioneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conferma_iscrizione, container, false);

        String auto = getArguments().getString("auto");
        String team = getArguments().getString("team");

        TextView nomeAuto = (TextView) v.findViewById(R.id.auto_selezionata);
        TextView nomeTeam = (TextView) v.findViewById(R.id.team_selezionato);
        Button vediIscrizioni = (Button) v.findViewById(R.id.vedi_iscrizioni);

        nomeAuto.setText(auto);
        nomeTeam.setText(team);

        vediIscrizioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context c = getContext();

                Intent i = new Intent(c, IscrizioniEffettuate.class);
                startActivity(i);
            }
        });

        return v;
    }
}