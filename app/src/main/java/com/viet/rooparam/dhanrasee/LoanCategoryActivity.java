package com.viet.rooparam.dhanrasee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoanCategoryActivity extends AppCompatActivity {

    LoanCategoryAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView minimum,maximum;
    SeekBar loan_seekbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_category);

        loan_seekbar = findViewById(R.id.loan_seek_bar);
        minimum = findViewById(R.id.min_loan);
        maximum = findViewById(R.id.max_loan);
        expListView = (ExpandableListView) findViewById(R.id.expandable_loan_list);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.select_loan);
        prepareListData();

        listAdapter = new LoanCategoryAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        loan_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        expListView.setGroupIndicator(null);
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if(groupPosition == 0 || groupPosition == 1 || groupPosition == 2 || groupPosition == 5)
                {
                    Intent intent = new Intent(LoanCategoryActivity.this, DataFillingActivity.class);
                    startActivity(intent);
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
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(LoanCategoryActivity.this,DataFillingActivity.class);
                startActivity(intent);
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
}
