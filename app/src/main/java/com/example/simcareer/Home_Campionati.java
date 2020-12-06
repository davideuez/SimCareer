package com.example.simcareer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home_Campionati extends AppCompatActivity implements CopertinaAdapter.OnCampionatoListener {

    private RecyclerView copertine_campionati;
    private RecyclerView.Adapter adapter;
    int nCampionati = 0;
    boolean firstTime=true;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_campionati);

        inizializzaCampionati(Home_Campionati.this);

        firstTime = false;

        ArrayList<Copertina_Campionato> copertine_campionati = creaAnteprimeCampionati();

        this.copertine_campionati = (RecyclerView) findViewById(R.id.lista_campionati);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.copertine_campionati.setLayoutManager(mLayoutManager);

        adapter = new CopertinaAdapter(copertine_campionati, this);
        this.copertine_campionati.setAdapter(adapter);

        //Modifica profilo

        ImageView modificaProfilo = (ImageView) findViewById(R.id.profile_icon);

        modificaProfilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ModificaProfilo.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

        // Bottom Navigation

        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        menu.setSelectedItemId(R.id.campionati_menu);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.campionati_menu:
                        return true;
                    case R.id.iscrizioni_menu:
                        Intent a = new Intent(getApplicationContext(), IscrizioniEffettuate.class);
                        startActivity(a);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.galleria_menu:
                        Intent b = new Intent(getApplicationContext(), GalleriaFotografica.class);
                        startActivity(b);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.novita_menu:
                        Intent c = new Intent(getApplicationContext(), NovitaCorse.class);
                        startActivity(c);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void inizializzaCampionati(Context context) {

        String jsonStr = Utils.getJsonFromAssets(context, "campionati.json");

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray arrayCampionati = jsonObject.getJSONArray("campionati");  //ottengo il nodo campionato

            //identificatore del campionato
            int idCampionato = 0;

            for (int i = 0; i < arrayCampionati.length(); i++) {
                //ottengo la lista di campionati
                JSONObject objCampionato = arrayCampionati.getJSONObject(i);
                idCampionato = Integer.parseInt(objCampionato.getString("id"));
                String nome = objCampionato.getString("nome");
                String logo = objCampionato.getString("logo");
                String data_inizio = objCampionato.getString("inizio_campionato");
                String data_fine = objCampionato.getString("fine_campionato");
                String auto_concesse = objCampionato.getString("lista-auto");

                //ottengo la lista di calendari
                JSONArray arrayCalendario = objCampionato.getJSONArray("calendario");
                int totaleGare=0;
                for (int j = 0; j < arrayCalendario.length(); j++) {
                    JSONObject objCalendario = arrayCalendario.getJSONObject(j);
                    int seq = Integer.parseInt(objCalendario.getString("seq"));
                    String data = objCalendario.getString("data");
                    String circuito = objCalendario.getString("circuito");
                    totaleGare++;
                    aggiungiCalendario(idCampionato, j, seq, data, circuito);

                }

                //elementi di impostazioni da aggiungere al campionato
                String bandiere = "";
                String cons_carb = "";
                String cons_gomme = "";
                String aiuti = "";

                JSONArray arrayImpostazioni = objCampionato.getJSONArray("impostazioni-gioco");
                for (int k = 0; k < arrayImpostazioni.length(); k++) {

                    JSONObject objImpostazioni = arrayImpostazioni.getJSONObject(k);
                    if (k == 0)
                        bandiere = objImpostazioni.getString("valore");
                    if (k == 1)
                        cons_carb = objImpostazioni.getString("valore");
                    if (k == 2)
                        cons_gomme = objImpostazioni.getString("valore");
                    if (k == 3)
                        aiuti = objImpostazioni.getString("valore");
                }

                Log.d("MYTAG", "Yolo");

                //ottengo la lista di piloti
                JSONArray arrayPiloti = objCampionato.getJSONArray("piloti-iscritti");
                int counter=0;
                for (int n = 0; n < arrayPiloti.length(); n++) {
                    JSONObject objPilota = arrayPiloti.getJSONObject(n);
                    String nomePilota = objPilota.getString("nome");
                    String team = objPilota.getString("team");
                    String auto = objPilota.getString("auto");
                    aggiungiPilotaIscritto(idCampionato, n, counter, nomePilota, team, auto);
                    counter++;

                }

                aggiungiCampionato(idCampionato, nome, logo, data_inizio, data_fine, auto_concesse, bandiere, cons_carb, cons_gomme, aiuti, totaleGare);

                idCampionato++;
                nCampionati++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void aggiungiCalendario(int idCampionato, int nSeq, int sequenza, String data, String circuito) {

        String MyPREFERENCES = "Calendario" + idCampionato;

        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt("sequenza" + nSeq, sequenza);
        editor.putString("data" + nSeq, data);
        editor.putString("circuito" + nSeq, circuito);

        editor.commit();

    }

    void aggiungiPilotaIscritto(int idCampionato, int nPilota, int counter, String nomePilota, String team, String auto) {

        String MyPREFERENCES = "Piloti_Iscritti_Campionato" + idCampionato;

        SharedPreferences x = getSharedPreferences("Piloti_Iscritti_Campionato"+idCampionato, MODE_PRIVATE);
        int howMany = x.getAll().size();
        int numeroCorrettoPiloti = (howMany-2)/3;


        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        if(numeroCorrettoPiloti != 0){
            editor.putInt("totale_piloti", numeroCorrettoPiloti);
        }else{
            editor.putInt("totale_piloti", counter+1);
        }

        editor.putInt("id_campionato", idCampionato);
        editor.putString("nome_pilota" + nPilota, nomePilota);
        editor.putString("team_pilota" + nPilota, team);
        editor.putString("auto_pilota" + nPilota, auto);

        editor.commit();


    }

    void aggiungiCampionato(int idCampionato, String nome, String logo, String start, String finish, String auto_concesse, String bandiere, String cons_carb, String cons_gomme, String aiuti, int totaleGare) {

        String MyPREFERENCES = "Campionato" + idCampionato;

        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt("id_campionato", idCampionato);
        editor.putString("nome_campionato", nome);
        editor.putString("logo_campionato", logo);
        editor.putString("inizio_campionato", start);
        editor.putString("fine_campionato", finish);
        editor.putString("auto_concesse", auto_concesse);
        editor.putString("bandiere_campionato", bandiere);
        editor.putString("cons-carb_campionato", cons_carb);
        editor.putString("cons-gomme_campionato", cons_gomme);
        editor.putString("aiuti_campionato", aiuti);
        editor.putInt("totale_gare", totaleGare);

        editor.commit();

    }

    private ArrayList<Copertina_Campionato> creaAnteprimeCampionati() {
        ArrayList<Copertina_Campionato> list = new ArrayList<>();

        SharedPreferences campionato, calendario;

        //calendario = getSharedPreferences("Calendario", MODE_PRIVATE);

        for (int i = 0; i < nCampionati; i++) {
            campionato = getSharedPreferences("Campionato" + i, MODE_PRIVATE);
            String nome = campionato.getString("nome_campionato", "");
            String logo = campionato.getString("logo_campionato", "");
            String inizio = campionato.getString("inizio_campionato", "");
            String fine = campionato.getString("fine_campionato", "");

            list.add(new Copertina_Campionato(nome, logo, inizio, fine));
            Log.d("Campionato", "N campionato: " + nCampionati);
        }

        return list;
    }


    @Override
    public void onCampionatoClick(int position) {

        Intent i = new Intent(this, InfoCampionatoHome.class);
        i.putExtra("position", position);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);

    }
}