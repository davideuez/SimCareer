package com.example.simcareer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ModificaInfoAccountFragment extends Fragment {

    Button salvaDati;
    TextInputEditText nickname, email, psw;
    SharedPreferences datiUtente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_modifica_info_account, container, false);

        salvaDati = (Button) v.findViewById(R.id.salva_modifiche2);
        nickname = (TextInputEditText) v.findViewById(R.id.modifica_nickname);
        email = (TextInputEditText) v.findViewById(R.id.modifica_email);
        psw = (TextInputEditText) v.findViewById(R.id.modifica_password);

        Context x = getContext();
        datiUtente = x.getSharedPreferences("Dati_Utente", Context.MODE_PRIVATE);

        String nick = datiUtente.getString("nickname", "");
        String mail = datiUtente.getString("email", "");
        String pass = datiUtente.getString("password", "");

        nickname.setText(nick);
        email.setText(mail);
        psw.setText(pass);

        salvaDati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nNickname = nickname.getText().toString();
                String nEmail = email.getText().toString();
                String nPsw = psw.getText().toString();

                SharedPreferences.Editor editor = datiUtente.edit();

                editor.putString("nickname", nNickname);
                editor.putString("email", nEmail);
                editor.putString("password", nPsw);

                editor.commit();

                Toast.makeText(x, "Informazioni aggiornate", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }
}