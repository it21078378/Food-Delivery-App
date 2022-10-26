package com.example.fooapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooapp11.model.Product;
import com.example.fooapp11.model.Review;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddProduct extends AppCompatActivity {

    private EditText imgUrlEditText, productNameEditText, priceEditText, productDescriptionEditText;
    private Button save;
    private String imgUrl, productName, productDescription;
    private int amount;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        db = FirebaseFirestore.getInstance();
        imgUrlEditText = findViewById(R.id.imgUrl);
        productNameEditText = findViewById(R.id.productName);
        priceEditText = findViewById(R.id.price);
        productDescriptionEditText = findViewById(R.id.productDescription);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgUrl = imgUrlEditText.getText().toString();
                productName = productNameEditText.getText().toString();
                amount = Integer.parseInt(priceEditText.getText().toString());
                productDescription = productDescriptionEditText.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(imgUrl)) {
                    imgUrlEditText.setError("Please enter Product Image Url");

                } 
                
                else if(TextUtils.isEmpty(productName)){
                    productNameEditText.setError("Please enter Product Name");
                }
                
                else if(TextUtils.isEmpty(productDescription)){
                productDescriptionEditText.setError("Please enter Product Name");
                }
                
                else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(productName, imgUrl, productDescription, amount);
                }
            }
        });
    }

    private void addDataToFirestore(String productName, String imgUrl, String productDescription, int amount) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbReviews = db.collection("Products");

        // adding our data to our courses object class.
        Product products = new Product(productName, imgUrl, productDescription, amount);

        // below method is use to add data to Firebase Firestore.
        dbReviews.add(products).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(AddProduct.this, "Product has been added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(AddProduct.this, "Fail to add product \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }


}