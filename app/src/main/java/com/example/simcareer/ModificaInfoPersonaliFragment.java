package com.example.simcareer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ModificaInfoPersonaliFragment extends Fragment {

    SharedPreferences datiUtente;
    TextInputEditText nome, cognome, comune, dataDiNascita;
    AutoCompleteTextView prov;
    View v;
    Button salva;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_modifica_info_personali, container, false);

        nome = (TextInputEditText) v.findViewById(R.id.modifica_nome);
        cognome = (TextInputEditText) v.findViewById(R.id.modifica_cognome);
        comune = (TextInputEditText) v.findViewById(R.id.modifica_comune);
        prov = (AutoCompleteTextView) v.findViewById(R.id.modifica_provincia);
        dataDiNascita = (TextInputEditText) v.findViewById(R.id.modifica_datadinascita);
        salva = (Button) v.findViewById(R.id.salva_modifiche);

        Context x = getContext();
        datiUtente = x.getSharedPreferences("Dati_Utente", Context.MODE_PRIVATE);

        String sNome = datiUtente.getString("name", "");
        String sCognome = datiUtente.getString("surname", "");
        String sComune = datiUtente.getString("comune", "");
        String sProvincia = datiUtente.getString("provincia", "");
        String sDataDiNascita = datiUtente.getString("birth", "");


        nome.setText(sNome);
        cognome.setText(sCognome);
        comune.setText(sComune);
        prov.setText(sProvincia);
        dataDiNascita.setText(sDataDiNascita);

        createList();
        createCalendar();

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nNome = nome.getText().toString();
                String nCognome = cognome.getText().toString();
                String nComune = comune.getText().toString();
                String nProvincia = prov.getText().toString();
                String nDataDiNascita = dataDiNascita.getText().toString();

                SharedPreferences.Editor editor = datiUtente.edit();

                editor.putString("name", nNome);
                editor.putString("surname", nCognome);
                editor.putString("comune", nComune);
                editor.putString("provincia", nProvincia);
                editor.putString("birth", nDataDiNascita);

                editor.commit();

                Toast.makeText(x, "Informazioni aggiornate", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }

    public void createList(){
        String[] PROVINCE = new String[] {"AG", "AL", "AN", "AO", "AP", "AQ", "AR", "AT", "AV", "BA", "BG", "BI", "BL", "BN", "BO", "BR", "BS", "BT", "BZ", "CA", "CB", "CE", "CH", "CI", "CL", "CN", "CO", "CR", "CS", "CT", "CZ", "EN", "FC", "FE", "FG", "FI", "FM", "FR", "GE", "GO", "GR", "IM", "IS", "KR", "LC", "LE", "LI", "LO", "LT", "LU", "MB", "MC", "ME", "MI", "MN", "MO", "MS", "MT", "NA", "NO", "NU", "OG", "OR", "OT", "PA", "PC", "PD", "PE", "PG", "PI", "PN", "PO", "PR", "PT", "PU", "PV", "PZ", "RA", "RC", "RE", "RG", "RI", "RM", "RN", "RO", "SA", "SI", "SO", "SP", "SR", "SS", "SV", "TA", "TE", "TN", "TO", "TP", "TR", "TS", "TV", "UD", "VA", "VB", "VC", "VE", "VI", "VR", "VS", "VT", "VV"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, PROVINCE);
        AutoCompleteTextView editTextFilledExposedDropdown = v.findViewById(R.id.modifica_provincia);
        editTextFilledExposedDropdown.setAdapter(adapter);
    }

    public void createCalendar(){

        final Calendar myCalendar = Calendar.getInstance();

        TextInputEditText edittext= (TextInputEditText) v.findViewById(R.id.modifica_datadinascita);
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
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


}