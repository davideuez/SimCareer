package com.example.simcareer;

public class Lista_gare {

    private String data_evento;
    private String circuito;

    public Lista_gare(String data_evento, String descrizione) {
        this.data_evento = data_evento;
        this.circuito = descrizione;
    }

    public String getData_evento() {
        return data_evento;
    }

    public void setData_evento(String data_evento) {
        this.data_evento = data_evento;
    }

    public String getDescrizione() {
        return circuito;
    }

    public void setDescrizione(String descrizione) {
        this.circuito = descrizione;
    }

}
