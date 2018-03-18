package com.example.android.popularmovies.network;

import com.example.android.popularmovies.data.MovieModel;

/**
 * Created by Luisa on 04/03/2018.
 */

public class JSONResponse {

    private MovieModel[] results;

    public MovieModel[] getMovies() {
        return results;
    }
}
