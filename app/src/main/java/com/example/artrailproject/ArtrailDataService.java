package com.example.artrailproject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArtrailDataService {
    Context context;
    public ArtrailDataService(Context context) {
        this.context = context;
    }


public interface ArtesResponse {
    void onError(String message);

    void onResponse(List<com.example.artrailproject.ArteModel> arteModels) throws JSONException;
}

public void getArtes(ArtesResponse artesResponse){
        ArrayList<com.example.artrailproject.ArteModel> artesReportModels = new ArrayList<>();
        try {
            String url = "https://artrail.herokuapp.com/api/artes";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONArray artes = response;
                        for (int i = 0; i < artes.length(); i++) {
                            JSONObject arteFromAPI = (JSONObject) artes.get(i);
                            artesReportModels.add(new com.example.artrailproject.ArteModel(arteFromAPI.getInt("id"), arteFromAPI.getString("nome"), arteFromAPI.getString("imagem"), arteFromAPI.getInt("arteID1"), arteFromAPI.getDouble("longitude"), arteFromAPI.getDouble("latitude"), arteFromAPI.getString("nome_artista")));
                        }
                        artesResponse.onResponse(artesReportModels);
                    } catch (JSONException e) {
                        System.out.println("ERROR getting JSONarray");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            com.example.artrailproject.MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (Exception e){
            System.err.println(e);
        }
}

    public interface sessoesResponse {
        void onError(String message);

        void onResponse(List<com.example.artrailproject.sessaoModel> arteModels);
    }

    public void getSessoes(sessoesResponse sessoesResponse, int arteId){
        ArrayList <com.example.artrailproject.sessaoModel> sessoesReportModels = new ArrayList<>();
        try {
            String url = "https://artrail.herokuapp.com/api/sessoes/" + arteId;
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONArray sessoes = response;
                        for (int i = 0; i < sessoes.length(); i++) {
                            JSONObject sessaoFromAPI = (JSONObject) sessoes.get(i);
                            sessoesReportModels.add(new com.example.artrailproject.sessaoModel(sessaoFromAPI.getString("nome"), sessaoFromAPI.getString("nome_user"), sessaoFromAPI.getString("descricao"), sessaoFromAPI.getInt("sessaoID1"), sessaoFromAPI.getString("imagem"), sessaoFromAPI.getString("timestamp"), sessaoFromAPI.getString("estado_conservacao"), sessaoFromAPI.getString("nome_artista")));
                        }
                        sessoesResponse.onResponse(sessoesReportModels);
                    } catch (JSONException e) {
                        System.out.println("ERROR getting JSONarray");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            com.example.artrailproject.MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (Exception e){
            System.err.println(e);
        }
    }

    public interface imagensResponse {
        void onError(String message);

        void onResponse(imagemModel imagensModels) throws JSONException;
    }


    public void getImagens(imagensResponse imagensResponse, int sessaoID){
        try {
            String url = "https://artrail.herokuapp.com/api/sessoes/info/" + sessaoID;
            System.out.println("SessãoID: " + sessaoID);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                public void onResponse(JSONObject response) {
                    try {
                        imagemModel imagemReportModel = new imagemModel(response.getJSONObject("sessaoInfo"), response.getJSONArray("fotos"));
                        imagensResponse.onResponse(imagemReportModel);
                    } catch (JSONException e) {
                        System.out.println("ERROR getting JSONarray");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            com.example.artrailproject.MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } catch (Exception e){
            System.err.println(e);
        }
    }

}
