package com.viet.rooparam.dhanrasee;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PersonalDetailFragment extends Fragment {

    Button next_button;
    EditText yourname, fathername, mothername, adr, d_o_b, contactno, mailid, spoucename, d_o_m;
    Spinner noofyears;
    RadioGroup residence, maritalstatus, occupation_name;
    RadioButton owened, ancstl, rntd, mried, unmrid, divorc, salari, busin, other;

    String str_name = "", str_father_name = "", str_mother_name = "", str_no_of_years = "", str_address = "", str_dob = "", str_contact_no = "",
            str_mail_id = "", str_spouce_name = "", str_dom = "", str_residence_type = "", str_marital_status = "", str_occupation = "";

    String item[] = {"No of Years", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    String loan_category;
    int married_flag = 0, j = 0;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    FragmentManager fragmentManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loan_category = getArguments().getString("loan_category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_detail, container, false);

        ((DataFillingActivity) getActivity()).getSupportActionBar().setTitle("Personal Details");

        next_button = view.findViewById(R.id.next_button);
        yourname = view.findViewById(R.id.et_name);
        fathername = view.findViewById(R.id.et_father_name);
        mothername = view.findViewById(R.id.et_mother_name);
        noofyears = view.findViewById(R.id.no_of_years);
        adr = view.findViewById(R.id.et_address);
        d_o_b = view.findViewById(R.id.et_dob);
        contactno = view.findViewById(R.id.et_contact_no);
        mailid = view.findViewById(R.id.et_mail_id);
        spoucename = view.findViewById(R.id.et_spouse_name);
        d_o_m = view.findViewById(R.id.et_dom);

        spoucename.setVisibility(View.GONE);
        d_o_m.setVisibility(View.GONE);

        residence = (RadioGroup) view.findViewById(R.id.rg_residence_type);
        maritalstatus = (RadioGroup) view.findViewById(R.id.rg_married_status);
        occupation_name = (RadioGroup) view.findViewById(R.id.rg_occupation);

        owened = (RadioButton) view.findViewById(R.id.owned_rb);
        ancstl = (RadioButton) view.findViewById(R.id.ancestral_rb);
        rntd = (RadioButton) view.findViewById(R.id.rented_rb);
        mried = (RadioButton) view.findViewById(R.id.married_rb);
        unmrid = (RadioButton) view.findViewById(R.id.unmarried_rb);
        divorc = (RadioButton) view.findViewById(R.id.divorcee_rb);
        salari = (RadioButton) view.findViewById(R.id.salaried_rb);
        busin = (RadioButton) view.findViewById(R.id.business_rb);
        other = (RadioButton) view.findViewById(R.id.others_rb);

        residence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (owened.isChecked()) {
                    str_residence_type = "Owened";
                } else if (ancstl.isChecked()) {
                    str_residence_type = "ancestral";
                } else if (rntd.isChecked()) {
                    str_residence_type = "rented";
                }
            }
        });

        maritalstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (mried.isChecked()) {
                    str_marital_status = "Married";
                    spoucename.setVisibility(View.VISIBLE);
                    d_o_m.setVisibility(View.VISIBLE);
                    married_flag = 1;
                } else if (unmrid.isChecked()) {
                    str_marital_status = "Unmarried";
                    spoucename.setVisibility(View.GONE);
                    d_o_m.setVisibility(View.GONE);
                    married_flag = 0;
                } else {
                    str_marital_status = "Divorcee";
                    spoucename.setVisibility(View.VISIBLE);
                    d_o_m.setVisibility(View.VISIBLE);
                    married_flag = 1;
                }
            }
        });

        occupation_name.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (salari.isChecked()) {
                    str_occupation = "Salaried";
                } else if (busin.isChecked()) {
                    str_occupation = "Business";
                } else if (other.isChecked()) {
                    str_occupation = "Others";
                }
            }
        });

        myCalendar = Calendar.getInstance();


        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        d_o_b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                j = 0;
            }
        });

        d_o_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                j = 1;
            }
        });



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, item);

        noofyears.setAdapter(arrayAdapter);

        noofyears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                str_no_of_years = noofyears.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (yourname.getText().toString().equalsIgnoreCase("")) {
                    yourname.setError("Enter Name");
                } else if (fathername.getText().toString().equalsIgnoreCase("")) {
                    fathername.setError("Enter Father Name");
                } else if (mothername.getText().toString().equalsIgnoreCase("")) {
                    mothername.setError("Enter Mother Name");
                } else if (str_residence_type.equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                } else if (str_no_of_years.equalsIgnoreCase("") && str_no_of_years.equalsIgnoreCase("No of Years")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                } else if (adr.getText().toString().equalsIgnoreCase("")) {
                    adr.setError("Enter Address");
                } else if (d_o_b.getText().toString().equalsIgnoreCase("")) {
                    d_o_b.setBackgroundResource(R.drawable.blank_bg);
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                } else if (contactno.getText().toString().equalsIgnoreCase("")) {
                    contactno.setError("Enter Contact Number");
                } else if (mailid.getText().toString().equalsIgnoreCase("")) {
                    mailid.setError("Enter Email ID");
                } else if (married_flag == 1) {
                    if (str_marital_status.equalsIgnoreCase("")) {
                        Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }else if (spoucename.getText().toString().equalsIgnoreCase("")) {
                        spoucename.setError("Enter Spouce Name");
                    } else if (d_o_m.getText().toString().equalsIgnoreCase("")) {
                        d_o_m.setBackgroundResource(R.drawable.blank_bg);
                        Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                } else if (str_occupation.equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                } else {

                    str_name = yourname.getText().toString();
                    str_father_name = fathername.getText().toString();
                    str_mother_name = mothername.getText().toString();
                    str_address = adr.getText().toString();
                    str_dob = d_o_b.getText().toString();
                    str_contact_no = contactno.getText().toString();
                    str_mail_id = mailid.getText().toString();
                    str_spouce_name = spoucename.getText().toString();
                    str_dom = d_o_m.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("name", str_name);
                    bundle.putString("father_name", str_father_name);
                    bundle.putString("mother_name", str_mother_name);
                    bundle.putString("residence_type", str_residence_type);
                    bundle.putString("no_of_years", str_no_of_years);
                    bundle.putString("contact_no", str_contact_no);
                    bundle.putString("address", str_address);
                    bundle.putString("date_of_birth", str_dob);
                    bundle.putString("email_id", str_mail_id);
                    bundle.putString("marital_status", str_marital_status);
                    bundle.putString("spouce_name", str_spouce_name);
                    bundle.putString("date_of_marriage", str_dom);
                    bundle.putString("occupation", str_occupation);
                    bundle.putString("loan_category", loan_category);

                    OfficialDetailFragment officialDetailFragment = new OfficialDetailFragment();
                    FragmentTransaction ftOfficial = getFragmentManager().beginTransaction();
                    officialDetailFragment.setArguments(bundle);
                    ftOfficial.replace(R.id.data_frame, officialDetailFragment);
                    ftOfficial.commit();

                }
            }
        });
        return view;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (j == 0) {
            d_o_b.setText(sdf.format(myCalendar.getTime()));
        } else if (j == 1) {
            d_o_m.setText(sdf.format(myCalendar.getTime()));
        }
    }


}