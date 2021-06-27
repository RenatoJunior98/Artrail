package com.example.artrailproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.artrailproject.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle b = getIntent().getExtras();
        String value = "";
        if (b != null) {
            value = b.getString("localizacoes");
        }
        mMap = googleMap;
        LatLng Lisbon = new LatLng(38.736946,  -9.142685);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Lisbon));
        try {
            JSONArray localizacoes = new JSONArray(value);
            for (int i = 0; i < localizacoes.length(); i++) {
                addMarker(localizacoes.getJSONObject(i).getDouble("lat"),localizacoes.getJSONObject(i).getDouble("long"), localizacoes.getJSONObject(i).getString("nome") , googleMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addMarker(Double lat, Double longitude, String nome, GoogleMap googleMap){
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat,longitude)).title(nome));
    }

}