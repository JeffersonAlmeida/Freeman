package com.freeman.model;

/**
 * Created by jalmei14 on 4/4/17.
 */

public class MovieSection {

    private String title;

    private MoviesResult moviesResult;

    public MovieSection(String title) {
        this.title = title;
    }

    public MovieSection(String title, MoviesResult moviesResult) {
        this.title = title;
        this.moviesResult = moviesResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MoviesResult getMoviesResult() {
        return moviesResult;
    }

    public void setMoviesResult(MoviesResult moviesResult) {
        this.moviesResult = moviesResult;
    }
}
