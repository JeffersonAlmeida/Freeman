package com.freeman.di.modules;

import com.freeman.remote.Service;
import com.freeman.util.RequestInterceptor;
import com.freeman.util.RxErrorHandlingCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by jalmei14 on 3/21/17.
 */

@Module
public class RestApiModule {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Provides
    @Singleton
    Service service() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);

        httpClient.addInterceptor(new RequestInterceptor());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                        .build();

        return retrofit.create(Service.class);
    }

}
