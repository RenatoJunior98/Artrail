package com.example.artrailproject;

import org.json.JSONArray;
import org.json.JSONObject;

public class imagemModel {

    private JSONObject info;
    private JSONArray imagens;


    public imagemModel() {
    }

    public imagemModel(JSONObject info, JSONArray imagens) {
        this.info = info;
        this.imagens = imagens;
    }

    @Override
    public String toString() {
        return "imagemModel{" +
                "info=" + info +
                ", imagens=" + imagens +
                '}';
    }

    public JSONObject getInfo() {
        return info;
    }

    public void setInfo(JSONObject info) {
        this.info = info;
    }

    public void addImagem(String img) {
        imagens.put(img);
    }

    public JSONArray getImagens() {
        return imagens;
    }

    public void setImagens(JSONArray imagens) {
        this.imagens = imagens;
    }
}
