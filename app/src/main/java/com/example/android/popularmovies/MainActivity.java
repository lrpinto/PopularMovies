package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Luisa on 04/03/2018.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList movies = initMovies();
        MovieAdapter adapter = new MovieAdapter(getApplicationContext(), movies);
        recyclerView.setAdapter(adapter);

    }

    private ArrayList initMovies() {

        ArrayList movies = new ArrayList<>();
        for (int i = 0; i < SampleData.originalTitles.length; i++) {
            MovieModel movie = new MovieModel();
            movie.setOriginalTitle(SampleData.originalTitles[i]);
            movie.setPosterPath("http://image.tmdb.org/t/p/" + "w185" + SampleData.movieImageUrls[i]);
            movies.add(movie);
        }
        return movies;
    }
}
