package com.example.simcareer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class IscrizioneCampionato1Fragment extends Fragment {

    SharedPreferences iscrizioniCampionatiTemp, pilotiIscritti, datiUtente, iscrizioniCampionati;
    TextView nomeAuto, nomeTeam;
    Button iscriviti;
    int getPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_iscrizione_campionato1, container, false);

        getPosition = getArguments().getInt("posizione");
        Bundle data = new Bundle();//Use bundle to pass data
        data.putInt("posizione", getPosition);

        Context context = getContext();
        iscrizioniCampionatiTemp = context.getSharedPreferences("IscrizioniCampionatiTemp", MODE_PRIVATE);
        pilotiIscritti = context.getSharedPreferences("Piloti_Iscritti_Campionato"+getPosition, MODE_PRIVATE);
        datiUtente = context.getSharedPreferences("Dati_Utente", MODE_PRIVATE);
        iscrizioniCampionati = context.getSharedPreferences("IscrizioniCampionati", MODE_PRIVATE);

        LinearLayout scegliAuto = (LinearLayout) v.findViewById(R.id.seleziona_auto);
        LinearLayout scegliTeam = (LinearLayout) v.findViewById(R.id.seleziona_team);
        nomeAuto = (TextView) v.findViewById(R.id.auto_nome);
        nomeTeam = (TextView) v.findViewById(R.id.team_nome);
        iscriviti = (Button) v.findViewById(R.id.btn_iscriviti);

        scegliAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Scegli Auto per campionato "+getPosition);

                Fragment newFragment = new ScegliAutoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        scegliTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Scegli Team per campionato "+getPosition);

                Fragment newFragment = new ScegliTeamFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        refreshLabels();

        return v;
    }

    public void refreshLabels() {

        String pref_1, pref_2;
        int counter = 0;

        if (iscrizioniCampionatiTemp.contains("auto_selezionata")) {
            pref_1 = iscrizioniCampionatiTemp.getString("auto_selezionata", "");
            nomeAuto.setText(pref_1);
            counter++;
        }

        if (iscrizioniCampionatiTemp.contains("team_selezionato")) {
            pref_2 = iscrizioniCampionatiTemp.getString("team_selezionato", "");
            nomeTeam.setText(pref_2);
            counter++;
        }

        if (counter == 2) {
            iscriviti.getBackground().setAlpha(255);
            iscriviti.setEnabled(true);

            iscriviti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: Tutti i parametri sono settati");

                    int numeroPiloti = pilotiIscritti.getInt("totale_piloti", 0);
                    int numeroIscrizioni = iscrizioniCampionati.getInt("Numero_Iscrizioni", 0);
                    int contatore = iscrizioniCampionati.getInt("Counter", 0);
                    String nomePilota = datiUtente.getString("name", "");
                    String cognomePilota = datiUtente.getString("surname", "");
                    String nomeCompleto = nomePilota + " " + cognomePilota;
                    String nomeAutoSelezionata = nomeAuto.getText().toString();
                    String nomeTeamSelezionato = nomeTeam.getText().toString();

                    int nuovonumeropiloti = numeroPiloti + 1;

                    SharedPreferences.Editor editor = pilotiIscritti.edit();
                    editor.putString("nome_pilota"+numeroPiloti, nomeCompleto);
                    editor.putString("auto_pilota"+numeroPiloti, nomeAutoSelezionata);
                    editor.putString("team_pilota"+numeroPiloti, nomeTeamSelezionato);
                    editor.putInt("totale_piloti", nuovonumeropiloti);
                    editor.commit();

                    SharedPreferences.Editor editor1 = iscrizioniCampionatiTemp.edit();
                    editor1.remove("auto_selezionata");
                    editor1.remove("team_selezionato");
                    editor1.commit();

                    SharedPreferences.Editor editor2 = iscrizioniCampionati.edit();
                    editor2.putInt("Iscritto_A_Campionato"+getPosition, getPosition);
                    editor2.putInt("Counter", contatore+1);
                    editor2.putInt("Numero_Iscrizioni", numeroIscrizioni+1);
                    editor2.commit();

                    Fragment newFragment = new ConfermaIscrizioneFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    Bundle dati = new Bundle();
                    dati.putString("auto", nomeAutoSelezionata);
                    dati.putString("team", nomeTeamSelezionato);
                    newFragment.setArguments(dati);
                    transaction.replace(R.id.home_infoFrame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

        } else {
            iscriviti.getBackground().setAlpha(50);
            iscriviti.setEnabled(false);
        }
    }
}