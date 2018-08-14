package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;
import static android.support.v4.media.MediaBrowserServiceCompat.RESULT_OK;


public class ImageUploadFragment extends Fragment {

    Button front_aadhar_button,submit_button;

    ImageView image;

    String str_name = "", str_father_name = "", str_mother_name = "", str_no_of_years = "", str_address = "", str_dob = "", str_contact_no = "",
            str_mail_id = "", str_spouce_name = "", str_dom = "", str_residence_type = "", str_marital_status = "", str_occupation = "",
            str_firm_name = "", str_department = "", str_designation = "", str_doe = "", str_doj = "", str_official_contact_no = "",
            str_official_mail_id = "";

    int flag;

    public static final int CAMERA_REQUEST = 1888;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            str_name = getArguments().getString("name");
            str_father_name = getArguments().getString("father_name");
            str_mother_name = getArguments().getString("mother_name");
            str_residence_type = getArguments().getString("residence_type");
            str_no_of_years = getArguments().getString("no_of_years");
            str_contact_no = getArguments().getString("contact_no");
            str_address = getArguments().getString("address");
            str_dob = getArguments().getString("date_of_birth");
            str_mail_id = getArguments().getString("email_id");
            str_marital_status = getArguments().getString("marital_status");
            str_spouce_name = getArguments().getString("spouce_name");
            str_dom = getArguments().getString("date_of_marriage");
            str_occupation = getArguments().getString("occupation");
            flag = getArguments().getInt("flag");
            if (flag == 0) {
                str_firm_name = getArguments().getString("firm_name");
            } else if (flag == 1) {
                str_department = getArguments().getString("department");
            }
            str_designation = getArguments().getString("designation");
            if (flag == 0) {
                str_doe = getArguments().getString("date_of_establishment");
            } else if (flag == 1) {
                str_doj = getArguments().getString("date_of_joining");
            }
            str_official_contact_no = getArguments().getString("official_contact_no");
            str_official_mail_id = getArguments().getString("official_mail_id");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_upload, container, false);

        submit_button = view.findViewById(R.id.submit_button);
        front_aadhar_button = view.findViewById(R.id.front_aadhar_button);


        front_aadhar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkSelfPermission(getActivity(),Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(),UserEnteredDataActivity.class);
                intent1.putExtra("name", str_name);
                intent1.putExtra("father_name", str_father_name);
                intent1.putExtra("mother_name", str_mother_name);
                intent1.putExtra("residence_type", str_residence_type);
                intent1.putExtra("no_of_years", str_no_of_years);
                intent1.putExtra("contact_no", str_contact_no);
                intent1.putExtra("address", str_address);
                intent1.putExtra("date_of_birth", str_dob);
                intent1.putExtra("email_id", str_mail_id);
                intent1.putExtra("marital_status", str_marital_status);
                intent1.putExtra("spouce_name", str_spouce_name);
                intent1.putExtra("date_of_marriage", str_dom);
                intent1.putExtra("occupation", str_occupation);
                intent1.putExtra("firm_name", str_firm_name);
                intent1.putExtra("department", str_department);
                intent1.putExtra("designation", str_designation);
                intent1.putExtra("date_of_establishment", str_doe);
                intent1.putExtra("date_of_joining", str_doj);
                intent1.putExtra("official_contact_no", str_official_contact_no);
                intent1.putExtra("official_mail_id", str_official_mail_id);
                intent1.putExtra("flag",flag);
                startActivity(intent1);
            }
        });


        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }


    }
    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
        }
    }
}


