package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.paracamera.Camera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ImageUploadAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    public static final int RESULT_GALLERY = 30;
    private int i, count = 0;
    private String loan_name, occupation_name;
    String headerTitle;
    File photoFile = null;
    static final int CAPTURE_IMAGE_REQUEST = 1;
    String mCurrentPhotoPath;
    private static final String IMAGE_DIRECTORY_NAME = "VLEMONN";
    Camera camera;
    Uri photoURI = null;

    String key, child_name;

    private static String FILE = "mnt/sdcard/documents/DhanRasee/myPdfFile.jpg";

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    public ImageUploadAdapter(Context imageUploadFragment, List<String> listDataHeader, HashMap<String, List<String>> listDataChild, int i, String loan_category, String str_occupation, Camera camera) {
        this._context = imageUploadFragment;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;
        this.i = i;
        this.loan_name = loan_category;
        this.occupation_name = str_occupation;
        this.camera = camera;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        headerTitle = (String) getGroup(groupPosition);
        try {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header_image_upload, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.image_upload_header_text);

        if (!(lblListHeader == null)) {
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
        }

//        try {
//            if (loan_name.equalsIgnoreCase("Daily Loan(only for shopkeepers)")) {
//                if ((headerTitle.equalsIgnoreCase("Proof Of Income"))) {
//                    LayoutInflater infalInflater = (LayoutInflater) this._context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    convertView = infalInflater.inflate(R.layout.null_layout, null);
//                }
//                if ((headerTitle.equalsIgnoreCase("Additional Attachments"))) {
//                    LayoutInflater infalInflater = (LayoutInflater) this._context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    convertView = infalInflater.inflate(R.layout.null_layout, null);
//                }
//            }
//
//            if (loan_name.equalsIgnoreCase("Gold Loan")) {
//                if ((headerTitle.equalsIgnoreCase("Proof Of Income"))) {
//                    LayoutInflater infalInflater = (LayoutInflater) this._context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    convertView = infalInflater.inflate(R.layout.null_layout, null);
//                }
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }


        Log.d("Loan", headerTitle);

        return convertView;

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        try {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_image_upload, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final TextView txtListChild = (TextView) convertView
                .findViewById(R.id.child_image_text);
        Log.d("i ki value", i + "");

        if (!(txtListChild == null))
            txtListChild.setText(childText);


        ImageView child_image_camera = convertView.findViewById(R.id.child_image_camera);
        final ImageView child_image_gallery = convertView.findViewById(R.id.child_image_gallery);

        if (!(child_image_camera == null)) {
            child_image_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (txtListChild.getText().toString().equalsIgnoreCase("Aadhar(FRONT)")) {
                        dispatchTakePictureIntent("Aadhar(Front)");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("PAN Card")) {
                        dispatchTakePictureIntent("PAN Card");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Driving License")) {
                        dispatchTakePictureIntent("Driving License");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Voter ID Card")) {
                        dispatchTakePictureIntent("Voter ID Card");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Aadhar(BACK)")) {
                        dispatchTakePictureIntent("Aadhar(BACK)");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Electricity Bill(Owned or Ancestral)")) {
                        dispatchTakePictureIntent("Electricity Bill(Owned or Ancestral)");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Gas Bill(Owned or Ancestral)")) {
                        dispatchTakePictureIntent("Gas Bill(Owned or Ancestral)");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Ration Card(By Self Name)")) {
                        dispatchTakePictureIntent("Ration Card(By Self Name)");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("3 Years of ITR")) {
                        dispatchTakePictureIntent("3 Years of ITR");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("3 Months of Salary Slips")) {
                        dispatchTakePictureIntent("3 Months of Salary Slips");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("1 Year Banking")) {
                        dispatchTakePictureIntent("1 Year Banking");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Firm Registration")) {
                        dispatchTakePictureIntent("Firm Registration");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Vehicle RC")) {
                        dispatchTakePictureIntent("Vehicle RC");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Property Papers")) {
                        dispatchTakePictureIntent("Property Papers");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Gold Jewelery")) {
                        dispatchTakePictureIntent("Gold Jewelery");
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Vehicle NOC")) {
                        dispatchTakePictureIntent("Vehicle NOC");
                    }
                }
            });
        }

        if (!(child_image_gallery == null)) {
            child_image_gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (txtListChild.getText().toString().equalsIgnoreCase("Aadhar(FRONT)")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 0);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("PAN Card")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 1);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Driving License")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 2);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Voter ID Card")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 3);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Aadhar(BACK)")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 4);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Electricity Bill(Owned or Ancestral)")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 5);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Gas Bill(Owned or Ancestral)")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 6);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Ration Card(By Self Name)")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 7);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("3 Years of ITR")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 8);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("3 Months of Salary Slips")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 9);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("1 Year Banking")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 10);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Firm Registration")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 11);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Vehicle RC")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 12);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Property Papers")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 13);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Gold Jewelery")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 14);
                    } else if (txtListChild.getText().toString().equalsIgnoreCase("Vehicle NOC")) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, 15);
                    }
                }
            });
        }

        if (loan_name.equalsIgnoreCase("Personal Loan(for salaried)")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }

        } else if (loan_name.equalsIgnoreCase("Business Loan(for self-employed)")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }

        } else if (loan_name.equalsIgnoreCase("Daily Loan(only for shopkeepers)")) {
            if ((headerTitle.equalsIgnoreCase("Proof Of Income"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
            if ((headerTitle.equalsIgnoreCase("Additional Attachments"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("New Purchase") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("New Purchase") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Construction") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Construction") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Mortgage") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Mortgage") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("2 Wheeler New") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("2 Wheeler New") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Vehicle RC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("4 Wheeler New") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("4 Wheeler New") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Vehicle RC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Commercial New") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Commercial New") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Vehicle RC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("2 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("2 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Property Papers"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("4 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("4 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Property Papers"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Commercial New Refinance") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Commercial New Refinance") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Property Papers"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Gold Loan") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("1 Year Banking")) || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        } else if (loan_name.equalsIgnoreCase("Gold Loan") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("3 Months of Salary Slips"))
                    || (childText.equalsIgnoreCase("Vehicle NOC")) || (childText.equalsIgnoreCase("Property Papers"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("1 Year Banking"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private void dispatchTakePictureIntentGallery(String document_name) {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Ensure that there's a camera activity to handle the intent
        if (galleryIntent.resolveActivity(_context.getPackageManager()) != null) {
            // Create the File where the photo should go
            // Continue only if the File was successfully created
            photoURI = FileProvider.getUriForFile(_context,
                    _context.
                            getApplicationContext().getPackageName() + ".my.package.name.provider",
                    new File(createImageFile(document_name)));
            ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
            Log.d("Gallery URI", "dispatchTakePictureIntentGallery: " + photoURI );
        }
    }

    private void dispatchTakePictureIntent(String document_name) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(_context.getPackageManager()) != null) {
            // Create the File where the photo should go
            // Continue only if the File was successfully created
            photoURI = FileProvider.getUriForFile(_context,
                    _context.
                            getApplicationContext().getPackageName() + ".my.package.name.provider",
                    new File(createImageFile(document_name)));
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            ((Activity) _context).startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    public Uri getUri()
    {
        return photoURI;
    }

    private String createImageFile(String document_name) {
        // Create an image file name


        String image = "mnt/sdcard/documents/DhanRasee/" + document_name + ".jpg";

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

    private void displayMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}