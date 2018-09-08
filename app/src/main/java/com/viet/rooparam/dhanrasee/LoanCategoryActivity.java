package com.viet.rooparam.dhanrasee;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.viet.rooparam.dhanrasee.LoginActivity.MY_PHONE_PERMISSION_CODE;

public class LoanCategoryActivity extends AppCompatActivity {

    LoanCategoryAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    EditText loanamount;
    String username,password;
    Toolbar toolbar;
    Intent intent;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_category);

        loanamount = findViewById(R.id.min_loan);
        expListView = (ExpandableListView) findViewById(R.id.expandable_loan_list);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.select_loan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prepareListData();

        listAdapter = new LoanCategoryAdapter(this, listDataHeader, listDataChild);


        intent = getIntent();
        if(getIntent() != null)
        {
            username = getIntent().getStringExtra("username");
            password = getIntent().getStringExtra("password");
        }

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setGroupIndicator(null);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == 0 || groupPosition == 1 || groupPosition == 2 || groupPosition == 5)
                {
                    Intent intent = new Intent(LoanCategoryActivity.this, DataFillingActivity.class);
                    intent.putExtra("loan_category", listDataHeader.get(groupPosition));
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                String key = listDataHeader.get(groupPosition);
                String value = listDataChild.get(key).get(childPosition);


                String loan = loanamount.getText().toString();


                Intent intent1 = new Intent(LoanCategoryActivity.this, DataFillingActivity.class);

                intent1.putExtra("loan_category", value);

                startActivity(intent1);
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getString(R.string.personal_loan));
        listDataHeader.add(getString(R.string.business_loan));
        listDataHeader.add(getString(R.string.daily_loan));
        listDataHeader.add(getString(R.string.home));
        listDataHeader.add(getString(R.string.vehicle_loan));
        listDataHeader.add(getString(R.string.gold_loan));

        List<String> personalLoan = new ArrayList<>();
        List<String> businessLoan = new ArrayList<>();
        List<String> dailyLoan = new ArrayList<>();
        List<String> goldLoan = new ArrayList<>();

        // Adding child data
        List<String> home = new ArrayList<String>();
        home.add(getString(R.string.new_purchase));
        home.add(getString(R.string.construction));
        home.add(getString(R.string.mortgage));

        List<String> vehicle = new ArrayList<String>();
        vehicle.add(getString(R.string.two_wheeler));
        vehicle.add(getString(R.string.four_wheeler));
        vehicle.add(getString(R.string.commercial_new));
        vehicle.add(getString(R.string.two_wheeler_ref));
        vehicle.add(getString(R.string.four_wheeler_ref));
        vehicle.add(getString(R.string.commercial_new_ref));

        listDataChild.put(listDataHeader.get(0), personalLoan); // Header, Child data
        listDataChild.put(listDataHeader.get(1), businessLoan);
        listDataChild.put(listDataHeader.get(2), dailyLoan);
        listDataChild.put(listDataHeader.get(3), home);
        listDataChild.put(listDataHeader.get(4), vehicle);
        listDataChild.put(listDataHeader.get(5), goldLoan);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(LoanCategoryActivity.this, LoginActivity.class);
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
        Intent intent = new Intent(LoanCategoryActivity.this, LoginActivity.class);
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
