package com.kkokate.markP.pages;

import static android.content.ContentValues.TAG;

import static com.kkokate.markP.constants.IntentConstants.AUTH_USER;
import static com.kkokate.markP.constants.IntentConstants.USER_TYPE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kkokate.markP.R;

public class AuthenticationPage extends AppCompatActivity implements View.OnClickListener {

    protected Button logInBtn;
    protected Button signInBtn;
    protected EditText userName;
    protected EditText password;

    private FirebaseAuth mAuth;
    private String userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_page);
        userType =getIntent().getStringExtra("USER_TYPE");
        initUi();
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToHomePage(currentUser);
        }
    }

    private void initUi() {
        logInBtn = findViewById(R.id.logInBtn);
        signInBtn = findViewById(R.id.signInBtn);
        userName = findViewById(R.id.userText);
        password = findViewById(R.id.passwordText);

        logInBtn.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(logInBtn)) {
            signIn(userName.getText().toString(),password.getText().toString());
        }else if(view.equals(signInBtn)){
            createAccount(userName.getText().toString(),password.getText().toString());
        }
    }


    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToHomePage(user);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AuthenticationPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToHomePage(FirebaseUser user) {
        Intent intent = new Intent(this,HomePage.class);
        intent.putExtra(AUTH_USER, user);
        intent.putExtra(USER_TYPE, userType);
        startActivity(intent);
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            goToHomePage(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthenticationPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendEmailVerification() {
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Email sent
                    }
                });
    }

    private void reload() { }

}
