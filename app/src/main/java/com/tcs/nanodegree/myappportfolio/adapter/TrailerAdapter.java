package com.tcs.nanodegree.myappportfolio.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcs.nanodegree.myappportfolio.activity.R;
import com.tcs.nanodegree.myappportfolio.bean.TrailerResult;

import java.util.List;

/**
 * Created by Jinal Tandel on 12/11/2015.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private List<TrailerResult> mItems;
    private Context context;

    public TrailerAdapter(Context context, List<TrailerResult> trailerList) {
        super();
        mItems = trailerList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trailer_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final TrailerResult trailerObj = mItems.get(i);

        viewHolder.tvTrailerTitle.setText(trailerObj.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = context.getString(R.string.youtube_base_url) + trailerObj.getKey();
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTrailerTitle;
        public ImageView ivPlay;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTrailerTitle = (TextView) itemView.findViewById(R.id.tvTrailerTitle);
            ivPlay = (ImageView) itemView.findViewById(R.id.ivPlayTrailer);
        }
    }
}
