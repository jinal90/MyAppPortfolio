package com.tcs.nanodegree.myappportfolio.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {

    private String movieName, reviewContent, reviewAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null && getIntent().hasExtra(getString(R.string.key_name)))
            movieName = getIntent().getExtras().getString(getString(R.string.key_name));

        if (getIntent() != null && getIntent().hasExtra(getString(R.string.key_author)))
            reviewAuthor = getIntent().getExtras().getString(getString(R.string.key_author));

        if (getIntent() != null && getIntent().hasExtra(getString(R.string.key_content)))
            reviewContent = getIntent().getExtras().getString(getString(R.string.key_content));


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(movieName);

        TextView tvAuthor = (TextView) findViewById(R.id.txtReviewAuthor);
        TextView tvReview = (TextView) findViewById(R.id.txtReviewContent);

        tvAuthor.setText(reviewAuthor);
        tvReview.setText(reviewContent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
