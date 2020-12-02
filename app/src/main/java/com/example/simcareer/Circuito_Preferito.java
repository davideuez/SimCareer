package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Circuito_Preferito extends AppCompatActivity {

    String MyPREFERENCES = "Preferenze_utente" ;
    String Name = "circuito_preferito";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circuito_preferito);

        //find bottoni
        Button btn1 = (Button)findViewById(R.id.circuito_barcellona);
        Button btn2 = (Button)findViewById(R.id.circuito_silverstone);
        Button btn3 = (Button)findViewById(R.id.circuito_redbull);
        Button btn4 = (Button)findViewById(R.id.circuito_monza);
        Button btn5 = (Button)findViewById(R.id.circuito_messico);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn1.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Circuito_Preferito.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Circuito_Preferito.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn2.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Circuito_Preferito.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Circuito_Preferito.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn3.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Circuito_Preferito.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Circuito_Preferito.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn4.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Circuito_Preferito.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Circuito_Preferito.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn5.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Circuito_Preferito.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Circuito_Preferito.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

    }
}