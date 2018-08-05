package com.viet.rooparam.dhanrasee;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class DataFillingActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_filling);

        frameLayout = findViewById(R.id.data_frame);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.personal_detail);

        PersonalDetailFragment detailFragment = new PersonalDetailFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.data_frame,detailFragment);

    }
}
