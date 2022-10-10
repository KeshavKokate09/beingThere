package com.kkokate.markP;

import static com.kkokate.markP.constants.IntentConstants.AUTH_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kkokate.markP.models.User;
import com.kkokate.markP.pages.AuthenticationPage;
import com.kkokate.markP.pages.HomePage;
import com.kkokate.markP.pages.RegistrationPage;
import com.kkokate.markP.service.AppConfigService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button studBtn;
    protected Button teacherBtn;
    protected FirebaseUser currentUser;
    protected TextView logInTextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){
            initUi();
        }else{
            goToHomePage();
        }
    }

    private void goToHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra(AUTH_USER,currentUser);
        startActivity(intent);
    }

    private void initUi() {
        studBtn= findViewById(R.id.studentBtn);
        studBtn.setOnClickListener(this);

        teacherBtn= findViewById(R.id.adminBtn);
        teacherBtn.setOnClickListener(this);

        logInTextBtn =findViewById(R.id.logInTextBtn);
        logInTextBtn.setOnClickListener(this);
    }

    private void goToRegistrationPage(User userType){
       Intent intent = new Intent(this, AuthenticationPage.class);
       intent.putExtra("USER_TYPE",userType.getDescription());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(studBtn)){
            goToRegistrationPage(User.STUDENT);
        }else if(view.equals(teacherBtn)){
            goToRegistrationPage(User.ORGANIZATION);
        } else if(view.equals(logInTextBtn)){
            goToRegistrationPage(User.ORGANIZATION);
        }
    }

}