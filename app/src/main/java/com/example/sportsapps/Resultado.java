package com.example.sportsapps;

public class Resultado {
    private String equipoL;
    private String equipoV;
    private int golesL;
    private int golesV;

    public Resultado(String equipoL, String equipoV, int golesL, int golesV) {
        this.equipoL = equipoL;
        this.equipoV = equipoV;
        this.golesL = golesL;
        this.golesV = golesV;
    }

    public String getEquipoL() {
        return equipoL;
    }

    public String getEquipoV() {
        return equipoV;
    }

    public int getGolesL() {
        return golesL;
    }

    public int getGolesV() {
        return golesV;
    }
}
