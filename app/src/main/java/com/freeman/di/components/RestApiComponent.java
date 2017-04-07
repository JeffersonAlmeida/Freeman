package com.freeman.di.components;

import com.freeman.di.modules.RestApiModule;
import com.freeman.features.home.view.MainActivity;
import com.freeman.features.search.view.SearchResultsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jalmei14 on 4/3/17.
 */

@Singleton
@Component ( modules = RestApiModule.class )
public interface RestApiComponent {

   void inject (MainActivity mainActivity);

   void inject(SearchResultsActivity searchResultsActivity);

}
