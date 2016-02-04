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
import android.widget.Toast;

import com.tcs.nanodegree.myappportfolio.activity.R;
import com.tcs.nanodegree.myappportfolio.activity.ReviewActivity;
import com.tcs.nanodegree.myappportfolio.bean.ReviewResult;
import com.tcs.nanodegree.myappportfolio.bean.TrailerResult;
import com.tcs.nanodegree.myappportfolio.util.Utility;

import java.util.List;

/**
 * Created by Jinal Tandel on 12/11/2015.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewResult> mItems;
    private Context context;
    private String movieName;

    public ReviewAdapter(Context context, List<ReviewResult> reviewList, String name) {
        super();
        mItems = reviewList;
        this.context = context;
        this.movieName = name;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.review_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ReviewResult reviewObj = mItems.get(i);

        viewHolder.tvReviewAuthor.setText(reviewObj.getAuthor());
        viewHolder.tvReviewContent.setText(reviewObj.getContent());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(context, ReviewActivity.class);
                reviewIntent.putExtra(context.getString(R.string.key_name), movieName);
                reviewIntent.putExtra(context.getString(R.string.key_author), reviewObj.getAuthor());
                reviewIntent.putExtra(context.getString(R.string.key_content), reviewObj.getContent());
                context.startActivity(reviewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvReviewContent, tvReviewAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            tvReviewContent = (TextView) itemView.findViewById(R.id.txtReviewContent);
            tvReviewAuthor = (TextView) itemView.findViewById(R.id.txtReviewAuthor);
        }
    }
}
