package com.example.fooapp11.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fooapp11.paid;
import com.example.fooapp11.unpaid;

public class paymentHistory extends FragmentStateAdapter {
    public paymentHistory(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new paid();
            case 1:
                return new unpaid();
            default:
                return new paid();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
