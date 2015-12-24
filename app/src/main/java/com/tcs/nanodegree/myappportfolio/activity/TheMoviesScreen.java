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
        FragmentManager fragMngr = getSupportFragmentManager();
        /*movieFrag = MoviesFragment.newInstance();
        fragMngr.beginTransaction().add(R.id.movieContainer, movieFrag).commit();*/
        if (savedInstanceState == null) {
            movieFrag = MoviesFragment.newInstance();
            fragMngr.beginTransaction().add(R.id.movieContainer, movieFrag).commit();
        } else {
            movieFrag = (MoviesFragment) fragMngr.findFragmentById(R.id.movieContainer);
        }
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


        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
