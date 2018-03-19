package com.example.android.popularmovies.network;

import com.example.android.popularmovies.data.MovieModel;

/**
 * JSONResponse.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that maps the 'results' json object from TheMovieAPI response to an array of movies.
 */

public class JSONResponse {

    private MovieModel[] results;

    public MovieModel[] getMovies() {
        return results;
    }
}
