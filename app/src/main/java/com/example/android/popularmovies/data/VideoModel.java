package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * VideoModel.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Class that represents a trailer object.
 */

public class VideoModel implements Parcelable {

    public static final Creator CREATOR = new Creator() {
        public VideoModel createFromParcel(Parcel in) {
            return readFromParcel(in);
        }

        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };

    private String id; // The video ID
    private String key; // The video key
    private String name; // The video name
    private String site; // The site where the video is hosted
    private Integer size; // The size of the video
    private String type; // The type of video (e.g. Trailer)

    public VideoModel() {
        // constructor for serialization
    }

    public VideoModel(String id, String key, String name, String site, Integer size, String type) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    private static VideoModel readFromParcel(Parcel parcel) {
        String id = parcel.readString();
        String key = parcel.readString();
        String name = parcel.readString();
        String site = parcel.readString();
        Integer size = parcel.readInt();
        String type = parcel.readString();

        return new VideoModel(id, key, name, site, size, type);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(key);
        parcel.writeString(name);
        parcel.writeString(site);
        parcel.writeInt(size);
        parcel.writeString(type);

    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoModel that = (VideoModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(key, that.key) &&
                Objects.equals(name, that.name) &&
                Objects.equals(site, that.site) &&
                Objects.equals(size, that.size) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, key, name, site, size, type);
    }

    public boolean isTrailer() {
        return getType().equalsIgnoreCase("trailer");
    }

    public boolean isInYoutube() {
        return getSite().equalsIgnoreCase("youtube");
    }
}
