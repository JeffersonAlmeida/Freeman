package com.freeman.features.home.presenter;

import com.freeman.base.BasePresenter;
import com.freeman.datamanager.DataManager;
import com.freeman.features.home.contract.HomeContract;
import com.freeman.model.MovieSection;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jalmei14 on 4/3/17.
 */

public class HomePresenterImpl
        extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {

    private final DataManager dataManager;

    @Inject
    HomePresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void fetchMovies() {

        getView().showLoading();

        dataManager.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MovieSection>>() {
                    @Override
                    public void onCompleted() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(List<MovieSection> movieSections) {
                        getView().showMovies(movieSections);
                    }
                });
    }
}
