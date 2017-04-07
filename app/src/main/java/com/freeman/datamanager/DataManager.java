package com.freeman.datamanager;

import com.freeman.model.MovieSection;
import com.freeman.model.MoviesResult;
import com.freeman.remote.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func4;

/**
 * Created by jalmei14 on 4/3/17.
 */

public class DataManager {

    private final Service service;

    @Inject
    DataManager(Service service) {
        this.service = service;
    }

    public Observable<MoviesResult> fetchMovies(final String query) {
        return Observable.defer(new Func0<Observable<MoviesResult>>() {
            @Override
            public Observable<MoviesResult> call() {
                return service.searchMovie(query);
            }
        });
    }

    private Observable<MoviesResult> fetchUpcoming() {
        return Observable.defer(new Func0<Observable<MoviesResult>>() {
            @Override
            public Observable<MoviesResult> call() {
                return service.fetchUpcoming();
            }
        });
    }

    private Observable<MoviesResult> fetchNowPlaying() {
        return Observable.defer(new Func0<Observable<MoviesResult>>() {
            @Override
            public Observable<MoviesResult> call() {
                return service.fetchNowPlaying();
            }
        });
    }

    private Observable<MoviesResult> fetchTopRated() {
        return Observable.defer(new Func0<Observable<MoviesResult>>() {
            @Override
            public Observable<MoviesResult> call() {
                return service.fetchTopRated();
            }
        });
    }

    private Observable<MoviesResult> fetchPopular() {
        return Observable.defer(new Func0<Observable<MoviesResult>>() {
            @Override
            public Observable<MoviesResult> call() {
                return service.fetchPopular();
            }
        });
    }

    public Observable<List<MovieSection>> fetchMovies(){

        return Observable.defer(new Func0<Observable<List<MovieSection>>>() {
            @Override
            public Observable<List<MovieSection>> call() {

               return Observable.zip(fetchNowPlaying(), fetchUpcoming(), fetchPopular(), fetchTopRated(),
                        new Func4<MoviesResult, MoviesResult, MoviesResult, MoviesResult, List<MovieSection>>() {
                            @Override
                            public List<MovieSection> call(MoviesResult nowPlaying,
                                                           MoviesResult upcoming,
                                                           MoviesResult popular,
                                                           MoviesResult topRated) {

                                MovieSection nowPlayingSection = new MovieSection("Now Playing", nowPlaying);
                                MovieSection upcomingSection = new MovieSection("Upcoming", upcoming);
                                MovieSection popularSection = new MovieSection("Popular", popular);
                                MovieSection topRatedSection = new MovieSection("Top Rated", topRated);

                                return new ArrayList<>(Arrays.asList(nowPlayingSection,
                                        upcomingSection, popularSection, topRatedSection));
                            }
                        });
            }
        });
    }


}
