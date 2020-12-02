package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class InfoCampionatoHome extends AppCompatActivity {

    private static final String TAG = "Numero Gare";
    private static int N_CAMPIONATO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_campionato_home);

        Intent i = getIntent();
        int posizione = i.getIntExtra("position", 0);

        N_CAMPIONATO = posizione;

        SharedPreferences campionato;

        campionato = getSharedPreferences("Campionato" + posizione, MODE_PRIVATE);
        String nome = campionato.getString("nome_campionato", "");
        String inizio = campionato.getString("inizio_campionato", "");
        String fine = campionato.getString("fine_campionato", "");

        TextView info_titolo = (TextView) findViewById(R.id.info_titolo);
        TextView data_inizio = (TextView) findViewById(R.id.info_dataInizio);
        TextView data_fine = (TextView) findViewById(R.id.info_dataFine);

        info_titolo.setText(nome);
        data_inizio.setText("Inizio: " + inizio);
        data_fine.setText("Fine: " + fine);

        Fragment newFragment = new InfoMenuFragment();
        Bundle data = new Bundle();//Use bundle to pass data
        data.putInt("posizione", posizione);
        newFragment.setArguments(data);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_infoFrame, newFragment);
        transaction.commit();

    }

}
