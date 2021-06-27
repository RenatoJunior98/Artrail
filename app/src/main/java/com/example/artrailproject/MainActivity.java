package com.example.artrailproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.riversun.promise.Promise;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;


public class MainActivity extends AppCompatActivity {
    ArtrailDataService ads = new ArtrailDataService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.uploadButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Upload.class);
                startActivity(it);
            }
        });
        ads.getArtes(new ArtrailDataService.ArtesResponse() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(List<ArteModel> arteModels) throws JSONException {
                displayArtes(arteModels);
            }
        });
    }


    public void displayArtes(List<ArteModel> arteModels) throws JSONException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);

        scrollView.addView(linearLayout);
        ArrayList<Object> loalizacoes = new ArrayList<>();
        JSONObject localizacao = new JSONObject();
        for (int i = 0; i < arteModels.size(); i++) {
            localizacao.put("nome", arteModels.get(i).getNome());
            localizacao.put("lat", arteModels.get(i).getLatitude());
            localizacao.put("long", arteModels.get(i).getLongitude());
            loalizacoes.add(localizacao.toString());
            ImageView imageView1 = new ImageView(this);
            TextView nome = new TextView(this);
            TextView artista = new TextView(this);
            TextView loc = new TextView(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, 30, 0, 30);
            params1.gravity = Gravity.CENTER;
            imageView1.setLayoutParams(params1);
            nome.setLayoutParams(params1);
            artista.setLayoutParams(params1);
            loc.setLayoutParams(params1);
            nome.setText(arteModels.get(i).getNome());
            artista.setText("Artista: " + arteModels.get(i).getNome_artista());
            loc.setText("Localização: " + arteModels.get(i).getLatitude() + ", " + arteModels.get(i).getLongitude());
            String[] img = arteModels.get(i).getImagem().split("\"");
            String imagem = img[1];
            Picasso.with(this).load(imagem).into(imageView1);
            linearLayout.addView(nome);
            linearLayout.addView(artista);
            linearLayout.addView(loc);
            linearLayout.addView(imageView1);
            int finalI = i;
            imageView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, sessoes.class);
                    intent.putExtra("arteID", arteModels.get(finalI).getArteID1());
                    startActivity(intent);
                }
            });
        }
        Button btn = (Button) findViewById(R.id.mapButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, MapsActivity.class);
                it.putExtra("localizacoes", loalizacoes.toString());
                startActivity(it);
            }
        });
        LinearLayout linearLayout1 = findViewById(R.id.rootContainer);
        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView);
        }
    }
}