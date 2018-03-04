package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    private TextView tvMovieId;
    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);

        tvMovieId = (TextView) findViewById(R.id.tv_original_title);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                movieId = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                tvMovieId.setText(movieId);
            }
        }
    }
}
