package com.example.simcareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Registrazione extends AppCompatActivity {

    Button registrazione;
    TextInputEditText nickname, email, psw, confirm_psw;

    String MyPREFERENCES = "Dati_Utente" ;
    String nickPr = "nickname";
    String mailPr = "email";
    String passPr = "password";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);

        Intent intent = new Intent(getBaseContext(), Registrazione_2.class);

        registrazione = (Button)findViewById(R.id.register_btn);
        nickname = (TextInputEditText)findViewById(R.id.nickname_txt);
        email = (TextInputEditText)findViewById(R.id.email_txt);
        psw = (TextInputEditText)findViewById(R.id.password);
        confirm_psw = (TextInputEditText)findViewById(R.id.conferma_password);

        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nick, mail, password, confirm_password;

                nick = nickname.getText().toString();
                mail = email.getText().toString();
                password = psw.getText().toString();
                confirm_password = confirm_psw.getText().toString();


                    if(valido()){

                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(nickPr, nick);
                        editor.putString(mailPr, mail);
                        editor.putString(passPr, password);
                        editor.commit();

                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
                    }

            }

            public boolean valido() {

                final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                        "[a-zA-Z0-9+._%-+]{1,256}" +
                                "@" +
                                "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                                "(" +
                                "." +
                                "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                                ")+"
                );

                boolean temp=true;
                String nick, mail, password, confirm_password;

                nick = nickname.getText().toString();
                mail = email.getText().toString();
                password = psw.getText().toString();
                confirm_password = confirm_psw.getText().toString();

                if(nick.isEmpty() || mail.isEmpty() || password.isEmpty() || confirm_password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tutti i campi sono richiesti",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(!EMAIL_ADDRESS_PATTERN.matcher(mail).matches()){
                    Toast.makeText(getApplicationContext(),"Indirizzo Email non valido",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                else if(!password.equals(confirm_password)){
                    Toast.makeText(getApplicationContext(),"Le password non corrispondono",Toast.LENGTH_SHORT).show();
                    temp=false;
                }
                return temp;
            }
        });
    }




}