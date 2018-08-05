package com.viet.rooparam.dhanrasee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OfficialDetailFragment extends Fragment {

    Button next_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_official_detail, container, false);
       next_btn = view.findViewById(R.id.official_next_button);

       next_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               ImageUploadFragment imageUploadFragment = new ImageUploadFragment();
               FragmentTransaction ftImage= getFragmentManager().beginTransaction();
               ftImage.replace(R.id.data_frame,imageUploadFragment);
               ftImage.commit();

           }
       });
        return view;
    }


}
