package com.example.artrailproject;




import android.content.Intent;
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

import java.io.ByteArrayOutputStream;
import java.util.List;


public class sessoes extends AppCompatActivity {
    ArtrailDataService ads = new ArtrailDataService(sessoes.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessoes);
        Bundle b = getIntent().getExtras();
        int value = 0;
        if (b != null) {
            value = b.getInt("arteID");
        }
        ads.getSessoes(new ArtrailDataService.sessoesResponse() {
                         @Override
                         public void onError(String message) {

                         }

                         @Override
                         public void onResponse(List <sessaoModel> sessaoModels) {
                            displaysessoes(sessaoModels);
                         }
                     }, value
        );
    }

    public void displaysessoes (List<sessaoModel> sessaoModels){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);

        scrollView.addView(linearLayout);
        for (int i = 0; i < sessaoModels.size(); i++) {
            ImageView imageView1 = new ImageView(this);
            TextView nome = new TextView(this);
            TextView descricao = new TextView(this);
            TextView user = new TextView(this);
            TextView estado = new TextView(this);
            TextView timeStamp = new TextView(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, 30, 0, 30);
            params1.gravity = Gravity.CENTER;
            imageView1.setLayoutParams(params1);
            nome.setLayoutParams(params1);
            descricao.setLayoutParams(params1);
            user.setLayoutParams(params1);
            estado.setLayoutParams(params1);
            timeStamp.setLayoutParams(params1);
            nome.setText(sessaoModels.get(i).getNome());
            descricao.setText("Descrição: " + sessaoModels.get(i).getDescricao());
            estado.setText("Estado de conservação: " + sessaoModels.get(i).getEstado_conservacao());
            user.setText("Publicado por: " + sessaoModels.get(i).getNome_user());
            timeStamp.setText("em: " + sessaoModels.get(i).getTimestamp());
            String[] img = sessaoModels.get(i).getImagem().split("\"");
            String imagem = img[1];
            Picasso.with(this).load(imagem).into(imageView1);
            linearLayout.addView(nome);
            linearLayout.addView(descricao);
            linearLayout.addView(estado);
            linearLayout.addView(user);
            linearLayout.addView(timeStamp);
            linearLayout.addView(imageView1);
            int finalI = i;
            imageView1.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    Intent intent = new Intent(sessoes.this, imagens.class);
                    intent.putExtra("sessaoID", sessaoModels.get(finalI).getSessaoID1());
                    startActivity(intent);
                }});
        }
        LinearLayout linearLayout1 = findViewById(R.id.rootContainerSessoes);
        if (linearLayout1 != null) {
            linearLayout1.addView(scrollView);
        }

    }
}