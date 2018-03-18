package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * MovieAdapter.java
 * Created by Luisa on 04/03/2018.
 *
 * Adapter to manage grid item views displayed in the recycler view.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieModel> movies;
    private Context context;

    public MovieAdapter(Context context, ArrayList<MovieModel> movies) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_layout, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {

        Picasso.with(context).load("http://image.tmdb.org/t/p/" + "w185" + movies.get(i).getPoster_path()).into(movieViewHolder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOriginalTitle;
        private ImageView ivMovie;

        MovieViewHolder(View view) {
            super(view);

            tvOriginalTitle = view.findViewById(R.id.tv_original_title);
            ivMovie = view.findViewById(R.id.iv_movie);

        }
    }

}
