package com.example.artrailproject;

public class sessaoModel {
    private String nome;
    private String nome_user;
    private String descricao;
    private int sessaoID1;
    private String imagem;
    private String timestamp;
    private String estado_conservacao;
    private String nome_artista;

    public sessaoModel(String nome, String nome_user, String descricao, int sessaoID1, String imagem, String timestamp, String estado_conservacao, String nome_artista) {
        this.nome = nome;
        this.nome_user = nome_user;
        this.descricao = descricao;
        this.sessaoID1 = sessaoID1;
        this.imagem = imagem;
        this.timestamp = timestamp;
        this.estado_conservacao = estado_conservacao;
        this.nome_artista = nome_artista;
    }

    @Override
    public String toString() {
        return "sessaoModel{" +
                "nome='" + nome + '\'' +
                ", nome_user='" + nome_user + '\'' +
                ", descricao='" + descricao + '\'' +
                ", sessaoID1=" + sessaoID1 +
                ", imagem='" + imagem + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", estado_conservacao='" + estado_conservacao + '\'' +
                ", nome_artista='" + nome_artista + '\'' +
                '}';
    }

    public sessaoModel() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_user() {
        return nome_user;
    }

    public void setNome_user(String nome_user) {
        this.nome_user = nome_user;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getSessaoID1() {
        return sessaoID1;
    }

    public void setSessaoID1(int sessaoID1) {
        this.sessaoID1 = sessaoID1;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEstado_conservacao() {
        return estado_conservacao;
    }

    public void setEstado_conservacao(String estado_conservacao) {
        this.estado_conservacao = estado_conservacao;
    }

    public String getNome_artista() {
        return nome_artista;
    }

    public void setNome_artista(String nome_artista) {
        this.nome_artista = nome_artista;
    }

}