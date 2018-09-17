package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.viet.rooparam.dhanrasee.ImageUploadAdapter.CAPTURE_IMAGE_REQUEST;

public class DataFillingActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;
    Intent intent, intent1;
    String loan_category;
    public static final int MY_PHONE_PERMISSION_CODE = 100;

    File photoDir;

    Bitmap photo;


    String[] files = {"image1.png", "image2.png", "image3.png", "image4.png", "image5.png", "image6.png", "image7.png", "image8.png", "image9.png", "image10.png",
            "image11.png", "image12.png", "image13.png", "image14.png", "image15.png", "image16.png", "image17.png"};


    ImageUploadAdapter imageUploadAdapter;

    Bitmap currentImage;

    public static final int CAMERA_REQUEST = 1888;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;
    public static final int RESULT_GALLERY = 30;


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
        //ft.add(R.id.personal_detail_fragment,detailFragment);
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

    @Override
    public void onActivityResult(int resultCode, int requestCode, Intent data){

        Log.d("ResultCode", "onActivityResult: " + resultCode);

        Fragment fragment = new ImageUploadFragment();
        fragment.onActivityResult(resultCode,resultCode,data);
    }
    
}