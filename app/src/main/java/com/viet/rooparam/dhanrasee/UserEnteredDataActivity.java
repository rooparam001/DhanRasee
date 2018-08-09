package com.viet.rooparam.dhanrasee;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class UserEnteredDataActivity extends AppCompatActivity {

    private static String FILE = "mnt/sdcard/invoice.pdf";

    Button confirm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_entered_data);

        confirm_button = findViewById(R.id.confirm_button);


        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "DhanRasee");

                if (!pdfDir.exists())
                    pdfDir.mkdir();

                File pdfFile = new File(pdfDir, "myPdfFile");

                printData();

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
//                        getApplicationContext().getPackageName() + ".my.package.name.provider", new File(pdfDir,  "pdfFileName"));
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.setDataAndType(uri, "application/pdf");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                startActivity(intent);

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, "u.lakhani10@gmail.com");
                email.putExtra(Intent.EXTRA_SUBJECT, "subject");
                email.putExtra(Intent.EXTRA_TEXT, "email body");
                Uri uri = FileProvider.getUriForFile(UserEnteredDataActivity.this, UserEnteredDataActivity.this.
                        getApplicationContext().getPackageName() + ".my.package.name.provider", new File(pdfDir,  "myPdfFile"));
                email.putExtra(Intent.EXTRA_STREAM, uri);
                email.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                email.setType("application/pdf");
                email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(email);

            }
        });

        // add permission in your manifest...


    }


    public void printData() {
        String state = Environment.getExternalStorageState();

        if (!Environment.getExternalStorageState().equals(state))
            Toast.makeText(this, "Give Storage Permissions", Toast.LENGTH_LONG).show();

        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "DhanRasee");

        if (!pdfDir.exists())
            pdfDir.mkdir();

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        RelativeLayout root = (RelativeLayout) inflater.inflate(R.layout.activity_user_entered_data, null); //RelativeLayout is root view of my UI(xml) file.

        root.setDrawingCacheEnabled(true);

        RelativeLayout relativeLayout = findViewById(R.id.scroll_data);

        ScrollView view = findViewById(R.id.scroll_view);

        Bitmap screen = getBitmapFromView(view, view.getChildAt(0).getHeight(), view.getChildAt(0).getWidth()); // here give id of our root layout (here its my RelativeLayout's id)


        File pdfFile = new File(pdfDir, "myPdfFile.pdf");


        try {


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 100, stream);

            Image image = Image.getInstance(stream.toByteArray());

//            image.setAbsolutePosition(0, 0);

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();


            float scale_width = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100;

            float scale_height = ((document.getPageSize().getHeight() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getHeight()) * 400;

            image.scalePercent(scale_width);
            image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);

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
}
