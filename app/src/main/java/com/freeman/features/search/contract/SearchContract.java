package com.freeman.features.search.contract;

import android.content.Intent;

import com.freeman.base.MvpPresenter;
import com.freeman.base.MvpView;
import com.freeman.model.MoviesResult;

/**
 * Created by jalmei14 on 4/6/17.
 */

public interface SearchContract {

    interface SearchView extends MvpView {

        void handleIntent(Intent intent);

        void showLoading();
        void hideLoading();

        void showNoResultsForSearch();
        void showError();

        void showResults(MoviesResult moviesResult);

    }

    interface SearchPresenter extends MvpPresenter<SearchView> {
        void searchMovies(String query);
    }
}
