package com.tcs.nanodegree.myappportfolio.intefaces;

import com.tcs.nanodegree.myappportfolio.bean.Movie;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IApiMethods {

    @GET("/movie")
    void getMovie(
            @Query("api_key") String key,
            @Query("page") String page,
            @Query("sort_by") String sortType,
            Callback<Movie> cb
    );

}
