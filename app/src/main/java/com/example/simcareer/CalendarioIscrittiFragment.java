package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

public class CalendarioIscrittiFragment extends Fragment implements CalendarioAdapter.OnCalendarioListener {

    private RecyclerView calendarioRv;
    private RecyclerView.Adapter adapter;
    SharedPreferences campionato, calendario;
    int posizione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.calendario_iscritti, container, false);

        ImageView back_btn = (ImageView) v.findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new InfoMenuFragment();
                Bundle data = new Bundle();//Use bundle to pass data
                data.putInt("posizione", posizione);
                newFragment.setArguments(data);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.commit();
            }
        });

        Context context = getContext();

        posizione = getArguments().getInt("posizione");
        campionato = context.getSharedPreferences("Campionato"+posizione, MODE_PRIVATE);
        calendario = context.getSharedPreferences("Calendario"+posizione, MODE_PRIVATE);


        ArrayList<Calendario> lista = creaAnteprimeGare();

        this.calendarioRv = (RecyclerView) v.findViewById(R.id.calendario_reciclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.calendarioRv.setLayoutManager(mLayoutManager);

        adapter = new CalendarioAdapter(lista, this);
        this.calendarioRv.setAdapter(adapter);

        // Inflate the layout for this fragment
        return v;
    }

    private ArrayList<Calendario> creaAnteprimeGare() {
        ArrayList<Calendario> list = new ArrayList<>();

        //campionato = campionato.getSharedPreferences("Campionato" + N_CAMPIONATO, MODE_PRIVATE);

        int numeroGare = campionato.getInt("totale_gare", 0);

        for (int i = 0; i < numeroGare; i++) {
            //calendario = getSharedPreferences("Calendario" + i, MODE_PRIVATE);
            String data = calendario.getString("data"+i, "");
            String circuito = calendario.getString("circuito"+i, "");

            list.add(new Calendario(data, circuito));
            Log.d("Gare", "N gare: " + numeroGare);
        }

        return list;
    }


    @Override
    public void onCalendarioClick(int position) {
        Log.d(TAG, "onCalendarioClick: Cliccato riga "+position);

        Context x = getContext();

        Fragment newFragment = new ClassificheFragment();
        Bundle data = new Bundle();//Use bundle to pass data
        data.putInt("posizione", posizione);
        newFragment.setArguments(data);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_infoFrame, newFragment);
        transaction.commit();

    }
}