package com.example.fooapp11;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MyAccountFragment extends Fragment {
    String currentUser;
    String userName;
    String admin="EdiZjtWX4fYKdDYo3Owg4NZckKf2";

    public static final int longTime=5000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        currentUser = currentFirebaseUser.getUid();
        userName = currentFirebaseUser.getDisplayName();

        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        Button startActivity = (Button) view.findViewById(R.id.paymentHISTORY);
        Button startActivity2 = (Button) view.findViewById(R.id.add);
        TextView showUid = (TextView)view.findViewById(R.id.userID);
        startActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity2();
            }
        });
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });
        if (currentUser.equals(admin)){
            showUid.setText("Hello Admin !");
        }
        else{
            showUid.setText("Hello Customer !");
        }
        //showUid.setText("Hello "+userName+" !");
        return view;
    }

    private void startActivity2() {
        if (currentUser.equals(admin)){
            Intent i = new Intent(getActivity(), AddProduct.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getActivity(), "Sorry!, This option only for the admins.", Toast.LENGTH_LONG).show();
        }

    }

    private void startActivity() {
        Intent i = new Intent(getActivity(), PaymentHistory.class);
        startActivity(i);
    }

}