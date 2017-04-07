package com.freeman.base;

/**
 * Created by jalmei14 on 4/3/17.
 */

public class BasePresenter <T extends MvpView> implements MvpPresenter<T> {

    T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public T getView() {
        return view;
    }
}
