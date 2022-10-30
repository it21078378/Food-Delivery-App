package com.example.fooapp11;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyAccountFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        Button startActivity = (Button) view.findViewById(R.id.paymentHISTORY);
        Button startActivity2 = (Button) view.findViewById(R.id.add);
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
        return view;
    }

    private void startActivity2() {
        Intent i = new Intent(getActivity(), AddProduct.class);
        startActivity(i);
    }

    private void startActivity() {
        Intent i = new Intent(getActivity(), PaymentHistory.class);
        startActivity(i);
    }

}