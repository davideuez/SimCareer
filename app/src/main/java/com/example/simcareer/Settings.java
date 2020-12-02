package com.example.simcareer;

public class Settings {

    String titolo;
    String impostazione;

    public Settings(String titolo, String impostazione) {
        this.titolo = titolo;
        this.impostazione = impostazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getImpostazione() {
        return impostazione;
    }

    public void setImpostazione(String impostazione) {
        this.impostazione = impostazione;
    }

}


