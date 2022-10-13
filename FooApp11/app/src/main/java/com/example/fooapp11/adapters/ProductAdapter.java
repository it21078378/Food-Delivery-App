package com.example.fooapp11.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fooapp11.R;
import com.example.fooapp11.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    // constructor for our list view adapter.
    public ProductAdapter(@NonNull Context context, ArrayList<Product> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.product_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Product dataModal = getItem(position);

        // initializing our UI components of list view item.
        TextView title = listitemView.findViewById(R.id.title);
        ImageView image = listitemView.findViewById(R.id.image);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        title.setText(dataModal.getName());

        // in below line we are using Picasso to
        // load image from URL in our Image VIew.
        Picasso.get().load(dataModal.getImgUrl()).into(image);


        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : " + dataModal.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}

