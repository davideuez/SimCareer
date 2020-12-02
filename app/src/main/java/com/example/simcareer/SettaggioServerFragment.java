package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SettaggioServerFragment extends Fragment {

    private RecyclerView settingsRv;
    private RecyclerView.Adapter adapter;
    SharedPreferences campionato;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settaggio_server, container, false);

        ImageView back_btn = (ImageView) v.findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Context context = getContext();

        posizione = getArguments().getInt("posizione");
        campionato = context.getSharedPreferences("Campionato"+posizione, MODE_PRIVATE);

        ArrayList<Settings> lista = creaSettings();

        this.settingsRv = (RecyclerView) v.findViewById(R.id.settings_recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.settingsRv.setLayoutManager(mLayoutManager);

        adapter = new SettingsAdapter(lista);
        this.settingsRv.setAdapter(adapter);


        return v;

    }

    private ArrayList<Settings> creaSettings() {
        ArrayList<Settings> list = new ArrayList<>();

            //campionato = campionato.getSharedPreferences("Campionato" + N_CAMPIONATO, MODE_PRIVATE);

            String bandiere = campionato.getString("bandiere_campionato", "");
            String aiuti = campionato.getString("aiuti_campionato", "");
            String carburante = campionato.getString("cons-carb_campionato", "");
            String gomme = campionato.getString("cons-gomme_campionato", "");

            String[] titoli = {"Bandiere Campionato:", "Aiuti campionato: ", "Consumo carburante: ", "Consumo gomme: "};

            list.add(new Settings(titoli[0], bandiere));
            list.add(new Settings(titoli[1], aiuti));
            list.add(new Settings(titoli[2], carburante));
            list.add(new Settings(titoli[3], gomme));

            return list;
        }

}