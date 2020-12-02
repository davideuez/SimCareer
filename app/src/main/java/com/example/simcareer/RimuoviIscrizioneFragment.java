package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.ContentValues.TAG;

public class RimuoviIscrizioneFragment extends Fragment {

    SharedPreferences pilotiIscitti, datiUtente, elencoIscrizioni;
    int getPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rimuovi_iscrizione, container, false);

        getPosition = getArguments().getInt("posizione");

        Context c = getContext();

        Button back = (Button) v.findViewById(R.id.back_home);
        Button disiscriviti = (Button) v.findViewById(R.id.disiscriviti);

        pilotiIscitti = c.getSharedPreferences("Piloti_Iscritti_Campionato"+getPosition, Context.MODE_PRIVATE);
        elencoIscrizioni = c.getSharedPreferences("IscrizioniCampionati", Context.MODE_PRIVATE);
        datiUtente = c.getSharedPreferences("Dati_Utente", Context.MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        disiscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posizioneMatch = findMatch();
                int numeroPiloti = pilotiIscitti.getInt("totale_piloti", 0);
                Log.d(TAG, "onClick: Cliccato posizione "+posizioneMatch);

                SharedPreferences.Editor editor = pilotiIscitti.edit();

                editor.remove("nome_pilota"+posizioneMatch);
                editor.remove("auto_pilota"+posizioneMatch);
                editor.remove("team_pilota"+posizioneMatch);
                editor.putInt("totale_piloti", numeroPiloti);
                editor.commit();

                SharedPreferences.Editor editor1 = elencoIscrizioni.edit();
                int counter = elencoIscrizioni.getInt("Counter", 0);
                int numeroIscr = elencoIscrizioni.getInt("Numero_Iscrizioni", 0);
                editor1.remove("Iscritto_A_Campionato"+getPosition);
                editor1.putInt("Counter", counter-1);
                editor1.putInt("Numero_Iscrizioni", numeroIscr-1);
                editor1.commit();

                Fragment newFragment = new ConfermaDisiscrizioneFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return v;
    }

    public int findMatch(){

        String nome_completo = datiUtente.getString("name", "") + " " + datiUtente.getString("surname", "");
        Log.d(TAG, "findMatch: Nome completo " +nome_completo);


        for(int i=0; i<25; i++){

            String match = pilotiIscitti.getString("nome_pilota"+i, "");
            Log.d(TAG, "findMatch: Pilota iscritto " +match);


            if(match.equals(nome_completo)){
                return i;
            }

        }

        return -1;

    }

}