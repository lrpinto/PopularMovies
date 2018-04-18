package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * ReviewModel.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that represents a trailer object.
 */

public class ReviewModel implements Parcelable {

    public static final Creator CREATOR = new Creator() {
        public ReviewModel createFromParcel(Parcel in) {
            return readFromParcel(in);
        }

        public ReviewModel[] newArray(int size) {
            return new ReviewModel[size];
        }
    };

    private String id; // The review ID
    private String author; // The review author
    private String content; // The review content
    private String url; // The url where the review is hosted

    public ReviewModel() {
        // constructor for serialization
    }

    public ReviewModel(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    private static ReviewModel readFromParcel(Parcel parcel) {
        String id = parcel.readString();
        String author = parcel.readString();
        String content = parcel.readString();
        String url = parcel.readString();

        return new ReviewModel(id, author, content, url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(url);
    }

    @Override
    public String toString() {
        return "ReviewModel{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewModel that = (ReviewModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(author, that.author) &&
                Objects.equals(content, that.content) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, author, content, url);
    }

}
