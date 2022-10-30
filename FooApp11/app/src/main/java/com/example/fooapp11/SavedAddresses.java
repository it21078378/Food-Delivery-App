package com.example.fooapp11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooapp11.adapters.DeliveryAdapter;
import com.example.fooapp11.model.DeliveryModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SavedAddresses extends AppCompatActivity {

    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    ArrayList<DeliveryModel> deliveryModelArrayList;
    DeliveryAdapter adapter;

    Button buttonAdd;
    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_addresses);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true); // work offline
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uId = currentFirebaseUser.getUid();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        deliveryModelArrayList = new ArrayList<>();

        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogAdd viewDialogAdd = new ViewDialogAdd();
                viewDialogAdd.showDialog(SavedAddresses.this);
            }
        });

        readData();

    }

    private void readData() {

        databaseReference.child("ADDRESSES").orderByChild("userId").startAt(uId).endAt(uId+"~").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deliveryModelArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DeliveryModel addresses = dataSnapshot.getValue(DeliveryModel.class);
                    deliveryModelArrayList.add(addresses);
                }
                adapter = new DeliveryAdapter(SavedAddresses.this, deliveryModelArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public class ViewDialogAdd {
        public void showDialog(Context context) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_address);

            EditText textHNo = dialog.findViewById(R.id.textHNo);
            EditText textBuilding = dialog.findViewById(R.id.textBuilding);
            EditText textStreet = dialog.findViewById(R.id.textStreet);
            EditText textArea = dialog.findViewById(R.id.textArea);
            EditText textLandmark = dialog.findViewById(R.id.textLandmark);

            Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonAdd.setText("SAVE");
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String userId = uId;
                    String id = "address" + new Date().getTime();
                    String HNo = textHNo.getText().toString();
                    String building = textBuilding.getText().toString();
                    String street = textStreet.getText().toString();
                    String area = textArea.getText().toString();
                    String landmark = textLandmark.getText().toString();

                    if (HNo.isEmpty() || building.isEmpty() || street.isEmpty() || area.isEmpty() || landmark.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("ADDRESSES").child(id).setValue(new DeliveryModel(userId, id, HNo, building, street, area, landmark));
                        Toast.makeText(context, "Address Added Successfully!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }


}