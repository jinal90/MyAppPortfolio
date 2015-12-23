package com.tcs.nanodegree.myappportfolio.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tcs.nanodegree.myappportfolio.fragment.MoviesFragment;

public class TheMoviesScreen extends AppCompatActivity {

    private MoviesFragment movieFrag;
    private int currentSelectedSort = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementExitTransition(TransitionInflater.from(this)
                    .inflateTransition(R.transition.shared_element_transition));
        }

        setContentView(R.layout.activity_the_movies_screen);
        setupUI();
    }

    private void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieFrag = MoviesFragment.newInstance();
        FragmentManager fragMngr = getSupportFragmentManager();
        fragMngr.beginTransaction().add(R.id.movieContainer, movieFrag).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_the_movies_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_popular_sort) {
            if (currentSelectedSort != 0) {
                movieFrag.setPageCount(1);
                movieFrag.fetchMovies(0);
                currentSelectedSort = 0;
            } else {
                Toast.makeText(this, getResources().getString(R.string.popular_already_sorted),
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.action_rating_sort) {
            if (currentSelectedSort != 1) {
                movieFrag.setPageCount(1);
                movieFrag.fetchMovies(1);
                currentSelectedSort = 1;
            } else {
                Toast.makeText(this, getResources().getString(R.string.highest_rating_already_sorted),
                        Toast.LENGTH_SHORT).show();
            }
            return true;
        } else{
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
