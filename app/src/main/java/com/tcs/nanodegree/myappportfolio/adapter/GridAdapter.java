package com.tcs.nanodegree.myappportfolio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tcs.nanodegree.myappportfolio.activity.R;
import com.tcs.nanodegree.myappportfolio.activity.TheMoviesScreen;
import com.tcs.nanodegree.myappportfolio.bean.Result;

import java.util.List;

/**
 * Created by Jinal Tandel on 12/11/2015.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<Result> mItems;
    private Context context;

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
                .load(context.getResources().getString(R.string.TMDB_thumbnail_url) + movieObj.getPosterPath())
                .placeholder(context.getResources().getDrawable(R.drawable.movie_default))
                .error(context.getResources().getDrawable(R.drawable.movie_default))
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

            if (mItems != null && mItems.size() > this.getAdapterPosition())
                fragmentJump(mItems.get(this.getAdapterPosition()));
        }
    }

    private void fragmentJump(Result mItemSelected) {

        if (context != null && context instanceof TheMoviesScreen) {
            TheMoviesScreen mainActivity = (TheMoviesScreen) context;
            mainActivity.switchContent(mItemSelected);
        }
    }
}
