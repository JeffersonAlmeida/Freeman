package com.freeman.features.search.view;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;

import com.freeman.FreemanApp;
import com.freeman.R;
import com.freeman.features.home.adapter.MovieAdapter;
import com.freeman.features.search.contract.SearchContract;
import com.freeman.features.search.presenter.SearchPresenterImpl;
import com.freeman.model.MoviesResult;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity implements SearchContract.SearchView {

    private static final int GRID_ITEM_COUNT = 3;

    @Inject
    SearchPresenterImpl searchPresenter;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ButterKnife.bind(this);

        ((FreemanApp) getApplication()).getRestApiComponent().inject(this);
        searchPresenter.attachView(this);

        handleIntent(getIntent());

        LinearLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_ITEM_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchPresenter.searchMovies(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(onQueryTextListener);

        ComponentName componentName = new ComponentName(getApplicationContext(), SearchResultsActivity.class);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);

        return true;
    }

    private OnQueryTextListener onQueryTextListener = new OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            searchPresenter.searchMovies(newText);
            return true;
        }
    };

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNoResultsForSearch() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showResults(MoviesResult moviesResult) {
        MovieAdapter movieAdapter = new MovieAdapter(moviesResult.getMovies());
        recyclerView.setAdapter(movieAdapter);
    }
}
