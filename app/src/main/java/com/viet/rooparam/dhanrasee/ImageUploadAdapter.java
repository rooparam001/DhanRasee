package com.viet.rooparam.dhanrasee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

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


    public ImageUploadAdapter(Context imageUploadFragment, List<String> listDataHeader, HashMap<String, List<String>> listDataChild, int i, String loan_category, String str_occupation) {
        this._context = imageUploadFragment;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;
        this.i = i;
        this.loan_name = loan_category;
        this.occupation_name = str_occupation;
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
        }
        catch(Exception e)
        {
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

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.child_image_text);
        Log.d("i ki value",i+"");

        if (!(txtListChild == null))
            txtListChild.setText(childText);


        ImageView child_image_camera = convertView.findViewById(R.id.child_image_camera);

        if (!(child_image_camera == null)) {
            child_image_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (groupPosition == 0 && childPosition == 0) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent, 0);

                    } else if (groupPosition == 0 && childPosition == 1) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                1);
                    } else if (groupPosition == 0 && childPosition == 2) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                2);
                    } else if (groupPosition == 0 && childPosition == 3) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                3);
                    } else if (groupPosition == 1 && childPosition == 0) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                4);
                    } else if (groupPosition == 1 && childPosition == 1) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                5);
                    } else if (groupPosition == 1 && childPosition == 2) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                6);
                    } else if (groupPosition == 1 && childPosition == 3) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                7);
                    } else if (groupPosition == 1 && childPosition == 4) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                8);
                    } else if (groupPosition == 1 && childPosition == 5) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                9);
                    } else if (groupPosition == 2 && childPosition == 0) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                10);
                    } else if (groupPosition == 2 && childPosition == 1) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                11);
                    } else if (groupPosition == 3 && childPosition == 0) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                12);
                    } else if (groupPosition == 3 && childPosition == 1) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                13);
                    } else if (groupPosition == 3 && childPosition == 2) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                14);
                    } else if (groupPosition == 3 && childPosition == 3) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                15);
                    } else if (groupPosition == 3 && childPosition == 4) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                16);
                    } else if (groupPosition == 3 && childPosition == 5) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity) _context).startActivityForResult(intent,
                                17);
                    }

                }
            });
        }

        ImageView child_image_gallery = convertView.findViewById(R.id.child_image_gallery);

        if (!(child_image_gallery == null)) {
            child_image_gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (groupPosition == 0 && childPosition == 0) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);

                    } else if (groupPosition == 0 && childPosition == 1) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 0 && childPosition == 2) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 0 && childPosition == 3) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 1 && childPosition == 0) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 1 && childPosition == 1) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 1 && childPosition == 2) {

                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 1 && childPosition == 3) {

                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 1 && childPosition == 4) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 1 && childPosition == 5) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 2 && childPosition == 0) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 2 && childPosition == 1) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 3 && childPosition == 0) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 3 && childPosition == 1) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 3 && childPosition == 2) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 3 && childPosition == 3) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 3 && childPosition == 4) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
                    } else if (groupPosition == 3 && childPosition == 5) {
                        Intent galleryIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity) _context).startActivityForResult(galleryIntent, RESULT_GALLERY);
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
            if ((headerTitle.equalsIgnoreCase("Proof Of Income")) ) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
            if ((headerTitle.equalsIgnoreCase("Additional Attachments"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("New Purchase") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("New Purchase") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Construction") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Construction") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Mortgage") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Mortgage") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Vehicle RC"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("2 Wheeler New") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("2 Wheeler New") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("Vehicle RC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("4 Wheeler New") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("4 Wheeler New") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("Vehicle RC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Commercial New") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Vehicle RC")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Commercial New") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("Vehicle RC"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("2 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("2 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Property Papers"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("4 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("4 Wheeler New Refinance") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Property Papers"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Commercial New Refinance") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Gold Jewelery"))
                    || (childText.equalsIgnoreCase("Property Papers"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Commercial New Refinance") && occupation_name.equalsIgnoreCase("Business")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                    || (childText.equalsIgnoreCase("3 Months of Salary Slips")) || (childText.equalsIgnoreCase("Firm Registration"))
                    || (childText.equalsIgnoreCase("Gold Jewelery")) || (childText.equalsIgnoreCase("Property Papers"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Gold Loan") && occupation_name.equalsIgnoreCase("Salaried")) {
            if ((i == 0 && childText.equalsIgnoreCase("Aadhar(FRONT)")) || (i == 0 && childText.equalsIgnoreCase("Aadhar(BACK)"))
                     || (childText.equalsIgnoreCase("Firm Registration")) || (childText.equalsIgnoreCase("Vehicle NOC"))
                     || (childText.equalsIgnoreCase("Property Papers")) || (childText.equalsIgnoreCase("Vehicle RC"))
                     || (childText.equalsIgnoreCase("1 Year Banking"))|| (childText.equalsIgnoreCase("3 Years of ITR"))) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.null_layout, null);
            }
        }

        else if (loan_name.equalsIgnoreCase("Gold Loan") && occupation_name.equalsIgnoreCase("Business")) {
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
}