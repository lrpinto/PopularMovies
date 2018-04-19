package com.example.android.popularmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavouriteMoviesContract {

    public static final String AUTHORITY = "com.example.android.popularmovies";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVOURITE_MOVIES = "favouriteMovies";

    public static final class FavouriteMovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITE_MOVIES).build();

        public static final String TABLE_NAME = "favouriteMovies";

        public static final String COLUMN_MOVIE_ID = "movieId"; // The movie ID
        public static final String COLUMN_ORIGINAL_TITLE = "orginalTitle"; // The original title
        public static final String COLUMN_POSTER_PATH = "posterPath"; // The movie poster (portrait)
        public static final String COLUMN_BACKDROP_PATH = "backdropPath"; // The movie poster (landscape)
        public static final String COLUMN_RELEASE_DATE = "releaseDate"; // The release date
        public static final String COLUMN_OVERVIEW = "overview"; // The plot synopsis
        public static final String COLUMN_VOTE_AVERAGE = "voteAverage"; // The user rating

        public class CONTENT_URI_WITH_MOVIE_ID {
        }
    }
}
