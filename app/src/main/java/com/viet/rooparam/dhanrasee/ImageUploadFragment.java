package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.mindorks.paracamera.Camera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;
import static android.support.v4.media.MediaBrowserServiceCompat.RESULT_OK;


public class ImageUploadFragment extends Fragment {

    Button submit_button;
    ImageView image;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Toolbar toolbar;

    String mCurrentPhotoPath;

    private static final String TAG = "CapturePicture";
    static final int REQUEST_PICTURE_CAPTURE = 1;

    ImageUploadAdapter imageUploadAdapter;

    Camera camera;

    ExpandableListView expandableListView;

    private static String FILE = "mnt/sdcard/documents/DhanRasee/myPdfFile.jpg";

    String uri_string[] = new String[18];

    Uri uri_gallery;



    String str_name = "", str_father_name = "", str_mother_name = "", str_no_of_years = "", str_address = "", str_dob = "", str_contact_no = "",
            str_mail_id = "", str_spouce_name = "", str_dom = "", str_residence_type = "", str_marital_status = "", str_occupation = "",
            str_firm_name = "", str_department = "", str_designation = "", str_doe = "", str_doj = "", str_official_contact_no = "",
            str_official_mail_id = "", loan_category;

    int flag, i = 1,poi,poa,adat;
    Bitmap currentImage;



    public static final int CAMERA_REQUEST = 1888;
    public static final int MY_CAMERA_PERMISSION_CODE = 100;
    public static final int RESULT_GALLERY = 30;

    ArrayList<ByteArrayOutputStream> bytestream = new ArrayList<>();
    ArrayList<Bitmap> photo = new ArrayList<>();

    String[] files = {"image1.png", "image2.png", "image3.png", "image4.png", "image5.png", "image6.png", "image7.png", "image8.png", "image9.png", "image10.png",
            "image11.png", "image12.png", "image13.png", "image14.png", "image15.png", "image16.png", "image17.png"};

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
            loan_category = getArguments().getString("loan_category");

        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_image_upload, container, false);

        ((DataFillingActivity) getActivity()).getSupportActionBar().setTitle("Upload Documents");

        prepareListData();

        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("ali_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                .build(this);


        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Image Upload")
                .setMessage("Do you have Aadhar?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        i = 1;
                        // continue with delete
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        i = 0;
                        // do nothing
                    }
                })
                .setIcon(null)
                .show();

        submit_button = view.findViewById(R.id.submit_button);

//        front_aadhar_button = view.findViewById(R.id.front_aadhar_button);

//
//        front_aadhar_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (checkSelfPermission(getActivity(),Manifest.permission.CAMERA)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA},
//                            MY_CAMERA_PERMISSION_CODE);
//                } else {
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }
//            }
//        });

        expandableListView = view.findViewById(R.id.expandable_image_upload);

        imageUploadAdapter = new ImageUploadAdapter(getActivity(), listDataHeader, listDataChild, i, loan_category, str_occupation, camera);
        expandableListView.setAdapter(imageUploadAdapter);
        expandableListView.setGroupIndicator(null);

        imageUploadAdapter.notifyDataSetChanged();
        final Uri uri = imageUploadAdapter.getUri();

        Log.d("getselected id",expandableListView.getSelectedId() + "");

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), UserEnteredDataActivity.class);
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
                intent1.putExtra("flag", flag);
                intent1.putExtra("images", files);
                intent1.putExtra("URi", uri);
                intent1.putExtra("uri_string",uri_string);
                startActivity(intent1);

                Log.d("Loan name", loan_category);
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


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getString(R.string.proof_of_identity));
        listDataHeader.add(getString(R.string.proof_of_address));
        listDataHeader.add(getString(R.string.proof_of_income));
        listDataHeader.add(getString(R.string.additional_attachments));

        // Adding child data
        List<String> proof_identity = new ArrayList<String>();
        proof_identity.add(getString(R.string.aadhar_front));
        proof_identity.add(getString(R.string.pan));
        proof_identity.add(getString(R.string.driving_license));
        proof_identity.add(getString(R.string.voter_id_card));

        List<String> proof_of_address = new ArrayList<String>();
        proof_of_address.add(getString(R.string.aadhar_back));
        proof_of_address.add(getString(R.string.driving_license));
        proof_of_address.add(getString(R.string.voter_id_card));
        proof_of_address.add(getString(R.string.electricity_bill));
        proof_of_address.add(getString(R.string.gas_bill));
        proof_of_address.add(getString(R.string.ration_card));

        List<String> proof_of_income = new ArrayList<String>();
        proof_of_income.add(getString(R.string.three_itr));
        proof_of_income.add(getString(R.string.three_salary));

        List<String> additional_attachments = new ArrayList<String>();
        additional_attachments.add(getString(R.string.one_banking));
        additional_attachments.add(getString(R.string.firm_registration));
        additional_attachments.add(getString(R.string.vehicle_rc));
        additional_attachments.add(getString(R.string.property_papers));
        additional_attachments.add(getString(R.string.gdd_jewelery));
        additional_attachments.add(getString(R.string.vehicle_noc));

        listDataChild.put(listDataHeader.get(0), proof_identity); // Header, Child data
        listDataChild.put(listDataHeader.get(1), proof_of_address);
        listDataChild.put(listDataHeader.get(2), proof_of_income);
        listDataChild.put(listDataHeader.get(3), additional_attachments);
    }

    @Override
    public void onActivityResult(int resultCode, int requestCode, Intent data){

        Log.d("RUN", "onActivityResult: RUN"+data);

        Log.d("ResultCode", "onFragmentResult: " + resultCode);

        Log.d("RequestCode", "onActivityResult: " + requestCode);


        for (int i = 0; i<18; i++){
            if(requestCode == i  && resultCode  == RESULT_OK) {
                uri_gallery = data.getData();

                uri_string[i]=uri_gallery.toString();
            }
        }
    }

}