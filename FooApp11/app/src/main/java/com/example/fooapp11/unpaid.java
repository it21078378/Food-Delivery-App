package com.example.fooapp11;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooapp11.adapters.paymentUnpaidRV;
import com.example.fooapp11.model.UnpaidModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class unpaid extends Fragment {

    RecyclerView rv;
    paymentUnpaidRV unpaidAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unpaid, container, false);

        rv = view.findViewById(R.id.unpaidRV);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String uid = currentFirebaseUser.getUid();

        FirebaseRecyclerOptions<UnpaidModel> option=
                new FirebaseRecyclerOptions.Builder<UnpaidModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("unpaidDetails").orderByChild("Uid").startAt(uid).endAt(uid+"~"), UnpaidModel.class)
                        .build();

        unpaidAdapter = new paymentUnpaidRV(option);
        rv.setAdapter(unpaidAdapter);




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        unpaidAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        unpaidAdapter.stopListening();
    }


}