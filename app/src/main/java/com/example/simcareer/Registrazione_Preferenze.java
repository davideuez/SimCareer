package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Registrazione_Preferenze extends AppCompatActivity {

    String MyPREFERENCES = "Preferenze_utente" ;
    String Name = "circuito_odiato";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione_preferenze);

        sharedpreferences = getSharedPreferences("Dati_Utente", MODE_PRIVATE);
        String display_nome = sharedpreferences.getString("name", "");
        //inserimento nome
        TextView nome = (TextView)findViewById(R.id.nome_iscritto);
        nome.setText(display_nome);

        refreshLabels();

        //recupero LinearLayout e bottone
        LinearLayout scheda1 = (LinearLayout)findViewById(R.id.first_scheda);
        LinearLayout scheda2 = (LinearLayout)findViewById(R.id.seconda_scheda);
        LinearLayout scheda3 = (LinearLayout)findViewById(R.id.third_scheda);
        LinearLayout scheda4 = (LinearLayout)findViewById(R.id.fourth_scheda);

        scheda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Gara_Preferita.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

        scheda2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Circuito_Preferito.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

        scheda3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Circuito_Odiato.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

        scheda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Auto_Preferita.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

    }

    public void refreshLabels(){

        String pref_1, pref_2, pref_3, pref_4;
        int counter = 0;

        TextView pref1 = (TextView)findViewById(R.id.gara_preferita);
        TextView pref2 = (TextView)findViewById(R.id.circuito_preferito);
        TextView pref3 = (TextView)findViewById(R.id.circuito_odiato);
        TextView pref4 = (TextView)findViewById(R.id.auto_preferita);
        Button cominciamo = (Button)findViewById(R.id.comiciamo_btn);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        if(sharedpreferences.contains("gara_preferita")){
            pref_1 = sharedpreferences.getString("gara_preferita", "");
            pref1.setText(pref_1);
            counter++;
        }

        if(sharedpreferences.contains("circuito_preferito")){
            pref_2 = sharedpreferences.getString("circuito_preferito", "");
            pref2.setText(pref_2);
            counter++;
        }

        if(sharedpreferences.contains("circuito_odiato")){
            pref_3 = sharedpreferences.getString("circuito_odiato", "");
            pref3.setText(pref_3);
            counter++;
        }

        if(sharedpreferences.contains("auto_preferita")){
            pref_4 = sharedpreferences.getString("auto_preferita", "");
            pref4.setText(pref_4);
            counter++;
        }

        if(counter==4){
            cominciamo.setEnabled(true);

            cominciamo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
                }
            });

        } else {
            cominciamo.setEnabled(false);
        }


    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(Registrazione_Preferenze.this, "Non è possibile tornare indietro", Toast.LENGTH_SHORT).show();
    }

}