package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Luisa on 04/03/2018.
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {

        movieViewHolder.tvOriginalTitle.setText(movies.get(i).getOriginalTitle());
        Picasso.with(context).load(movies.get(i).getPosterPath()).into(movieViewHolder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOriginalTitle;
        private ImageView ivMovie;

        public MovieViewHolder(View view) {
            super(view);

            tvOriginalTitle = (TextView) view.findViewById(R.id.tv_original_title);
            ivMovie = (ImageView) view.findViewById(R.id.iv_movie);
        }
    }

}
