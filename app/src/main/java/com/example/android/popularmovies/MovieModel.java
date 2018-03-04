package com.example.android.popularmovies;

/**
 * Created by Luisa on 04/03/2018.
 */

public class MovieModel {

    private Integer id; // The movie ID
    private String original_title; // The original title
    private String poster_path; // The movie poster thumbnail
    private String release_date; // The release date
    private String overview; // The plot synopsis
    private Number vote_average; // The user rating

    public MovieModel() {
        // constructor for serialization
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public void setVote_average(Number vote_average) {
        this.vote_average = vote_average;
    }
}
