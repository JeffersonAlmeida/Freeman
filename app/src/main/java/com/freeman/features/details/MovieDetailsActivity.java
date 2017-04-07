package com.freeman.features.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.freeman.R;
import com.freeman.model.Movie;
import com.freeman.util.ImageProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.poster_image)
    ImageView posterImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, MovieDetailsActivity.class);
        starter.putExtra("movie", movie);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        String imageURL = ImageProvider.provideQualityImageURL(movie.getPosterPath());

        Glide.with(this)
                .load(imageURL)
                .centerCrop()
                .dontAnimate()
                .into(posterImage);

    }
}
