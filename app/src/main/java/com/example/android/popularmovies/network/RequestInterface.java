package com.example.android.popularmovies.network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * RequestInterface.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that defines the contract for TheMovieAPI requests.
 */

public interface RequestInterface {

    @GET("movie/popular?api_key=%API_KEY%")
    Call<JSONResponse> getPopularMovies();

    @GET("movie/top_rated?api_key=%API_KEY%")
    Call<JSONResponse> getTopRatedMovies();
}