package com.example.android.popularmovies.network;

import com.example.android.popularmovies.data.ReviewModel;

/**
 * ReviewsJSONResponse.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that maps the 'results' json object from TheMovieAPI response to an array of reviews.
 */

public class ReviewsJSONResponse {

    private ReviewModel[] results;

    public ReviewModel[] getReviews() {
        return results;
    }
}
