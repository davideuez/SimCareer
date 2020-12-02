package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    String MyPREFERENCES = "Dati_Utente" ;
    String nickPr = "nickname";
    String passPr = "password";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView nick = (TextView)findViewById(R.id.nickname_login);
        TextView pass = (TextView)findViewById(R.id.password_login);

        Button registrati = (Button)findViewById(R.id.registrati_btn);
        Button login = (Button)findViewById(R.id.login_btn);

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registrazione.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

                String nickname = nick.getText().toString();
                String password = pass.getText().toString();
                String savedNick = sp.getString("nickname", "");
                String savedPass = sp.getString("password", "");

                if(sp.contains("nickname") && sp.contains("password") && nickname.equals(savedNick) && password.equals(savedPass)){
                    Toast.makeText(Login.this, "Bentornato " + nickname, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Home_Campionati.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
                } else {
                    Toast.makeText(Login.this, "Credenziali errate", Toast.LENGTH_SHORT).show();

                }


            }
        });



    }
}