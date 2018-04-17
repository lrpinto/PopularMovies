package com.example.android.popularmovies.data;

import android.provider.BaseColumns;

public class FavouriteMoviesContract {

    public static final class FavouriteMovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "favouriteMovies";

        public static final String COLUMN_MOVIE_ID = "movieId"; // The movie ID
        public static final String COLUMN_ORIGINAL_TITLE = "orginalTitle"; // The original title
        public static final String COLUMN_POSTER_PATH = "posterPath"; // The movie poster (portrait)
        public static final String COLUMN_BACKDROP_PATH = "backdropPath"; // The movie poster (landscape)
        public static final String COLUMN_RELEASE_DATE = "releaseDate"; // The release date
        public static final String COLUMN_OVERVIEW = "overview"; // The plot synopsis
        public static final String COLUMN_VOTE_AVERAGE = "voteAverage"; // The user rating
    }
}
