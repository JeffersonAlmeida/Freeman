package com.freeman;

import android.support.multidex.MultiDexApplication;

import com.freeman.di.components.DaggerRestApiComponent;
import com.freeman.di.components.RestApiComponent;

/**
 * Created by jalmei14 on 4/3/17.
 */

public class FreemanApp extends MultiDexApplication {

    private RestApiComponent restApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        restApiComponent = DaggerRestApiComponent.builder().build();
    }

    public RestApiComponent getRestApiComponent() {
        return restApiComponent;
    }
}
