package com.kkokate.beingthere.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkokate.beingthere.R;

public class AccountFrag extends Fragment {
    private View view;
    protected ImageView profileImg;
    protected TextView nameText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_account, container, false);
        profileImg = view.findViewById(R.id.profileImg);
       // profileImg.setImageResource(R.drawable._cropped);

        nameText =view.findViewById(R.id.nameText);
        nameText.setAllCaps(true);
        return view;
    }
}