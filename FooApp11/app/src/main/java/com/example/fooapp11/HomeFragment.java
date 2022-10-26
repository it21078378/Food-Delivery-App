package com.example.fooapp11;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooapp11.adapters.ProductAdapter;
import com.example.fooapp11.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    ListView productsLV;
    ArrayList<Product> productModalArrayList;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;

    Button add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        View contentView = inflater.inflate(R.layout.fragment_home, container, false);
        productsLV = (ListView)contentView.findViewById(R.id.products);
        productModalArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        loadDatainListview();

        return contentView;
    }



    private void loadDatainListview() {
        db.collection("Products").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding
                            // our progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                Product dataModal = d.toObject(Product.class);

                                // after getting data from Firebase we are
                                // storing that data in our array list
                                productModalArrayList.add(dataModal);
                            }
                            // after that we are passing our array list to our adapter class.
                            ProductAdapter adapter = new ProductAdapter(getContext(), productModalArrayList);

                            // after passing this array list to our adapter
                            // class we are setting our adapter to our list view.
                            productsLV.setAdapter(adapter);


                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(getContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // we are displaying a toast message
                        // when we get any error from Firebase.
                        Toast.makeText(getContext(), "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}