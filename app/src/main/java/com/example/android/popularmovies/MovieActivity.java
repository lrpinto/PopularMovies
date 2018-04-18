package com.example.android.popularmovies;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmovies.adapter.VideoAdapter;
import com.example.android.popularmovies.adapter.ViewPagerAdapter;
import com.example.android.popularmovies.data.MovieModel;
import com.example.android.popularmovies.data.VideoModel;
import com.example.android.popularmovies.fragment.FavouritesFragment;
import com.example.android.popularmovies.network.RequestInterface;
import com.example.android.popularmovies.network.VideosJSONResponse;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends AppCompatActivity {

    private ImageView ivMoviePoster;
    private TextView tvOriginalTitle;
    private TextView tvReleaseDate;
    private TextView tvVoteAverage;
    private TextView tvOverview;
    private RatingBar rbMovieRating;
    private Button btnFavourite;

    private RecyclerView recyclerView;
    private LinkedList<VideoModel> videos;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ivMoviePoster = findViewById(R.id.iv_movie);
        tvOriginalTitle = findViewById(R.id.tv_original_title);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvVoteAverage = findViewById(R.id.tv_vote_average);
        tvOverview = findViewById(R.id.tv_overview);
        rbMovieRating = findViewById(R.id.rb_movie_rating);
        btnFavourite = findViewById(R.id.btn_favourite);

        recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        setMovieDetails();

    }

    private void setMovieDetails() {
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("MOVIE")) {
                final MovieModel movie = intentThatStartedThisActivity.getParcelableExtra("MOVIE");

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

                btnFavourite.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        FavouritesFragment.addFavouriteMovie(movie, getApplicationContext());
                        ViewPagerAdapter.reloadFavouriteMovies(getApplicationContext());
                    }
                });

                loadTrailers(movie.getId());

            }
        }
    }

    private void loadTrailers(Integer movieId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<VideosJSONResponse> call = request.getMovieVideos(movieId);
        call.enqueue(new Callback<VideosJSONResponse>() {
            @Override
            public void onResponse(Call<VideosJSONResponse> call, Response<VideosJSONResponse> response) {

                VideosJSONResponse jsonResponse = response.body();

                videos = new LinkedList<>(Arrays.asList(jsonResponse.getVideos()));

                Iterator<VideoModel> it = videos.iterator();
                while (it.hasNext()) {

                    VideoModel current = it.next();

                    if (!current.isTrailer() || !current.isInYoutube()) {
                        it.remove();
                    }
                }

                videoAdapter = new VideoAdapter(getApplicationContext(), videos);
                recyclerView.setAdapter(videoAdapter);
            }

            @Override
            public void onFailure(Call<VideosJSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
