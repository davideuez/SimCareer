package com.example.simcareer;

public class Classificato {

    String nome, auto, team;
    int punti;

    //costruttore per i classificati come singoli piloti
    public Classificato(String nome, String auto, String team, int punti) {
        this.nome = nome;
        this.auto = auto;
        this.team = team;
        this.punti = punti;
    }

    public Classificato(String team, String auto, int punti) {
        this.team = team;
        this.auto = auto;
        this.punti = punti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }
}
