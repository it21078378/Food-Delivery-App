package com.example.fooapp11.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fooapp11.LoginActivity;
import com.example.fooapp11.R;
import com.example.fooapp11.SingleFood;
import com.example.fooapp11.model.Cart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Cart> {

    FirebaseFirestore db;
    Button remove;
    // constructor for our list view adapter.
    public CartAdapter(@NonNull Context context, ArrayList<Cart> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Cart dataModal = getItem(position);

        // initializing our UI components of list view item.
        TextView title = listitemView.findViewById(R.id.title);
        ImageView image = listitemView.findViewById(R.id.image);
        TextView amount = listitemView.findViewById(R.id.amount);
        TextView quantity = listitemView.findViewById(R.id.quantity);
        TextView total = listitemView.findViewById(R.id.total);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        title.setText(dataModal.getPtitle());
        amount.setText(String.valueOf(dataModal.getPamount()));
        quantity.setText(String.valueOf(dataModal.getPquantity()));
        total.setText(String.valueOf(dataModal.getPtotal()));

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

                Toast.makeText(getContext(), "Item clicked is : " + dataModal.getPtitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SingleFood.class);
                String ptitle = dataModal.getPtitle();
                int pamount= dataModal.getPamount();
                String imgUrl= dataModal.getImgUrl();
                String pdescription = dataModal.getPdescription();
                int pquantity = dataModal.getPquantity();

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add data to the bundle
                bundle.putString("title", ptitle);
                bundle.putInt("amount", pamount);
                bundle.putString("description", pdescription);
                bundle.putString("imgUrl", imgUrl);
                bundle.putString("description", pdescription);
                bundle.putInt("quantity", pquantity);

                //Add the bundle to the intent
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }
        });

        remove = (Button)listitemView.findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Cart").document(dataModal.getPtitle()).delete();

            }
        });
        return listitemView;
    }


}
