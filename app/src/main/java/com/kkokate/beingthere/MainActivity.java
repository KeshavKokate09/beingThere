package com.kkokate.beingthere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kkokate.beingthere.pages.RegistrationPage;

public class MainActivity extends AppCompatActivity {

    protected Button studBtn;
    protected Button teacherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        studBtn= findViewById(R.id.studentBtn);
        studBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.equals(studBtn)){
                    goToRegistrationPage();
                    Toast.makeText(MainActivity.this,"You are done bvcjdmhgfyjtrgfytdmf,kjh glufug ljyflu",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToRegistrationPage(){
       Intent intent = new Intent(this, RegistrationPage.class);
        startActivity(intent);
    }
}