package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * MovieModel.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that represents a movie object.
 */

public class MovieModel implements Parcelable {

    public static final Creator CREATOR = new Creator() {
        public MovieModel createFromParcel(Parcel in) {
            return readFromParcel(in);
        }

        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    private Integer id; // The movie ID
    private String original_title; // The original title
    private String poster_path; // The movie poster (portrait)
    private String backdrop_path; // The movie poster (landscape)
    private String release_date; // The release date
    private String overview; // The plot synopsis
    private Double vote_average; // The user rating

    public MovieModel() {
        // constructor for serialization
    }

    private MovieModel(Integer id, String original_title, String poster_path, String backdrop_path, String release_date, String overview, Double vote_average) {
        this.id = id;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.overview = overview;
        this.vote_average = vote_average;
    }

    private static MovieModel readFromParcel(Parcel parcel) {
        Integer id = parcel.readInt();
        String originalTitle = parcel.readString();
        String posterPath = parcel.readString();
        String backdropPath = parcel.readString();
        String releaseDate = parcel.readString();
        String overview = parcel.readString();
        Double voteAverage = parcel.readDouble();

        return new MovieModel(id, originalTitle, posterPath, backdropPath, releaseDate, overview, voteAverage);
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

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
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

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(release_date);
        parcel.writeString(overview);
        parcel.writeDouble(vote_average);

    }

    @Override
    public String toString() {
        return "MovieModel{" +
                "id=" + id +
                ", original_title='" + original_title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieModel that = (MovieModel) o;

        return id.equals(that.id) &&
                original_title.equals(that.original_title) &&
                poster_path.equals(that.poster_path) &&
                backdrop_path.equals(that.backdrop_path) &&
                release_date.equals(that.release_date) &&
                overview.equals(that.overview) &&
                vote_average.equals(that.vote_average);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + original_title.hashCode();
        result = 31 * result + poster_path.hashCode();
        result = 31 * result + backdrop_path.hashCode();
        result = 31 * result + release_date.hashCode();
        result = 31 * result + overview.hashCode();
        result = 31 * result + vote_average.hashCode();
        return result;
    }
}
