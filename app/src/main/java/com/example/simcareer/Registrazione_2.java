package com.example.simcareer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class Registrazione_2 extends AppCompatActivity {

    TextInputEditText name, surname, comune1, birth;
    AutoCompleteTextView prov;
    Button registrazione;
    ImageView profileIcon, editProfileIcon;

    String MyPREFERENCES = "Dati_Utente" ;
    String namePr = "name";
    String surnamePr = "surname";
    String comunePr = "comune";
    String provinciaPr = "provincia";
    String birthPr = "birth";
    String profilePicture = "profile_picture";
    String immagineCodificata;

    SharedPreferences sharedpreferences;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione_2);

        name = (TextInputEditText)findViewById(R.id.name_txt);
        surname = (TextInputEditText)findViewById(R.id.surname_txt);
        comune1 = (TextInputEditText)findViewById(R.id.comune);
        prov = (AutoCompleteTextView)findViewById(R.id.filled_exposed_dropdown);
        birth = (TextInputEditText)findViewById(R.id.birthday);
        registrazione = (Button)findViewById(R.id.register2_btn);

        createList();
        createCalendar();

        profileIcon = findViewById(R.id.profile_image);
        editProfileIcon = findViewById(R.id.editprofile);

        editProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //permesso non garantito, lo richiedo
                        String[] permessi = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        // mostra popup per permessi runtime
                        requestPermissions(permessi, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();
                    }
                    
                    
                } else {  // se sistema operativo è minore della versione Marshmello
                    pickImageFromGallery();
                }

            }

        });

        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome, cognome, comune, provincia, dataNascita;

                nome = name.getText().toString();
                cognome = surname.getText().toString();
                comune = comune1.getText().toString();
                provincia = prov.getText().toString();
                dataNascita = birth.getText().toString();

                if(valido()){

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(namePr, nome);
                    editor.putString(surnamePr, cognome);
                    editor.putString(comunePr, comune);
                    editor.putString(provinciaPr, provincia);
                    editor.putString(birthPr, dataNascita);
                    editor.putString(profilePicture, immagineCodificata);
                    editor.commit();

                    updateUI();
                }

            }
        });


    }


    public void createList(){
        String[] PROVINCE = new String[] {"AG", "AL", "AN", "AO", "AP", "AQ", "AR", "AT", "AV", "BA", "BG", "BI", "BL", "BN", "BO", "BR", "BS", "BT", "BZ", "CA", "CB", "CE", "CH", "CI", "CL", "CN", "CO", "CR", "CS", "CT", "CZ", "EN", "FC", "FE", "FG", "FI", "FM", "FR", "GE", "GO", "GR", "IM", "IS", "KR", "LC", "LE", "LI", "LO", "LT", "LU", "MB", "MC", "ME", "MI", "MN", "MO", "MS", "MT", "NA", "NO", "NU", "OG", "OR", "OT", "PA", "PC", "PD", "PE", "PG", "PI", "PN", "PO", "PR", "PT", "PU", "PV", "PZ", "RA", "RC", "RE", "RG", "RI", "RM", "RN", "RO", "SA", "SI", "SO", "SP", "SR", "SS", "SV", "TA", "TE", "TN", "TO", "TP", "TR", "TS", "TV", "UD", "VA", "VB", "VC", "VE", "VI", "VR", "VS", "VT", "VV"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, PROVINCE);
        AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }

    public void createCalendar(){

        final Calendar myCalendar = Calendar.getInstance();

        TextInputEditText edittext= (TextInputEditText) findViewById(R.id.birthday);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel(){
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);
                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Registrazione_2.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public void updateUI(){
        Toast.makeText(Registrazione_2.this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Registrazione_Preferenze.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left);
    }

    public boolean valido() {

        boolean temp=true;

        String Vnome = name.getText().toString();
        String Vcognome = surname.getText().toString();
        String Vcomune = comune1.getText().toString();
        String Vprov = prov.getText().toString();
        String Vbirth = birth.getText().toString();

        if(Vnome.isEmpty() || Vcognome.isEmpty() || Vcomune.isEmpty() || Vprov.isEmpty() || Vbirth.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tutti i campi sono richiesti",Toast.LENGTH_SHORT).show();
            temp=false;
        }

        return temp;
    }

    private void pickImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if(grantResults.length <0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permesso alla fotocamera negato..", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                immagineCodificata = encodeTobase64(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            profileIcon.setImageURI(data.getData());
        }
    }

    // method for bitmap to base64
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

}