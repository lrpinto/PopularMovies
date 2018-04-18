package com.example.android.popularmovies.network;

import com.example.android.popularmovies.data.VideoModel;

/**
 * VideosJSONResponse.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that maps the 'results' json object from TheMovieAPI response to an array of videos.
 */

public class VideosJSONResponse {

    private VideoModel[] results;

    public VideoModel[] getVideos() {
        return results;
    }
}
