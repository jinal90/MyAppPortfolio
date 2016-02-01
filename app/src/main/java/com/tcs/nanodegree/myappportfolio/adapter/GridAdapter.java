package com.tcs.nanodegree.myappportfolio.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tcs.nanodegree.myappportfolio.activity.MainActivity;
import com.tcs.nanodegree.myappportfolio.activity.MovieFullScreenActivity;
import com.tcs.nanodegree.myappportfolio.activity.R;
import com.tcs.nanodegree.myappportfolio.activity.TheMoviesScreen;
import com.tcs.nanodegree.myappportfolio.bean.Result;
import com.tcs.nanodegree.myappportfolio.fragment.MovieDetailsFragment;

import java.util.List;

/**
 * Created by Jinal Tandel on 12/11/2015.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<Result> mItems;
    private Context context;
    private DisplayMetrics metrix;

    public GridAdapter(Context context, List<Result> movieList) {
        super();
        mItems = movieList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Result movieObj = mItems.get(i);
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w342" + movieObj.getPosterPath())
                .into(viewHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imgThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fragmentJump(mItems.get(this.getAdapterPosition()));

            /*Intent detailIntent = new Intent(context, MovieFullScreenActivity.class);

            detailIntent.putExtra(context.getResources().getString(R.string.movie_obj), mItems.get(this.getAdapterPosition()));
            if (Build.VERSION.SDK_INT >= 21) {
                v.setTransitionName(context.getResources().getString(R.string.movie_poster_transition));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, v, context.getResources().getString(R.string.movie_poster_transition));
                context.startActivity(detailIntent, options.toBundle());
            } else {
                context.startActivity(detailIntent);
            }*/
        }
    }

    private void fragmentJump(Result mItemSelected) {

        if (context!=null && context instanceof TheMoviesScreen) {
            TheMoviesScreen mainActivity = (TheMoviesScreen) context;
            mainActivity.switchContent(mItemSelected);
        }
    }
}
