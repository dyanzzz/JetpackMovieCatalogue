package com.imucreative.jetpackmoviecatalogue.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movieentities")
public class MovieEntity implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    private int movieId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "tvShow")
    private String tvShow;

    @ColumnInfo(name = "imagePath")
    private String imagePath;

    @ColumnInfo(name = "imageBackDrop")
    private String imageBackdropPath;

    @ColumnInfo(name = "voteCount")
    private int voteCount;

    @ColumnInfo(name = "popularity")
    private double popularity;

    @ColumnInfo(name = "language")
    private String language;

    private boolean status = false;

    public MovieEntity() {
    }

    protected MovieEntity(Parcel in) {
        this.movieId = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.tvShow = in.readString();
        this.imagePath = in.readString();
        this.imageBackdropPath = in.readString();
        this.voteCount = in.readInt();
        this.popularity = in.readDouble();
        this.language = in.readString();
    }

    @Ignore
    public MovieEntity(int movieId, String title, String description, String date, String tvShow, String imagePath, String imageBackdropPath, int voteCount, double popularity, String language) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.tvShow = tvShow;
        this.imagePath = imagePath;
        this.imageBackdropPath = imageBackdropPath;
        this.voteCount = voteCount;
        this.popularity = popularity;
        this.language = language;
    }

    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
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

    public String getTvShow() {
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

    public String getImageBackdropPath() {
        return imageBackdropPath;
    }
    public void setImageBackdropPath(String imageBackdropPath) {
        this.imageBackdropPath = imageBackdropPath;
    }

    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movieId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.tvShow);
        dest.writeString(this.imagePath);
        dest.writeString(this.imageBackdropPath);
        dest.writeInt(this.voteCount);
        dest.writeDouble(this.popularity);
        dest.writeString(this.language);
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
