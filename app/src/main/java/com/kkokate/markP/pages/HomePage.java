package com.kkokate.markP.pages;

import static com.kkokate.markP.constants.IntentConstants.AUTH_USER;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.kkokate.markP.R;
import com.kkokate.markP.database.RealDB;
import com.kkokate.markP.fragments.AccountFrag;
import com.kkokate.markP.fragments.HomeFrag;
import com.kkokate.markP.fragments.QrGenerator;
import com.kkokate.markP.fragments.ReportFrag;
import com.kkokate.markP.fragments.ScannerFrag;
import com.kkokate.markP.models.User;


public class HomePage extends AppCompatActivity{
    protected BottomNavigationView bottomNav;
    protected Fragment selectedFragment;
    private FirebaseUser cUser;
    private String userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        cUser=getIntent().getParcelableExtra(AUTH_USER);
        if(cUser!=null) {
            String test =cUser.getEmail().split("@")[0].toLowerCase();
            RealDB.getInstance().getReference(test).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    userType= (String) task.getResult().getValue();
                }
            });
            initUi();
        }
    }

    private void initUi() {
        try {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag(cUser)).commit();
        bottomNav = findViewById(R.id.bottomView);
        bottomNav.setOnItemSelectedListener(item -> {
            selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                selectedFragment = new HomeFrag(cUser);
            } else if (itemId == R.id.scanner) {
                if (userType.equals(User.ORGANIZATION.getDescription())) {
                    selectedFragment = new QrGenerator(HomePage.this);
                } else {
                    selectedFragment = new ScannerFrag(HomePage.this);
                }
            } else if (itemId == R.id.report) {
                selectedFragment = new ReportFrag();
            } else if (itemId == R.id.accounts) {
                selectedFragment = new AccountFrag(cUser, this);
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.moveTaskToBack(true);
    }
}
