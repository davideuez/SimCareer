package com.example.simcareer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class NovitaCorse extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TAG";
    AutoCompleteTextView campionato;
    String[] campionati;
    int campionato_selezionato;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novita_corse);
        createList();

        Button modificaCorse = (Button) findViewById(R.id.modificaCorse_btn);
        campionato = findViewById(R.id.filled_exposed_dropdown);

        campionato.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                campionato_selezionato = position;
                Log.d(TAG, "onItemClick: Cliccato item n "+position);
            }
        });

        modificaCorse.setOnClickListener(this);

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



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modificaCorse_btn:
                notificationDialog();
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notificationDialog() {

        SharedPreferences sp = getSharedPreferences("Campionato"+campionato_selezionato, MODE_PRIVATE);
        SharedPreferences cal = getSharedPreferences("Calendario"+campionato_selezionato, MODE_PRIVATE);

        final Random myRandom = new Random();

        int i = myRandom.nextInt(2);
        String nome_campionato = sp.getString("nome_campionato","");
        String oldDate = sp.getString("inizio_campionato","");

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "simcareer";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
            // Configure the notification channel.
            notificationChannel.setDescription("Sample Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        switch(i){

            case 0:

                Log.d(TAG, "modificaInformazioniCampionato: caso 0");
                Log.d(TAG, "posizione: "+campionato_selezionato);

                String nuovaDataInizio = "10/01/2021";
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("inizio_campionato", nuovaDataInizio);
                editor.commit();

                final Intent newIntent = new Intent(getApplicationContext(), InfoCampionatoHome.class);
                newIntent.putExtra("position", campionato_selezionato);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
                notificationBuilder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.logonotifica)
                        .setTicker("SimCareer")
                        //.setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle("sample notification")
                        .setContentText("This is sample notification")
                        .setContentInfo("Information")
                        .setContentIntent(pendingIntent);
                notificationManager.notify(0, notificationBuilder.build());

                break;

            case 1:

                Log.d(TAG, "modificaInformazioniCampionato: caso 1");
                Log.d(TAG, "posizione: "+campionato_selezionato);

                String nuoviAiutiCampionato = "Frizione manuale";
                String nuovoConsCarburante = "Basso";
                String nuovoConsGomme = "Alto";
                SharedPreferences.Editor editor1 = sp.edit();
                editor1.putString("aiuti_campionato", nuoviAiutiCampionato);
                editor1.putString("cons-carb_campionato", nuovoConsCarburante);
                editor1.putString("cons-gomme_campionato", nuovoConsGomme);
                editor1.commit();

                final Intent newIntent2 = new Intent(getApplicationContext(), InfoCampionatoHome.class);
                newIntent2.putExtra("position", campionato_selezionato);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 0, newIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder notificationBuilder1 = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
                notificationBuilder1.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.logonotifica)
                        .setTicker("SimCareer")
                        //.setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle("Modifica aiuti campionato "+ nome_campionato)
                        .setContentText("Consumo gomme: "+nuovoConsGomme+", consumo carburante: "+nuovoConsGomme+", aiuti campionato: "+nuoviAiutiCampionato)
                        .setContentInfo("Information")
                        .setContentIntent(pendingIntent2);
                notificationManager.notify(1, notificationBuilder1.build());

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
}

