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

public class Movie implements Parcelable {

    private Long page;
    private List<Result> results = new ArrayList<Result>();
    private Long totalResults;
    private Long totalPages;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Movie() {
    }

    /**
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public Movie(Long page, List<Result> results, Long totalResults, Long totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    /**
     * @return The page
     */
    public Long getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Long page) {
        this.page = page;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * @return The totalResults
     */
    public Long getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The totalPages
     */
    public Long getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    protected Movie(Parcel in) {
        this.page = in.readLong();
        this.results = in.readArrayList(Result.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.page);
        dest.writeTypedList(this.results);
    }
}