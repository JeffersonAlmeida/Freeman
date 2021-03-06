package com.freeman.remote;

import com.freeman.model.MoviesResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jalmei14 on 4/3/17.
 */

public interface Service {

    @GET("movie/now_playing")
    Observable<MoviesResult> fetchNowPlaying();

    @GET("movie/top_rated")
    Observable<MoviesResult> fetchTopRated();

    @GET("movie/popular")
    Observable<MoviesResult> fetchPopular();

    @GET("movie/upcoming")
    Observable<MoviesResult> fetchUpcoming();

    @GET("search/movie")
    Observable<MoviesResult> searchMovie(@Query("query") String query);

}
