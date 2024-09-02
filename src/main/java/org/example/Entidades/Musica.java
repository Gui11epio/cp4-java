package org.example.Entidades;

public class Musica extends _EntidadeBase {
    private String titulo;
    private double duracao;
    private Album album;

    public Musica(String titulo, double duracao, Album album) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.album = album;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String toString() {
        return this.titulo;
    }
}
