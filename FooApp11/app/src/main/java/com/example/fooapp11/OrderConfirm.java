package com.example.fooapp11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrderConfirm extends AppCompatActivity {


    private TextView nameText, descriptionText, priceText;
    private ImageView imgView;

    private String name;
    private String description;
    private String img;
    private double price;

    public double TCost;
    public int Qty;

    public static final String Name = "Name";
    public static final String Description = "Description";
    public static final String Img = "Img";
    public static final String Price= "Price";





    Button nextActivity ;

    int dNumber = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        nextActivity =findViewById(R.id.nextBtn);

        nameText = (TextView)findViewById(R.id.foodNameOC);
        imgView = (ImageView) findViewById(R.id.foodImgOC);
        descriptionText = (TextView)findViewById(R.id.foodDescriptionOC);
        priceText = (TextView)findViewById(R.id.priceOC);

        Intent i =getIntent();
        name = i.getStringExtra(Name);
        description = i.getStringExtra(Description);
        img = i.getStringExtra(Img);
        price = i.getDoubleExtra(Price, 0.0);

        nameText.setText(name);
        descriptionText.setText(description);
        priceText.setText(String.valueOf(price));
        Glide.with(imgView)
                .load(img)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(imgView);


        nextActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openNextActivity();
            }

        });


    }


    public void increaseInteger(View view) {
        if(dNumber>9){
            dNumber=10;
        }else {
            dNumber = dNumber + 1;
            display(dNumber);
        }

    }public void decreaseInteger(View view) {
        if(dNumber<1){
            dNumber=0;
        }
        else{
            dNumber = dNumber - 1;
            display(dNumber);
        }
    }

    private void display(int number) {
        double tICost=0, sCost=250.0, tCost=0;
        price = 300;
        TextView displayInteger = (TextView) findViewById(R.id.integer_number);
        TextView totalItemCost = (TextView)findViewById(R.id.tic);
        TextView shippingCost = (TextView) findViewById(R.id.sc);
        TextView totalCost = (TextView)findViewById(R.id.tc);

        tICost= price*number;
        tCost= tICost+sCost;

        displayInteger.setText("" + number);

        totalItemCost.setText(String.valueOf(tICost));
        shippingCost.setText(String.valueOf(sCost));
        totalCost.setText(String.valueOf(tCost));
        TCost=tCost;
        Qty=number;

    }



    public void openNextActivity(){
        Intent i = new Intent(this, CardDetailsForm.class);

        i.putExtra(CardDetailsForm.Name, name);
        i.putExtra(CardDetailsForm.Description, description);
        i.putExtra(CardDetailsForm.Img, img);
        i.putExtra(String.valueOf(CardDetailsForm.Total), TCost);
        i.putExtra(String.valueOf(CardDetailsForm.quantity), Qty);



        startActivity(i);
    }





}