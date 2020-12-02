package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Auto_Preferita extends AppCompatActivity {

    String MyPREFERENCES = "Preferenze_utente" ;
    String Name = "auto_preferita";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_preferita);

        //find bottoni
        Button btn1 = (Button)findViewById(R.id.car_volkswagen);
        Button btn2 = (Button)findViewById(R.id.car_honda);
        Button btn3 = (Button)findViewById(R.id.car_peugeot);
        Button btn4 = (Button)findViewById(R.id.car_hyundai);
        Button btn5 = (Button)findViewById(R.id.car_alfa);
        Button btn6 = (Button)findViewById(R.id.car_lancia);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn1.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Auto_Preferita.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Auto_Preferita.this, Registrazione_Preferenze.class);
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
                Toast.makeText(Auto_Preferita.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Auto_Preferita.this, Registrazione_Preferenze.class);
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
                Toast.makeText(Auto_Preferita.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Auto_Preferita.this, Registrazione_Preferenze.class);
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
                Toast.makeText(Auto_Preferita.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Auto_Preferita.this, Registrazione_Preferenze.class);
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
                Toast.makeText(Auto_Preferita.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Auto_Preferita.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String btn_text = btn6.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, btn_text);
                editor.commit();
                Toast.makeText(Auto_Preferita.this, btn_text + " selezionato",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Auto_Preferita.this, Registrazione_Preferenze.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right);

            }
        });

    }
}