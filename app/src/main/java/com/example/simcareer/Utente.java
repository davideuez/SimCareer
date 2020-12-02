package com.example.simcareer;

import java.util.Date;

public class Utente {

    String nickname, email, pass, nome, cognome, comune, provincia, dataNascita;

    public Utente(String nickname, String email, String pass, String nome, String cognome, String comune, String provincia, String dataNascita){
        this.nickname = nickname;
        this.email = email;
        this.pass = pass;
        this.nome = nome;
        this.cognome = cognome;
        this.comune = comune;
        this.provincia = provincia;
        this.dataNascita = dataNascita;

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", comune='" + comune + '\'' +
                ", provincia='" + provincia + '\'' +
                ", dataNascita='" + dataNascita + '\'' +
                '}';
    }
}
