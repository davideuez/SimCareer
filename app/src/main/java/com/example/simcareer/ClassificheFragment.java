package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class ClassificheFragment extends Fragment {

    int posizione;
    int teamInClassifica=0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_classifiche, container, false);

        posizione = getArguments().getInt("posizione");

        TextView tab1 = (TextView) v.findViewById(R.id.classifica_piloti);
        TextView tab2 = (TextView) v.findViewById(R.id.classifica_team);

        Context x = getContext();

        inizializzaClassifiche(x.getApplicationContext());

        Fragment newFragment = new ClassificaPilotiFragment();
        Bundle data = new Bundle();//Use bundle to pass data
        data.putInt("posizione", posizione);
        newFragment.setArguments(data);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lista_classificaFrame, newFragment);
        transaction.commit();

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab1.setTextColor(Color.rgb(230, 57, 80));
                tab2.setTextColor(Color.rgb(0, 0, 0));

                Fragment newFragment = new ClassificaPilotiFragment();
                Bundle data = new Bundle();//Use bundle to pass data
                data.putInt("posizione", posizione);
                newFragment.setArguments(data);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.lista_classificaFrame, newFragment);
                transaction.commit();
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab2.setTextColor(Color.rgb(230, 57, 80));
                tab1.setTextColor(Color.rgb(0, 0, 0));

                Fragment newFragment = new ClassificaTeamFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.lista_classificaFrame, newFragment);
                transaction.commit();
            }
        });


        ImageView back_btn = (ImageView) v.findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new CalendarioIscrittiFragment();
                Bundle data = new Bundle();//Use bundle to pass data
                data.putInt("posizione", posizione);
                newFragment.setArguments(data);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.home_infoFrame, newFragment);
                transaction.commit();
            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void inizializzaClassifiche(Context context) {

        String jsonStr = Utils.getJsonFromAssets(context, "classifiche.json");

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray arrayClassifiche = jsonObject.getJSONArray("campionati");  //ottengo il nodo campionato

            //identificatore del campionato
            int idCampionato = 0;

            for (int i = 0; i < arrayClassifiche.length(); i++) {
                //ottengo la lista di classifiche
                JSONObject objCampionato = arrayClassifiche.getJSONObject(i);
                idCampionato = Integer.parseInt(objCampionato.getString("id"));
                String nome = objCampionato.getString("nome");
                String logo = objCampionato.getString("logo");

                //ottengo la classifica dei piloti
                JSONArray arrayClassifica = objCampionato.getJSONArray("classifica-piloti");
                int pilotiInClassifica=0;
                for (int j = 0; j < arrayClassifica.length(); j++) {
                    JSONObject objClassifica = arrayClassifica.getJSONObject(j);
                    String nomeClass = objClassifica.getString("nome");
                    String team = objClassifica.getString("team");
                    String auto = objClassifica.getString("auto");
                    int punti = objClassifica.getInt("punti");
                    pilotiInClassifica++;
                    aggiungiClassificato(idCampionato, j, pilotiInClassifica, nomeClass, team, auto, punti);

                }

                JSONArray arrayClassificaTeam = objCampionato.getJSONArray("classifica-team");

                for (int x = 0; x < arrayClassificaTeam.length(); x++) {
                    JSONObject objClassificaTeam = arrayClassificaTeam.getJSONObject(x);
                    String team = objClassificaTeam.getString("team");
                    String auto = objClassificaTeam.getString("auto");
                    int punti = objClassificaTeam.getInt("punti");
                    teamInClassifica++;
                    aggiungiTeamClassificato(idCampionato, x, team, auto, punti);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void aggiungiClassificato(int idCampionato, int nSeq, int pilotiInClassifica, String nome, String team, String auto, int punti) {

        String MyPREFERENCES = "Classifiche_Campionato" + idCampionato;
        Context x = getContext();

        SharedPreferences sharedpreferences;
        sharedpreferences = x.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nome" + nSeq, nome);
        editor.putString("team" + nSeq, team);
        editor.putString("auto" + nSeq, auto);
        editor.putInt("punti" + nSeq, punti);
        editor.putInt("pilotiInClassifica", pilotiInClassifica);

        editor.commit();

    }

    void aggiungiTeamClassificato(int idCampionato, int nSeq, String team, String auto, int punti) {

        String MyPREFERENCES = "Classifiche_Team_Campionato" + idCampionato;
        Context x = getContext();

        SharedPreferences sharedpreferences;
        sharedpreferences = x.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("team" + nSeq, team);
        editor.putString("auto" + nSeq, auto);
        editor.putInt("punti" + nSeq, punti);
        editor.putInt("teamInClassifica", teamInClassifica);

        editor.commit();

    }

}