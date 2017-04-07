package com.freeman.features.search.presenter;

import com.freeman.base.BasePresenter;
import com.freeman.datamanager.DataManager;
import com.freeman.features.search.contract.SearchContract;
import com.freeman.model.MoviesResult;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jalmei14 on 4/6/17.
 */

public class SearchPresenterImpl
        extends BasePresenter<SearchContract.SearchView> implements SearchContract.SearchPresenter {

    private final DataManager dataManager;

    @Inject
    public SearchPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void searchMovies(String query) {

        getView().showLoading();

        dataManager.fetchMovies(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoviesResult>() {
                    @Override
                    public void onCompleted() {
                        getView().showLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError();
                    }

                    @Override
                    public void onNext(MoviesResult moviesResult) {
                        getView().showResults(moviesResult);
                    }
                });
    }

}
