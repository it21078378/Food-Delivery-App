package com.example.fooapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooapp11.model.Cart;
import com.example.fooapp11.model.Review;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SingleFood extends AppCompatActivity {
    Button add, backButton;
    TextView title, amount, quantity, total;
    ImageButton increment, decrement;
    String ptitle, imgurl;
    int pamount, pquantity, ptotal;
    FirebaseUser user;
    String userId;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_food);
        db = FirebaseFirestore.getInstance();
        backButton = findViewById(R.id.backButton);
        add = findViewById(R.id.add);
        title = (TextView)findViewById(R.id.title);
        amount = (TextView)findViewById(R.id.amount);
        quantity = (TextView)findViewById(R.id.quantity);
        total = (TextView)findViewById(R.id.total);
        user = FirebaseAuth.getInstance().getCurrentUser();
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        userId = user.getUid();

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        ptitle = bundle.getString("title");
        pamount = bundle.getInt("amount");
        imgurl = bundle.getString("imgUrl");

        title.setText(ptitle);
        amount.setText(String.valueOf(pamount));
        pquantity = 1;
        total.setText(String.valueOf(pquantity*pamount));

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pquantity = Integer.parseInt(quantity.getText().toString().trim());
                pquantity++;
                quantity.setText(String.valueOf(pquantity));
                ptotal = pquantity*pamount;
                total.setText(String.valueOf(ptotal));
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pquantity = Integer.parseInt(quantity.getText().toString().trim());
                if(pquantity>1){
                    pquantity--;
                    quantity.setText(String.valueOf(pquantity));
                    ptotal = pquantity*pamount;
                    total.setText(String.valueOf(ptotal));
                }


            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleFood.this, MainActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ptitle = title.getText().toString().trim();
                pamount = Integer.parseInt(amount.getText().toString().trim());
                pquantity = Integer.parseInt(quantity.getText().toString().trim());
                ptotal = pamount*pquantity;
                total.setText(String.valueOf(ptotal));
                addDataToFirestore(userId, ptitle, pamount, pquantity, ptotal, imgurl);
            }
        });
    }

    private void addDataToFirestore(String userId, String ptitle, int pamount, int pquantity, int ptotal, String imgurl) {
        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbReviews = db.collection("Cart");

        // adding our data to our courses object class.
        Cart cart = new Cart(userId, ptitle, pamount, pquantity, ptotal, imgurl);

        // below method is use to add data to Firebase Firestore.
        dbReviews.add(cart).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(SingleFood.this, "Product has been added to the cart", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(SingleFood.this, "Fail to add product \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}