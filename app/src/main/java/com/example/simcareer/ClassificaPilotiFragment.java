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

public class ClassificaPilotiFragment extends Fragment {

    private RecyclerView classificaPilotiRv;
    private RecyclerView.Adapter adapter;
    SharedPreferences classificaPiloti;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_classifica_piloti, container, false);

        Context context = getContext();
        int posizione = getArguments().getInt("posizione");
        classificaPiloti = context.getSharedPreferences("Classifiche_Campionato"+posizione, MODE_PRIVATE);


        ArrayList<Classificato> lista = creaAnteprimeClassificati();

        this.classificaPilotiRv = (RecyclerView) v.findViewById(R.id.lista_classificaPiloti);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.classificaPilotiRv.setLayoutManager(mLayoutManager);

        adapter = new ClassificaAdapter(lista);
        this.classificaPilotiRv.setAdapter(adapter);

        return v;
    }

    private ArrayList<Classificato> creaAnteprimeClassificati() {

        ArrayList<Classificato> list = new ArrayList<>();

        int numeroClassificati = classificaPiloti.getInt("pilotiInClassifica", 0);

        for (int i = 0; i < numeroClassificati; i++) {
            String nome = classificaPiloti.getString("nome"+i, "");
            String team = classificaPiloti.getString("team"+i, "");
            String auto = classificaPiloti.getString("auto"+i, "");
            int punti = classificaPiloti.getInt("punti"+i, 0);

            list.add(new Classificato(nome, auto, team, punti));
            Log.d("Classificati", "N classificati: " + numeroClassificati);
        }

        Collections.sort(list, new Comparator<Classificato>(){
            public int compare(Classificato obj1, Classificato obj2) {
                 return Integer.valueOf(obj2.getPunti()).compareTo(Integer.valueOf(obj1.getPunti()));

            }
        });

        return list;

    }
}