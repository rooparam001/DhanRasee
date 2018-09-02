package com.viet.rooparam.dhanrasee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class OfficialDetailFragment extends Fragment {

    Button next_btn;

    EditText firm_name, department, designation, d_o_e, d_o_j, official_contactno, official_mailid;

    String str_name = "", str_father_name = "", str_mother_name = "", str_no_of_years = "", str_address = "", str_dob = "", str_contact_no = "",
            str_mail_id = "", str_spouce_name = "", str_dom = "", str_residence_type = "", str_marital_status = "", str_occupation = "",
            str_firm_name = "", str_department = "", str_designation = "", str_doe = "", str_doj = "", str_official_contact_no = "",
            str_official_mail_id = "", loan_category;

    int flag = 0;

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
            loan_category = getArguments().getString("loan_category");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_official_detail, container, false);

        ((DataFillingActivity) getActivity()).getSupportActionBar().setTitle("Official Details");

        firm_name = view.findViewById(R.id.et_firm_name);
        department = view.findViewById(R.id.et_name_of_department);
        designation = view.findViewById(R.id.et_designation);
        d_o_e = view.findViewById(R.id.et_dob_establishment);
        d_o_j = view.findViewById(R.id.et_date_of_joining);
        official_contactno = view.findViewById(R.id.et_official_contact_no);
        official_mailid = view.findViewById(R.id.et_official_mail_id);
        next_btn = view.findViewById(R.id.official_next_button);

        if (str_occupation.equalsIgnoreCase("Salaried")) {
            firm_name.setVisibility(View.GONE);
            d_o_e.setVisibility(View.GONE);
            department.setVisibility(View.VISIBLE);
            d_o_j.setVisibility(View.VISIBLE);
            flag = 1;
        } else {
            firm_name.setVisibility(View.VISIBLE);
            d_o_e.setVisibility(View.VISIBLE);
            department.setVisibility(View.GONE);
            d_o_j.setVisibility(View.GONE);
            flag = 0;
        }

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (flag == 0) {
                    if (firm_name.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                } else if (flag == 1) {
                    if (department.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }
                if (designation.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }
                if (flag == 0) {
                    if (d_o_e.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                } else if (flag == 1) {
                    if (d_o_j.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                }
                if (official_contactno.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                } else if (official_mailid.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                } else {

                    if (flag == 0) {
                        str_firm_name = firm_name.getText().toString();
                    } else if (flag == 1) {
                        str_department = department.getText().toString();
                    }
                    str_designation = designation.getText().toString();

                    if (flag == 0) {
                        str_doe = d_o_e.getText().toString();
                    } else if (flag == 1) {
                        str_doj = d_o_j.getText().toString();
                    }
                    str_official_contact_no = official_contactno.getText().toString();
                    str_official_mail_id = official_mailid.getText().toString();

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
                    if (flag == 0) {
                        bundle.putString("firm_name", str_firm_name);
                    } else if (flag == 1) {
                        bundle.putString("department", str_department);
                    }
                    bundle.putString("designation", str_designation);
                    if (flag == 0) {
                        bundle.putString("date_of_establishment", str_doe);
                    } else if (flag == 1) {
                        bundle.putString("date_of_joining", str_doj);
                    }
                    bundle.putString("official_contact_no", str_official_contact_no);
                    bundle.putString("official_mail_id", str_official_mail_id);
                    bundle.putString("loan_category", loan_category);
                    bundle.putInt("flag", flag);

                    ImageUploadFragment imageUploadFragment = new ImageUploadFragment();
                    FragmentTransaction ftImage = getFragmentManager().beginTransaction();
                    imageUploadFragment.setArguments(bundle);
                    ftImage.replace(R.id.data_frame, imageUploadFragment);
                    ftImage.commit();
                }
            }
        });
        return view;
    }


}
