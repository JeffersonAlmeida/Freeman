package com.freeman.util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jalmei14 on 4/4/17.
 */

public class RequestInterceptor implements Interceptor {

    private static final String KEY = "api_key";

    private static final String PARAMETER = "808cc238156a4a4aadd48cd7b24a7a66";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        HttpUrl httpUrl = originalRequest
                .url()
                .newBuilder()
                .addQueryParameter(KEY, PARAMETER)
                .build();

        Request newRequest = originalRequest
                .newBuilder()
                .url(httpUrl)
                .build();

        return chain.proceed(newRequest);
    }
}
