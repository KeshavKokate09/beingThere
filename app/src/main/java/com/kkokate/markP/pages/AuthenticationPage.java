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
import android.widget.TextView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kkokate.markP.R;
import com.kkokate.markP.database.FireBaseAuth;
import com.kkokate.markP.database.RealDB;

import java.util.Objects;

public class AuthenticationPage extends AppCompatActivity implements View.OnClickListener {

    protected Button logInBtn;
    protected Button cancelBtn;
    protected Button signInBtn;
    protected EditText userName;
    protected EditText password;
    protected EditText name;
    protected EditText phone;
    protected TextView signUpLink;

    private FirebaseAuth mAuth;
    private String userType;
    DatabaseReference myRef;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_page);
        userType =getIntent().getStringExtra(USER_TYPE);
        initUi();
        mAuth = FireBaseAuth.getInstance(this);
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToHomePage(currentUser);
        }
    }

    private void initUi() {
        logInBtn = findViewById(R.id.logInBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        signInBtn = findViewById(R.id.signInBtn);
        signInBtn.setVisibility(View.INVISIBLE);
        userName = findViewById(R.id.userText);
        password = findViewById(R.id.passwordText);
        name = findViewById(R.id.fullNameText);
        name.setVisibility(View.INVISIBLE);
        phone = findViewById(R.id.phoneText);
        phone.setVisibility(View.INVISIBLE);
        signUpLink = findViewById(R.id.signUpLink);

        logInBtn.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        signUpLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(logInBtn)) {
            signIn(userName.getText().toString(),password.getText().toString());
        }else if(view.equals(signInBtn)){
            myRef = RealDB.getInstance().getReference(Objects.requireNonNull(userName.getText().toString().split("@")[0].toLowerCase()));
            myRef.setValue(userType);
            createAccount(userName.getText().toString(),password.getText().toString());
        }else if(view.equals(cancelBtn)){
           //ToDo
        }else if(view.equals(signUpLink)){
           logInBtn.setVisibility(View.GONE);
           cancelBtn.setVisibility(View.GONE);
           signUpLink.setVisibility(View.GONE);
           name.setVisibility(View.VISIBLE);
           phone.setVisibility(View.VISIBLE);
           signInBtn.setVisibility(View.VISIBLE);
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
