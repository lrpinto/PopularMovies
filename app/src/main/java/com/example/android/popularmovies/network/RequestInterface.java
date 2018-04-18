package com.example.android.popularmovies.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * RequestInterface.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that defines the contract for TheMovieAPI requests.
 */

public interface RequestInterface {

    @GET("movie/popular?api_key=%API_KEY%")
    Call<MoviesJSONResponse> getPopularMovies();

    @GET("movie/top_rated?api_key=%API_KEY%")
    Call<MoviesJSONResponse> getTopRatedMovies();

    @GET("movie/{movie_id}/videos?api_key=%API_KEY%")
    Call<VideosJSONResponse> getMovieVideos(@Path("movie_id") Integer id);
}