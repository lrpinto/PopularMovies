package com.example.android.popularmovies.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * RequestInterface.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that defines the contract for TheMovieAPI requests.
 */

public interface RequestInterface {

    @GET("movie/popular?")
    Call<MoviesJSONResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated?")
    Call<MoviesJSONResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos?}")
    Call<VideosJSONResponse> getMovieVideos(@Path("movie_id") Integer id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews?")
    Call<ReviewsJSONResponse> getMovieReviews(@Path("movie_id") Integer id, @Query("api_key") String apiKey);
}