package com.tcs.nanodegree.myappportfolio.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tcs.nanodegree.myappportfolio.activity.R;

public class ReviewActivity extends AppCompatActivity {

    private String movieName, reviewContent, reviewAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent()!=null && getIntent().hasExtra("name"))
            movieName  = getIntent().getExtras().getString("name");

        if(getIntent()!=null && getIntent().hasExtra("author"))
            reviewAuthor  = getIntent().getExtras().getString("author");

        if(getIntent()!=null && getIntent().hasExtra("content"))
            reviewContent  = getIntent().getExtras().getString("content");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(movieName);

        TextView tvAuthor = (TextView) findViewById(R.id.txtReviewAuthor);
        TextView tvReview = (TextView) findViewById(R.id.txtReviewContent);

        tvAuthor.setText(reviewAuthor);
        tvReview.setText(reviewContent);
    }

}
