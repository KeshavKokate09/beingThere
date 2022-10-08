package com.kkokate.markP.pages;

import static com.kkokate.markP.constants.IntentConstants.AUTH_USER;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.kkokate.markP.R;
import com.kkokate.markP.fragments.AccountFrag;
import com.kkokate.markP.fragments.HomeFrag;
import com.kkokate.markP.fragments.ReportFrag;
import com.kkokate.markP.fragments.ScannerFrag;

public class HomePage extends AppCompatActivity{
    protected BottomNavigationView bottomNav;
    protected Fragment selectedFragment;
    private FirebaseUser cUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        cUser=getIntent().getParcelableExtra(AUTH_USER);
        if(cUser!=null) {
            initUi();
        }
    }

    private void initUi() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag(cUser)).commit();
        bottomNav = findViewById(R.id.bottomView);
        bottomNav.setOnItemSelectedListener(item -> {
            selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                selectedFragment = new HomeFrag(cUser);
            } else if (itemId == R.id.scanner) {
                selectedFragment = new ScannerFrag(HomePage.this);
            } else if (itemId == R.id.report) {
                selectedFragment = new ReportFrag();
            }else if (itemId == R.id.accounts) {
                selectedFragment = new AccountFrag(cUser,this);
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }


}
