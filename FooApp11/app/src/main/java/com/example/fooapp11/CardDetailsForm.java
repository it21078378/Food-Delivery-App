package com.example.fooapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class CardDetailsForm extends Activity {
    public static final String Name = "Name";
    public static final String Description = "Description";
    public static final String Img = "Img";
    public static final String Total= "Total";
    public static final String quantity="quantity";
    public TextView totalText, qtyText;
    private double total;
    private String qty;
    private String name, description, img, uid;

    TextView showPayment;
    EditText cardNo, chName, expDate, cvvNo;
    Button payBtn, unpaidBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details_form);

        //totalText = findViewById(R.id.expDate);
        //qtyText = findViewById(R.id.cvvNo);


        cardNo = (EditText)findViewById(R.id.CardNo);
        chName = (EditText)findViewById(R.id.cardHolderName);
        expDate = (EditText) findViewById(R.id.expDate);
        cvvNo = (EditText)findViewById(R.id.cvvNo);
        showPayment = (TextView) findViewById(R.id.paymentMessage);
        payBtn = (Button)findViewById(R.id.payBtn);
        unpaidBtn = (Button)findViewById(R.id.unPaidBtn);

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

        unpaidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUnpaidData();
                clearAll();
                finish();
            }
        });



        Intent i = getIntent();
        total = i.getDoubleExtra(Total, 0.0);
        qty = i.getStringExtra(quantity);
        name = i.getStringExtra(Name);
        description = i.getStringExtra(Description);
        img = i.getStringExtra(Img);



        showPayment.setText("Total amount due RS."+String.valueOf(qty));
        // qtyText.setText(String.valueOf(qty));
        //cardNo.setText(name);


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
        map.put("uid", uid);


        FirebaseDatabase.getInstance().getReference().child("paidDetails").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CardDetailsForm.this, "Data inserted Successfully.", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CardDetailsForm.this, "Error while insertion data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void insertUnpaidData(){
        Map<String, Object> mapUp = new HashMap<>();
        mapUp.put("cardNo", cardNo.getText().toString());
        mapUp.put("chName", chName.getText().toString());
        mapUp.put("expDate", expDate.getText().toString());
        mapUp.put("cvvNo", cvvNo.getText().toString());
        mapUp.put("quantity", qty);
        mapUp.put("total", total);
        mapUp.put("name", name);
        mapUp.put("description", description);
        mapUp.put("img", img);
        mapUp.put("uid", uid);


        FirebaseDatabase.getInstance().getReference().child("unpaidDetails").push()
                .setValue(mapUp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CardDetailsForm.this, "Data inserted Successfully.", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CardDetailsForm.this, "Error while insertion data.", Toast.LENGTH_SHORT).show();
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