package com.kkokate.beingthere.pages;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kkokate.beingthere.R;
import com.kkokate.beingthere.fragments.AccountFrag;
import com.kkokate.beingthere.fragments.HomeFrag;
import com.kkokate.beingthere.fragments.ReportFrag;
import com.kkokate.beingthere.fragments.ScannerFrag;
import com.kkokate.beingthere.service.AppConfigService;

public class HomePage extends AppCompatActivity{
    protected BottomNavigationView bottomNav;
    protected Fragment selectedFragment;
    protected AppConfigService appConfigService= new AppConfigService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        if(appConfigService.isAppActivationComplete()) {
            initUi();
        }
    }

    private void initUi() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
        bottomNav = findViewById(R.id.bottomView);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedFragment = null;
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    selectedFragment = new HomeFrag();
                } else if (itemId == R.id.scanner) {
                    selectedFragment = new ScannerFrag(HomePage.this);
                } else if (itemId == R.id.report) {
                    selectedFragment = new ReportFrag();
                }else if (itemId == R.id.accounts) {
                    selectedFragment = new AccountFrag();
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                }
                return true;
            }
        });
    }


}
