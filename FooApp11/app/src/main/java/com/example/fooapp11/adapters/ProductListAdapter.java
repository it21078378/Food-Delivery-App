package com.example.fooapp11.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooapp11.AddProduct;
import com.example.fooapp11.R;
import com.example.fooapp11.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<Product> productlistsArrayList;
    private Context context;

    Button edit;

    // creating constructor for our adapter class
    public ProductListAdapter(ArrayList<Product> coursesArrayList, Context context) {
        this.productlistsArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.productlist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        Product productlist = productlistsArrayList.get(position);

        holder.name.setText(productlist.getPtitle());
        holder.price.setText(String.valueOf(productlist.getPamount()));
        holder.description.setText(productlist.getPdescription());
        Picasso.get().load(productlist.getImgUrl()).into(holder.image);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Item clicked is : " + productlist.getPtitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddProduct.class);
                String ptitle = productlist.getPtitle();
                int pamount= productlist.getPamount();
                String imgUrl= productlist.getImgUrl();
                String pdescription = productlist.getPdescription();


                //Create the bundle
                Bundle bundle = new Bundle();

                //Add data to the bundle
                bundle.putString("title", ptitle);
                bundle.putInt("amount", pamount);
                bundle.putString("description", pdescription);
                bundle.putString("imgUrl", imgUrl);

                //Add the bundle to the intent
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return productlistsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.

        private final TextView name;
        private final TextView price;
        private final TextView description;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);

            edit = itemView.findViewById(R.id.edit);






        }
    }
}
