package com.freeman.features.home.contract;

import com.freeman.base.MvpPresenter;
import com.freeman.base.MvpView;
import com.freeman.model.MovieSection;

import java.util.List;

/**
 * Created by jalmei14 on 4/3/17.
 */

public interface HomeContract {

    interface HomeView extends MvpView {

        void showMovies(List<MovieSection> movieSections);

        void showLoading();
        void hideLoading();

    }

    interface HomePresenter extends MvpPresenter<HomeView> {

        void fetchMovies();

    }
}
