package com.example.simcareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class IscrizioniEffettuate extends AppCompatActivity implements CopertinaAdapter.OnCampionatoListener{

    private RecyclerView copertine_iscrizioni;
    private RecyclerView.Adapter adapter;
    ArrayList<Integer> trovati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iscrizioni_effettuate);

        ArrayList<Copertina_Campionato> copertine_campionati = creaAnteprimeCampionati();

        this.copertine_iscrizioni = (RecyclerView) findViewById(R.id.lista_iscrizioni);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.copertine_iscrizioni.setLayoutManager(mLayoutManager);

        adapter = new CopertinaAdapter(copertine_campionati, this);
        this.copertine_iscrizioni.setAdapter(adapter);

        ImageView modificaProfilo = (ImageView) findViewById(R.id.profile_icon);

        modificaProfilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ModificaProfilo.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        menu.setSelectedItemId(R.id.iscrizioni_menu);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.campionati_menu:
                        Intent a = new Intent(getApplicationContext(), Home_Campionati.class);
                        startActivity(a);
                        overridePendingTransition(0,0);
                    case R.id.iscrizioni_menu:
                        return true;
                    case R.id.galleria_menu:
                        Intent b = new Intent(getApplicationContext(), GalleriaFotografica.class);
                        startActivity(b);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.novita_menu:
                        Intent c = new Intent(getApplicationContext(), GalleriaFotografica.class);
                        startActivity(c);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }

    private ArrayList<Copertina_Campionato> creaAnteprimeCampionati() {
        ArrayList<Copertina_Campionato> list = new ArrayList<>();
        trovati = new ArrayList<Integer>();
        SharedPreferences iscrizioni, campionato;

        iscrizioni = getSharedPreferences("IscrizioniCampionati", MODE_PRIVATE);

        for (int i = 0; i < 5; i++) {
            int numeroCampionato = iscrizioni.getInt("Iscritto_A_Campionato"+i, -1);

            if(numeroCampionato >= 0){
                campionato = getSharedPreferences("Campionato" + numeroCampionato, MODE_PRIVATE);
                String nome = campionato.getString("nome_campionato", "");
                String logo = campionato.getString("logo_campionato", "");
                String inizio = campionato.getString("inizio_campionato", "");
                String fine = campionato.getString("fine_campionato", "");
                trovati.add(i);

                list.add(new Copertina_Campionato(nome, logo, inizio, fine));
            }

        }

        return list;
    }

    @Override
    public void onCampionatoClick(int position) {

        SharedPreferences x = getSharedPreferences("IscrizioniCampionati", MODE_PRIVATE);
        int numeroCampionato = trovati.get(position);

        Intent i = new Intent(this, InfoCampionatoHome.class);
        i.putExtra("position", numeroCampionato);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);

    }

}