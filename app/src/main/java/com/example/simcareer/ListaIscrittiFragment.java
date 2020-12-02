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
import android.widget.ImageView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class ListaIscrittiFragment extends Fragment implements IscrittiAdapter.OnIscrittoListener{

    private RecyclerView iscrittiRv;
    private RecyclerView.Adapter adapter;
    SharedPreferences iscrittiCampionato;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista_iscritti, container, false);

        ImageView back_btn = (ImageView) v.findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Context context = getContext();

        posizione = getArguments().getInt("posizione");
        iscrittiCampionato = context.getSharedPreferences("Piloti_Iscritti_Campionato"+posizione, MODE_PRIVATE);

        ArrayList<Iscritto> lista = creaAnteprimeIscritti();

        this.iscrittiRv = (RecyclerView) v.findViewById(R.id.iscritti_reciclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.iscrittiRv.setLayoutManager(mLayoutManager);

        adapter = new IscrittiAdapter(lista, this);
        this.iscrittiRv.setAdapter(adapter);


        return v;
    }

    private ArrayList<Iscritto> creaAnteprimeIscritti() {
        ArrayList<Iscritto> list = new ArrayList<>();

        //campionato = campionato.getSharedPreferences("Campionato" + N_CAMPIONATO, MODE_PRIVATE);

        int numero_iscritti = iscrittiCampionato.getInt("totale_piloti", 0);

        for (int i = 0; i < numero_iscritti; i++) {
            //calendario = getSharedPreferences("Calendario" + i, MODE_PRIVATE);
            String nome = iscrittiCampionato.getString("nome_pilota"+i, "");
            String team = iscrittiCampionato.getString("team_pilota"+i, "");
            String auto = iscrittiCampionato.getString("auto_pilota"+i, "");

            list.add(new Iscritto(nome, team, auto));
            Log.d("Gare", "N iscritti: " + numero_iscritti);
        }

        return list;
    }


    public void onIscrittoClick(int position) {
        Log.d(TAG, "onCalendarioClick: Cliccato riga "+position);
    }



}