package com.example.artrailproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;

public class imagens extends AppCompatActivity {
    ArtrailDataService ads = new ArtrailDataService(imagens.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagens);
        Bundle b = getIntent().getExtras();
        int value = 0;
        if (b != null) {
            value = b.getInt("sessaoID");
        }
        ads.getImagens(new ArtrailDataService.imagensResponse() {
                           @Override
                           public void onError(String message) {

                           }

            @Override
            public void onResponse(imagemModel imagensModels) throws JSONException {
                               displayImagens(imagensModels);
                           }
            }, value
        );
        }


    public void displayImagens(imagemModel imagemModels) throws JSONException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);
        scrollView.addView(linearLayout);
        TextView descricao = new TextView(this);
        TextView user = new TextView(this);
        TextView estado = new TextView(this);
        TextView timeStamp = new TextView(this);
        descricao.setLayoutParams(params);
        user.setLayoutParams(params);
        estado.setLayoutParams(params);
        timeStamp.setLayoutParams(params);
        descricao.setText("Descrição: " + imagemModels.getInfo().getString("descricao"));
        estado.setText("Estado de conservação: " + imagemModels.getInfo().getString("estado_conservacao"));
        user.setText("Publicado por: " + imagemModels.getInfo().getString("nome_user"));
        timeStamp.setText("em: " + imagemModels.getInfo().getString("timestamp"));
        linearLayout.addView(descricao);
        linearLayout.addView(estado);
        linearLayout.addView(user);
        linearLayout.addView(timeStamp);
        for (int i = 0; i < imagemModels.getImagens().length(); i++) {
            ImageView imageView1 = new ImageView(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, 30, 0, 30);
            params1.gravity = Gravity.CENTER;
            imageView1.setLayoutParams(params1);
            String[] img = imagemModels.getImagens().getJSONObject(i).getString("imagem").split("\"");
            String imagem = img[1];
            Picasso.with(this).load(imagem).into(imageView1);
            linearLayout.addView(imageView1);
            imageView1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                }
            });
        }
        LinearLayout linearLayout1 = findViewById(R.id.rootContainerImagens);
        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView);
        }

    }
}
