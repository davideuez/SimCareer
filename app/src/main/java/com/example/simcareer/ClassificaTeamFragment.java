package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Context.MODE_PRIVATE;

public class ClassificaTeamFragment extends Fragment {

    private RecyclerView classificaTeamRv;
    private RecyclerView.Adapter adapter;
    SharedPreferences classificaTeam;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_classifica_team, container, false);

        Context context = getContext();
        posizione = getArguments().getInt("posizione");
        classificaTeam = context.getSharedPreferences("Classifiche_Team_Campionato"+posizione, MODE_PRIVATE);

        ArrayList<Classificato> lista = creaAnteprimeClassificati();

        this.classificaTeamRv = (RecyclerView) v.findViewById(R.id.lista_classificaTeam);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.classificaTeamRv.setLayoutManager(mLayoutManager);

        adapter = new ClassificaAdapter(lista);
        this.classificaTeamRv.setAdapter(adapter);

        return v;
    }

    private ArrayList<Classificato> creaAnteprimeClassificati() {

        ArrayList<Classificato> list = new ArrayList<>();

        int numeroTeamClassificati = classificaTeam.getInt("teamInClassifica", 0);

        for (int i = 0; i < numeroTeamClassificati; i++) {
            String team = classificaTeam.getString("team"+i, "");
            String auto = classificaTeam.getString("auto"+i, "");
            int punti = classificaTeam.getInt("punti"+i, 0);

            list.add(new Classificato(team, auto, punti));
            Log.d("Classificati", "N team classificati: " + numeroTeamClassificati);
        }

        Collections.sort(list, new Comparator<Classificato>(){
            public int compare(Classificato obj1, Classificato obj2) {
                return Integer.valueOf(obj2.getPunti()).compareTo(Integer.valueOf(obj1.getPunti()));

            }
        });

        return list;

    }

}