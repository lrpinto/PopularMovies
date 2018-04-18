package com.example.android.popularmovies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.MovieActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MovieAdapter;
import com.example.android.popularmovies.data.MovieModel;
import com.example.android.popularmovies.network.MoviesJSONResponse;
import com.example.android.popularmovies.network.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * PopularFragment.java
 * Created by Luisa on 03/03/2018.
 * <p>
 * Fragment representing the content for the tab "Popular Movies".
 */

public class PopularFragment extends Fragment {

    public static final String TITLE = "Popular";

    private RecyclerView recyclerView;
    private ArrayList<MovieModel> movies;
    private MovieAdapter movieAdapter;
    private View popularView;

    public static PopularFragment newInstance() {

        return new PopularFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        popularView = inflater.inflate(R.layout.fragment_popular, container, false);

        loadViews();

        return popularView;
    }

    private void loadViews() {
        recyclerView = popularView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        loadMovies();

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            final GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    MovieModel movie = movies.get(position);

                    Intent intentToStartDetailActivity = new Intent(getActivity().getApplicationContext(), MovieActivity.class);
                    intentToStartDetailActivity.putExtra("MOVIE", movie);
                    startActivity(intentToStartDetailActivity);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void loadMovies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<MoviesJSONResponse> call = request.getPopularMovies();
        call.enqueue(new Callback<MoviesJSONResponse>() {
            @Override
            public void onResponse(Call<MoviesJSONResponse> call, Response<MoviesJSONResponse> response) {

                MoviesJSONResponse jsonResponse = response.body();

                movies = new ArrayList<>(Arrays.asList(jsonResponse.getMovies()));
                movieAdapter = new MovieAdapter(getActivity().getApplicationContext(), movies);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<MoviesJSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
