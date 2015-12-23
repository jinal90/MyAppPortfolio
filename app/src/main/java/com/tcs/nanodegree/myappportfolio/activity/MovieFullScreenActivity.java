package com.tcs.nanodegree.myappportfolio.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tcs.nanodegree.myappportfolio.bean.Result;

import java.util.Locale;

public class MovieFullScreenActivity extends AppCompatActivity {

    private Result movieObj;
    private ImageView imgPoster;
    private ActionBar actionBar;
    private TextView tvOverview, tvReleaseDate, tvLanguage;
    private RatingBar movieRating;


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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
}
