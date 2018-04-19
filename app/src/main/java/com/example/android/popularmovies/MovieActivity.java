package com.example.android.popularmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.android.popularmovies.adapter.ReviewAdapter;
import com.example.android.popularmovies.adapter.VideoAdapter;
import com.example.android.popularmovies.adapter.ViewPagerAdapter;
import com.example.android.popularmovies.data.FavouriteMoviesContract;
import com.example.android.popularmovies.data.MovieModel;
import com.example.android.popularmovies.data.ReviewModel;
import com.example.android.popularmovies.data.VideoModel;
import com.example.android.popularmovies.fragment.FavouritesFragment;
import com.example.android.popularmovies.network.RequestInterface;
import com.example.android.popularmovies.network.ReviewsJSONResponse;
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

    private RecyclerView recyclerViewVideos;
    private LinkedList<VideoModel> videos;
    private VideoAdapter videoAdapter;

    private RecyclerView recyclerViewReviews;
    private LinkedList<ReviewModel> reviews;
    private ReviewAdapter reviewAdapter;

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

        recyclerViewVideos = findViewById(R.id.card_recycler_view_videos);
        recyclerViewVideos.setHasFixedSize(true);
        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewReviews = findViewById(R.id.card_recycler_view_reviews);
        recyclerViewReviews.setHasFixedSize(true);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));

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

               if ( isFavoriteMovie( movie ) ) {
                   btnFavourite.setText("UNMARK AS FAVOURITE");
                   btnFavourite.setBackgroundColor( getResources().getColor(R.color.red, null) );
                   btnFavourite.setTextColor(getResources().getColor(R.color.white, null));
               } else {
                   btnFavourite.setText("MARK AS FAVOURITE");
                   btnFavourite.setBackgroundColor( getResources().getColor(R.color.yellow, null) );
                   btnFavourite.setTextColor(getResources().getColor(R.color.black, null));
               }

                tvOriginalTitle.setText(movie.getOriginal_title());
                tvReleaseDate.setText(movie.getRelease_date());
                tvVoteAverage.setText(String.valueOf(movie.getVote_average()));
                tvOverview.setText(movie.getOverview());
                rbMovieRating.setRating(0.5f * movie.getVote_average().floatValue());

                LayerDrawable stars = (LayerDrawable) rbMovieRating.getProgressDrawable();
                stars.getDrawable(0).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

                loadTrailers(movie.getId());
                loadReviews(movie.getId());

            }
        }
    }

    private boolean isFavoriteMovie(MovieModel movie) {

        Uri movieUri = FavouriteMoviesContract.FavouriteMovieEntry.CONTENT_URI.buildUpon().appendEncodedPath("/" + movie.getId() ).build();

        int rowsReturned = getContentResolver().query(movieUri,
                null,
                null,
                null,
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_VOTE_AVERAGE).getCount();

        return rowsReturned > 0;
    }

    public void onClickUpdateFavouriteMovies(View view) {

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("MOVIE")) {
                final MovieModel movie = intentThatStartedThisActivity.getParcelableExtra("MOVIE");

                if ( isFavoriteMovie(movie) ) {
                    long result = deleteFavouriteMovie(movie);

                    if (result > 0) {
                        btnFavourite.setText("MARK AS FAVOURITE");
                        btnFavourite.setBackgroundColor( getResources().getColor(R.color.yellow, null) );
                        btnFavourite.setTextColor(getResources().getColor(R.color.black, null));
                        ViewPagerAdapter.reloadFavouriteMovies(getApplicationContext());
                    }
                } else {
                    long result = addFavouriteMovie(movie);

                    if (result > 0) {
                        btnFavourite.setText("UNMARK AS FAVOURITE");
                        btnFavourite.setBackgroundColor( getResources().getColor(R.color.red, null) );
                        btnFavourite.setTextColor(getResources().getColor(R.color.white, null));
                        ViewPagerAdapter.reloadFavouriteMovies(getApplicationContext());
                    }
                }
            }
        }
    }

    private long deleteFavouriteMovie(MovieModel movie) {

        if (movie == null) {
            return 0;
        }

        Uri movieUri = FavouriteMoviesContract.FavouriteMovieEntry.CONTENT_URI.buildUpon().appendEncodedPath("/" + movie.getId() ).build();

        int rowsDeleted = getContentResolver().delete(movieUri, null, null);

        if (rowsDeleted > 0) {
            Toast.makeText(getBaseContext(), "Unmarked as favourite successfully", Toast.LENGTH_LONG).show();
        }

        return rowsDeleted;
    }

    private void loadTrailers(Integer movieId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<VideosJSONResponse> call = request.getMovieVideos(movieId, MainActivity.API_KEY);
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
                recyclerViewVideos.setAdapter(videoAdapter);
            }

            @Override
            public void onFailure(Call<VideosJSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void loadReviews(Integer movieId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<ReviewsJSONResponse> call = request.getMovieReviews(movieId, MainActivity.API_KEY);
        call.enqueue(new Callback<ReviewsJSONResponse>() {
            @Override
            public void onResponse(Call<ReviewsJSONResponse> call, Response<ReviewsJSONResponse> response) {

                ReviewsJSONResponse jsonResponse = response.body();

                reviews = new LinkedList<>(Arrays.asList(jsonResponse.getReviews()));

                reviewAdapter = new ReviewAdapter(getApplicationContext(), reviews);
                recyclerViewReviews.setAdapter(reviewAdapter);
            }

            @Override
            public void onFailure(Call<ReviewsJSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public long addFavouriteMovie(MovieModel movie) {

        if (movie == null) {
            return 0;
        }

        ContentValues cv = new ContentValues();
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_MOVIE_ID, movie.getId());
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_ORIGINAL_TITLE, movie.getOriginal_title());
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_POSTER_PATH, movie.getPoster_path());
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_BACKDROP_PATH, movie.getBackdrop_path());
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_RELEASE_DATE, movie.getRelease_date());
        cv.put(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_VOTE_AVERAGE, movie.getVote_average());

        Uri uri = getContentResolver().insert(FavouriteMoviesContract.FavouriteMovieEntry.CONTENT_URI, cv);

        if (uri != null) {
            Toast.makeText(getBaseContext(), "Marked as favourite successfully", Toast.LENGTH_LONG).show();
        }

        return 1;

    }
}
