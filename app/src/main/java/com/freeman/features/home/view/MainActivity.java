package com.freeman.features.home.view;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.freeman.FreemanApp;
import com.freeman.R;
import com.freeman.features.home.adapter.HomeAdapter;
import com.freeman.features.home.contract.HomeContract;
import com.freeman.features.home.presenter.HomePresenterImpl;
import com.freeman.features.search.view.SearchResultsActivity;
import com.freeman.model.MovieSection;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeContract.HomeView {

    @Inject
    HomePresenterImpl homePresenter;

    @Inject
    HomeAdapter homeAdapter;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((FreemanApp) getApplication()).getRestApiComponent().inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homePresenter.attachView(this);
        homePresenter.fetchMovies();

    }

    @Override
    protected void onDestroy() {
        homePresenter.detachView();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        ComponentName componentName = new ComponentName(getApplicationContext(), SearchResultsActivity.class);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);

        return true;
    }

    @Override
    public void showMovies(List<MovieSection> moviesResult) {
        homeAdapter = new HomeAdapter(moviesResult);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
