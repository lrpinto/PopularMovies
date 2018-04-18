package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.ReviewModel;

import java.util.LinkedList;

/**
 * ReviewAdapter.java
 * Created by Luisa on 04/03/2018.
 * <p>
 * Adapter to manage list item views displayed in the recycler view.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private final Context context;
    private LinkedList<ReviewModel> reviews;

    public ReviewAdapter(Context context, LinkedList<ReviewModel> reviews) {
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_review_item, viewGroup, false);

        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder reviewViewHolder, int i) {

        final ReviewModel review = reviews.get(i);

        String blob = "By " + review.getAuthor() + ": " + review.getContent();

        reviewViewHolder.tvBlob.setText(blob);

        reviewViewHolder.btnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String url = review.getUrl();

                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                context.startActivity(webIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(LinkedList<ReviewModel> reviews) {
        this.reviews = reviews;
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvBlob;
        private final Button btnRead;

        ReviewViewHolder(View view) {
            super(view);

            tvBlob = view.findViewById(R.id.tv_blob);
            btnRead = view.findViewById(R.id.btn_read);

        }
    }

}
