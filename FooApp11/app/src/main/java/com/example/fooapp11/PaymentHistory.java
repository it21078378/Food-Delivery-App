package com.example.fooapp11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.fooapp11.adapters.paymentHistory;
import com.google.android.material.tabs.TabLayout;

public class PaymentHistory extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPage2;
    paymentHistory phAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        tabLayout = findViewById(R.id.paymentHistory);
        viewPage2 = findViewById(R.id.view_pager);
        phAdapter = new paymentHistory(this);
        viewPage2.setAdapter(phAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPage2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPage2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}