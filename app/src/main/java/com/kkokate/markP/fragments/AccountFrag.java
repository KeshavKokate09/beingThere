package com.kkokate.markP.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kkokate.markP.MainActivity;
import com.kkokate.markP.R;

public class AccountFrag extends Fragment {
    private View view;
    protected ImageView profileImg;
    protected TextView nameText;
    protected FirebaseUser user;
    protected Button signOutBtn;
    protected Context appContext;

    public AccountFrag(FirebaseUser cUser,Context appContext) {
        this.user=cUser;
        this.appContext=appContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_account, container, false);
        profileImg = view.findViewById(R.id.profileImg);
        signOutBtn = view.findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(view -> {
            if(view.equals(signOutBtn)){
                FirebaseAuth.getInstance().signOut();
                goToMainActivity();
            }
        });
       // profileImg.setImageResource(R.drawable._cropped);

        nameText =view.findViewById(R.id.nameText);
        nameText.setText(user.getDisplayName());
        nameText.setAllCaps(true);
        return view;
    }

    private void goToMainActivity() {
        Intent intent = new Intent(appContext, MainActivity.class);
        startActivity(intent);
    }
}