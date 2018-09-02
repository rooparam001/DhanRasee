package com.viet.rooparam.dhanrasee;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class DataFillingActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;
    Intent intent;
    String loan_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_filling);

        frameLayout = findViewById(R.id.data_frame);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        if(getIntent()!= null)
        {
            loan_category = getIntent().getStringExtra("loan_category");
        }
        getSupportActionBar().setTitle(R.string.personal_detail);

        PersonalDetailFragment detailFragment = new PersonalDetailFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("loan_category",loan_category);
        detailFragment.setArguments(bundle);
        ft.replace(R.id.data_frame,detailFragment);
        ft.commit();

    }
}
