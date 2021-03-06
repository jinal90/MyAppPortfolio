package com.tcs.nanodegree.myappportfolio.intefaces;

import com.tcs.nanodegree.myappportfolio.bean.Movie;
import com.tcs.nanodegree.myappportfolio.bean.Review;
import com.tcs.nanodegree.myappportfolio.bean.Trailer;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IApiMethods {

    @GET("/discover/movie")
    void getMovie(
            @Query("api_key") String key,
            @Query("page") String page,
            @Query("sort_by") String sortType,
            Callback<Movie> cb
    );

    @GET("/videos")
    void getMovieTrailer(
            @Query("api_key") String key,
            Callback<Trailer> cb
    );

    @GET("/reviews")
    void getMovieReview(
            @Query("api_key") String key,
            Callback<Review> cb
    );

}
