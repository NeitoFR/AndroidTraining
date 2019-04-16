package com.example.listtraining.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.listtraining.R;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        // cacher les elements par défauts
      //  getSupportActionBar().hide();

        // récupération de données

        String title  = getIntent().getExtras().getString("anime_title");
        String description = getIntent().getExtras().getString("anime_description");
        String maker = getIntent().getExtras().getString("anime_maker") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;

        //ini Views

        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_title =findViewById(R.id.aa_anime_title);
        TextView tv_maker=findViewById(R.id.aa_maker);
        TextView tv_descrip=findViewById(R.id.aa_description);
        final ImageView img = findViewById(R.id.aa_thumbnail);


        // on met les valeurs dans les textview adéquat

        tv_title.setText(title);
        tv_maker.setText(maker);
        tv_descrip.setText(description);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // concernant l image il faut Glide

        Glide.with(this).load(image_url).apply(requestOptions).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(600, 800);
                img.setLayoutParams(layoutParams);
            }
        });

    }
}
