package com.example.android.popularmovies;

/**
 * Created by Luisa on 04/03/2018.
 */

public class MovieModel {

    private String originalTitle; // The original title
    private String posterPath; // The movie poster thumbnail
    private String releaseDate; // The release date
    private String overview; // The plot synopsis
    private Number voteAvg; // The user rating

    public MovieModel() {
        // constructor for serialization
    }

    public MovieModel(String originalTitle, String posterPath, String releaseDate, String overview, Number voteAvg) {
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAvg = voteAvg;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Number getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(Number voteAvg) {
        this.voteAvg = voteAvg;
    }
}
