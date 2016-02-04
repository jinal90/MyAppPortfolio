package com.tcs.nanodegree.myappportfolio.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;

import com.tcs.nanodegree.myappportfolio.bean.Result;
import com.tcs.nanodegree.myappportfolio.fragment.MovieDetailsFragment;
import com.tcs.nanodegree.myappportfolio.fragment.MoviesFragment;

public class TheMoviesScreen extends AppCompatActivity {

    private MoviesFragment movieFrag;
    FragmentManager fragMngr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementExitTransition(TransitionInflater.from(this)
                    .inflateTransition(R.transition.shared_element_transition));
        }

        setContentView(R.layout.activity_the_movies_screen);
        setupUI(savedInstanceState);
    }

    private void setupUI(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragMngr = getSupportFragmentManager();

        if (savedInstanceState == null) {
            movieFrag = MoviesFragment.newInstance();
            fragMngr.beginTransaction().add(R.id.movieContainerLeft, movieFrag).commit();
        }
    }

    public void switchContent(Result obj) {

        if ("true".equalsIgnoreCase(getString(R.string.is_tablet))) {
            MovieDetailsFragment frag = MovieDetailsFragment.newInstance(obj);
            fragMngr.beginTransaction()
                    .replace(R.id.movieContainerRight, frag, frag.toString())
                    .commit();
        } else {
            MovieDetailsFragment frag = MovieDetailsFragment.newInstance(obj);
            fragMngr.beginTransaction()
                    .replace(R.id.movieContainerLeft, frag, frag.toString())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
