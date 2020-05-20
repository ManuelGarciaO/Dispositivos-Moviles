package com.example.sportsapps;

public class Noticia {
    private String titulo;
    private String descripcion;
    private String url;


    public Noticia(String titulo, String descripcion, String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getDescripcion() { return this.descripcion; }

    public String getUrl() { return this.url; }

}
