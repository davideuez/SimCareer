package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static java.sql.Types.NULL;

public class ScegliAutoFragment extends Fragment implements AutoConcesseAdapter.OnAutoConcessaListener{

    private RecyclerView autoConcesseRV;
    private RecyclerView.Adapter adapter;
    String[] auto;
    SharedPreferences campionato, temp;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scegli_auto, container, false);

        Context context = getContext();

        if(getArguments().getInt("posizione") != NULL){
            posizione = getArguments().getInt("posizione");
        }

        campionato = context.getSharedPreferences("Campionato"+posizione, MODE_PRIVATE);
        temp = context.getSharedPreferences("IscrizioniCampionatiTemp", MODE_PRIVATE);

        ArrayList<Auto> lista = creaAnteprimeAutoConcesse();

        this.autoConcesseRV = (RecyclerView) v.findViewById(R.id.lista_auto_concesse);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.autoConcesseRV.setLayoutManager(mLayoutManager);

        adapter = new AutoConcesseAdapter(lista, this);
        this.autoConcesseRV.setAdapter(adapter);

        return v;
    }

    private ArrayList<Auto> creaAnteprimeAutoConcesse() {
        ArrayList<Auto> list = new ArrayList<>();
        int i=0;
        String autoConcesse = campionato.getString("auto_concesse", "");
        auto = autoConcesse.split(",");
        for (String item : auto)
        {
            auto[i] = auto[i].trim();
            i++;
            list.add(new Auto(item));
        }

        return list;
    }

    @Override
    public void onAutoConcessaClick(int position) {
        Log.d("prova", "onAutoConcessaClick: Auto "+auto[position]);

        SharedPreferences.Editor editor = temp.edit();
        editor.putString("auto_selezionata", auto[position]);
        editor.commit();

        getActivity().onBackPressed();

    }
}