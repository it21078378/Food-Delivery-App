package com.example.fooapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UnpaidCardDetails extends AppCompatActivity {
    public static final String Name = "Name";
    public static final String Description = "Description";
    public static final String Img = "Img";
    public static final String Total= "Total";
    public static final String quantity="quantity";
    public TextView totalText, qtyText;
    private double total;
    private int qty;
    private String name, description, img, uid;

    TextView showPayment;
    EditText cardNo, chName, expDate, cvvNo;
    Button payBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_card_details);

        //totalText = findViewById(R.id.expDate);
        //qtyText = findViewById(R.id.cvvNo);


        cardNo = (EditText)findViewById(R.id.UpCardNo);
        chName = (EditText)findViewById(R.id.UpCardHolderName);
        expDate = (EditText) findViewById(R.id.UpExpDate);
        cvvNo = (EditText)findViewById(R.id.UpCvvNo);
        showPayment = (TextView) findViewById(R.id.UpPaymentMessage);
        payBtn = (Button)findViewById(R.id.UpPayBtn);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uid = currentFirebaseUser.getUid();



        payBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                insertPaidData();
                clearAll();
                finish();
            }
        });




        Intent i = getIntent();
        total = i.getDoubleExtra(Total, 0.0);
        qty = i.getIntExtra(quantity, 0);
        name = i.getStringExtra(Name);
        description = i.getStringExtra(Description);
        img = i.getStringExtra(Img);



        showPayment.setText("Total amount due RS."+String.valueOf(total));
        // qtyText.setText(String.valueOf(qty));
        cardNo.setText(name);


    }



    private void insertPaidData(){
        Map<String, Object> map = new HashMap<>();
        map.put("cardNo", cardNo.getText().toString());
        map.put("chName", chName.getText().toString());
        map.put("expDate", expDate.getText().toString());
        map.put("cvvNo", cvvNo.getText().toString());
        map.put("quantity", qty);
        map.put("total", total);
        map.put("name", name);
        map.put("description", description);
        map.put("img", img);
        map.put("Uid", uid);


        FirebaseDatabase.getInstance().getReference().child("paidDetails").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UnpaidCardDetails.this, "Data inserted Successfully.", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UnpaidCardDetails.this, "Error while insertion data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void clearAll(){
        cardNo.setText("");
        chName.setText("");
        expDate.setText("");
        cvvNo.setText("");
        showPayment.setText("");
    }


}