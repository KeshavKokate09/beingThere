package com.kkokate.beingthere.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kkokate.beingthere.R;

public class HomeFrag extends Fragment {
    private View view;
    protected TextView welcomeText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home, container, false);
        welcomeText = view.findViewById(R.id.welcomeText);
        welcomeText.setAllCaps(true);
        return view;
    }
}