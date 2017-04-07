package com.freeman.features.home.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeman.R;
import com.freeman.model.MovieSection;
import com.freeman.model.MoviesResult;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created by jalmei14 on 4/3/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{

    private Context context;

    private List<MovieSection> movieSections;

    @Inject
    public HomeAdapter() {
    }

    public HomeAdapter(List<MovieSection> moviesResult) {
        this.movieSections = moviesResult;
    }

    public void setMoviesResult(List<MovieSection> moviesResult) {
        this.movieSections = moviesResult;
        notifyDataSetChanged();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.activity_main_movie_section, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        MovieSection movieSection = movieSections.get(position);
        String title = movieSection.getTitle();

        holder.textView.setText(title);

        RecyclerView recyclerView = holder.recyclerView;

        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START, false);
        snapHelper.attachToRecyclerView(recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        MoviesResult moviesResult = movieSection.getMoviesResult();
        MovieAdapter adapter = new MovieAdapter(moviesResult.getMovies());

        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(scaleInAnimationAdapter);

    }

    @Override
    public int getItemCount() {
        return movieSections.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_title)
        TextView textView;

        @BindView(R.id.horizontal_rv)
        RecyclerView recyclerView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
