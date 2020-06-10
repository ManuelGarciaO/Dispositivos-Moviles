package com.example.sportsapps;

public class Partido {
    private String equipoL;
    private String equipoV;
    private String hora;

    public Partido(String equipoL, String equipoV, String hora) {
        this.equipoL = equipoL;
        this.equipoV = equipoV;
        this.hora = hora;
    }

    public String getEquipoL() {
        return equipoL;
    }

    public void setEquipoL(String equipoL) {
        this.equipoL = equipoL;
    }

    public String getEquipoV() {
        return equipoV;
    }

    public void setEquipov(String equipov) {
        this.equipoV = equipov;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
