package com.example.fooapp11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class EditProduct extends AppCompatActivity {

    Button save;
    TextView title, description, amount, quantity, total;
    ImageView image;
    ImageButton increment, decrement;
    String ptitle, imgurl, pdescription;
    int pamount, pquantity, ptotal;
    FirebaseUser user;
    String userId;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        db = FirebaseFirestore.getInstance();
        title = (TextView)findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        amount = (TextView)findViewById(R.id.amount);
        quantity = (TextView)findViewById(R.id.quantity);
        total = (TextView)findViewById(R.id.total);
        image = (ImageView)findViewById(R.id.image);
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
        pdescription = bundle.getString("description");

        title.setText(ptitle);
        description.setText(pdescription);
        amount.setText(String.valueOf(pamount));
        pquantity = 1;
        total.setText(String.valueOf(pquantity*pamount));
        Picasso.get().load(imgurl).into(image);
    }
}