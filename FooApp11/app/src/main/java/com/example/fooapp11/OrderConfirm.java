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

    String name;
    String description;
    String img;
    double price;

    public double TCost;
    public String Qty;

    public static String Name;
    public static String Description;
    public static String Img;
    public static String Total;
    public static String Quantity;
    public static String Price;
    double tICost=0, sCost=250.0, tCost=0;

    Button nextActivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        nextActivity =findViewById(R.id.nextBtn);

        nameText = (TextView)findViewById(R.id.foodNameOC);
        imgView = (ImageView) findViewById(R.id.foodImgOC);
        descriptionText = (TextView)findViewById(R.id.foodDescriptionOC);
        priceText = (TextView)findViewById(R.id.priceOC);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        Name = bundle.getString("title");
        Description = bundle.getString("description");
        Price = bundle.getString("amount");
        Img = bundle.getString("imgUrl");
        Total = bundle.getString("total");
        Quantity = bundle.getString("quantity");

        nameText.setText(Name);
        descriptionText.setText(Description);
        priceText.setText(Price);
        Glide.with(imgView)
                .load(Img)
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(imgView);

        name = nameText.getText().toString();
        description = descriptionText.getText().toString();
        img = bundle.getString("imgUrl");
        Qty = Quantity;

        display();

        nextActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openNextActivity();
            }

        });



    }


    private void display() {


        TextView totalItemCost = (TextView)findViewById(R.id.tic);
        TextView shippingCost = (TextView) findViewById(R.id.sc);
        TextView totalCost = (TextView)findViewById(R.id.tc);

        tICost = Integer.parseInt(Total);
        tCost= tICost+sCost;

        totalItemCost.setText(String.valueOf(tICost));
        shippingCost.setText(String.valueOf(sCost));
        totalCost.setText(String.valueOf(tCost));


    }

    public void openNextActivity(){
        Intent i = new Intent(this, CardDetailsForm.class);
        i.putExtra(CardDetailsForm.Name, name);
        i.putExtra(CardDetailsForm.Description, description);
        i.putExtra(CardDetailsForm.Img, img);
        i.putExtra(String.valueOf(CardDetailsForm.Total), tCost);
        i.putExtra(CardDetailsForm.quantity, Qty);

        startActivity(i);
    }





}