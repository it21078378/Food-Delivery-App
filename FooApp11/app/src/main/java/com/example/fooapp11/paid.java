package com.example.fooapp11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooapp11.adapters.paymentPaidRV;
import com.example.fooapp11.model.PaidModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class paid extends Fragment {

    RecyclerView rv;
    paymentPaidRV paidAdapter;
    double test = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paid, container, false);

        rv = view.findViewById(R.id.paidRV);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();

        FirebaseRecyclerOptions<PaidModel> option=
                new FirebaseRecyclerOptions.Builder<PaidModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("paidDetails").orderByChild("uid").startAt(uid).endAt(uid+"~"), PaidModel.class)
                        .build();

        paidAdapter = new paymentPaidRV(option);
        rv.setAdapter(paidAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        paidAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        paidAdapter.stopListening();
    }
}