package com.kkokate.beingthere.pages;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kkokate.beingthere.R;

public class RegistrationPage extends AppCompatActivity {

    protected TextView studTextTest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        initUi();
    }

    private void initUi() {
        studTextTest = findViewById(R.id.studRegText);
    }
}
