package com.example.android.popularmovies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmovies.data.MovieModel;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    private ImageView ivMoviePoster;
    private TextView tvOriginalTitle;
    private TextView tvReleaseDate;
    private TextView tvVoteAverage;
    private TextView tvOverview;
    private RatingBar rbMovieRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);

        ivMoviePoster = findViewById(R.id.iv_movie);
        tvOriginalTitle = findViewById(R.id.tv_original_title);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvVoteAverage = findViewById(R.id.tv_vote_average);
        tvOverview = findViewById(R.id.tv_overview);
        rbMovieRating = findViewById(R.id.rb_movie_rating);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("MOVIE")) {
                MovieModel movie = intentThatStartedThisActivity.getParcelableExtra("MOVIE");

                Picasso.with(getApplicationContext())
                        .load("http://image.tmdb.org/t/p/" + "w300" + movie.getBackdrop_path())
                        .into(ivMoviePoster);

                tvOriginalTitle.setText(movie.getOriginal_title());
                tvReleaseDate.setText(movie.getRelease_date());
                tvVoteAverage.setText(String.valueOf(movie.getVote_average()));
                tvOverview.setText(movie.getOverview());
                rbMovieRating.setRating(0.5f * movie.getVote_average().floatValue());

                LayerDrawable stars = (LayerDrawable) rbMovieRating.getProgressDrawable();
                stars.getDrawable(0).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

            }
        }
    }
}
