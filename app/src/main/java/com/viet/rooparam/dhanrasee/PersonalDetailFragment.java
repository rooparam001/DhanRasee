package com.viet.rooparam.dhanrasee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PersonalDetailFragment extends Fragment {

    Button next_button;
    EditText yourname,fathername,mothername,noofyears,adr,d_o_b,contactno,mailid,spoucename,d_o_m;
    RadioGroup residence,maritalstatus,occupation_name;
    RadioButton owened,ancstl,rntd,mried,unmrid,divorc,salari,busin,other;

    String str_name = "",str_father_name = "",str_mother_name = "",str_no_of_years = "",str_address = "",str_dob = "",str_contact_no = "",
            str_mail_id = "",str_spouce_name = "", str_dom = "",str_residence_type = "",str_marital_status = "",str_occupation = "";

    String loan_category;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loan_category = getArguments().getString("loan_category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_detail, container, false);

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

        residence = (RadioGroup) view.findViewById(R.id.rg_residence_type);
        maritalstatus = (RadioGroup) view.findViewById(R.id.rg_married_status);
        occupation_name = (RadioGroup) view.findViewById(R.id.rg_occupation);

        owened = (RadioButton)view.findViewById(R.id.owned_rb);
        ancstl = (RadioButton)view.findViewById(R.id.ancestral_rb);
        rntd = (RadioButton)view.findViewById(R.id.rented_rb);
        mried = (RadioButton)view.findViewById(R.id.married_rb);
        unmrid = (RadioButton)view.findViewById(R.id.unmarried_rb);
        divorc = (RadioButton)view.findViewById(R.id.divorcee_rb);
        salari = (RadioButton)view.findViewById(R.id.salaried_rb);
        busin = (RadioButton)view.findViewById(R.id.business_rb);
        other = (RadioButton)view.findViewById(R.id.others_rb);

        residence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == 0)
                {
                    str_residence_type = "Owened";
                }else if(i == 1)
                {
                    str_residence_type = "ancestral";
                }else
                {
                    str_residence_type = "rented";
                }
            }
        });

        maritalstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == 0)
                {
                    str_marital_status = "Married";
                }else if(i == 1)
                {
                    str_marital_status = "Unmarried";
                }else
                {
                    str_marital_status = "Divorcee";
                }
            }
        });

        occupation_name.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == 0)
                {
                    str_occupation = "Salaried";
                }else if(i == 1)
                {
                    str_occupation = "Business";
                }else
                {
                    str_occupation = "Others";
                }
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(yourname.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }
                else  if(fathername.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(mothername.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(noofyears.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(adr.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(d_o_b.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(contactno.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(mailid.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(spoucename.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }else  if(d_o_m.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Enter Details", Toast.LENGTH_SHORT).show();
                }
                else {

                    str_name = yourname.getText().toString();
                    str_father_name = fathername.getText().toString();
                    str_mother_name = mothername.getText().toString();
                    str_no_of_years = noofyears.getText().toString();
                    str_address = adr.getText().toString();
                    str_dob = d_o_b.getText().toString();
                    str_contact_no = contactno.getText().toString();
                    str_mail_id = mailid.getText().toString();
                    str_spouce_name = spoucename.getText().toString();
                    str_dom = d_o_m.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("name",str_name);
                    bundle.putString("father_name",str_father_name);
                    bundle.putString("mother_name",str_mother_name);
                    bundle.putString("residence_type",str_residence_type);
                    bundle.putString("no_of_years",str_no_of_years);
                    bundle.putString("contact_no",str_contact_no);
                    bundle.putString("address",str_address);
                    bundle.putString("date_of_birth",str_dob);
                    bundle.putString("email_id",str_mail_id);
                    bundle.putString("marital_status",str_marital_status);
                    bundle.putString("spouce_name",str_spouce_name);
                    bundle.putString("date_of_marriage",str_dom);
                    bundle.putString("occupation",str_occupation);
                    bundle.putString("loan_category",loan_category);

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

}
