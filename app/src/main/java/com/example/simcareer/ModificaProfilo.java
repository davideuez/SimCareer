package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ModificaProfilo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifica_profilo);

        TextView info1 = (TextView) findViewById(R.id.info_personali);
        TextView info2 = (TextView) findViewById(R.id.info_account);

        Fragment newFragment = new ModificaInfoPersonaliFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.modifica_profiloFrame, newFragment);
        transaction.commit();

        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ModificaInfoPersonaliFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.modifica_profiloFrame, newFragment);
                transaction.commit();
            }
        });

        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new ModificaInfoAccountFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.modifica_profiloFrame, newFragment);
                transaction.commit();
            }
        });



    }
}