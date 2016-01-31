package com.tcs.nanodegree.myappportfolio.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tcs.nanodegree.myappportfolio.adapter.TrailerAdapter;
import com.tcs.nanodegree.myappportfolio.bean.Result;
import com.tcs.nanodegree.myappportfolio.bean.Trailer;
import com.tcs.nanodegree.myappportfolio.intefaces.IApiMethods;
import com.tcs.nanodegree.myappportfolio.util.WrappingLinearLayoutManager;

import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieFullScreenActivity extends AppCompatActivity {

    private Result movieObj;
    private ImageView imgPoster;
    private ActionBar actionBar;
    private TextView tvOverview, tvReleaseDate, tvLanguage;
    private RatingBar movieRating;
    private Trailer movieTrailerObj;
    private RecyclerView mRecyclerView;
    private TrailerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this)
                    .inflateTransition(R.transition.shared_element_transition));
        }

        setContentView(R.layout.activity_movie_full_screen);
        setupUI();
    }

    private void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        if (getIntent() != null) {
            movieObj = (Result) getIntent().getParcelableExtra(getResources().getString(R.string.movie_obj));
        }
        imgPoster = (ImageView) findViewById(R.id.img_movie_poster);
        Picasso.with(this)
                .load(getResources().getString(R.string.TMDB_image_url) + movieObj.getPosterPath())
                .into(imgPoster);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(movieObj.getTitle());

        tvOverview = (TextView) findViewById(R.id.txtOverview);
        tvOverview.setText(movieObj.getOverview());

        float rating = (float) ((5 * movieObj.getVoteAverage()) / 10);

        movieRating = (RatingBar) findViewById(R.id.movieRating);
        movieRating.setRating(rating);
        movieRating.setIsIndicator(true);

        tvReleaseDate = (TextView) findViewById(R.id.txtReleaseDateValue);
        tvReleaseDate.setText(movieObj.getReleaseDate());

        tvLanguage = (TextView) findViewById(R.id.txtLanguageValue);
        Locale current = new Locale(movieObj.getOriginalLanguage());

        tvLanguage.setText(current.getDisplayLanguage());

        WrappingLinearLayoutManager layoutManager = new WrappingLinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_trailers);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(layoutManager);
        fetchMovieTrailer();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPlayTrailer:
                String url = "http://www.youtube.com/watch?v=" + movieTrailerObj.getResults().get(0).getKey();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
            default:
        }
    }

    public void fetchMovieTrailer() {
        final IApiMethods methods;

        try {

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(getResources().getString(R.string.TMDB_base_url)
                            + "/movie/" + movieObj.getId())
                    .build();

            methods = restAdapter.create(IApiMethods.class);

            Callback callback = new Callback() {
                @Override
                public void success(Object o, Response response) {

                    movieTrailerObj = (Trailer) o;
                    mAdapter = new TrailerAdapter(MovieFullScreenActivity.this, movieTrailerObj.getResults());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                    System.out.println("error --- 1");


                }
            };

            methods.getMovieTrailer(getResources().getString(R.string.TMDB_ApiKey),
                    callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
}
