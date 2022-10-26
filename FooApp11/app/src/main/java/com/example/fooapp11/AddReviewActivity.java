package com.example.fooapp11;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooapp11.model.Review;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddReviewActivity extends AppCompatActivity {

    // creating variables for our edit text
    private EditText nameEditText, reviewEditText;

    // creating variable for button
    private Button submitReviewBtn, viewReviewsBtn, idBtnViewReviews;

    // creating a strings for storing
    // our values from edittext fields.
    private String name, review;

    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        // initializing our edittext and buttons

        nameEditText = findViewById(R.id.name);
        reviewEditText = findViewById(R.id.review);

        submitReviewBtn = findViewById(R.id.idBtnSubmitReview);
        viewReviewsBtn = findViewById(R.id.idBtnViewReviews);
        idBtnViewReviews = findViewById(R.id.idBtnViewReviews);
        // adding onclick listener to view data in new activity
        viewReviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity on button click
                Intent i = new Intent(AddReviewActivity.this, ReviewDetails.class);
                startActivity(i);
            }
        });

        // adding on click listener for button
        submitReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                name = nameEditText.getText().toString();
                review = reviewEditText.getText().toString();


                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(review)) {
                    reviewEditText.setError("Please enter Review");

                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(name, review);
                }
            }
        });

        idBtnViewReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddReviewActivity.this, ReviewDetails.class);
                startActivity(intent);
            }
        });

    }

    private void addDataToFirestore(String name, String review) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbReviews = db.collection("Reviews");

        // adding our data to our courses object class.
        Review reviews = new Review(name, review);

        // below method is use to add data to Firebase Firestore.
        dbReviews.add(reviews).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(AddReviewActivity.this, "Your Review has been added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(AddReviewActivity.this, "Fail to add review \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}