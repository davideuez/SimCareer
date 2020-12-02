package com.example.simcareer;

public class Calendario {

    String data, circuito;

    public Calendario(String data, String circuito) {
        this.data = data;
        this.circuito = circuito;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

}
