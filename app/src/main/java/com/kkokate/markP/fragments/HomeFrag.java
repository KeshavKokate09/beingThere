package com.kkokate.markP.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.kkokate.markP.R;

public class HomeFrag extends Fragment {
    private View view;
    protected TextView welcomeText;
    private FirebaseUser cUser;

    public HomeFrag(FirebaseUser cUser) {
        this.cUser=cUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home, container, false);
        welcomeText = view.findViewById(R.id.welcomeText);
        welcomeText.setText("welcome \n   "+cUser.getEmail());
        welcomeText.setAllCaps(true);
        return view;
    }
}