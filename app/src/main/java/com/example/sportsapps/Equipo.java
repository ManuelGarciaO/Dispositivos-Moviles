package com.example.sportsapps;

public class Equipo {
    private String equipo, liga;

    public Equipo(String equipo, String liga) {
        this.equipo = equipo;
        this.liga = liga;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }
}
