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

import static android.content.Context.MODE_PRIVATE;
import static java.sql.Types.NULL;

public class ScegliTeamFragment extends Fragment implements TeamAdapter.OnTeamListener{

    private RecyclerView teamRV;
    private RecyclerView.Adapter adapter;
    SharedPreferences temp;
    String[] team;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scegli_team, container, false);

        Context context = getContext();

        if(getArguments().getInt("posizione") != NULL){
            posizione = getArguments().getInt("posizione");
        }

        temp = context.getSharedPreferences("IscrizioniCampionatiTemp", MODE_PRIVATE);

        ArrayList<Team> lista = creaTeam();

        this.teamRV = (RecyclerView) v.findViewById(R.id.lista_team);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.teamRV.setLayoutManager(mLayoutManager);

        adapter = new TeamAdapter(lista, this);
        this.teamRV.setAdapter(adapter);

        return v;
    }

    private ArrayList<Team> creaTeam() {
        ArrayList<Team> list = new ArrayList<>();
        int i=0;
        String teams = "Busters eRacing Team, ALDesign Racing Team, Delta Squadra Corse, BlueBolt Academy Lions, FIP 3.6 Rantgen Racing Team, Energy Racing Team";
        team = teams.split(",");
        for (String item : team)
        {
            team[i] = team[i].trim();
            i++;
            list.add(new Team(item));
        }

        return list;
    }

    @Override
    public void onTeamClick(int position) {
        Log.d("prova", "onTeamClick: Team "+team[position]);

        SharedPreferences.Editor editor = temp.edit();
        editor.putString("team_selezionato", team[position]);
        editor.commit();

        getActivity().onBackPressed();

    }

}