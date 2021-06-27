package com.example.artrailproject;

public class ArteModel {
    private int id;
    private String nome;
    private String imagem;
    private int arteID1;
    private double longitude;
    private double latitude;
    private String nome_artista;

    public ArteModel(int id, String nome, String imagem, int arteID1, double longitude, double latitude, String nome_artista) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.arteID1 = arteID1;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nome_artista = nome_artista;
    }

    public int getArteID1() {
        return arteID1;
    }


    public void setArteID1(int arteID1) {
        this.arteID1 = arteID1;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNome_artista() {
        return nome_artista;
    }

    public void setNome_artista(String nome_artista) {
        this.nome_artista = nome_artista;
    }



    public ArteModel() {
    }

    @Override
    public String toString() {
        return "ArteModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", imagem='" + imagem + '\'' +
                ", arteID1=" + arteID1 +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", nome_artista='" + nome_artista + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
