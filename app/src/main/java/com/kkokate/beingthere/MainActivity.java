package com.kkokate.beingthere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kkokate.beingthere.pages.HomePage;
import com.kkokate.beingthere.pages.RegistrationPage;
import com.kkokate.beingthere.service.AppConfigService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button studBtn;
    protected Button teacherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!new AppConfigService().isAppActivationComplete()) {
            initUi();
        }else{
            goToHomePage();
        }
    }

    private void initUi() {
        studBtn= findViewById(R.id.studentBtn);
        studBtn.setOnClickListener(this);

        teacherBtn= findViewById(R.id.adminBtn);
        teacherBtn.setOnClickListener(this);
    }

    private void goToRegistrationPage(){
       Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(studBtn)){
            goToRegistrationPage();
        }else if(view.equals(teacherBtn)){
            goToHomePage();
        }
    }

    private void goToHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}