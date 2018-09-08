package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class DataFillingActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;
    Intent intent, intent1;
    String loan_category;
    public static final int MY_PHONE_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_filling);

        toolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        frameLayout = findViewById(R.id.data_frame);

        intent = getIntent();
        if (getIntent() != null) {
            loan_category = getIntent().getStringExtra("loan_category");
            Log.d("Category Loan1", loan_category);
        }

        intent1 = getIntent();
        if (getIntent() != null) {
            loan_category = getIntent().getStringExtra("loan_category");
            Log.d("Category Loan2", loan_category);
        }

        PersonalDetailFragment detailFragment = new PersonalDetailFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("loan_category", loan_category);
        detailFragment.setArguments(bundle);
        ft.replace(R.id.data_frame, detailFragment);
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(DataFillingActivity.this, LoanCategoryActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.call_button) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                            MY_PHONE_PERMISSION_CODE);
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_CALL);
                    intent1.setData(Uri.parse("tel:" + "+918963006300"));
                    startActivity(intent1);
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DataFillingActivity.this, LoanCategoryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_call, menu);
        return super.onCreateOptionsMenu(menu);
    }
}