package com.imucreative.jetpackmoviecatalogue.data;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieEntity implements Parcelable {
    private String movieId;
    private String title;
    private String description;
    private String date;
    private String tvShow;
    private String imagePath;

    public MovieEntity() {
    }

    protected MovieEntity(Parcel in) {
        this.movieId = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.tvShow = in.readString();
        this.imagePath = in.readString();
    }

    public String getMovieId() {
        return movieId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String isTvShow() {
        return tvShow;
    }
    public void setTvShow(String tvShow) {
        this.tvShow = tvShow;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.movieId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.tvShow);
        dest.writeString(this.imagePath);
    }

    public static final Parcelable.Creator<MovieEntity> CREATOR = new Parcelable.Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
