package com.example.simcareer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class InfoMenuFragment extends Fragment {

    public InfoMenuFragment() {}

    SharedPreferences iscrizioni;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_menu, container, false);

        LinearLayout cal = (LinearLayout) view.findViewById(R.id.info_calendario);
        LinearLayout generali = (LinearLayout) view.findViewById(R.id.info_generali);
        LinearLayout iscritti = (LinearLayout) view.findViewById(R.id.info_iscritti);
        LinearLayout format = (LinearLayout) view.findViewById(R.id.info_format);
        LinearLayout settaggio = (LinearLayout) view.findViewById(R.id.info_settaggio);
        LinearLayout meteo = (LinearLayout) view.findViewById(R.id.info_meteo);
        Button forum = (Button) view.findViewById(R.id.info_goToForum);
        Button iscriviti = (Button) view.findViewById(R.id.info_iscriviti);

        int getPosition = getArguments().getInt("posizione");
        Bundle data = new Bundle();//Use bundle to pass data
        data.putInt("posizione", getPosition);

        Context context = getContext();

        iscrizioni = context.getSharedPreferences("IscrizioniCampionati", MODE_PRIVATE);

        for (int i=0; i<5; i++){

            int numeroCampionato = iscrizioni.getInt("Iscritto_A_Campionato"+i, -1);

            if(numeroCampionato == getPosition && numeroCampionato >=0){
                iscriviti.setTextSize(13);
                iscriviti.setText("DISISCRIVITI");
                iscriviti.setBackgroundColor(Color.RED);
                break;
            }

        }

        Log.d(TAG, "onCreateView: Get position" + getPosition);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Calendario Eventi");

                Fragment newFragment = new CalendarioIscrittiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        generali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Info Generali");

                Fragment newFragment = new InfoGeneraliFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        iscritti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Lista iscritti");

                Fragment newFragment = new ListaIscrittiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        format.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Format evento");

                Fragment newFragment = new FormatEventiFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }


        });

        settaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Settaggio Server");

                Fragment newFragment = new SettaggioServerFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        meteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Condizioni Meteo");

                Fragment newFragment = new Meteo();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment.setArguments(data);
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://www.simcareer.org/forum/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        iscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iscriviti.getText().toString().equals("ISCRIVITI")){
                    Fragment newFragment = new IscrizioneCampionato1Fragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    newFragment.setArguments(data);
                    transaction.replace(R.id.home_infoFrame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                else {
                    Fragment newFragment = new RimuoviIscrizioneFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    newFragment.setArguments(data);
                    transaction.replace(R.id.home_infoFrame, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}