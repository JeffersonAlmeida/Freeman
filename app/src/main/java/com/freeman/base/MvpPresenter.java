package com.freeman.base;

/**
 * Created by jalmei14 on 4/3/17.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);
    void detachView();

}
