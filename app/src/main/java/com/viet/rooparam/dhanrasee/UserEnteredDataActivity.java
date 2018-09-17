package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import static android.os.Environment.DIRECTORY_DOCUMENTS;

public class UserEnteredDataActivity extends AppCompatActivity {

    private static String FILE = "mnt/sdcard/documents/DhanRasee/myPdfFile.pdf";

    Button confirm_button;
    Intent intent1;

    String str_name = "", str_father_name = "", str_mother_name = "", str_no_of_years = "", str_address = "", str_dob = "", str_contact_no = "",
            str_mail_id = "", str_spouce_name = "", str_dom = "", str_residence_type = "", str_marital_status = "", str_occupation = "",
            str_firm_name = "", str_department = "", str_designation = "", str_doe = "", str_doj = "", str_official_contact_no = "",
            str_official_mail_id = "";

    int flag;

    TextView yourname, fathername, mothername, noofyears, adr, d_o_b, contactno, mailid, spoucename, d_o_m, residence, maritalstatus,
            occupation_name, nameoffirm, nameofdepartment, designation_name, dateofjoining, dateofestablishment, officialcontact_no,
            officialmail_id, identity_aadhar, identity_voter_id, identity_license, identity_pan_card, address_aadhar, address_voter,
            address_license, address_gas_bill, address_elctricity_bill, address_ration_card, income_itr, income_salary_slip,
            addi_atts_banking_statement, addi_atts_firm_reg, addi_atts_vehicle_rc, addi_atts_property_paper, addi_atts_gold_jwrllery,
            addi_atts_vehicle_noc;

    ArrayList<Uri> photo = new ArrayList<>();

    Uri uri_gallery, uri_aadhar_front,
            uri_pan,
            uri_license,
            uri_voter,
            uri_aadhar_back,
            uri_electricity,
            uri_gas,
            uri_ration,
            uri_itr,
            uri_salary,
            uri_bank,
            uri_firm,
            uri_vehicle;

    String[] uri_string = new String[18];

    String[] image_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entered_data);

        yourname = findViewById(R.id.person_name);
        fathername = findViewById(R.id.father_name);
        mothername = findViewById(R.id.mother_name);
        residence = findViewById(R.id.residence_type);
        noofyears = findViewById(R.id.no_of_years);
        adr = findViewById(R.id.address);
        d_o_b = findViewById(R.id.DOB);
        contactno = findViewById(R.id.contact_no);
        mailid = findViewById(R.id.email_id);
        maritalstatus = findViewById(R.id.marital_status);
        spoucename = findViewById(R.id.spouse_name);
        d_o_m = findViewById(R.id.DOM);
        occupation_name = findViewById(R.id.occupation);
        nameoffirm = findViewById(R.id.name_of_firm);
        nameofdepartment = findViewById(R.id.name_of_department);
        designation_name = findViewById(R.id.designation);
        dateofestablishment = findViewById(R.id.DOE);
        dateofjoining = findViewById(R.id.DOJ);
        officialcontact_no = findViewById(R.id.official_contact_no);
        officialmail_id = findViewById(R.id.official_email_id);
        identity_aadhar = findViewById(R.id.aadhar_front);
        identity_pan_card = findViewById(R.id.pan_card);
        identity_license = findViewById(R.id.driving_license);
        identity_voter_id = findViewById(R.id.voter_id);
        address_aadhar = findViewById(R.id.aadhar_back);
        address_gas_bill = findViewById(R.id.gas_bill);
        address_license = findViewById(R.id.driving_license_address_proof);
        address_voter = findViewById(R.id.voter_id_address);
        address_elctricity_bill = findViewById(R.id.electricity_bill);
        address_ration_card = findViewById(R.id.ration_card);
        income_itr = findViewById(R.id.itr_slip);
        income_salary_slip = findViewById(R.id.salary_slip);
        addi_atts_banking_statement = findViewById(R.id.banking_slip);
        addi_atts_firm_reg = findViewById(R.id.firm_registration);
        addi_atts_vehicle_rc = findViewById(R.id.vehicle_rc);
        addi_atts_property_paper = findViewById(R.id.property_paper);
        addi_atts_gold_jwrllery = findViewById(R.id.gold_jewelery);
        addi_atts_vehicle_noc = findViewById(R.id.vechile_noc);
        confirm_button = findViewById(R.id.confirm_button);

        intent1 = getIntent();

        if (getIntent() != null) {

            str_name = getIntent().getStringExtra("name");
            str_father_name = getIntent().getStringExtra("father_name");
            str_mother_name = getIntent().getStringExtra("mother_name");
            str_residence_type = getIntent().getStringExtra("residence_type");
            str_no_of_years = getIntent().getStringExtra("no_of_years");
            str_contact_no = getIntent().getStringExtra("contact_no");
            str_address = getIntent().getStringExtra("address");
            str_dob = getIntent().getStringExtra("date_of_birth");
            str_mail_id = getIntent().getStringExtra("email_id");
            str_marital_status = getIntent().getStringExtra("marital_status");
            str_spouce_name = getIntent().getStringExtra("spouce_name");
            str_dom = getIntent().getStringExtra("date_of_marriage");
            str_occupation = getIntent().getStringExtra("occupation");
            str_firm_name = getIntent().getStringExtra("firm_name");
            str_department = getIntent().getStringExtra("department");
            str_designation = getIntent().getStringExtra("designation");
            str_doe = getIntent().getStringExtra("date_of_establishment");
            str_doj = getIntent().getStringExtra("date_of_joining");
            str_official_contact_no = getIntent().getStringExtra("official_contact_no");
            str_official_mail_id = getIntent().getStringExtra("official_mail_id");
            flag = getIntent().getExtras().getInt("flag");
            image_name = getIntent().getStringArrayExtra("images");
            uri_gallery = (Uri) getIntent().getExtras().get("URi");
            uri_string = getIntent().getStringArrayExtra("uri_string");


            yourname.setText(str_name);
            fathername.setText(str_father_name);
            mothername.setText(str_mother_name);
            residence.setText(str_residence_type);
            noofyears.setText(str_no_of_years);
            contactno.setText(str_contact_no);
            residence.setText(str_residence_type);
            adr.setText(str_address);
            d_o_b.setText(str_dob);
            mailid.setText(str_mail_id);
            maritalstatus.setText(str_marital_status);
            spoucename.setText(str_spouce_name);
            d_o_m.setText(str_dom);
            occupation_name.setText(str_occupation);
            nameoffirm.setText(str_firm_name);
            nameofdepartment.setText(str_department);
            designation_name.setText(str_designation);
            dateofestablishment.setText(str_doe);
            dateofjoining.setText(str_doj);
            officialcontact_no.setText(str_official_contact_no);
            officialmail_id.setText(str_official_mail_id);

            Log.d("data in intent", str_residence_type);
        }

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isStoragePermissionGranted();
                sharePdf();

            }
        });
        // add permission in your manifest...

    }

    public void sharePdf() {
        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOCUMENTS), "DhanRasee");

        if (!pdfDir.exists())
            pdfDir.mkdirs();

        File pdfFile = new File(pdfDir, "myPdfFile.pdf");

        printData();

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
//                        getApplicationContext().getPackageName() + ".my.package.name.provider", new File(pdfDir,  "pdfFileName"));
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.setDataAndType(uri, "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);

        Uri[] uris = new Uri[17];

        try {
            for (int j = 0; j <= 17; j++) {
                uris[j] = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                        getApplicationContext().getPackageName() + ".my.package.name.provider", new File(image_name[j]));

                photo.set(0, uris[j]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        String aadhar_front_add = "mnt/sdcard/documents/DhanRasee/" + "Aadhar(Front)" + ".jpg";
        String pan_add = "mnt/sdcard/documents/DhanRasee/" + "PAN Card" + ".jpg";
        String license_add = "mnt/sdcard/documents/DhanRasee/" + "Driving License" + ".jpg";
        String voter_add = "mnt/sdcard/documents/DhanRasee/" + "Voter ID Card" + ".jpg";
        String aadhar_back_add = "mnt/sdcard/documents/DhanRasee/" + "Aadhar(BACK)" + ".jpg";
        String electricity_add = "mnt/sdcard/documents/DhanRasee/" + "Electricity Bill(Owned or Ancestral)" + ".jpg";
        String gas_add = "mnt/sdcard/documents/DhanRasee/" + "Gas Bill(Owned or Ancestral)" + ".jpg";
        String ration_add = "mnt/sdcard/documents/DhanRasee/" + "Ration Card(By Self Name)" + ".jpg";
        String itr_add = "mnt/sdcard/documents/DhanRasee/" + "3 Years of ITR" + ".jpg";
        String salary_add = "mnt/sdcard/documents/DhanRasee/" + "3 Months of Salary Slips" + ".jpg";
        String bank_add = "mnt/sdcard/documents/DhanRasee/" + "1 Year Banking" + ".jpg";
        String firm_add = "mnt/sdcard/documents/DhanRasee/" + "Firm Registration" + ".jpg";
        String vehicle_add = "mnt/sdcard/documents/DhanRasee/" + "Vehicle RC" + ".jpg";

        Intent email = new Intent(Intent.ACTION_SEND_MULTIPLE);
        Uri uri = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                getApplicationContext().getPackageName() + ".my.package.name.provider", new File(FILE));
        try {
            uri_aadhar_front = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(aadhar_front_add));
            uri_pan = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(pan_add));
            uri_license = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(license_add));
            uri_voter = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(voter_add));
            uri_aadhar_back = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(aadhar_back_add));
            uri_electricity = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(electricity_add));
            uri_gas = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(gas_add));
            uri_ration = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(ration_add));
            uri_itr = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(itr_add));
            uri_salary = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(salary_add));
            uri_bank = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(bank_add));
            uri_firm = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(firm_add));
            uri_vehicle = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                    getApplicationContext().getPackageName() + ".my.package.name.provider", new File(vehicle_add));
        } catch (Exception e) {
            e.printStackTrace();
        }

        photo.add(uri);
        try {
            photo.add(uri_aadhar_front);
            photo.add(uri_pan);
            photo.add(uri_license);
            photo.add(uri_voter);
            photo.add(uri_aadhar_back);
            photo.add(uri_electricity);
            photo.add(uri_gas);
            photo.add(uri_ration);
            photo.add(uri_itr);
            photo.add(uri_salary);
            photo.add(uri_bank);
            photo.add(uri_firm);
            photo.add(uri_vehicle);

            photo.addAll(Arrays.asList(uri_array).subList(0, 19));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        email.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
        email.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker"));
        email.setType("application/pdf");
        email.putExtra(Intent.EXTRA_STREAM, photo);
        email.putExtra("jid", PhoneNumberUtils.stripSeparators("918890233290") + "@s.whatsapp.net");
        email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(email);

    }

    public void printData() {
        String state = Environment.getExternalStorageState();

        if (!Environment.getExternalStorageState().equals(state))
            Toast.makeText(this, "Give Storage Permissions", Toast.LENGTH_LONG).show();

        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "DhanRasee");

        if (!pdfDir.exists())
            pdfDir.mkdirs();

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        ConstraintLayout root = (ConstraintLayout) inflater.inflate(R.layout.activity_user_entered_data, null); //RelativeLayout is root view of my UI(xml) file.

        root.setDrawingCacheEnabled(true);

        ScrollView view = findViewById(R.id.scroll_view);

        int scroll_height = view.getChildAt(0).getHeight();
        int scroll_width = view.getChildAt(0).getWidth();

        Bitmap screen = getBitmapFromView(view, view.getChildAt(0).getHeight(), view.getChildAt(0).getWidth()); // here give id of our root layout (here its my RelativeLayout's id)


        File pdfFile = new File(pdfDir, "myPdfFile.pdf");


        try {

            Rectangle rectangle_size = new Rectangle(scroll_width, scroll_height);

            Document document = new Document(rectangle_size);

            Rectangle rectangle = document.getPageSize();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 100, stream);

            Image image = Image.getInstance(stream.toByteArray());

//            if(screen.getWidth()>rectangle.getWidth()||screen.getHeight()>rectangle.getHeight()){
//                image.scaleToFit(rectangle.getWidth(),rectangle.getHeight());
//            }else {
//                image.scaleToFit(screen.getWidth(),screen.getHeight());
//            }
//            image.setAbsolutePosition(0, 0);

            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();


            float scale_width = ((document.getPageSize().getHeight() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getHeight()) * 100;

            image.scalePercent(scale_width);
            image.setAlignment(Image.ALIGN_CENTER);

            document.add(image);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view, int height, int width) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private static void addImage(Document document, byte[] byteArray) {
        Image image = null;
        try {
            image = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission granted", "Permission is granted");
                return true;
            } else {

                Log.v("permission denied", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission already", "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("permission granted", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
            sharePdf();
        }
    }

    public void documentName(String document_name) {

    }
}