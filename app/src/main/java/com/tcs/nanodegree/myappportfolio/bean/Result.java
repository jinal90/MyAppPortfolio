package com.tcs.nanodegree.myappportfolio.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jinal Tandel on 12/11/2015.
 */

public class Result implements Parcelable{

    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private List<Long> genre_ids = new ArrayList<Long>();
    private long id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private Double popularity;
    private Long vote_count;
    private Boolean video;
    private Double vote_average;
    private Map<String, Object> additional_properties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param id
     * @param genreIds
     * @param title
     * @param releaseDate
     * @param overview
     * @param posterPath
     * @param originalTitle
     * @param voteAverage
     * @param originalLanguage
     * @param adult
     * @param backdropPath
     * @param voteCount
     * @param video
     * @param popularity
     */
    public Result(String posterPath, Boolean adult, String overview, String releaseDate,
                  List<Long> genreIds, Long id, String originalTitle, String originalLanguage,
                  String title, String backdropPath, Double popularity, Long voteCount,
                  Boolean video, Double voteAverage) {
        this.poster_path = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.release_date = releaseDate;
        this.genre_ids = genreIds;
        this.id = id;
        this.original_title = originalTitle;
        this.original_language = originalLanguage;
        this.title = title;
        this.backdrop_path = backdropPath;
        this.popularity = popularity;
        this.vote_count = voteCount;
        this.video = video;
        this.vote_average = voteAverage;
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return poster_path;
    }

    /**
     * @param posterPath The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    /**
     * @return The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * @param adult The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * @param releaseDate The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    /**
     * @return The genreIds
     */
    public List<Long> getGenreIds() {
        return genre_ids;
    }

    /**
     * @param genreIds The genre_ids
     */
    public void setGenreIds(List<Long> genreIds) {
        this.genre_ids = genreIds;
    }

    /**
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return original_title;
    }

    /**
     * @param originalTitle The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    /**
     * @return The originalLanguage
     */
    public String getOriginalLanguage() {
        return original_language;
    }

    /**
     * @param originalLanguage The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The backdropPath
     */
    public String getBackdropPath() {
        return backdrop_path;
    }

    /**
     * @param backdropPath The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    /**
     * @return The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * @param popularity The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     * @return The voteCount
     */
    public Long getVoteCount() {
        return vote_count;
    }

    /**
     * @param voteCount The vote_count
     */
    public void setVoteCount(Long voteCount) {
        this.vote_count = voteCount;
    }

    /**
     * @return The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * @param video The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }

    /**
     * @return The voteAverage
     */
    public Double getVoteAverage() {
        return vote_average;
    }

    /**
     * @param voteAverage The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.vote_average = voteAverage;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additional_properties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additional_properties.put(name, value);
    }

    protected Result(Parcel in){
        this.poster_path = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.release_date = in.readString();
        //this.genre_ids = in.readArrayList(Long.class.getClassLoader());
        this.id = in.readLong();
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = in.readDouble();
        this.vote_count = in.readLong();
        this.video = in.readByte() != 0;
        this.vote_average = in.readDouble();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster_path);
        dest.writeByte((byte) (this.adult ? 1 : 0));
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        //dest.writeTypedList(this.genre_ids);
        dest.writeLong(this.id);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeDouble(this.popularity);
        dest.writeLong(this.vote_count);
        dest.writeByte((byte) (this.video ? 1 : 0));
        dest.writeDouble(this.vote_average);
    }
}