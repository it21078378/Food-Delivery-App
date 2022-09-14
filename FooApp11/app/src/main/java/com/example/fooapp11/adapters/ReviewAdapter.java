package com.example.fooapp11.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooapp11.R;
import com.example.fooapp11.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<Review> reviewsArrayList;
    private Context context;

    // creating constructor for our adapter class
    public ReviewAdapter(ArrayList<Review> coursesArrayList, Context context) {
        this.reviewsArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        Review review = reviewsArrayList.get(position);
        holder.username.setText(review.getUsername());
        holder.review.setText(review.getReview());

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return reviewsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.

        private final TextView username;
        private final TextView review;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            username = itemView.findViewById(R.id.username);
            review = itemView.findViewById(R.id.review);

        }
    }
}

