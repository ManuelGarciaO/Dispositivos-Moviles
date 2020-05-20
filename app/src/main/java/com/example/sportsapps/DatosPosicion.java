package com.example.sportsapps;

public class DatosPosicion {
    private String equipo;
    private int jj, jg, je, jp, gf, gc, dif, pts;

    public DatosPosicion(String equipo, int jj, int jg, int je, int jp, int gf, int gc, int dif, int pts) {
        this.equipo = equipo;
        this.jj = jj;
        this.jg = jg;
        this.je = je;
        this.jp = jp;
        this.gf = gf;
        this.gc = gc;
        this.dif = dif;
        this.pts = pts;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getJj() {
        return jj;
    }

    public void setJj(int jj) {
        this.jj = jj;
    }

    public int getJg() {
        return jg;
    }

    public void setJg(int jg) {
        this.jg = jg;
    }

    public int getJe() {
        return je;
    }

    public void setJe(int je) {
        this.je = je;
    }

    public int getJp() {
        return jp;
    }

    public void setJp(int jp) {
        this.jp = jp;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc = gc;
    }

    public int getDif() {
        return dif;
    }

    public void setDif(int dif) {
        this.dif = dif;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
