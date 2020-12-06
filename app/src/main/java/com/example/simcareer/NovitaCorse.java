package com.example.simcareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class NovitaCorse extends AppCompatActivity {

    private static final String TAG = "TAG";
    AutoCompleteTextView campionato;
    String[] campionati;
    int campionato_selezionato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novita_corse);
        createNotificationChannel();
        createList();

        Button modifica = findViewById(R.id.modifica_btn);
        campionato = findViewById(R.id.filled_exposed_dropdown);

        campionato.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                campionato_selezionato = position;
                Log.d(TAG, "onItemClick: Cliccato item n "+position);
            }
        });

        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Bottom Navigation

        BottomNavigationView menu = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        menu.setSelectedItemId(R.id.novita_menu);

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.novita_menu:
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
                    case R.id.campionati_menu:
                        Intent c = new Intent(getApplicationContext(), Home_Campionati.class);
                        startActivity(c);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    public void createList(){

        campionati = new String[3];

        Log.d(TAG, "Lungezza array campionati: "+ campionati.length);

        for(int i=0; i<campionati.length; i++) {

            SharedPreferences sp = getSharedPreferences("Campionato"+i, MODE_PRIVATE);
            campionati[i] = sp.getString("nome_campionato","");

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, campionati);
        AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }

    public void modificaInformazioniCampionato() {

        Log.d(TAG, "modificaInformazioniCampionato: ");

        SharedPreferences sp = getSharedPreferences("Campionato"+campionato_selezionato, MODE_PRIVATE);
        SharedPreferences cal = getSharedPreferences("Calendario"+campionato_selezionato, MODE_PRIVATE);

        final Random myRandom = new Random();

        int i = myRandom.nextInt(2);
        String nome_campionato = sp.getString("nome_campionato","");
        String oldDate = sp.getString("inizio_campionato","");

        switch(i){

            case 0:

                Log.d(TAG, "modificaInformazioniCampionato: caso 0");

                String nuovaDataInizio = "10/01/2021";
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("inizio_campionato", nuovaDataInizio);
                editor.commit();

                NotificationCompat.Builder n = new NotificationCompat.Builder(this);
                n.setContentTitle("Aggiornata data inizio campionato "+ nome_campionato);
                n.setContentText("Inizio campionato spostato dal "+ oldDate+ " al " + nuovaDataInizio);
                n.setSmallIcon(R.mipmap.ic_launcher);

                Intent x = new Intent(getApplicationContext(), Home_Campionati.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, x, PendingIntent.FLAG_UPDATE_CURRENT);

                n.setContentIntent(pi);
                n.setAutoCancel(true);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0, n.build());

                break;

            case 1:

                Log.d(TAG, "modificaInformazioniCampionato: caso 1");

                String nuoviAiutiCampionato = "Frizione manuale";
                String nuovoConsCarburante = "Basso";
                String nuovoConsGomme = "Alto";
                SharedPreferences.Editor editor1 = sp.edit();
                editor1.putString("aiuti_campionato", nuoviAiutiCampionato);
                editor1.putString("cons-carb_campionato", nuovoConsCarburante);
                editor1.putString("cons-gomme_campionato", nuovoConsGomme);
                editor1.commit();

                NotificationCompat.Builder n1 = new NotificationCompat.Builder(this);
                n1.setContentTitle("Aggiornati settaggi campionato "+ nome_campionato);
                n1.setContentText("Aggiornati aiuti campionato, consumo carburante e consumo gomme");
                n1.setSmallIcon(R.drawable.logonotifica);

                Intent y = new Intent(getApplicationContext(), Home_Campionati.class);
                PendingIntent pi1 = PendingIntent.getActivity(getApplicationContext(), 0, y, PendingIntent.FLAG_UPDATE_CURRENT);

                n1.setContentIntent(pi1);
                n1.setAutoCancel(true);

                NotificationManager notificationManager1 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager1.notify(1, n1.build());

                break;

                /*
            case 2:

                String nuovoCircuito = "Roma";
                String nuovaData = "20/09/2020";
                SharedPreferences.Editor editor2 = cal.edit();
                editor2.putString("circuito"+i, nuovoCircuito);
                editor2.putString("data"+i, nuovaData);
                editor2.commit();



                break;*/

        }

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "generali";
            String description = "generali";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("general", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}