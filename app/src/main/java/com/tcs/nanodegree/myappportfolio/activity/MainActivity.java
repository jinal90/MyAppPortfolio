package com.tcs.nanodegree.myappportfolio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle(getString(R.string.app_name));

        //TextView title = (TextView) findViewById(R.id.feed_detail_title);
        TextView date = (TextView) findViewById(R.id.tvTitle);
        ViewCompat.setTransitionName(date, "Title");
        //ViewCompat.setTransitionName(date, getString(R.string.transition_date));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(this, getString(R.string.settings_menu_click), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        String message = "Button is clicked!";

        switch (view.getId()) {
            case R.id.btnPopularMovies1:
                message = getString(R.string.this_button_click) + " "
                        + getString(R.string.popular_moview_1) + " app!";
                Intent i = new Intent(this, TheMoviesScreen.class);
                startActivity(i);

                break;

            case R.id.btnSpotifyApp:
                message = getString(R.string.this_button_click) + " " + getString(R.string.spotify_streamer) + " app!";
                break;

            case R.id.btnScoresApp:
                message = getString(R.string.this_button_click) + " " + getString(R.string.scores_app) + " app!";
                break;

            case R.id.btnLibraryApp:
                message = getString(R.string.this_button_click) + " " + getString(R.string.library_app) + " app!";
                break;

            case R.id.btnBuiltItBigger:
                message = getString(R.string.this_button_click) + " " + getString(R.string.build_it_bigger) + " app!";
                break;

            case R.id.btnXYZReader:
                message = getString(R.string.this_button_click) + " " + getString(R.string.xyz_reader) + " app!";
                break;

            case R.id.btnCapstoneMyApp:
                message = getString(R.string.this_button_click) + " " + getString(R.string.capstone_my_app) + " app!";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
