package com.viet.rooparam.dhanrasee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PersonalDetailFragment extends Fragment {

    Button next_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_detail, container, false);
        next_button = view.findViewById(R.id.next_button);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OfficialDetailFragment officialDetailFragment = new OfficialDetailFragment();
                FragmentTransaction ftOfficial = getFragmentManager().beginTransaction();
                ftOfficial.replace(R.id.data_frame,officialDetailFragment);
                ftOfficial.commit();

            }
        });
        return view;
    }

}
