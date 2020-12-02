package com.example.simcareer;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Copertina_Campionato{

    private String nome_campionato;
    private String logo_campionato;
    private String inizio_campionato;
    private String fine_campionato;

    public Copertina_Campionato(String nome_campionato, String logo_campionato, String inizio_campionato, String fine_campionato) {
        this.nome_campionato = nome_campionato;
        this.logo_campionato = logo_campionato;
        this.inizio_campionato = inizio_campionato;
        this.fine_campionato = fine_campionato;
    }

    public String getNome_campionato() {
        return nome_campionato;
    }

    public void setNome_campionato(String nome_campionato) {
        this.nome_campionato = nome_campionato;
    }

    public String getLogo_campionato() {
        return logo_campionato;
    }

    public void setLogo_campionato(String logo_campionato) {
        this.logo_campionato = logo_campionato;
    }

    public String getInizio_campionato() {
        return inizio_campionato;
    }

    public void setInizio_campionato(String inizio_campionato) {
        this.inizio_campionato = inizio_campionato;
    }

    public String getFine_campionato() {
        return fine_campionato;
    }

    public void setFine_campionato(String fine_campionato) {
        this.fine_campionato = fine_campionato;
    }

}
